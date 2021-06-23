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
public class TipoEstado {
    private int IdTipoEstado;
    private String nombre;

    public TipoEstado() {
    }

    public TipoEstado(int IdTipoEstado, String nombre) {
        this.IdTipoEstado = IdTipoEstado;
        this.nombre = nombre;
    }

    public int getIdTipoEstado() {
        return IdTipoEstado;
    }

    public void setIdTipoEstado(int IdTipoEstado) {
        this.IdTipoEstado = IdTipoEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
