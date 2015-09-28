package com.android.aleon.model;

/**
 * Created by LEONES on 25/09/2015.
 */
public class PuntoInteres {
    private Integer _id;
    private String nombre;
    private String descripcion;

    public PuntoInteres(){
    }

    public PuntoInteres(Integer _id, String nombre, String descripcion) {
        this._id = _id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
