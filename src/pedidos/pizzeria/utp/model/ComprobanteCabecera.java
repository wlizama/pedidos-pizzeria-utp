/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.model;

import java.util.Date;

/**
 *
 * @author Percy
 */
public class ComprobanteCabecera {
    int IdComprobante;
    int numero;
    Double monto;
    int IdPedido;
    Date fechacreacion;
    int IdCliente;
    String cliente;
    String clienteapellidos;
    String clientedocIdentidad;
    String clientetipodocidentidad;
    int IdDireccionEnvio;
    String direccionenvio;

    public ComprobanteCabecera() {
    }

    public ComprobanteCabecera(int IdComprobante, int numero, Double monto, int IdPedido, Date fechacreacion, int IdCliente, String cliente, String clienteapellidos, String clientedocIdentidad, String clientetipodocidentidad, int IdDireccionEnvio, String direccionenvio) {
        this.IdComprobante = IdComprobante;
        this.numero = numero;
        this.monto = monto;
        this.IdPedido = IdPedido;
        this.fechacreacion = fechacreacion;
        this.IdCliente = IdCliente;
        this.cliente = cliente;
        this.clienteapellidos = clienteapellidos;
        this.clientedocIdentidad = clientedocIdentidad;
        this.clientetipodocidentidad = clientetipodocidentidad;
        this.IdDireccionEnvio = IdDireccionEnvio;
        this.direccionenvio = direccionenvio;
    }

    public int getIdComprobante() {
        return IdComprobante;
    }

    public void setIdComprobante(int IdComprobante) {
        this.IdComprobante = IdComprobante;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public int getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(int IdPedido) {
        this.IdPedido = IdPedido;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getClienteapellidos() {
        return clienteapellidos;
    }

    public void setClienteapellidos(String clienteapellidos) {
        this.clienteapellidos = clienteapellidos;
    }

    public String getClientedocIdentidad() {
        return clientedocIdentidad;
    }

    public void setClientedocIdentidad(String clientedocIdentidad) {
        this.clientedocIdentidad = clientedocIdentidad;
    }

    public String getClientetipodocidentidad() {
        return clientetipodocidentidad;
    }

    public void setClientetipodocidentidad(String clientetipodocidentidad) {
        this.clientetipodocidentidad = clientetipodocidentidad;
    }

    public int getIdDireccionEnvio() {
        return IdDireccionEnvio;
    }

    public void setIdDireccionEnvio(int IdDireccionEnvio) {
        this.IdDireccionEnvio = IdDireccionEnvio;
    }

    public String getDireccionenvio() {
        return direccionenvio;
    }

    public void setDireccionenvio(String direccionenvio) {
        this.direccionenvio = direccionenvio;
    }
    
    
    
}
