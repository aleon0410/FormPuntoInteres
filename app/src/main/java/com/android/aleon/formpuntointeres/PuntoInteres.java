package com.android.aleon.formpuntointeres;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by LEONES on 29/09/2015.
 */
public class PuntoInteres {

    private Context context;

    private Long id;
    private String nombre;
    private String condiciones;
    private String contacto;
    private String email;
    private String telefono;
    private String observaciones;
    private boolean pasivo;
    private Long situacionId;

    public PuntoInteres(Context context)
    {
        this.context = context;
    }

    public PuntoInteres(Context context, Long id, String nombre, String condiciones, String contacto, String email, String telefono, String observaciones, boolean pasivo, Long situacionId) {
        this.context = context;
        this.id = id;
        this.nombre = nombre;
        this.condiciones = condiciones;
        this.contacto = contacto;
        this.email = email;
        this.telefono = telefono;
        this.observaciones = observaciones;
        this.pasivo = pasivo;
        this.situacionId = situacionId;
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

    public String getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(String condiciones) {
        this.condiciones = condiciones;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isPasivo() {
        return pasivo;
    }

    public void setPasivo(boolean pasivo) {
        this.pasivo = pasivo;
    }

    public Long getSituacionId() {
        return situacionId;
    }

    public void setSituacionId(Long situacionId) {
        this.situacionId = situacionId;
    }

    public static PuntoInteres find(Context context, long id)
    {
        PuntoInteresDbAdapter dbAdapter = new PuntoInteresDbAdapter(context);

        Cursor c = dbAdapter.getRegistro(id);

        PuntoInteres puntoInteres = PuntoInteres.cursorToPuntoInteres(context, c);

        c.close();

        return puntoInteres;
    }

    public static PuntoInteres cursorToPuntoInteres(Context context, Cursor c)
    {
        PuntoInteres puntoInteres = null;

        if (c != null)
        {
            puntoInteres = new PuntoInteres(context);

            puntoInteres.setId(c.getLong(c.getColumnIndex(PuntoInteresDbAdapter.C_COLUMNA_ID)));
            puntoInteres.setNombre(c.getString(c.getColumnIndex(PuntoInteresDbAdapter.C_COLUMNA_NOMBRE)));
            puntoInteres.setCondiciones(c.getString(c.getColumnIndex(PuntoInteresDbAdapter.C_COLUMNA_CONDICIONES)));
            puntoInteres.setContacto(c.getString(c.getColumnIndex(PuntoInteresDbAdapter.C_COLUMNA_CONTACTO)));
            puntoInteres.setEmail(c.getString(c.getColumnIndex(PuntoInteresDbAdapter.C_COLUMNA_EMAIL)));
            puntoInteres.setTelefono(c.getString(c.getColumnIndex(PuntoInteresDbAdapter.C_COLUMNA_TELEFONO)));
            puntoInteres.setObservaciones(c.getString(c.getColumnIndex(PuntoInteresDbAdapter.C_COLUMNA_OBSERVACIONES)));
            puntoInteres.setPasivo(c.getString(c.getColumnIndex(PuntoInteresDbAdapter.C_COLUMNA_PASIVO)).equals("S"));
            puntoInteres.setSituacionId(c.getLong(c.getColumnIndex(PuntoInteresDbAdapter.C_COLUMNA_SITUACION)));
        }

        return puntoInteres ;
    }

    private ContentValues toContentValues()
    {
        ContentValues reg = new ContentValues();

        reg.put(PuntoInteresDbAdapter.C_COLUMNA_ID, this.getId());
        reg.put(PuntoInteresDbAdapter.C_COLUMNA_NOMBRE, this.getNombre());
        reg.put(PuntoInteresDbAdapter.C_COLUMNA_CONDICIONES, this.getCondiciones());
        reg.put(PuntoInteresDbAdapter.C_COLUMNA_CONTACTO, this.getContacto());
        reg.put(PuntoInteresDbAdapter.C_COLUMNA_EMAIL, this.getEmail());
        reg.put(PuntoInteresDbAdapter.C_COLUMNA_TELEFONO, this.getTelefono());
        reg.put(PuntoInteresDbAdapter.C_COLUMNA_OBSERVACIONES, this.getObservaciones());
        reg.put(PuntoInteresDbAdapter.C_COLUMNA_PASIVO, (this.isPasivo())?"S":"N");
        reg.put(PuntoInteresDbAdapter.C_COLUMNA_SITUACION, this.getSituacionId());

        return reg;
    }

    public long save()
    {
        PuntoInteresDbAdapter dbAdapter = new PuntoInteresDbAdapter(this.getContext());

        // comprobamos si estamos insertando o actualizando según esté o no relleno el identificador
        if ((this.getId() == null) || (!dbAdapter.exists(this.getId())))
        {
            long nuevoId = dbAdapter.insert(this.toContentValues());

            if (nuevoId != -1)
            {
                this.setId(nuevoId);
            }
        }
        else
        {
            dbAdapter.update(this.toContentValues());
        }

        return this.getId();
    }

    public long delete()
    {
        // borramos el registro
        PuntoInteresDbAdapter dbAdapter = new PuntoInteresDbAdapter(this.getContext());

        return dbAdapter.delete(this.getId());
    }

}
