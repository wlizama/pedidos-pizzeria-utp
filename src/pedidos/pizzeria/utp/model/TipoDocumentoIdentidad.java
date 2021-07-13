/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.model;

/**
 *
 * @author wilderlizama
 */
public class TipoDocumentoIdentidad {
    private int IdTipoDocIdentidad;
    private String nombre;
    private int cantidadCaracteres;

    public TipoDocumentoIdentidad() {
    }

    public TipoDocumentoIdentidad(int IdTipoDocIdentidad, String nombre, int cantidadCaracteres) {
        this.IdTipoDocIdentidad = IdTipoDocIdentidad;
        this.nombre = nombre;
        this.cantidadCaracteres = cantidadCaracteres;
    }

    public int getIdTipoDocIdentidad() {
        return IdTipoDocIdentidad;
    }

    public void setIdTipoDocIdentidad(int IdTipoDocIdentidad) {
        this.IdTipoDocIdentidad = IdTipoDocIdentidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadCaracteres() {
        return cantidadCaracteres;
    }

    public void setCantidadCaracteres(int cantidadCaracteres) {
        this.cantidadCaracteres = cantidadCaracteres;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
}
