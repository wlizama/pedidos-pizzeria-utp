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
import pedidos.pizzeria.utp.dao.TipoPersonaDAO;
import pedidos.pizzeria.utp.model.TipoPersona;
import pedidos.pizzeria.utp.view.ListaPersonalView;

/**
 *
 * @author wilderlizama
 */
public class TipoPersonaController implements BaseControllerInterface {
    
    String op;
    int IdTipoPersona_edit;
    
    ListaPersonalView personalView;
    TipoPersonaDAO tipopersonaDAO;

    public TipoPersonaController(ListaPersonalView personalView) {
        this.personalView = personalView;
        this.tipopersonaDAO = new TipoPersonaDAO();
        
        // eventos de form
        personalView.btnEditarTipo.addActionListener((ae) -> {
            obtener();
        });
        
        personalView.btnNuevoTipo.addActionListener((ae) -> {
            nuevo();
        });
        
        personalView.btnGuardarTipoPersona.addActionListener((ae) -> {
            guardar();
        });
        
        limpiarForm();
        buscar();
        this.op = Constants.OP_NEW;
        personalView.lblOpTipoPersona.setText("( NUEVO )");
    }
    
    @Override
    public void limpiarForm() {
        personalView.txtNombres.setText("");
    }

    @Override
    public void buscar() {
        try {
            
            List<TipoPersona> lstTipoPersona = tipopersonaDAO.getListaTipoPersona();
            
            DefaultTableModel pizzasViewTblModel = (DefaultTableModel) personalView.tblTipoPersonal.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(pizzasViewTblModel);
            

            if (lstTipoPersona.size() > 0) {
                for (TipoPersona tipoPersona : lstTipoPersona) {
                    pizzasViewTblModel.addRow(new Object[] {
                        tipoPersona.getIdTipoPersona(),
                        tipoPersona.getNombre()
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
            int selectedRow = personalView.tblTipoPersonal.getSelectedRow();
            if (selectedRow != -1) {
                int IdTipoPersona = (int) personalView.tblTipoPersonal.getValueAt(selectedRow, 0);
                TipoPersona tipopersona = tipopersonaDAO.getTipoPersona(IdTipoPersona);
                
                IdTipoPersona_edit = tipopersona.getIdTipoPersona();
                personalView.txtNombres.setText(tipopersona.getNombre());
                
                this.op = Constants.OP_EDIT;
                personalView.lblOpTipoPersona.setText("( EDITAR )");
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
            personalView.lblOpTipoPersona.setText("( NUEVO )");
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
            TipoPersona tipopersona = new TipoPersona(
                0,
                personalView.txtNombres.getText()
            );
            
            int IdTipoPersona = tipopersonaDAO.insertarTipoPersona(tipopersona);
            tipopersona.setIdTipoPersona(IdTipoPersona);
            
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
            TipoPersona tipopersona = new TipoPersona(
                IdTipoPersona_edit,
                personalView.txtNombres.getText()
            );
            
            tipopersonaDAO.modificarTipoPersona(tipopersona);
            
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
        if (personalView.txtNombres.getText().trim().equals(""))
            msgValidacion += "- Ingresar nombre de Tipo Personal.\n";
        
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
        personalView.lblOpTipoPersona.setText("( NUEVO )");
        
        buscar();
    }
    
}
