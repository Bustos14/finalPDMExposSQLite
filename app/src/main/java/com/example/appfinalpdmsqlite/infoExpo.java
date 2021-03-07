package com.example.appfinalpdmsqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfinalpdmsqlite.ui.Modelo.Artistas;

import java.util.ArrayList;


public class infoExpo extends AppCompatActivity {
    private RecyclerView.Adapter adaptadorExponen;
    private RecyclerView rvArt;
    private RecyclerView.Adapter adaptadorComentario;
    private RecyclerView rvCom;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.LayoutManager layoutManagerCom;

    ArrayList<Artistas> listaArt = new ArrayList<>();
    ArrayList<String> listaArtAux = new ArrayList<>();
    Button btExponen;
    TextView id;
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.infoexpo);

            Bundle datos = this.getIntent().getExtras();
            TextView nom = findViewById(R.id.nombreExpo);
            TextView descripcion = findViewById(R.id.descripcionExpo);
            TextView fechaIni = findViewById(R.id.inicioExpo);
            TextView fechaFin = findViewById(R.id.finExpo);
            id = findViewById(R.id.idExpo);
            btExponen = findViewById(R.id.btExponen);
            nom.setText(datos.getString("nombre"));
            descripcion.setText(datos.getString("descripcion"));
            id.setText(datos.getString("id"));
            fechaFin.setText(datos.getString("fechaFin"));
            fechaIni.setText(datos.getString("fechaIni"));

            SQLiteOpenHelper bd = new BD(this);
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

            String[] columnasExponen = new String[2];
            columnasExponen[0] = "IDEXPOSICION";
            columnasExponen[1] = "DNIPASAPORTE";

            Cursor listaExponen = db.query("EXPONEN", columnasExponen, "IDEXPOSICION = " + id.getText().toString(), null, null, null, null);

            if (listaExponen.moveToFirst()) {
                do {
                    String dni = listaExponen.getString(listaExponen.getColumnIndex("DNIPASAPORTE"));
                    listaArtAux.add(dni);
                } while (listaExponen.moveToNext());
            }

            Cursor listaArtistas = db.query("ARTISTAS", columnas, null, null, null, null, null);
            listaArt.clear();
            if (listaArtistas.moveToFirst()) {
                do {
                    String dni = listaArtistas.getString(listaArtistas.getColumnIndex("DNIPASAPORTE"));
                    String nombre = listaArtistas.getString(listaArtistas.getColumnIndex("NOMBRE"));
                    String direccion = listaArtistas.getString(listaArtistas.getColumnIndex("DIRECCION"));
                    String poblacion = listaArtistas.getString(listaArtistas.getColumnIndex("POBLACION"));
                    String provincia = listaArtistas.getString(listaArtistas.getColumnIndex("PROVINCIA"));
                    String pais = listaArtistas.getString(listaArtistas.getColumnIndex("PAIS"));
                    Integer moviltrabajo = listaArtistas.getInt(listaArtistas.getColumnIndex("MOVILTRABAJO"));
                    Integer movilpersonal = listaArtistas.getInt(listaArtistas.getColumnIndex("MOVILPERSONAL"));
                    Integer telefonofijo = listaArtistas.getInt(listaArtistas.getColumnIndex("TELEFONOFIJO"));
                    String email = listaArtistas.getString(listaArtistas.getColumnIndex("EMAIL"));
                    String webblog = listaArtistas.getString(listaArtistas.getColumnIndex("WEBBLOG"));
                    String fechanacimiento = listaArtistas.getString(listaArtistas.getColumnIndex("FECHANACIMIENTO"));

                    Artistas art = new Artistas(dni, nombre, direccion, poblacion, provincia, pais, email, webblog, fechanacimiento, moviltrabajo, movilpersonal, telefonofijo);
                    for (int i = 0; i < listaArtAux.size(); i++) {
                        if (art.getDniPasaporte().equals(listaArtAux.get(i))) {
                            listaArt.add(art);
                        }
                    }
                } while (listaArtistas.moveToNext());
            }

            rvArt = this.findViewById(R.id.rvExpArt);
            layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rvArt.setLayoutManager(layoutManager);
            adaptadorExponen = new controladorRVArtistas(listaArt, this);
            rvArt.setAdapter(adaptadorExponen);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        btExponen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(infoExpo.this, exponen.class);
                intent.putExtra("id", id.getText().toString() );
                startActivity(intent);
    }
});
    }
}
