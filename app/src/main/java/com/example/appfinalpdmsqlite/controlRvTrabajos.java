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
import com.example.appfinalpdmsqlite.ui.Modelo.Trabajo;

import java.util.ArrayList;

public class controlRvTrabajos extends RecyclerView.Adapter<controlRvTrabajos .AdaptadorViewHolder>{
    private ArrayList<Trabajo> ltrabajos;
        private Context context;

        public controlRvTrabajos (ArrayList<Trabajo> ltrabajos, Context context) {
            this.ltrabajos = ltrabajos;
            this.context = context;
        }

        @NonNull
        @Override
        public controlRvTrabajos.AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_trabajos, parent, false);
            controlRvTrabajos.AdaptadorViewHolder aavh = new controlRvTrabajos.AdaptadorViewHolder(v);
            return aavh;
        }

        @Override
        public void onBindViewHolder(@NonNull controlRvTrabajos .AdaptadorViewHolder holder, int position) {
            Trabajo trabajo = ltrabajos.get(position);
            holder.nombre.setText(trabajo.getNombre());
            if (ltrabajos.size() / 2 == 0) {
                holder.itemView.setBackgroundColor(Color.CYAN);
            } else {
                holder.itemView.setBackgroundColor(Color.BLUE);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, infoTrabajo.class);
                    intent.putExtra("dni", ltrabajos.get(position).getDNI());
                    intent.putExtra("nombre", ltrabajos.get(position).getNombre());
                    intent.putExtra("descripcion", ltrabajos.get(position).getDescripcion());
                    intent.putExtra("peso", ltrabajos.get(position).getPeso());
                    intent.putExtra("tamaño", ltrabajos.get(position).getTamaño());
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
                            if (db.delete("TRABAJOS", "NOMBRETRAB = '" + holder.nombre.getText().toString()+"'", null) == 1) {
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
            return ltrabajos.size();
        }

public class AdaptadorViewHolder extends RecyclerView.ViewHolder {
    private TextView nombre;

    public AdaptadorViewHolder(@NonNull View itemView) {
        super(itemView);
        nombre = itemView.findViewById(R.id.txNombreTrab);

    }
}
}