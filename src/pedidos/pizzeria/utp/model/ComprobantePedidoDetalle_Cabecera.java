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
public class ComprobantePedidoDetalle_Cabecera {
    private int idComprobante;
    private String nombres;
    private int documento;
    private String direccion;
    private Date fecha;

    public ComprobantePedidoDetalle_Cabecera(){
    }
    
    public ComprobantePedidoDetalle_Cabecera(int idComprobante, String nombres, int documento, String direccion, Date fecha) {
        this.idComprobante = idComprobante;
        this.nombres = nombres;
        this.documento = documento;
        this.direccion = direccion;
        this.fecha = fecha;
    }

    public int getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    
}
