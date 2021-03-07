package com.example.appfinalpdmsqlite.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appfinalpdmsqlite.BD;
import com.example.appfinalpdmsqlite.R;
import com.example.appfinalpdmsqlite.controlRvTrabajos;
import com.example.appfinalpdmsqlite.controladorRVExposiciones;
import com.example.appfinalpdmsqlite.ui.Modelo.Exposicion;
import com.example.appfinalpdmsqlite.ui.Modelo.Trabajo;

import java.util.ArrayList;

public class fragment_trabajos extends Fragment {
    private RecyclerView recyclerView;
    private controlRvTrabajos controladorRVtrabajos;
    private ArrayList<Trabajo> ltrabajos;
    Trabajo trabajo;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trabajos, container, false);
        recyclerView = view.findViewById(R.id.rvTrabajos);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ltrabajos = new ArrayList<>();
        leo();
        return view;
    }

    public void leo() {
        try {
            SQLiteOpenHelper bd = new BD(getContext());
            SQLiteDatabase db = bd.getReadableDatabase();
            String[] columnas = new String[5];
            columnas[0] = "NOMBRETRAB";
            columnas[1] = "DESCRIPCION";
            columnas[2] = "TAMAÑO";
            columnas[3] = "PESO";
            columnas[4] = "DNIPASAPORTE";
            Cursor c = db.query("TRABAJOS", columnas, null, null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    String nombretrab = c.getString(c.getColumnIndex("NOMBRETRAB"));
                    String descripcion = c.getString(c.getColumnIndex("DESCRIPCION"));
                    String tamaño = c.getString(c.getColumnIndex("TAMAÑO"));
                    String peso = c.getString(c.getColumnIndex("PESO"));
                    String dnipasaporte = c.getString(c.getColumnIndex("DNIPASAPORTE"));
                    trabajo = new Trabajo(nombretrab,descripcion,tamaño, peso, dnipasaporte);
                    ltrabajos.add(trabajo);
                } while (c.moveToNext());
                controladorRVtrabajos = new controlRvTrabajos(ltrabajos,getContext());
                recyclerView.setAdapter(controladorRVtrabajos);
                c.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}