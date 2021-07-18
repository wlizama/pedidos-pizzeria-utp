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
public class ComprobantePedidoDetalle_Lista {
    private int idPedido;
    private String pizza;
    private String tamano;
    private int cantidad;
    private Double precio;
    
    public ComprobantePedidoDetalle_Lista() {
        
    }

    public ComprobantePedidoDetalle_Lista(int idPedido, String pizza, String tamano, int cantidad, Double precio) {
        this.idPedido = idPedido;
        this.pizza = pizza;
        this.tamano = tamano;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getPizza() {
        return pizza;
    }

    public void setPizza(String pizza) {
        this.pizza = pizza;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    
    
}
