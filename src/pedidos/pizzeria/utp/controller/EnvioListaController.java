/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pedidos.pizzeria.utils.Constants;
import pedidos.pizzeria.utils.Helpers;
import pedidos.pizzeria.utp.dao.EnvioDAO;
import pedidos.pizzeria.utp.dao.EstadoDAO;
import pedidos.pizzeria.utp.model.Envio;
import pedidos.pizzeria.utp.model.Estado;
import pedidos.pizzeria.utp.view.EnvioListaView;

/**
 *
 * @author wilderlizama
 */
public class EnvioListaController implements BaseControllerInterface {

    EnvioListaView envioListaView;
    
    EnvioController envioController;
    
    EnvioDAO envioDAO;
    
    public EnvioListaController(EnvioListaView envioListaView) {
        this.envioListaView = envioListaView;
        this.envioDAO = new EnvioDAO();
        
        // eventos de form
        envioListaView.cboEstado.addItemListener((ie) -> {
            buscar();
        });
        
        envioListaView.btnVerEnvio.addActionListener((ae) -> {
            obtener();
        });
        
        envioListaView.btnNuevoEnvio.addActionListener((ae) -> {
            nuevo();
        });
        
        
        limpiarForm();
        initCombo();
        buscar();
    }
    
    private void initCombo() {
        try {
            
            List<Estado> lstEstado = new EstadoDAO().getListaEstado(Constants.ID_TESTADO_ENVIO);
            
            envioListaView.cboEstado.setModel(new DefaultComboBoxModel());
            
            envioListaView.cboEstado.addItem(new Estado(0, "Todos")); // para que busque todos
            for (Estado estado : lstEstado) {
                envioListaView.cboEstado.addItem(estado); // para lista filtro
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Error initCombos: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void limpiarForm() {
        // F no hay nada que limpiar
    }

    @Override
    public void buscar() {
        try {
            
            Estado estado_selected = (Estado) envioListaView.cboEstado.getSelectedItem();
            int idEstado = estado_selected.getIdEstado();
           
            
            List<Envio> lstEnvio = envioDAO.getListaEnvio(idEstado);
            
            DefaultTableModel envioviewTblModel = (DefaultTableModel) envioListaView.tblListaEnvios.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(envioviewTblModel);

            if (lstEnvio.size() > 0) {
                for (Envio envio : lstEnvio) {
                    envioviewTblModel.addRow(new Object[] {
                        envio.getIdEnvio(),
                        envio.getNumero(),
                        envio.getHoraInicio(),
                        envio.getHoraFin(),
                        envio.getEstado().getNombre()
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
            int selectedRow = envioListaView.tblListaEnvios.getSelectedRow();
            if (selectedRow != -1) {
                int IdEenvio = (int) envioListaView.tblListaEnvios.getValueAt(selectedRow, 0);
                
                JDesktopPane mainContainer = (JDesktopPane) envioListaView.getParent();
                envioController = new EnvioController(this, mainContainer);
                mainContainer.remove(envioListaView);
                mainContainer.updateUI();
                envioController.setIdEnvio_edit(IdEenvio);
                envioController.obtener();
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
        JDesktopPane mainContainer = (JDesktopPane) envioListaView.getParent();
        envioController = new EnvioController(this, mainContainer);
        mainContainer.remove(envioListaView);
        mainContainer.updateUI();
        envioController.nuevo();
    }

    @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
