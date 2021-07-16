/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.model;

import java.sql.Timestamp;

/**
 *
 * @author Percy
 */
public class DetalleEnvio {
    private int IdDetalleEnvio;
    private Timestamp horaFin;
    private Envio envio;
    private String observaciones;
    private Pedido pedido;
    
    public DetalleEnvio() {
        
    }

    public DetalleEnvio(int IdDetalleEnvio, Timestamp horaFin, Envio envio, String observaciones, Pedido pedido) {
        this.IdDetalleEnvio = IdDetalleEnvio;
        this.horaFin = horaFin;
        this.envio = envio;
        this.observaciones = observaciones;
        this.pedido = pedido;
    }

    public int getIdDetalleEnvio() {
        return IdDetalleEnvio;
    }

    public void setIdDetalleEnvio(int IdDetalleEnvio) {
        this.IdDetalleEnvio = IdDetalleEnvio;
    }

    public Timestamp getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Timestamp horaFin) {
        this.horaFin = horaFin;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
