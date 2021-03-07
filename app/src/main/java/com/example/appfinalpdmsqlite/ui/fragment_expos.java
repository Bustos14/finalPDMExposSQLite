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
import com.example.appfinalpdmsqlite.controladorRVArtistas;
import com.example.appfinalpdmsqlite.controladorRVExposiciones;
import com.example.appfinalpdmsqlite.ui.Modelo.Artistas;
import com.example.appfinalpdmsqlite.ui.Modelo.Exposicion;

import java.util.ArrayList;


public class fragment_expos extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private com.example.appfinalpdmsqlite.controladorRVExposiciones controladorRVExposiciones;
    private ArrayList<Exposicion> listExpos;
    Exposicion exposicion;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expos, container, false);
        recyclerView = view.findViewById(R.id.rvExpos);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        listExpos = new ArrayList<>();
        leo();
        return view;
    }

    public void leo() {
        try {
            SQLiteOpenHelper bd = new BD(getContext());
            SQLiteDatabase db = bd.getReadableDatabase();
            String[] columnas = new String[5];
            columnas[0] = "IDEXPOSICION";
            columnas[1] = "NOMBREEXP";
            columnas[2] = "DESCRIPCION";
            columnas[3] = "FECHAINICIO";
            columnas[4] = "FECHAFIN";
            Cursor c = db.query("EXPOSICIONES", columnas, null, null, null, null, null);
            listExpos.clear();
            if (c.moveToFirst()) {
                do {
                    Integer id = c.getInt(c.getColumnIndex("IDEXPOSICION"));
                    String nombre = c.getString(c.getColumnIndex("NOMBREEXP"));
                    String descripcion = c.getString(c.getColumnIndex("DESCRIPCION"));
                    String fechaIni = c.getString(c.getColumnIndex("FECHAINICIO"));
                    String fechaFin = c.getString(c.getColumnIndex("FECHAFIN"));
                    exposicion = new Exposicion(id, nombre, descripcion, fechaIni, fechaFin);
                    listExpos.add(exposicion);
                } while (c.moveToNext());
                controladorRVExposiciones = new controladorRVExposiciones(listExpos,getContext());
                recyclerView.setAdapter(controladorRVExposiciones);
                c.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}