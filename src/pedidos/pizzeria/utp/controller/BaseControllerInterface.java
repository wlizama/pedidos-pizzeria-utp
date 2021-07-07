/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

/**
 *
 * @author wilderlizama
 */
public interface BaseControllerInterface {
    public void limpiarForm(); // limpiar todos los elemetos de vista
    
    public void buscar();      // buscar con o sin filtros para lista 
    
    public void obtener();     // obtener modelo por id y settear operación en EDIT
    
    public void nuevo();       // limpiar elementos y settear operación en NEW
    
    public void insertar();    // ejecutar método dao para insertar
    
    public void modificar();   // ejecutar método dao para modificar
    
    public void guardar();     // insertar o modificar dependiendo de OP
}
