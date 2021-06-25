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
public class Pizza {
    private int IdPizza;
    private String nombre;
    private Double precio;
    private TamanhoPizza tamanhoPizza;
    private TipoPizza tipoPizza;

    public Pizza(){
        
    }

    public Pizza(int IdPizza, String nombre, Double precio, TamanhoPizza tamanhoPizza, TipoPizza tipoPizza) {
        this.IdPizza = IdPizza;
        this.nombre = nombre;
        this.precio = precio;
        this.tamanhoPizza = tamanhoPizza;
        this.tipoPizza = tipoPizza;
    }

    public int getIdPizza() {
        return IdPizza;
    }

    public void setIdPizza(int IdPizza) {
        this.IdPizza = IdPizza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public TamanhoPizza getTamanhoPizza() {
        return tamanhoPizza;
    }

    public void setTamanhoPizza(TamanhoPizza tamanhoPizza) {
        this.tamanhoPizza = tamanhoPizza;
    }

    public TipoPizza getTipoPizza() {
        return tipoPizza;
    }

    public void setTipoPizza(TipoPizza tipoPizza) {
        this.tipoPizza = tipoPizza;
    }
    
   
    
    
}
