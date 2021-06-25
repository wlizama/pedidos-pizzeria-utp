/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.model;

import java.sql.Time;

/**
 *
 * @author Percy
 */
public class Envio {
    private int IdEnvio;
    private int numero;
    private Time horaInicio;
    private Time horaFin;
    private Repartidor repartidor;
    
    public Envio(){
        
    }

    public Envio(int IdEnvio, int numero, Time horaInicio, Time horaFin, Repartidor repartidor) {
        this.IdEnvio = IdEnvio;
        this.numero = numero;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.repartidor = repartidor;
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

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    
    
    
}
