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
public class TipoComprobante {
    
    private int idTipoComprobante;
    private String nombre;

    public TipoComprobante() {
    }

    public TipoComprobante(int idTipoComprobante, String nombre) {
        this.idTipoComprobante = idTipoComprobante;
        this.nombre = nombre;
    }

    public int getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(int idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
