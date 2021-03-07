package com.example.appfinalpdmsqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appfinalpdmsqlite.ui.Modelo.Comentario;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class infoTrabajo  extends AppCompatActivity {


    Button btComentario;
    TextView dni;
    TextView nom;
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_infotrabajos);

            Bundle datos = this.getIntent().getExtras();
            nom = findViewById(R.id.nomTrabajo);
            TextView descripcion = findViewById(R.id.descripcionTrabajo);
            TextView peso = findViewById(R.id.pesoTrabajo);
            TextView tamanio = findViewById(R.id.tamanioTrabajo);
            dni = findViewById(R.id.dniArtTrabajo);

            btComentario= findViewById(R.id.btComentario);

            nom.setText(datos.getString("nombre"));
            descripcion.setText(datos.getString("descripcion"));
            dni.setText(datos.getString("dni"));
            peso.setText(datos.getString("peso"));
            tamanio.setText(datos.getString("tamaño"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        btComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(infoTrabajo.this, comentario.class);
                intent.putExtra("nombre", nom.getText().toString() );
                startActivity(intent);
            }
        });
    }
}