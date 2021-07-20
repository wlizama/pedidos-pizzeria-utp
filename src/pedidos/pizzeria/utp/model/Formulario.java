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
public class Formulario {
    private int IdFormulario;
    private String nombre;
    
    public Formulario() {
        // ...
    }

    public Formulario(int IdFormulario, String nombre) {
        this.IdFormulario = IdFormulario;
        this.nombre = nombre;
    }

    public int getIdFormulario() {
        return IdFormulario;
    }

    public void setIdFormulario(int IdFormulario) {
        this.IdFormulario = IdFormulario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
    
    
}
