/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.model;

/**
 *
 * @author jonas
 */
public class Distrito {
    
    private int idDistrito;
    private String nombre;
    private Boolean cobertura;

    public Distrito() {
    }

    public Distrito(int idDistrito, String nombre, Boolean cobertura) {
        this.idDistrito = idDistrito;
        this.nombre = nombre;
        this.cobertura = cobertura;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getCobertura() {
        return cobertura;
    }

    public void setCobertura(Boolean cobertura) {
        this.cobertura = cobertura;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
    
    
    
}
