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
public class Persona {
    private int IdPersona;
    private String nombres;
    private String apellidos;
    private String telefono;
    private TipoPersona tipoPersona;
    private DocumentoIdentidad documentoIdentidad;
    private Estado estado;

    public Persona() {
    }

    public Persona(int IdPersona, String nombres, String apellidos, String telefono, TipoPersona tipoPersona, DocumentoIdentidad documentoIdentidad, Estado estado) {
        this.IdPersona = IdPersona;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.tipoPersona = tipoPersona;
        this.documentoIdentidad = documentoIdentidad;
        this.estado = estado;
    }

    public int getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(int IdPersona) {
        this.IdPersona = IdPersona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public DocumentoIdentidad getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(DocumentoIdentidad documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
}
