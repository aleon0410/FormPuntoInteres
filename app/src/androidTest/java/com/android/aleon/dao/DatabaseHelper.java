package com.android.aleon.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by LEONES on 28/09/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLA = "punto_interes";
    private static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, TABLA, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE PuntoInteres(" +
                " _id INTEGER PRIMARY KEY," +
                " nombre TEXT NOT NULL, " +
                " observacion TEXT " );
        Log.i(this.getClass().toString(), "Tabla Punto Interes creada");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public static class PuntoInteres{
        public static final String TABLA = "punto_interes";
        public static final String _ID = "_id";
        public static final String NOMBRE = "nombre";
        public static final String DESCRIPCION = "descripcion";

        public static final String[] COLUNAS = new String[]{
                _ID, NOMBRE,DESCRIPCION
        };
    }
}
