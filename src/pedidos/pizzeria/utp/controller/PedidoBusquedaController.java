/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pedidos.pizzeria.utils.Helpers;
import pedidos.pizzeria.utp.dao.PizzaDAO;
import pedidos.pizzeria.utp.model.Estado;
import pedidos.pizzeria.utp.model.Pizza;
import pedidos.pizzeria.utp.model.TamanhoPizza;
import pedidos.pizzeria.utp.model.TipoPizza;
import pedidos.pizzeria.utp.view.PedidoBusquedaPizzaView;

/**
 *
 * @author wilderlizama
 */
public class PedidoBusquedaController {
    
    PedidoBusquedaPizzaView pedidobusqpizzaView;
    PedidoController pedidoController;
    
    PizzaDAO pizzaDAO; 

    public PedidoBusquedaController(PedidoBusquedaPizzaView pedidobusqpizzaView, PedidoController pedidoController) {
        this.pedidobusqpizzaView = pedidobusqpizzaView;

        this.pedidoController = pedidoController;
        this.pizzaDAO = new PizzaDAO();
        
        // eventos de form
        
        pedidobusqpizzaView.btnBuscarPizza.addActionListener((ae) -> {
            buscar();
        });
        
        pedidobusqpizzaView.btnSeleccionar.addActionListener((ae) -> {
            seleccionar();
        });
        
        pedidobusqpizzaView.btnRegresar.addActionListener((ae) -> {
            regresar();
        });
        
    }
    
    public void mostrar() {
        pedidobusqpizzaView.setVisible(true);
    }
    
    public void buscar() {
        try {
            
            String nombrepizza = pedidobusqpizzaView.txtNombrePizza.getText().trim();
            List<Pizza> lstpizzas = pizzaDAO.getListaPizzasXNombre(nombrepizza);

            DefaultTableModel pizzaviewTblModel = (DefaultTableModel) pedidobusqpizzaView.tblListaPizza.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(pizzaviewTblModel);

            if (lstpizzas.size() > 0) {
                for (Pizza pizza : lstpizzas) {
                    pizzaviewTblModel.addRow(new Object[] {
                        pizza.getIdPizza(),
                        pizza.getNombre(),
                        pizza.getTipoPizza().getNombre(),
                        pizza.getTamanhoPizza().getNombre(),
                        pizza.getTamanhoPizza().getCantidadPorciones(),
                        pizza.getPrecio()
                    });
                }
            }
        
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error buscar pizzas: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void seleccionar() {
        try {
            int selectedRow = pedidobusqpizzaView.tblListaPizza.getSelectedRow();
            if (selectedRow != -1) {
                Pizza pizza = new Pizza(
                    (int) pedidobusqpizzaView.tblListaPizza.getValueAt(selectedRow, 0),
                    pedidobusqpizzaView.tblListaPizza.getValueAt(selectedRow, 1).toString(),
                    (Double) pedidobusqpizzaView.tblListaPizza.getValueAt(selectedRow, 5),
                    new TamanhoPizza(
                        0,
                        pedidobusqpizzaView.tblListaPizza.getValueAt(selectedRow, 3).toString(),
                        (int) pedidobusqpizzaView.tblListaPizza.getValueAt(selectedRow, 4)
                    ),
                    new TipoPizza(
                        0,
                        pedidobusqpizzaView.tblListaPizza.getValueAt(selectedRow, 2).toString(),
                        ""
                    ),
                    new Estado()
                );
                
                pedidoController.setPizzaDetalle(pizza);
                pedidoController.obtenerPizzaDetalle();
                pedidobusqpizzaView.setVisible(false);
            }
            else {
                JOptionPane.showMessageDialog(
                    null,
                    "Debe seleccionar un item",
                    "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error obtener: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void regresar() {
        pedidobusqpizzaView.setVisible(false);
    }
}
