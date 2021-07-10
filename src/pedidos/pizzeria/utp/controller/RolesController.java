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
import pedidos.pizzeria.utp.dao.*;
import pedidos.pizzeria.utp.model.*;
import pedidos.pizzeria.utp.view.*;

/**
 *
 * @author jonas
 */
public class RolesController implements BaseControllerInterface{
    
    String op;
    int IdRol_edit;
    
    ListaPersonalView personalView;
    RolesDAO rolesDAO;

    public RolesController(ListaPersonalView personalView) {
        this.personalView = personalView;
        this.rolesDAO = new RolesDAO();
        
        // eventos de form
        personalView.btnEditarRol.addActionListener((ae) -> {
            obtener();
        });
        
        personalView.btnNuevoRol.addActionListener((ae) -> {
            nuevo();
        });
        
        personalView.btnGuardarRol.addActionListener((ae) -> {
            guardar();
        });
        
        limpiarForm();
        buscar();
        this.op = Constants.OP_NEW;
        personalView.lblOpRolPersona.setText("( NUEVO )");
    }

    @Override
    public void limpiarForm() {
        personalView.txtNombreRol.setText("");
        personalView.chkDelivery.setSelected(false);
        personalView.chkMantenimiento.setSelected(false);
        personalView.chkMiSesion.setSelected(false);
        personalView.chkPedidos.setSelected(false);
        personalView.chkReportes.setSelected(false);
    }

    @Override
    public void buscar() {
        
        try {
            
            List<Roles> lstRoles = rolesDAO.getListaRoles();
            
            DefaultTableModel pizzasViewTblModel = (DefaultTableModel) personalView.tblListaRoles.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(pizzasViewTblModel);
            

            if (lstRoles.size() > 0) {
                for (Roles roles : lstRoles) {
                    pizzasViewTblModel.addRow(new Object[] {
                        roles.getIdRol(),
                        roles.getNombre()
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
            int selectedRow = personalView.tblListaRoles.getSelectedRow();            
            if (selectedRow != -1) {
                int IdRol = (int) personalView.tblListaRoles.getValueAt(selectedRow, 0);
                Roles roles = rolesDAO.getRol(IdRol);
                
                IdRol_edit = roles.getIdRol();
                personalView.txtNombreRol.setText(roles.getNombre());
                
                this.op = Constants.OP_EDIT;
                personalView.lblOpRolPersona.setText("( EDITAR )");
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
            personalView.lblOpRolPersona.setText("( NUEVO )");
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
            Roles roles = new Roles(
                0,
                personalView.txtNombreRol.getText()
            );
            
            int IdRol = rolesDAO.insertarRoles(roles);
            roles.setIdRol(IdRol);
            
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
            Roles roles = new Roles(
                IdRol_edit,
                personalView.txtNombreRol.getText()
            );
            
            rolesDAO.modificarRoles(roles);
            
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
        if (personalView.txtNombreRol.getText().trim().equals(""))
            msgValidacion += "- Ingresar nombre del Rol.\n";
        
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
        personalView.lblOpRolPersona.setText("( NUEVO )");
        
        buscar();
    }
    
}
