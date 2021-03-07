package com.example.appfinalpdmsqlite.ui;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appfinalpdmsqlite.BD;
import com.example.appfinalpdmsqlite.R;

public class manejoTrabajos extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manejo_trabajos, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button btAñadir = getView().findViewById(R.id.btAddTrab);
        Button bMod = getView().findViewById(R.id.btModTra);
        TextView tvTamaño = getView().findViewById(R.id.traTamaño);
        TextView tvNombre = getView().findViewById(R.id.traNombre);
        TextView tvDescipcion = getView().findViewById(R.id.traDescripcion);
        TextView tvPeso = getView().findViewById(R.id.traPeso);
        TextView tvDNI = getView().findViewById(R.id.traArtDni);

        btAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tvTamaño.getText().toString().equals("") && !tvNombre.getText().toString().equals("") && !tvDescipcion.getText().toString().equals("") && !tvPeso.getText().toString().equals("") && !tvDNI.getText().toString().equals("")) {
                    SQLiteOpenHelper bd = new BD(getContext());
                    final SQLiteDatabase db = bd.getReadableDatabase();
                    db.execSQL("PRAGMA foreign_keys = ON");

                    final ContentValues newTrabajo = new ContentValues();
                    newTrabajo.put("NOMBRETRAB", tvNombre.getText().toString());
                    newTrabajo.put("TAMAÑO", tvTamaño.getText().toString());
                    newTrabajo.put("DESCRIPCION", tvDescipcion.getText().toString());
                    newTrabajo.put("PESO", tvPeso.getText().toString());
                    newTrabajo.put("DNIPASAPORTE", tvDNI.getText().toString());
                    if (db.insert("TRABAJOS", null, newTrabajo) != -1) {
                        Toast.makeText(getActivity(),"Inserccion exitosa", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Error en la inserccion", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        bMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tvNombre.getText().toString().equals("")) {
                    SQLiteOpenHelper bd = new BD(getContext());
                    final SQLiteDatabase db = bd.getReadableDatabase();
                    db.execSQL("PRAGMA foreign_keys = ON");

                    final ContentValues newTrabajo = new ContentValues();
                    newTrabajo.put("NOMBRETRAB", tvNombre.getText().toString());

                    if (!tvDescipcion.getText().toString().equals("")) {
                        newTrabajo.put("DESCRIPCION", tvDescipcion.getText().toString());
                    }
                    if (!tvTamaño.getText().toString().equals("")) {
                        newTrabajo.put("TAMAÑO", tvTamaño.getText().toString());
                    }
                    if (!tvPeso.getText().toString().equals("")) {
                        newTrabajo.put("PESO", tvPeso.getText().toString());
                    }
                    if (!tvDNI.getText().toString().equals("")) {
                        newTrabajo.put("DNIPASAPORTE", tvDNI.getText().toString());

                    }
                    if (db.update("TRABAJOS", newTrabajo, "NOMBRETRAB = '" + tvNombre.getText().toString() + "'", null) != 0) {
                        Toast.makeText(getActivity(), "Modificacion exitosa", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
 }


