/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pedidos.pizzeria.utp.dao.TipoPizzaDAO;
import pedidos.pizzeria.utp.model.TipoPizza;
import pedidos.pizzeria.utp.view.ListaPizzasView;

/**
 *
 * @author wilderlizama
 */
public class TipoPizzaController {
    
    String op;
    int IdTipoPizza_edit;
    
    ListaPizzasView pizzasView;
    TipoPizzaDAO tpDAO = new TipoPizzaDAO();
    

    public TipoPizzaController(ListaPizzasView pizzasView) {
        this.pizzasView = pizzasView;
        
        // eventos de form
        pizzasView.btnEditarTipo.addActionListener((ae) -> {
            // obtener modelo
            getTipoTipizza();
        });
        
        pizzasView.btnNuevoTipo.addActionListener((ae) -> {
            // obtener modelo
            nuevoTipoPizza();
        });
        
        pizzasView.btnGuardarTipoPizza.addActionListener((ae) -> {
            guardarTipoPizza();
            buscarTipoPizzaLista();
        });
        
        
        limpiarForm();
        buscarTipoPizzaLista();
        this.op = "new";
        pizzasView.lblOperacion.setText("( Nuevo )");
    }
    
    public void limpiarForm() {
        pizzasView.txtNombresTipoPizza.setText("");
        pizzasView.txaDescripcion.setText("");
    }
    
    public void buscarTipoPizzaLista() {
        try {
            
            List<TipoPizza> lstTipoPizza = tpDAO.getListaTipoPizza();
            
            DefaultTableModel pizzasViewTblModel = (DefaultTableModel) pizzasView.tblListaTipo.getModel();
            // limpiar tabla antes de agregar
            for (int i = 0; i < pizzasViewTblModel.getRowCount(); i++) {
                pizzasViewTblModel.removeRow(i);
                i = i - 1;
            }

            if (lstTipoPizza.size() > 0) {
                System.out.println("llenado tabla");
                for (TipoPizza tipoPizza : lstTipoPizza) {
                    pizzasViewTblModel.addRow(new Object[] {
                        tipoPizza.getIdTipoPizza(),
                        tipoPizza.getNombre()
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Error buscarTipoPizzaLista: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void nuevoTipoPizza() {
        
        try {
            this.op = "new";
            limpiarForm();
            pizzasView.lblOperacion.setText("( NUEVO )");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error insertarTipoPizza: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void insertarTipoPizza() {
        
        try {
            TipoPizza tipopizza = new TipoPizza(
                0,
                pizzasView.txtNombresTipoPizza.getText(),
                pizzasView.txaDescripcion.getText()
            );
            
            int IdTipoPizza = tpDAO.insertarTipoPizza(tipopizza);
            tipopizza.setIdTipoPizza(IdTipoPizza);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error insertarTipoPizza: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void modificarTipoPizza() {
        
        try {
            TipoPizza tipopizza = new TipoPizza(
                IdTipoPizza_edit,
                pizzasView.txtNombresTipoPizza.getText(),
                pizzasView.txaDescripcion.getText()
            );
            
            tpDAO.modificarTipoPizza(tipopizza);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error modificarTipoPizza: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void guardarTipoPizza() {
        
        // validaciones
        
        if (this.op.equals("new"))
            insertarTipoPizza();
        else if (this.op.equals("edit"))
            modificarTipoPizza();
        
        limpiarForm();
        this.op = "new";
        pizzasView.lblOperacion.setText("( NUEVO )");
    }
    
    
    public void getTipoTipizza() {
        try {
            int selectedRow = pizzasView.tblListaTipo.getSelectedRow();
            if (selectedRow != -1) {
                int IdTipoPizza = (int) pizzasView.tblListaTipo.getValueAt(selectedRow, 0);
                TipoPizza tipopizza = tpDAO.getTipoPizza(IdTipoPizza);
                
                IdTipoPizza_edit = tipopizza.getIdTipoPizza();
                pizzasView.txtNombresTipoPizza.setText(tipopizza.getNombre());
                pizzasView.txaDescripcion.setText(tipopizza.getDescripcion());
                this.op = "edit";
                pizzasView.lblOperacion.setText("( EDITAR )");
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
                "Error getTipoTipizza: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }

    }
}
