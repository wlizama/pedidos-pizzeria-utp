/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pedidos.pizzeria.utils.Constants;
import pedidos.pizzeria.utils.Helpers;
import pedidos.pizzeria.utp.dao.TipoPizzaDAO;
import pedidos.pizzeria.utp.model.TipoPizza;
import pedidos.pizzeria.utp.view.ListaPizzasView;

/**
 *
 * @author wilderlizama
 */
public class TipoPizzaController implements BaseControllerInterface {
    
    String op;
    int IdTipoPizza_edit;
    
    ListaPizzasView pizzasView;
    TipoPizzaDAO tipopizzasDAO;
    
    public TipoPizzaController(ListaPizzasView pizzasView) {
        this.pizzasView = pizzasView;
        this.tipopizzasDAO = new TipoPizzaDAO();
        
        // eventos de form
        pizzasView.btnEditarTipo.addActionListener((ae) -> {
            obtener();
        });
        
        pizzasView.btnNuevoTipo.addActionListener((ae) -> {
            nuevo();
        });
        
        pizzasView.btnGuardarTipoPizza.addActionListener((ae) -> {
            guardar();
        });
        
        limpiarForm();
        buscar();
        this.op = Constants.OP_NEW;
        pizzasView.lblOpTipoPizza.setText("( NUEVO )");
    }

    @Override
    public void limpiarForm() {
        pizzasView.txtNombresTipoPizza.setText("");
        pizzasView.txaDescripcion.setText("");
    }

    @Override
    public void buscar() {
        try {
            
            List<TipoPizza> lstTipoPizza = tipopizzasDAO.getListaTipoPizza();
            
            DefaultTableModel pizzasViewTblModel = (DefaultTableModel) pizzasView.tblListaTipo.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(pizzasViewTblModel);
            
            if (lstTipoPizza.size() > 0) {
                for (TipoPizza tipoPizza : lstTipoPizza) {
                    pizzasViewTblModel.addRow(new Object[] {
                        tipoPizza.getIdTipoPizza(),
                        tipoPizza.getNombre()
                    });
                }
            }
        } catch (Exception e) {
             e.getStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error buscar xxxx: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void obtener() {
        try {
            int selectedRow = pizzasView.tblListaTipo.getSelectedRow();
            if (selectedRow != -1) {
                int IdTipoPizza = (int) pizzasView.tblListaTipo.getValueAt(selectedRow, 0);
                TipoPizza tipopizza = tipopizzasDAO.getTipoPizza(IdTipoPizza);
                
                IdTipoPizza_edit = tipopizza.getIdTipoPizza();
                pizzasView.txtNombresTipoPizza.setText(tipopizza.getNombre());
                pizzasView.txaDescripcion.setText(tipopizza.getDescripcion());
                
                this.op = Constants.OP_EDIT;
                pizzasView.lblOpTipoPizza.setText("( EDITAR )");
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

    @Override
    public void nuevo() {
        try {
            limpiarForm();
            this.op = Constants.OP_NEW;
            pizzasView.lblOpTipoPizza.setText("( NUEVO )");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error nuevo: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void insertar() {
        try {
            TipoPizza tipopizza = new TipoPizza(
                0,
                pizzasView.txtNombresTipoPizza.getText(),
                pizzasView.txaDescripcion.getText()
            );
            
            int IdTipoPizza = tipopizzasDAO.insertarTipoPizza(tipopizza);
            tipopizza.setIdTipoPizza(IdTipoPizza);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error insertar: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void modificar() {
        try {
            TipoPizza tipopizza = new TipoPizza(
                IdTipoPizza_edit,
                pizzasView.txtNombresTipoPizza.getText(),
                pizzasView.txaDescripcion.getText()
            );
            
            tipopizzasDAO.modificarTipoPizza(tipopizza);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error modificar: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void guardar() {
         
        // validaciones
        String msgValidacion = "";
        if (pizzasView.txtNombresTipoPizza.getText().trim().equals(""))
            msgValidacion += "- Ingresar nombre de Tipo Pizza.\n";
        if (pizzasView.txaDescripcion.getText().trim().equals(""))
            msgValidacion += "- Ingresar descripción de Tipo Pizza.\n";
        
        if (!msgValidacion.equals("")) {
            JOptionPane.showMessageDialog(
                null,
                "Corregir los siguiente:\n" + msgValidacion,
                "Mensaje",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        if (this.op.equals(Constants.OP_NEW))
            insertar();
        else if (this.op.equals(Constants.OP_EDIT))
            modificar();
        
        limpiarForm();
        this.op = Constants.OP_NEW;
        pizzasView.lblOpTipoPizza.setText("( NUEVO )");
        
        buscar();
    }
}
