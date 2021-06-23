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
public class Usuario extends Persona {
    private int IdUsuario;
    private String nombreUsuario;
    private String contrasenha;
    private Roles rol;

    public Usuario() {
    }

    public Usuario(int IdUsuario, String nombreUsuario, String contrasenha, Roles rol) {
        this.IdUsuario = IdUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenha = contrasenha;
        this.rol = rol;
    }
    
    
    public Usuario(int IdUsuario, String nombreUsuario, String contrasenha, Roles rol, int IdPersona, String nombres, String apellidos, String telefono, TipoPersona tipoPersona, DocumentoIdentidad documentoIdentidad, Estado estado) {
        super(IdPersona, nombres, apellidos, telefono, tipoPersona, documentoIdentidad, estado);
        this.IdUsuario = IdUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenha = contrasenha;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    
}
