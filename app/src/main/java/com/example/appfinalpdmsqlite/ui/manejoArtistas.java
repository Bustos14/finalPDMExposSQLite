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

public class manejoArtistas extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manejo_artistas, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button btAceptar = getView().findViewById(R.id.idAceptarArt);
        Button btMod = getView().findViewById(R.id.btModArt);
        final TextView tvDNI = getView().findViewById(R.id.gesArtDNI);
        final TextView tvNombre = getView().findViewById(R.id.gesArtNombre);
        final TextView tvDireccion = getView().findViewById(R.id.gesArtDireccion);
        final TextView tvPoblacion = getView().findViewById(R.id.gesArtPoblacion);
        final TextView tvProvincia = getView().findViewById(R.id.gesArtProvincia);
        final TextView tvPais = getView().findViewById(R.id.gesArtPais);
        final TextView tvMovilTrab = getView().findViewById(R.id.gesArtMovilTrab);
        final TextView tvMovilPers = getView().findViewById(R.id.gesArtMovilPersonal);
        final TextView tvTlfFijo = getView().findViewById(R.id.gesArtTlfFijo);
        final TextView tvEmail = getView().findViewById(R.id.gesArtEmail);
        final TextView tvWEbBlog = getView().findViewById(R.id.gesArtWebBlog);
        final TextView tvFechaNaci = getView().findViewById(R.id.gesArtFechaNaci);


        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tvDNI.getText().toString().equals("") && !tvDireccion.getText().toString().equals("") && !tvNombre.getText().toString().equals("") && !tvPoblacion.getText().toString().equals("") && !tvProvincia.getText().toString().equals("")
                        && !tvPais.getText().toString().equals("") && !tvMovilTrab.getText().toString().equals("") && !tvMovilTrab.getText().toString().equals("") && !tvMovilPers.getText().toString().equals("") && !tvTlfFijo.getText().toString().equals("")
                        && !tvEmail.getText().toString().equals("") && !tvWEbBlog.getText().toString().equals("") && !tvFechaNaci.getText().toString().equals("")) {
                    SQLiteOpenHelper bd = new BD(getContext());
                    final SQLiteDatabase db = bd.getReadableDatabase();
                    db.execSQL("PRAGMA foreign_keys = ON");

                    final ContentValues newArtista = new ContentValues();
                    newArtista.put("DNIPASAPORTE", tvDNI.getText().toString());
                    newArtista.put("NOMBRE", tvNombre.getText().toString());
                    newArtista.put("DIRECCION", tvDireccion.getText().toString());
                    newArtista.put("POBLACION", tvPoblacion.getText().toString());
                    newArtista.put("PROVINCIA", tvProvincia.getText().toString());
                    newArtista.put("PAIS", tvPais.getText().toString());
                    newArtista.put("MOVILTRABAJO", tvMovilTrab.getText().toString());
                    newArtista.put("MOVILPERSONAL", tvMovilPers.getText().toString());
                    newArtista.put("TELEFONOFIJO", tvTlfFijo.getText().toString());
                    newArtista.put("EMAIL", tvEmail.getText().toString());
                    newArtista.put("WEBBLOG", tvWEbBlog.getText().toString());
                    newArtista.put("FECHANACIMIENTO", tvFechaNaci.getText().toString());
                    if (db.insert("ARTISTAS", null, newArtista) != -1) {
                        Toast.makeText(getActivity(), "Inserccion correcta", Toast.LENGTH_SHORT).show();
                        db.close();
                    } else {
                        Toast.makeText(getActivity(), "Se ha producido un error", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        btMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tvDNI.getText().toString().equals("")) {
                    SQLiteOpenHelper bd = new BD(getContext());
                    final SQLiteDatabase db = bd.getReadableDatabase();
                    db.execSQL("PRAGMA foreign_keys = ON");
                    final ContentValues newExposicion = new ContentValues();
                    newExposicion.put("DNIPASAPORTE", tvDNI.getText().toString());
                    if (!tvNombre.getText().toString().equals("")) {
                        newExposicion.put("NOMBRE", tvNombre.getText().toString());
                    }
                    if (!tvDireccion.getText().toString().equals("")) {
                        newExposicion.put("DIRECCION", tvDireccion.getText().toString());
                    }
                    if (!tvPoblacion.getText().toString().equals("")) {
                        newExposicion.put("POBLACION", tvPoblacion.getText().toString());
                    }
                    if (!tvProvincia.getText().toString().equals("")) {
                        newExposicion.put("PROVINCIA", tvProvincia.getText().toString());
                    }
                    if (!tvPais.getText().toString().equals("")) {
                        newExposicion.put("PAIS", tvPais.getText().toString());
                    }
                    if (!tvMovilPers.getText().toString().equals("")) {
                        newExposicion.put("MOVILPERSONAL", tvMovilPers.getText().toString());
                    }
                    if (!tvMovilTrab.getText().toString().equals("")) {
                        newExposicion.put("MOVILTRABAJO", tvMovilTrab.getText().toString());
                    }
                    if (!tvTlfFijo.getText().toString().equals("")) {
                        newExposicion.put("TELEFONOFIJO", tvTlfFijo.getText().toString());
                    }
                    if (!tvWEbBlog.getText().toString().equals("")) {
                        newExposicion.put("WEBBLOG", tvWEbBlog.getText().toString());
                    }
                    if (!tvEmail.getText().toString().equals("")) {
                        newExposicion.put("EMAIL", tvEmail.getText().toString());
                    }
                    if (!tvFechaNaci.getText().toString().equals("")) {
                        newExposicion.put("FECHANACIMIENTO", tvFechaNaci.getText().toString());
                    }
                    if (db.update("ARTISTAS", newExposicion, "DNIPASAPORTE = " + tvDNI.getText().toString(), null) != 0) {
                        Toast.makeText(getActivity(),"Modificacion exitosa", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(),"Error al modificar, compruebe los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
