/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.model;

/**
 *
 * @author Percy
 */
public class TipoPizza {
    private int IdTipoPizza;
    private String nombre;
    private String descripcion;
    
    public TipoPizza() {
    
    }

    public TipoPizza(int IdTipoPizza, String nombre, String descripcion) {
        this.IdTipoPizza = IdTipoPizza;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdTipoPizza() {
        return IdTipoPizza;
    }

    public void setIdTipoPizza(int IdTipoPizza) {
        this.IdTipoPizza = IdTipoPizza;
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
