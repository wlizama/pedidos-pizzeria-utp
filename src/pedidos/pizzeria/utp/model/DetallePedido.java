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
public class DetallePedido {
    
    private int idDetallePedido;
    private Pizza pizza;
    private Pedido pedido;
    private int cantidad;

    public DetallePedido() {
    }

    public DetallePedido(int idDetallePedido, Pizza pizza, Pedido pedido, int cantidad) {
        this.idDetallePedido = idDetallePedido;
        this.pizza = pizza;
        this.pedido = pedido;
        this.cantidad = cantidad;
    }

    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
    
    
}
