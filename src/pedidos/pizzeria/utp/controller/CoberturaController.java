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
import pedidos.pizzeria.utp.dao.DistritoDAO;
import pedidos.pizzeria.utp.model.Distrito;
import pedidos.pizzeria.utp.view.ListaCoberturaView;

/**
 *
 * @author wilderlizama
 */
public class CoberturaController implements BaseControllerInterface {
    
    String op;
    int IdCobertura_edit;
    
    ListaCoberturaView coberturaView;
    DistritoDAO coberturaDAO;
    
    public CoberturaController(ListaCoberturaView coberturaView) {
        this.coberturaView = coberturaView;
        this.coberturaDAO = new DistritoDAO();
        
        // eventos de form
        coberturaView.btnEditar.addActionListener((ae) -> {
            obtener();
        });
        
        coberturaView.btnGuardar.addActionListener((ae) -> {
            guardar();
        });
        
        limpiarForm();
        buscar();
        this.op = null;
        coberturaView.btnGuardar.setEnabled(false);
        coberturaView.lblOperacion.setText("");
    }

    @Override
    public void limpiarForm() {
        coberturaView.txtNombres.setText("");
        coberturaView.chkCobertura.setSelected(false);
    }

    @Override
    public void buscar() {
        try {
            
            List<Distrito> lstCobertura = coberturaDAO.getListaDistrito();
            
            DefaultTableModel coberturaViewTblModel = (DefaultTableModel) coberturaView.tblListaCobertura.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(coberturaViewTblModel);
            
            if (lstCobertura.size() > 0) {
                for (Distrito cobertura : lstCobertura) {
                    coberturaViewTblModel.addRow(new Object[] {
                        cobertura.getIdDistrito(),
                        cobertura.getNombre(),
                        cobertura.getCobertura() ? "SI" : "NO"
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
            int selectedRow = coberturaView.tblListaCobertura.getSelectedRow();
            if (selectedRow != -1) {
                int IdDistrito = (int) coberturaView.tblListaCobertura.getValueAt(selectedRow, 0);
                Distrito cobertura = coberturaDAO.getDistrito(IdDistrito);
                
                IdCobertura_edit = cobertura.getIdDistrito();
                coberturaView.txtNombres.setText(cobertura.getNombre());
                coberturaView.chkCobertura.setSelected(cobertura.getCobertura());
                
                this.op = Constants.OP_EDIT;
                coberturaView.btnGuardar.setEnabled(true);
                coberturaView.lblOperacion.setText("( EDITAR )");
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
    public void nuevo() { // no hay
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertar() { // no hay
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar() {
        try {
            Distrito distrito = new Distrito(
                IdCobertura_edit,
                coberturaView.txtNombres.getText(),
                coberturaView.chkCobertura.isSelected()
            );
            
            coberturaDAO.modificarDistrito(distrito);
            
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
        // ...
        
        if (this.op.equals(Constants.OP_EDIT))
            modificar();
        
        limpiarForm();
        this.op = null;
        coberturaView.lblOperacion.setText("");
        coberturaView.btnGuardar.setEnabled(false);
        
        buscar();
    }
    
}
