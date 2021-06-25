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
public class Direccion {
    
    private int idDireccion;
    private String referencia;
    private Boolean principal;
    private Distrito distrito;
    private Cliente cliente;

    public Direccion() {
    }

    public Direccion(int idDireccion, String referencia, Boolean principal, Distrito distrito, Cliente cliente) {
        this.idDireccion = idDireccion;
        this.referencia = referencia;
        this.principal = principal;
        this.distrito = distrito;
        this.cliente = cliente;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    
}
