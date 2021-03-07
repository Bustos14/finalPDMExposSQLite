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

public class exponen extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exponen_layout);
        Bundle datos = this.getIntent().getExtras();
        Button add = findViewById(R.id.btArtExpAñadir);
        TextView id = findViewById(R.id.idExponen);
        EditText dni = findViewById(R.id.dniExponen);
        String id1 = getIntent().getExtras().getString("id");
        id.setText(id1);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dni.getText().toString().equals("")){
                    SQLiteOpenHelper bd = new BD(exponen.this);
                    SQLiteDatabase db = bd.getReadableDatabase();
                    db.execSQL("PRAGMA foreign_keys = ON");

                    ContentValues newExponen = new ContentValues();
                    newExponen.put("IDEXPOSICION",id.getText().toString());
                    newExponen.put("DNIPASAPORTE", dni.getText().toString());

                    if (db.insert("EXPONEN", null, newExponen) != -1) {
                        Toast.makeText(exponen.this, "exito", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(exponen.this, "Fallo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
