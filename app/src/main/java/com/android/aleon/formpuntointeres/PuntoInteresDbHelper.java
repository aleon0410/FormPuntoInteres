package com.android.aleon.formpuntointeres;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by LEONES on 29/09/2015.
 */
public class PuntoInteresDbHelper extends SQLiteOpenHelper{

    private static int version = 4;
    private static String name = "PuntoInteresDb" ;
    private static SQLiteDatabase.CursorFactory factory = null;

    public PuntoInteresDbHelper(Context context)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i(this.getClass().toString(), "Creando base de datos");

        db.execSQL( "CREATE TABLE PUNTO_INTERES(" +
                " _id INTEGER PRIMARY KEY," +
                " hip_nombre TEXT NOT NULL, " +
                " hip_condiciones TEXT, " +
                " hip_contacto TEXT," +
                " hip_email TEXT," +
                " hip_telefono TEXT," +
                " hip_observaciones TEXT)" );

        db.execSQL( "CREATE UNIQUE INDEX hip_nombre ON PUNTO_INTERES(hip_nombre ASC)" );

        Log.i(this.getClass().toString(), "Tabla PUNTO_INTERES creada");

    /*
     * Insertamos datos iniciales
     */
        db.execSQL("INSERT INTO PUNTO_INTERES(_id, hip_nombre) VALUES(1,'Santander')");
        db.execSQL("INSERT INTO PUNTO_INTERES(_id, hip_nombre) VALUES(2,'BBVA')");
        db.execSQL("INSERT INTO PUNTO_INTERES(_id, hip_nombre) VALUES(3,'La Caixa')");
        db.execSQL("INSERT INTO PUNTO_INTERES(_id, hip_nombre) VALUES(4,'Cajamar')");
        db.execSQL("INSERT INTO PUNTO_INTERES(_id, hip_nombre) VALUES(5,'Bankia')");
        db.execSQL("INSERT INTO PUNTO_INTERES(_id, hip_nombre) VALUES(6,'Banco Sabadell')");
        db.execSQL("INSERT INTO PUNTO_INTERES(_id, hip_nombre) VALUES(7,'Banco Popular')");

        Log.i(this.getClass().toString(), "Datos iniciales PUNTO_INTERES insertados");

        Log.i(this.getClass().toString(), "Base de datos creada");

        // Aplicamos las sucesivas actualizaciones
        upgrade_2(db);
        upgrade_3(db);
        upgrade_4(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Actualización a versión 2
        if (oldVersion < 2)
        {
            upgrade_2(db);
        }
        // Actualización a versión 3
        if (oldVersion < 3)
        {
            upgrade_3(db);
        }
        // Actualización a versión 4
        if (oldVersion < 4)
        {
            upgrade_4(db);
        }
    }

    private void upgrade_2(SQLiteDatabase db)
    {
        //
        // Upgrade versión 2: definir algunos datos de ejemplo
        //
        db.execSQL( "UPDATE PUNTO_INTERES SET hip_contacto = 'Julián Gómez Martínez'," +
                "					hip_email = 'jgmartinez@gmail.com'," +
                "					hip_observaciones = 'Tiene toda la documentación y está estudiando la solicitud. En breve llamará para informar de las condiciones'" +
                " WHERE _id = 1");

        Log.i(this.getClass().toString(), "Actualización versión 2 finalizada");
    }

    private void upgrade_3(SQLiteDatabase db)
    {
        //
        // Upgrade versión 3: Incluir pasivo_sn
        //
        db.execSQL("ALTER TABLE PUNTO_INTERES ADD hip_pasivo_sn   VARCHAR2(1) NOT NULL DEFAULT 'N'");

        Log.i(this.getClass().toString(), "Actualización versión 3 finalizada");
    }

    private void upgrade_4(SQLiteDatabase db)
    {
        //
        // Upgrade versión 4: Incluir la clasificación SITUACION para las hipotecas
        //
        db.execSQL( "CREATE TABLE TIPO_INTERVENCION(" +
                " _id INTEGER PRIMARY KEY," +
                " sit_nombre TEXT NOT NULL)");

        db.execSQL( "CREATE UNIQUE INDEX sit_nombre ON TIPO_INTERVENCION(sit_nombre ASC)" );

        db.execSQL("INSERT INTO TIPO_INTERVENCION(_id, sit_nombre) VALUES(1,'Inicial')");
        db.execSQL("INSERT INTO TIPO_INTERVENCION(_id, sit_nombre) VALUES(2,'Información')");
        db.execSQL("INSERT INTO TIPO_INTERVENCION(_id, sit_nombre) VALUES(3,'Solicitada')");
        db.execSQL("INSERT INTO TIPO_INTERVENCION(_id, sit_nombre) VALUES(4,'Negociación')");
        db.execSQL("INSERT INTO TIPO_INTERVENCION(_id, sit_nombre) VALUES(5,'Denegada')");
        db.execSQL("INSERT INTO TIPO_INTERVENCION(_id, sit_nombre) VALUES(6,'Desestimada')");
        db.execSQL("INSERT INTO TIPO_INTERVENCION(_id, sit_nombre) VALUES(7,'Concedida')");
        db.execSQL("INSERT INTO TIPO_INTERVENCION(_id, sit_nombre) VALUES(8,'Firmada')");

        db.execSQL("ALTER TABLE PUNTO_INTERES ADD hip_sit_id INTEGER NOT NULL DEFAULT 1");

        Log.i(this.getClass().toString(), "Actualización versión 4 finalizada");
    }
}
