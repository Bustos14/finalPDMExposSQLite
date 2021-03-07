package com.example.appfinalpdmsqlite.ui;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appfinalpdmsqlite.BD;
import com.example.appfinalpdmsqlite.R;


public class manejoExposiciones extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manejo_exposiciones, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button btAceptar = getView().findViewById(R.id.idAceptarExp);
        Button btMod = getView().findViewById(R.id.btModExpo);
        final TextView tvId = getView().findViewById(R.id.gesExpID);
        final TextView tvNombre = getView().findViewById(R.id.gesExpNombre);
        final TextView tvDescipcion = getView().findViewById(R.id.gesExpDescripcion);
        final TextView tvFechaIni = getView().findViewById(R.id.gesExpFechaIni);
        final TextView tvFechaFin = getView().findViewById(R.id.gesExpFechaFin);
        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tvId.getText().toString().equals("") && !tvDescipcion.getText().toString().equals("") && !tvNombre.getText().toString().equals("") && !tvFechaIni.getText().toString().equals("") && !tvFechaFin.getText().toString().equals("")) {
                    SQLiteOpenHelper bd = new BD(getContext());
                    final SQLiteDatabase db = bd.getReadableDatabase();
                    db.execSQL("PRAGMA foreign_keys = ON");

                    final ContentValues newExposicion = new ContentValues();
                    newExposicion.put("IDEXPOSICION", tvId.getText().toString());
                    newExposicion.put("NOMBREEXP", tvNombre.getText().toString());
                    newExposicion.put("DESCRIPCION", tvDescipcion.getText().toString());
                    newExposicion.put("FECHAINICIO", tvFechaIni.getText().toString());
                    newExposicion.put("FECHAFIN", tvFechaFin.getText().toString());
                    if (db.insert("EXPOSICIONES", null, newExposicion) != -1) {
                        Toast.makeText(getActivity(), "Añadido correctamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Hubo un problema", Toast.LENGTH_SHORT).show();
                    }
                }
            };
        });
        btMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tvId.getText().toString().equals("")) {
                    SQLiteOpenHelper bd = new BD(getContext());
                    final SQLiteDatabase db = bd.getReadableDatabase();
                    db.execSQL("PRAGMA foreign_keys = ON");

                    final ContentValues newExposicion = new ContentValues();
                    newExposicion.put("IDEXPOSICION", tvId.getText().toString());
                    if (!tvNombre.getText().toString().equals("")) {
                        newExposicion.put("NOMBREEXP", tvNombre.getText().toString());
                    }
                    if (!tvDescipcion.getText().toString().equals("")) {
                        newExposicion.put("DESCRIPCION", tvDescipcion.getText().toString());
                    }
                    if (!tvFechaIni.getText().toString().equals("")) {
                        newExposicion.put("FECHAINICIO", tvFechaIni.getText().toString());
                    }
                    if (!tvFechaFin.getText().toString().equals("")) {
                        newExposicion.put("FECHAFIN", tvFechaFin.getText().toString());
                    }
                    if (db.update("EXPOSICIONES", newExposicion, "IDEXPOSICION = " + tvId.getText().toString(), null) != 0) {
                        Toast.makeText(getActivity(),"Modificacion exitosa", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(),"Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}