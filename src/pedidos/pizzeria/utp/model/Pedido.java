/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.model;

import java.util.Date;

/**
 *
 * @author jonas
 */
public class Pedido {
    
    private int idPedido;
    private int numero;
    private Date fechaCreacion;
    private int direccionEnvio;
    private String observaciones;
    private Cliente cliente;

    public Pedido() {
    }

    public Pedido(int idPedido, int numero, Date fechaCreacion, int direccionEnvio, String observaciones, Cliente cliente) {
        this.idPedido = idPedido;
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
        this.direccionEnvio = direccionEnvio;
        this.observaciones = observaciones;
        this.cliente = cliente;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(int direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    
}
