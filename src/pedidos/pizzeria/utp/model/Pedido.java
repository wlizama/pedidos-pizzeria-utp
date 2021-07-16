/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.model;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author jonas
 */
public class Pedido {
    
    private int idPedido;
    private int numero;
    private int numeroComprobante; // pertenece a comprobante
    private Date fechaCreacion;
    private Time horacreacion;
    private Direccion direccionEnvio;
    private String observaciones;
    private Cliente cliente;
    private Estado estado;
    
    public Pedido() {
    }

    public Pedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Pedido(int idPedido, int numero, int numeroComprobante, Date fechaCreacion, Time horacreacion, Direccion direccionEnvio, String observaciones, Cliente cliente, Estado estado) {
        this.idPedido = idPedido;
        this.numero = numero;
        this.numeroComprobante = numeroComprobante;
        this.fechaCreacion = fechaCreacion;
        this.horacreacion = horacreacion;
        this.direccionEnvio = direccionEnvio;
        this.observaciones = observaciones;
        this.cliente = cliente;
        this.estado = estado;
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

    public int getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(int numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Time getHoracreacion() {
        return horacreacion;
    }

    public void setHoracreacion(Time horacreacion) {
        this.horacreacion = horacreacion;
    }

    public Direccion getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(Direccion direccionEnvio) {
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    
}
