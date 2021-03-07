package com.example.appfinalpdmsqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class comentario extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comentario_layout);
        Button add = findViewById(R.id.addComentario);
        Button mod = findViewById(R.id.modComentario);
        TextView id = findViewById(R.id.txComentarNombreTrabajo);
        EditText comentario = findViewById(R.id.txComentarCometario);
        EditText idExpo = findViewById(R.id.idExposicion);
        String id1 = getIntent().getExtras().getString("nombre");
        id.setText(id1);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!comentario.getText().toString().equals("")){
                    SQLiteOpenHelper bd = new BD(comentario.this);
                    SQLiteDatabase db = bd.getReadableDatabase();
                    db.execSQL("PRAGMA foreign_keys = ON");

                    ContentValues nuevoComentario = new ContentValues();
                    nuevoComentario.put("IDEXPOSICION",idExpo.getText().toString());
                    nuevoComentario.put("NOMBRETRAB", id.getText().toString());
                    nuevoComentario.put("COMENTARIO", comentario.getText().toString());

                    if (db.insert("COMENTARIOS", null, nuevoComentario) != -1) {
                        Toast.makeText(comentario.this, "exito", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(comentario.this, "Fallo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!comentario.getText().toString().equals("")){
                    SQLiteOpenHelper bd = new BD(comentario.this);
                    SQLiteDatabase db = bd.getReadableDatabase();
                    db.execSQL("PRAGMA foreign_keys = ON");
                    ContentValues nuevoComentario = new ContentValues();
                    nuevoComentario.put("IDEXPOSICION",idExpo.getText().toString());
                    nuevoComentario.put("NOMBRETRAB", id.getText().toString());
                    nuevoComentario.put("COMENTARIO", comentario.getText().toString());
                    if (db.update("COMENTARIOS", nuevoComentario, "NOMBRETRAB = '" + id.getText().toString()+"' AND IDEXPOSICION = " + idExpo.getText().toString(), null) != 0) {
                        Toast.makeText(comentario.this, "exito", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(comentario.this, "Fallo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
