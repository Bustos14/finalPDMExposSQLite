package com.example.appfinalpdmsqlite;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



public class infoArtista extends AppCompatActivity {

    ImageButton btLlamar;
    ImageButton btEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infoartista);

        btLlamar = findViewById(R.id.imgbLlamar);
        btEmail = findViewById(R.id.imgBEmail);

        Bundle datos = this.getIntent().getExtras();
        TextView DNI = findViewById(R.id.txDNI);
        TextView nombre = findViewById(R.id.txNom);
        TextView direccion = findViewById(R.id.txDireccion);
        TextView poblacion = findViewById(R.id.txPoblacion);
        TextView provincia = findViewById(R.id.txProvincia);
        TextView pais = findViewById(R.id.txPais);
        final TextView movilTrab = findViewById(R.id.txMovTra);
        final TextView movilPers = findViewById(R.id.txMovPer);
        final TextView fijo = findViewById(R.id.txFijo);
        final TextView email = findViewById(R.id.txEmail);
        TextView webBlog = findViewById(R.id.txWebBlog);
        TextView fechaNaci = findViewById(R.id.txFechaNaci);

        DNI.setText(datos.getString("dni"));
        nombre.setText(datos.getString("nombre"));
        direccion.setText(datos.getString("direccion"));
        poblacion.setText(datos.getString("poblacion"));
        provincia.setText(datos.getString("provincia"));
        pais.setText(datos.getString("pais"));
        movilTrab.setText(datos.getString("movilTrab"));
        movilPers.setText(datos.getString("movilPersonal"));
        fijo.setText(datos.getString("fijo"));
        email.setText(datos.getString("email"));
        webBlog.setText(datos.getString("webblog"));
        fechaNaci.setText(datos.getString("fechaNacimiento"));

        btLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(infoArtista.this);
                builder.setTitle("Opciones:");
                builder.setMessage("¿Qué quieres hacer?.");
                builder.setPositiveButton("Fijo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialPhoneNumber(fijo.getText().toString());
                    }
                });
                builder.setNeutralButton("Personal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialPhoneNumber(movilPers.getText().toString());
                    }
                });
                builder.setNegativeButton("Trabajo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialPhoneNumber(movilTrab.getText().toString());
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] emils = new String[1];
                emils[0] = email.getText().toString();

                composeEmail(emils, "Expo");
            }
        });
    }
    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }
    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        startActivity(intent);
    }

}