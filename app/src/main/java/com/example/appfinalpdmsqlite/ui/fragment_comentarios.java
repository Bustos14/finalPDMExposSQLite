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
import com.example.appfinalpdmsqlite.controlRVComentarios;
import com.example.appfinalpdmsqlite.controlRvTrabajos;
import com.example.appfinalpdmsqlite.controladorRVExposiciones;
import com.example.appfinalpdmsqlite.ui.Modelo.Comentario;
import com.example.appfinalpdmsqlite.ui.Modelo.Exposicion;
import com.example.appfinalpdmsqlite.ui.Modelo.Trabajo;

import java.util.ArrayList;

public class fragment_comentarios extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adaptadorComentario;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Comentario> listaComentariosFin = new ArrayList<>();
    @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_comentarios, container, false);
            recyclerView = view.findViewById(R.id.rvComentarios);
            recyclerView.setNestedScrollingEnabled(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            leo();
            return view;
        }


    public void leo() {
        try {
            SQLiteOpenHelper bd = new BD(getContext());
            SQLiteDatabase db = bd.getReadableDatabase();

            String[] columnasComentario = new String[3];
            columnasComentario[0] = "IDEXPOSICION";
            columnasComentario[1] = "NOMBRETRAB";
            columnasComentario[2] = "COMENTARIO";

            Cursor listaCometarios = db.query("COMENTARIOS", columnasComentario, null, null, null, null, null);
            listaComentariosFin.clear();
            if (listaCometarios.moveToFirst()) {
                do {
                    String idexposicion = listaCometarios.getString(listaCometarios.getColumnIndex("IDEXPOSICION"));
                    String nombretrab = listaCometarios.getString(listaCometarios.getColumnIndex("NOMBRETRAB"));
                    String comentario = listaCometarios.getString(listaCometarios.getColumnIndex("COMENTARIO"));
                    Comentario com = new Comentario(idexposicion, nombretrab, comentario);
                    listaComentariosFin.add(com);
                } while (listaCometarios.moveToNext());
            }
            layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            adaptadorComentario = new controlRVComentarios(listaComentariosFin, getContext());
            recyclerView.setAdapter(adaptadorComentario);
            } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }}