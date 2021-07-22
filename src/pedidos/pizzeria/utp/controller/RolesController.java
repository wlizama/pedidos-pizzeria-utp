/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
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
    String ids_selecteds = "";
    
    ListaPersonalView personalView;
    RolesDAO rolesDAO;
    AccesoDAO accesoDAO;

    public RolesController(ListaPersonalView personalView) {
        this.personalView = personalView;
        this.rolesDAO = new RolesDAO();
        this.accesoDAO = new AccesoDAO();
        
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
        
        initChks();
        limpiarForm();
        buscar();
        this.op = Constants.OP_NEW;
        personalView.lblOpRolPersona.setText("( NUEVO )");
    }
    
    public void initChks() {
        try {
            List<Formulario> lstFormulario = new FormularioDAO().getListaFormulario();
            for (Formulario formulario : lstFormulario) {
                JCheckBox chkformulario = new JCheckBox(formulario.toString());
                chkformulario.setName(String.valueOf(formulario.getIdFormulario()));
                personalView.pnFormularios.add(chkformulario);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error buscar: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void limpiarForm() {
        personalView.txtNombreRol.setText("");
        
        Component lstFormularios[] = personalView.pnFormularios.getComponents();
        for (Component lstFormulario : lstFormularios) {
            ((JCheckBox)lstFormulario).setSelected(false);
        }
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
                
                limpiarForm();
                int IdRol = (int) personalView.tblListaRoles.getValueAt(selectedRow, 0);
                Roles roles = rolesDAO.getRol(IdRol);                
                List<Acceso> lstaccesos = accesoDAO.getListaAccesoRol(IdRol);
               
                personalView.txtNombreRol.setText(roles.getNombre());
                Component lstFormularios[] = personalView.pnFormularios.getComponents();
                for (Acceso lstacceso : lstaccesos) {
                    for (Component lstFormulario : lstFormularios) {
                        if (String.valueOf(lstacceso.getFormulario().getIdFormulario()).equals(lstFormulario.getName()))
                            ((JCheckBox)lstFormulario).setSelected(true);
                    }
                }
                
                IdRol_edit = IdRol;
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
            IdRol_edit = 0;
            ids_selecteds = "";
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
            Roles rol = new Roles(
                0,
                personalView.txtNombreRol.getText()
            );
            
            int IdRol = rolesDAO.insertarRoles(rol);
            rol.setIdRol(IdRol);
            rolesDAO.eliminaInsertarAccesosRoles(IdRol, ids_selecteds);
            
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
            rolesDAO.eliminaInsertarAccesosRoles(IdRol_edit, ids_selecteds);
            
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
        
        Component lstFormularios[] = personalView.pnFormularios.getComponents();
        List <String> lstids = new ArrayList<String>();
        for (Component lstFormulario : lstFormularios) {
            if(((JCheckBox)lstFormulario).isSelected())
                lstids.add(((JCheckBox)lstFormulario).getName());
        }
        ids_selecteds = String.join(",", lstids);
        
        if (ids_selecteds.equals(""))
            msgValidacion += "- Debe seleccionar almenos un formulario.\n";
        
        
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

