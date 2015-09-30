package com.android.aleon.formpuntointeres;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by LEONES on 29/09/2015.
 */
public class TipoIntervencion {

    private Context context;

    private Long id;
    private String nombre;

    public TipoIntervencion(Context context)
    {
        this.context = context;
    }

    public TipoIntervencion(Context context, Long id, String nombre) {
        this.context = context;
        this.id = id;
        this.nombre = nombre;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static TipoIntervencion find(Context context, long id)
    {
        TipoIntervencionDbAdapter dbAdapter = new TipoIntervencionDbAdapter(context);

        Cursor c = dbAdapter.getRegistro(id);

        TipoIntervencion tipoIntervencion = TipoIntervencion.cursorToSituacion(context, c);

        c.close();

        return tipoIntervencion;
    }

    public static ArrayList<TipoIntervencion> getAll(Context context, String filter)
    {
        TipoIntervencionDbAdapter dbAdapter = new TipoIntervencionDbAdapter(context);

        return dbAdapter.getTiposIntervecion(filter) ;

    }


    public static TipoIntervencion cursorToSituacion(Context context, Cursor c)
    {
        TipoIntervencion situacion = null;

        if (c != null)
        {
            situacion = new TipoIntervencion(context);

            situacion.setId(c.getLong(c.getColumnIndex(TipoIntervencionDbAdapter.C_COLUMNA_ID)));
            situacion.setNombre(c.getString(c.getColumnIndex(TipoIntervencionDbAdapter.C_COLUMNA_NOMBRE)));
        }

        return situacion ;
    }

}
