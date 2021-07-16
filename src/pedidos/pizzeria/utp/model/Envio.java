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
public class Envio {
    private int IdEnvio;
    private int numero;
    private Timestamp horaInicio;
    private Timestamp horaFin;
    private Repartidor repartidor;
    private Estado estado;
    
    public Envio(){
        
    }

    public Envio(int IdEnvio, int numero, Timestamp horaInicio, Timestamp horaFin, Repartidor repartidor, Estado estado) {
        this.IdEnvio = IdEnvio;
        this.numero = numero;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.repartidor = repartidor;
        this.estado = estado;
    }

    public int getIdEnvio() {
        return IdEnvio;
    }

    public void setIdEnvio(int IdEnvio) {
        this.IdEnvio = IdEnvio;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Timestamp getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Timestamp horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Timestamp getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Timestamp horaFin) {
        this.horaFin = horaFin;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    
}
