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
public class Acceso {
    
    private int IdAcceso;
    private Formulario formulario;
    private Roles rol;
    

    public Acceso() {
    }

    public Acceso(int IdAcceso, Formulario formulario, Roles rol) {
        this.IdAcceso = IdAcceso;
        this.formulario = formulario;
        this.rol = rol;
    }

    public int getIdAcceso() {
        return IdAcceso;
    }

    public void setIdAcceso(int IdAcceso) {
        this.IdAcceso = IdAcceso;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    
}
