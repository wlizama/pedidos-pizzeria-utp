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
public class Roles {
    private int IdRol;
    private String nombre;

    public Roles() {
    }

    public Roles(int IdRol) {
        this.IdRol = IdRol;
    }

    public Roles(int IdRol, String nombre) {
        this.IdRol = IdRol;
        this.nombre = nombre;
    }

    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int IdRol) {
        this.IdRol = IdRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
