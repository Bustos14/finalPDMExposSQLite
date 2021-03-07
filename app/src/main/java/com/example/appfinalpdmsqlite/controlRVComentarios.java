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

import com.example.appfinalpdmsqlite.ui.Modelo.Comentario;
import com.example.appfinalpdmsqlite.ui.Modelo.Exposicion;

import java.util.ArrayList;

public class controlRVComentarios  extends RecyclerView.Adapter<controlRVComentarios.AdaptadorViewHolder> {
    private ArrayList<Comentario> listComentarios;
    Context context;

    public controlRVComentarios (ArrayList<Comentario> listComentarios, Context context) {
        this.listComentarios = listComentarios;
        this.context = context;
    }

    @NonNull
    @Override
    public controlRVComentarios.AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comentario, parent, false);
        controlRVComentarios.AdaptadorViewHolder aavh = new controlRVComentarios.AdaptadorViewHolder(v);
        return aavh;
    }

    @Override
    public void onBindViewHolder(@NonNull controlRVComentarios.AdaptadorViewHolder holder, int position) {
       Comentario comentario = listComentarios.get(position);
        holder.nombre.setText(comentario.getNombreTrabajo());
        holder.comentario.setText(comentario.getComentario());
        if (listComentarios.size() / 2 == 0) {
            holder.itemView.setBackgroundColor(Color.CYAN);
        } else {
            holder.itemView.setBackgroundColor(Color.BLUE);
        }
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
                        if (db.delete("COMENTARIOS", "IDEXPOSICION = " +listComentarios.get(position).getIdExposicion(), null) == 1) {
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
        return listComentarios.size();
    }

    public class AdaptadorViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre;
        private TextView comentario;

        public AdaptadorViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nomComentario);
            comentario = itemView.findViewById(R.id.contenidoComentario);

        }
    }
}