package com.example.appfinalpdmsqlite.ui;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appfinalpdmsqlite.BD;
import com.example.appfinalpdmsqlite.MainActivity;
import com.example.appfinalpdmsqlite.R;
import com.example.appfinalpdmsqlite.controladorRVArtistas;
import com.example.appfinalpdmsqlite.ui.Modelo.Artistas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class fragment_artistas extends Fragment {
   //Adaptadores
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private controladorRVArtistas controladorRVArtistas;
    Artistas artistas;
    //ArrayArtistas
    private ArrayList<Artistas> listArtistas;
    TextView tv;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artistas, container, false);
        recyclerView = view.findViewById(R.id.rvArtista);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        listArtistas = new ArrayList<>();
        leo();
        return view;
    }

    public void leo() {
        try {
            SQLiteOpenHelper bd = new BD(getContext());
            SQLiteDatabase db = bd.getReadableDatabase();

            String[] columnas = new String[12];
            columnas[0] = "DNIPASAPORTE";
            columnas[1] = "NOMBRE";
            columnas[2] = "DIRECCION";
            columnas[3] = "POBLACION";
            columnas[4] = "PROVINCIA";
            columnas[5] = "PAIS";
            columnas[6] = "MOVILTRABAJO";
            columnas[7] = "MOVILPERSONAL";
            columnas[8] = "TELEFONOFIJO";
            columnas[9] = "EMAIL";
            columnas[10] = "WEBBLOG";
            columnas[11] = "FECHANACIMIENTO";

            Cursor c = db.query("ARTISTAS", columnas, null, null, null, null, null);

            listArtistas.clear();
            if (c.moveToFirst()) {
                do {
                    String dni = c.getString(c.getColumnIndex("DNIPASAPORTE"));
                    String nombre = c.getString(c.getColumnIndex("NOMBRE"));
                    String direccion = c.getString(c.getColumnIndex("DIRECCION"));
                    String poblacion = c.getString(c.getColumnIndex("POBLACION"));
                    String provincia = c.getString(c.getColumnIndex("PROVINCIA"));
                    String pais = c.getString(c.getColumnIndex("PAIS"));
                    Integer moviltrabajo = c.getInt(c.getColumnIndex("MOVILTRABAJO"));
                    Integer movilpersonal = c.getInt(c.getColumnIndex("MOVILPERSONAL"));
                    Integer telefonofijo = c.getInt(c.getColumnIndex("TELEFONOFIJO"));
                    String email = c.getString(c.getColumnIndex("EMAIL"));
                    String webblog = c.getString(c.getColumnIndex("WEBBLOG"));
                    String fechanacimiento= c.getString(c.getColumnIndex("FECHANACIMIENTO"));

                    Artistas art = new Artistas(dni, nombre, direccion, poblacion,provincia, pais, email, webblog, fechanacimiento,moviltrabajo, movilpersonal, telefonofijo);
                    listArtistas.add(art);
                } while (c.moveToNext());
                controladorRVArtistas = new controladorRVArtistas(listArtistas,getContext());
                recyclerView.setAdapter(controladorRVArtistas);
             c.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}