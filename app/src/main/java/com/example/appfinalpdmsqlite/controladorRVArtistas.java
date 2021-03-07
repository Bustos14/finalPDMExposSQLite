package com.example.appfinalpdmsqlite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appfinalpdmsqlite.ui.Modelo.Artistas;

import java.util.ArrayList;

public class controladorRVArtistas extends RecyclerView.Adapter<controladorRVArtistas.AdaptadorViewHolder> {
    private ArrayList<Artistas> listArtis;
    private Context context;

    public controladorRVArtistas(ArrayList<Artistas> listArtis, Context context) {
        this.listArtis = listArtis;
        this.context = context;

    }
    @NonNull
    @Override
    public AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item_artista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_artistas, parent, false);
        controladorRVArtistas.AdaptadorViewHolder aavh = new  controladorRVArtistas.AdaptadorViewHolder(item_artista);
        return aavh;
    }

    @Override
    public void onBindViewHolder(@NonNull controladorRVArtistas.AdaptadorViewHolder holder, int position) {
        Artistas artista = listArtis.get(position);
        holder.tx_dni.setText(artista.getDniPasaporte());
        holder.tx_nombre.setText(artista.getNombre());
        holder.tx_mtrb.setText(Integer.toString(artista.getMovilTrabajo()));
        holder.tx_email.setText(artista.getEmail());
        holder.tx_Web.setText(artista.getWebBlog());
        if(listArtis.size()/2 ==0){
            holder.itemView.setBackgroundColor(Color.CYAN);
        }else{
            holder.itemView.setBackgroundColor(Color.BLUE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, infoArtista.class);
                intent.putExtra("dni", listArtis.get(position).getDniPasaporte());
                intent.putExtra("nombre", listArtis.get(position).getNombre());
                intent.putExtra("direccion", listArtis.get(position).getDireccion());
                intent.putExtra("poblacion", listArtis.get(position).getPoblacion());
                intent.putExtra("provincia", listArtis.get(position).getProvincia());
                intent.putExtra("pais", listArtis.get(position).getPais());
                String movilTrabajo = String.valueOf(listArtis.get(position).getMovilTrabajo());
                intent.putExtra("movilTrab", movilTrabajo);
                String movilPersonal = String.valueOf(listArtis.get(position).getMovilPersonal());
                intent.putExtra("movilPersonal", movilPersonal);
                String tlfijo = String.valueOf(listArtis.get(position).getTelefonoFijo());
                intent.putExtra("fijo",tlfijo);
                intent.putExtra("email", listArtis.get(position).getEmail());
                intent.putExtra("webblog", listArtis.get(position).getWebBlog());
                intent.putExtra("fechaNacimiento", listArtis.get(position).getfNacimiento());
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Opciones:");
                builder.setMessage("¿Qué quieres hacer?.");        // add the buttons
                builder.setNeutralButton("Cancelar", null);
                builder.setNegativeButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteOpenHelper bd = new BD(context);
                        final SQLiteDatabase db = bd.getReadableDatabase();
                        db.execSQL("PRAGMA foreign_keys = ON");
                        if (db.delete("ARTISTAS", "DNIPASAPORTE = '" + holder.tx_dni.getText().toString() + "'", null) == 1) {

                        } else {

                        }
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listArtis.size();
    }
    public class AdaptadorViewHolder extends RecyclerView.ViewHolder {
        private TextView  tx_nombre, tx_Web, tx_email, tx_mtrb, tx_dni;
        public AdaptadorViewHolder(@NonNull View itemView) {
            super(itemView);
            tx_dni = itemView.findViewById(R.id.tvDni);
            tx_nombre = itemView.findViewById(R.id.nomArtista);
            tx_mtrb = itemView.findViewById(R.id.tvFijo);
            tx_Web= itemView.findViewById(R.id.tv_web);
            tx_email = itemView.findViewById(R.id.tvEmail);

        }
    }
}
