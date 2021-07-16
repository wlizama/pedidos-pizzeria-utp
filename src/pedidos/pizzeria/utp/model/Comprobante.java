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
public class Comprobante {
    
    private int idComprobante;
    private int numero;
    private Date fechaEmision;
    private double monto;
    private TipoComprobante tipoComprobante;
    private Pedido pedido;

    public Comprobante() {
    }

    public Comprobante(int idComprobante, int numero, Date fechaEmision, double monto, TipoComprobante tipoComprobante, Pedido pedido) {
        this.idComprobante = idComprobante;
        this.numero = numero;
        this.fechaEmision = fechaEmision;
        this.monto = monto;
        this.tipoComprobante = tipoComprobante;
        this.pedido = pedido;
    }

    public int getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public TipoComprobante getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(TipoComprobante tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    
}
