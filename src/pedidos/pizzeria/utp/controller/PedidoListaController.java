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
import pedidos.pizzeria.utils.Helpers;
import pedidos.pizzeria.utp.dao.PedidoDAO;
import pedidos.pizzeria.utp.dao.TipoDocumentoIdentidadDAO;
import pedidos.pizzeria.utp.model.Pedido;
import pedidos.pizzeria.utp.model.TipoDocumentoIdentidad;
import pedidos.pizzeria.utp.view.PedidoListaView;

/**
 *
 * @author wilderlizama
 */
public class PedidoListaController implements BaseControllerInterface {
    
    
    PedidoListaView pedidoListaView;
    
    PedidoController pedidoController;
    
    PedidoDAO pedidoListaDAO;

    public PedidoListaController(PedidoListaView pedidoListaView) {
        this.pedidoListaView = pedidoListaView;
        this.pedidoListaDAO = new PedidoDAO();
        
        // eventos de form
        pedidoListaView.btnBucarCliente.addActionListener((ae) -> {
            buscar();
        });
        
        pedidoListaView.btnVerPedido.addActionListener((ae) -> {
            obtener();
        });
        
        pedidoListaView.btnNuevoPedido.addActionListener((ae) -> {
            nuevo();
        });
        
        limpiarForm();
        initCombo();
        buscar();
    }
    
    private void initCombo() {
        try {
            List<TipoDocumentoIdentidad> tipoDocumentoIdentidadDAO = new TipoDocumentoIdentidadDAO().getListaTipoDocumentoIdentidad();
            
            pedidoListaView.cboTipoDocumento.setModel(new DefaultComboBoxModel());
            
            pedidoListaView.cboTipoDocumento.addItem(new TipoDocumentoIdentidad(0, "Todos", 0)); // para que busque todos
            for (TipoDocumentoIdentidad tipoDocumentoIdentidad : tipoDocumentoIdentidadDAO) {
                pedidoListaView.cboTipoDocumento.addItem(tipoDocumentoIdentidad); // para lista filtro
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
        pedidoListaView.txtDocumento.setText("");
    }

    @Override
    public void buscar() {
        try {
            
            TipoDocumentoIdentidad tipoDocIdentidad_selected = (TipoDocumentoIdentidad) pedidoListaView.cboTipoDocumento.getSelectedItem();
            int idtipoDocIdentidad = tipoDocIdentidad_selected.getIdTipoDocIdentidad();
            String numeroDocIdentidad = pedidoListaView.txtDocumento.getText().trim();
            
            List<Pedido> lstPedido = pedidoListaDAO.getListaPedido(idtipoDocIdentidad, numeroDocIdentidad);
            
            DefaultTableModel pedidoviewTblModel = (DefaultTableModel) pedidoListaView.tblListaPedido.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(pedidoviewTblModel);

            if (lstPedido.size() > 0) {
                for (Pedido pedido : lstPedido) {
                    pedidoviewTblModel.addRow(new Object[] {
                        pedido.getIdPedido(),
                        pedido.getNumero(),
                        pedido.getNumeroComprobante(),
                        pedido.getCliente().getNombres(),
                        pedido.getFechaCreacion() + " " + pedido.getHoracreacion(),
                        pedido.getDireccionEnvio(),
                        pedido.getEstado().getNombre()
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
            int selectedRow = pedidoListaView.tblListaPedido.getSelectedRow();
            if (selectedRow != -1) {
                int IdPedido = (int) pedidoListaView.tblListaPedido.getValueAt(selectedRow, 0);
                
                JDesktopPane mainContainer = (JDesktopPane) pedidoListaView.getParent();
                pedidoController = new PedidoController(this, mainContainer);
                
                mainContainer.remove(pedidoListaView);
                mainContainer.updateUI();
                pedidoController.setIdPedido_edit(IdPedido);
                pedidoController.obtener();
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
        JDesktopPane mainContainer = (JDesktopPane) pedidoListaView.getParent();
        pedidoController = new PedidoController(this, mainContainer);
        mainContainer.remove(pedidoListaView);
        mainContainer.updateUI();
        pedidoController.nuevo();
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
