/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.model;

/**
 *
 * @author Percy
 */
public class ListaComprobante {
    private int idComprobante;
    private int numero;
    private double monto;
    private String nombres;
    private String apellidos;
    
    
   public ListaComprobante(){
   
   }

    public ListaComprobante(int idComprobante, int numero, double monto, String nombres) {
        this.idComprobante = idComprobante;
        this.numero = numero;
        this.monto = monto;
        this.nombres = nombres;
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

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
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
    
    
    
    
}

