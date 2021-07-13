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
    private boolean estado;
    private Roles rol;
    private Formulario formulario;

    public Acceso() {
    }

    public Acceso(int IdAcceso, boolean estado, Roles rol, Formulario formulario) {
        this.IdAcceso = IdAcceso;
        this.estado = estado;
        this.rol = rol;
        this.formulario = formulario;
    }

    public Acceso(int IdAcceso, Roles rol, Formulario formulario) {
        this.IdAcceso = IdAcceso;
        this.rol = rol;
        this.formulario = formulario;
    }       

    public int getIdAcceso() {
        return IdAcceso;
    }

    public void setIdAcceso(int IdAcceso) {
        this.IdAcceso = IdAcceso;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    
}
