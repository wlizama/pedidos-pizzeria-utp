/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import pedidos.pizzeria.utp.dao.TipoPizzaDAO;

/**
 *
 * @author wilderlizama
 */
public class TipoPizzaController {
    
    public void initTipoPizza() {
        buscarTipoPizzaLista();
    }
    
    public void buscarTipoPizzaLista() {
        try {
            TipoPizzaDAO tpDAO = new TipoPizzaDAO();
            System.out.println("Lista de Tipo Pizzas:" + tpDAO.getListaTipoPizza().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
