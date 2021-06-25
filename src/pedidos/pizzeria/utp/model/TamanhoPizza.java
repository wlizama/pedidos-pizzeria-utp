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
public class TamanhoPizza {
    private int IdTamanhoPizza;
    private String nombre;
    private int cantidadPorciones;

    public TamanhoPizza(){
        
    }

    public TamanhoPizza(int IdTamanhoPizza, String nombre, int cantidadPorciones) {
        this.IdTamanhoPizza = IdTamanhoPizza;
        this.nombre = nombre;
        this.cantidadPorciones = cantidadPorciones;
    }

    public int getIdTamanhoPizza() {
        return IdTamanhoPizza;
    }

    public void setIdTamanhoPizza(int IdTamanhoPizza) {
        this.IdTamanhoPizza = IdTamanhoPizza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadPorciones() {
        return cantidadPorciones;
    }

    public void setCantidadPorciones(int cantidadPorciones) {
        this.cantidadPorciones = cantidadPorciones;
    }
    
   
    
}
