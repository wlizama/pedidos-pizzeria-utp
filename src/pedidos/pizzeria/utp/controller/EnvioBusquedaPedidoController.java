/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pedidos.pizzeria.utils.Helpers;
import pedidos.pizzeria.utp.dao.EnvioDAO;
import pedidos.pizzeria.utp.dao.PedidoDAO;
import pedidos.pizzeria.utp.dao.TipoDocumentoIdentidadDAO;
import pedidos.pizzeria.utp.model.Cliente;
import pedidos.pizzeria.utp.model.Direccion;
import pedidos.pizzeria.utp.model.Pedido;
import pedidos.pizzeria.utp.model.TipoDocumentoIdentidad;
import pedidos.pizzeria.utp.view.EnvioBusquedaPedidoView;
/**
 *
 * @author wilderlizama
 */
public class EnvioBusquedaPedidoController {
    EnvioBusquedaPedidoView enviobusqpedidoView;
    EnvioController envioController;
    
    EnvioDAO envioDAO; 
    
    public EnvioBusquedaPedidoController(EnvioBusquedaPedidoView enviobusqpedidoView, EnvioController envioController) {
        this.enviobusqpedidoView = enviobusqpedidoView;

        this.envioController = envioController;
        this.envioDAO = new EnvioDAO();
        
        // eventos de form
        
        enviobusqpedidoView.btnBuscar.addActionListener((ae) -> {
            buscar();
        });
        
        enviobusqpedidoView.btnSeleccionar.addActionListener((ae) -> {
            seleccionar();
        });
        
        enviobusqpedidoView.btnCancelar.addActionListener((ae) -> {
            regresar();
        });
        
        initCombo();
        buscar();
    }
    
    private void initCombo() {
        try {
            List<TipoDocumentoIdentidad> tipoDocumentoIdentidadDAO = new TipoDocumentoIdentidadDAO().getListaTipoDocumentoIdentidad();
            
            enviobusqpedidoView.cboTipoDocumento.setModel(new DefaultComboBoxModel());
            
            enviobusqpedidoView.cboTipoDocumento.addItem(new TipoDocumentoIdentidad(0, "Todos", 0)); // para que busque todos
            for (TipoDocumentoIdentidad tipoDocumentoIdentidad : tipoDocumentoIdentidadDAO) {
                enviobusqpedidoView.cboTipoDocumento.addItem(tipoDocumentoIdentidad); // para lista filtro
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
    
    public void mostrar() {
        enviobusqpedidoView.setVisible(true);
    }
    
    public void buscar() {
        try {
            
            TipoDocumentoIdentidad tipoDocIdentidad_selected = (TipoDocumentoIdentidad) enviobusqpedidoView.cboTipoDocumento.getSelectedItem();
            int idtipoDocIdentidad = tipoDocIdentidad_selected.getIdTipoDocIdentidad();
            String numeroDocIdentidad = enviobusqpedidoView.txtNroDocumento.getText().trim();
            
            List<Pedido> lstPedido = new PedidoDAO().getListaPedidoXClienteListoEntrega(idtipoDocIdentidad, numeroDocIdentidad);
            DefaultTableModel enviobusqpedidoviewTblModel = (DefaultTableModel) enviobusqpedidoView.tblEnvioBusqPedido.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(enviobusqpedidoviewTblModel);

            if (lstPedido.size() > 0) {
                for (Pedido pedido : lstPedido) {
                    enviobusqpedidoviewTblModel.addRow(new Object[] {
                        pedido.getIdPedido(),
                        pedido.getNumero(),
                        pedido.getNumeroComprobante(),
                        pedido.getCliente().getNombres(),
                        pedido.getDireccionEnvio().getDireccion()
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
            int selectedRow = enviobusqpedidoView.tblEnvioBusqPedido.getSelectedRow();
            if (selectedRow != -1) {
                Pedido pedido = new Pedido((int) enviobusqpedidoView.tblEnvioBusqPedido.getValueAt(selectedRow, 0));
                pedido.setNumero((int) enviobusqpedidoView.tblEnvioBusqPedido.getValueAt(selectedRow, 1));
                Cliente cliente = new Cliente();
                cliente.setNombres(enviobusqpedidoView.tblEnvioBusqPedido.getValueAt(selectedRow, 3).toString());
                pedido.setCliente(cliente);
                Direccion direccion = new Direccion();
                direccion.setDireccion(enviobusqpedidoView.tblEnvioBusqPedido.getValueAt(selectedRow, 4).toString());
                pedido.setDireccionEnvio(direccion);
                
                envioController.setPedidoDetalle(pedido);
                envioController.obtenerPedidoDetalle();
                enviobusqpedidoView.setVisible(false);
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
        enviobusqpedidoView.setVisible(false);
    }
}
