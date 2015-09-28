package com.android.aleon.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.aleon.model.PuntoInteres;

/**
 * Created by LEONES on 25/09/2015.
 */
public class PuntoInteresDao {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public PuntoInteresDao(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase(){
        if (database == null){
            database = databaseHelper.getWritableDatabase();
        }

        return database;
    }

    private PuntoInteres crearPuntoInteres(Cursor cursor){
        PuntoInteres model = new PuntoInteres(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PuntoInteres._ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.PuntoInteres.NOMBRE)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.PuntoInteres.DESCRIPCION)));

        return model;
        
    }


}
