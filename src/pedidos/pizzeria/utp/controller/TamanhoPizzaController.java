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
import pedidos.pizzeria.utp.model.TamanhoPizza;
import pedidos.pizzeria.utp.view.ListaPizzasView;
import pedidos.pizzeria.utp.dao.TamanhoPizzaDAO;

/**
 *
 * @author Percy
 */
public class TamanhoPizzaController implements BaseControllerInterface {
    
    String op;
    int IdTamanhoPizza_edit;
    
    ListaPizzasView pizzasView;
    TamanhoPizzaDAO tamanhopizzaDAO;
    
    public TamanhoPizzaController(ListaPizzasView pizzasView) {
        this.pizzasView = pizzasView;
        //this.tipopizzasDAO = new TipoPizzaDAO();
        this.tamanhopizzaDAO = new TamanhoPizzaDAO();
        
        // eventos de form
        pizzasView.btnEditarTamano.addActionListener((ae) -> {
            obtener();
        });
        
        pizzasView.btnNuevoTamano.addActionListener((ae) -> {
            nuevo();
        });
        
        pizzasView.btnGuardarTamPizza.addActionListener((ae) -> {
            guardar();
        });
        
        limpiarForm();
        buscar();
        this.op = Constants.OP_NEW;
        pizzasView.lblOpTamanoPizza.setText("( NUEVO )");
    }

    @Override
    public void limpiarForm() {
        pizzasView.txtNombresTamPizza.setText("");
        pizzasView.txtCanPorciones.setText("");
    }

    @Override
    public void buscar() {
        try {
            
            List<TamanhoPizza> lstTamanhoPizza = tamanhopizzaDAO.getListaTamanhoPizza();
            
            DefaultTableModel pizzasViewTblModel = (DefaultTableModel) pizzasView.tblListaTamanos.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(pizzasViewTblModel);
            
            if (lstTamanhoPizza.size() > 0) {
                for (TamanhoPizza tamanhoPizza : lstTamanhoPizza) {
                    pizzasViewTblModel.addRow(new Object[] {
                        tamanhoPizza.getIdTamanhoPizza(),
                        tamanhoPizza.getNombre(),
                        tamanhoPizza.getCantidadPorciones()                            
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Error buscar: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void obtener() {
        try {
            int selectedRow = pizzasView.tblListaTamanos.getSelectedRow();
            if (selectedRow != -1) {
                int IdTamanhoPizza = (int) pizzasView.tblListaTamanos.getValueAt(selectedRow, 0);
                TamanhoPizza tamanhopizza = tamanhopizzaDAO.getTamanhoPizza(IdTamanhoPizza);             
                
                IdTamanhoPizza_edit = tamanhopizza.getIdTamanhoPizza();
                pizzasView.txtNombresTamPizza.setText(tamanhopizza.getNombre());
                pizzasView.txtCanPorciones.setText(Integer.toString(tamanhopizza.getCantidadPorciones()));
                
                this.op = Constants.OP_EDIT;
                pizzasView.lblOpTamanoPizza.setText("( EDITAR )");
            }
            else {
                JOptionPane.showMessageDialog(
                    null, "Debe seleccionar un item", "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null, "Error obtener: " + e.getMessage(), "Excepción",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void nuevo() {
        try {
            limpiarForm();
            this.op = Constants.OP_NEW;
            pizzasView.lblOpTamanoPizza.setText("( NUEVO )");
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
            TamanhoPizza tamanhopizza = new TamanhoPizza(
                0,
                pizzasView.txtNombresTamPizza.getText(),
                Integer.parseInt(pizzasView.txtCanPorciones.getText())
            );
            
            int IdTamanhoPizza = tamanhopizzaDAO.insertarTamanhoPizza(tamanhopizza);
            tamanhopizza.setIdTamanhoPizza(IdTamanhoPizza);
            
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
            TamanhoPizza tamanhopizza = new TamanhoPizza(
                IdTamanhoPizza_edit,
                pizzasView.txtNombresTamPizza.getText(),
                Integer.parseInt(pizzasView.txtCanPorciones.getText())
            );
            
            tamanhopizzaDAO.modificarTamanhoPizza(tamanhopizza);
            
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
        if (pizzasView.txtNombresTamPizza.getText().trim().equals(""))
            msgValidacion += "- Ingresar nombre de Tamaño Pizza.\n";
        if (pizzasView.txtCanPorciones.getText().trim().equals(""))
            msgValidacion += "- Ingresar cantidad de Porciones Pizza.\n";
        
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
        pizzasView.lblOpTamanoPizza.setText("( NUEVO )");
        
        buscar();
    }
    
}
