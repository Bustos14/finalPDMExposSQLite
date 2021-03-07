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

import com.example.appfinalpdmsqlite.ui.Modelo.Exposicion;

import java.util.ArrayList;

public class controladorRVExposiciones extends RecyclerView.Adapter<controladorRVExposiciones.AdaptadorViewHolder> {
    private ArrayList<Exposicion> listexpos;
    private Context context;

    public controladorRVExposiciones(ArrayList<Exposicion> listExpos, Context context) {
        this.listexpos = listExpos;
        this.context = context;
    }

    @NonNull
    @Override
    public controladorRVExposiciones.AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_expos, parent, false);
        controladorRVExposiciones.AdaptadorViewHolder aavh = new controladorRVExposiciones.AdaptadorViewHolder(v);
        return aavh;
    }

    @Override
    public void onBindViewHolder(@NonNull controladorRVExposiciones.AdaptadorViewHolder holder, int position) {
        Exposicion exposicion = listexpos.get(position);
        holder.nombre.setText(exposicion.getNombre());
        holder.inicio.setText(exposicion.getFechaIni());
        holder.fin.setText(exposicion.getFechaFin());
        if (listexpos.size() / 2 == 0) {
            holder.itemView.setBackgroundColor(Color.CYAN);
        } else {
            holder.itemView.setBackgroundColor(Color.BLUE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, infoExpo.class);
                intent.putExtra("id", listexpos.get(position).getId().toString());
                intent.putExtra("nombre", listexpos.get(position).getNombre());
                intent.putExtra("descripcion", listexpos.get(position).getDescripcion());
                intent.putExtra("fechaIni", listexpos.get(position).getFechaIni());
                intent.putExtra("fechaFin", listexpos.get(position).getFechaFin());
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
                        if (db.delete("EXPOSICIONES", "IDEXPOSICION = " + listexpos.get(position).getId().toString(), null) == 1) {
                            Toast.makeText(context, "Borrar exitos", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Borrar fracaso", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listexpos.size();
    }

    public class AdaptadorViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre;
        private TextView inicio;
        private TextView fin;

        public AdaptadorViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txNombreExpo);
            inicio = itemView.findViewById(R.id.txFechaInicio);
            fin = itemView.findViewById(R.id.txFechaFin);


        }
    }
}