package com.example.appfinalpdmsqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BD extends SQLiteOpenHelper {

    private static final int VERSION_BASEDATOS= 1;
    private static final String NOMBRE_BASEDATOS= "BD_SalaExposiciones.db";


    private static final String creaExposiciones= "CREATE  TABLE  EXPOSICIONES  (IDEXPOSICION INT  PRIMARY  KEY, NOMBREEXP  VARCHAR(100), " +
            " DESCRIPCION    VARCHAR(150),  FECHAINICIO  VARCHAR(20),  FECHAFIN VARCHAR(20))";

    private static final String creaArtistas= "CREATE  TABLE  ARTISTAS  (DNIPASAPORTE VARCHAR(50)  PRIMARY  KEY, NOMBRE VARCHAR(50), DIRECCION VARCHAR(50), " +
            " POBLACION VARCHAR(50),  PROVINCIA VARCHAR(50),  PAIS VARCHAR(50), MOVILTRABAJO INTEGER, MOVILPERSONAL INTEGER, TELEFONOFIJO INTEGER," +
            "EMAIL VARCHAR(50), WEBBLOG VARCHAR(50), FECHANACIMIENTO VARCHAR(20))";

    private static final String creaExponen= "CREATE  TABLE  EXPONEN  (IDEXPOSICION INT, DNIPASAPORTE VARCHAR(50)," +
            //"ALTER TABLE EXPONEN ADD constraint pk_exponen " +
            "primary key(IDEXPOSICION,DNIPASAPORTE)," +
            //"ALTER TABLE EXPONEN ADD constraint fk_exponen_exposiciones " +
            "foreign key(IDEXPOSICION) references EXPOSICIONES(IDEXPOSICION), " +
            //"ALTER TABLE EXPONEN ADD constraint fk_exponen_artistas " +
            "foreign key(DNIPASAPORTE) references ARTISTAS(DNIPASAPORTE))";

    private static final String creaTrabajos= "CREATE  TABLE  TRABAJOS  (NOMBRETRAB VARCHAR(50) PRIMARY KEY, DESCRIPCION  VARCHAR(150), TAMAÑO VARCHAR(50), PESO VARCAHR(50)," +
            "DNIPASAPORTE VARCHAR(50)," +
            //"ALTER TABLE TRABAJOS ADD constraint pk_trabajos primary key(NOMBRETRAB);" +
            //"ALTER TABLE TRABAJOS ADD constraint fk_trabajos_artistas" +
            "foreign key(DNIPASAPORTE) references ARTISTAS(DNIPASAPORTE))";

    private static final String creaComentarios= "CREATE  TABLE  COMENTARIOS  (IDEXPOSICION INT, NOMBRETRAB VARCHAR(50), COMENTARIO VARCHAR(150)," +
            //"ALTER TABLE COMENTARIOS ADD constraint pk_comentarios " +
            "primary key(IDEXPOSICION, NOMBRETRAB)," +
            //"ALTER TABLE COMENTARIOS ADD constraint fk_comentarios_exposiciones " +
            "foreign key(IDEXPOSICION) references EXPOSICIONES(IDEXPOSICION)," +
            //"ALTER TABLE COMENTARIOS ADD constraint fk_exponen_trabajos " +
            "foreign key(NOMBRETRAB) references TRABAJOS(NOMBRETRAB))";

    public BD(@Nullable Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(creaExposiciones);
        db.execSQL(creaArtistas);
        db.execSQL(creaExponen);
        db.execSQL(creaTrabajos);
        db.execSQL(creaComentarios);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys = ON");
    }
}