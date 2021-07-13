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
import pedidos.pizzeria.utils.Constants;
import pedidos.pizzeria.utils.Helpers;
import pedidos.pizzeria.utp.dao.ClienteDAO;
import pedidos.pizzeria.utp.dao.TipoDocumentoIdentidadDAO;
import pedidos.pizzeria.utp.model.Cliente;
import pedidos.pizzeria.utp.model.DocumentoIdentidad;
import pedidos.pizzeria.utp.model.TipoDocumentoIdentidad;
import pedidos.pizzeria.utp.view.DireccionClienteView;
import pedidos.pizzeria.utp.view.ListaClientesView;

/**
 *
 * @author wilderlizama
 */
public class ClienteController implements BaseControllerInterface {
    
    String op;
    int IdCliente_edit;
    
    ListaClientesView clienteView;
    DireccionClienteView direccionClienteView;
    
    DireccionClienteController direccionClienteController;
    
    ClienteDAO clienteDAO;

    public ClienteController(ListaClientesView clienteView) {
        this.clienteView = clienteView;
        this.direccionClienteView = new DireccionClienteView(null, true);
        
        this.direccionClienteController = new DireccionClienteController(direccionClienteView, this);
        
        this.clienteDAO = new ClienteDAO();
        
        // eventos de form
        clienteView.btnBuscar.addActionListener((ae) -> {
            buscar();
        });
        
        clienteView.btnEditar.addActionListener((ae) -> {
            obtener();
        });
        
        clienteView.btnNuevo.addActionListener((ae) -> {
            nuevo();
        });
        
        clienteView.btnGuardar.addActionListener((ae) -> {
            guardar();
        });
        
        // direcciones
        clienteView.btnEditarDireccion.addActionListener((ae) -> {
            obtenerDireccion();
        });
        
        clienteView.btnAgregarDireccion.addActionListener((ae) -> {
            nuevoDireccionCliente();
        });
        
        limpiarForm();
        initCombos();
        buscar();
        this.op = Constants.OP_NEW;
        clienteView.btnEditarDireccion.setEnabled(false);
        clienteView.btnAgregarDireccion.setEnabled(false);
        clienteView.lblOpCliente.setText("( NUEVO )");
    }

    public ListaClientesView getClienteView() {
        return clienteView;
    }
    
    private void initCombos() {
        try {
            List<TipoDocumentoIdentidad> tipoDocumentoIdentidadDAO = new TipoDocumentoIdentidadDAO().getListaTipoDocumentoIdentidad();
            
            clienteView.cbxTDocClienteFiltro.setModel(new DefaultComboBoxModel());
            clienteView.cbxTDocCliente.setModel(new DefaultComboBoxModel());
            
            clienteView.cbxTDocClienteFiltro.addItem(new TipoDocumentoIdentidad(0, "Todos", 0)); // para que busque todos
            for (TipoDocumentoIdentidad tipoDocumentoIdentidad : tipoDocumentoIdentidadDAO) {
                clienteView.cbxTDocClienteFiltro.addItem(tipoDocumentoIdentidad); // para lista filtro
                clienteView.cbxTDocCliente.addItem(tipoDocumentoIdentidad); // para lista filtro
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
        clienteView.txtDocumentoFiltro.setText("");
        clienteView.txtNombres.setText("");
        clienteView.txtApellidos.setText("");
        clienteView.txtTelefono.setText("");
        clienteView.txtDocumento.setText("");
        Helpers.clearTable((DefaultTableModel) clienteView.tblClientesDirecciones.getModel());
    }

    @Override
    public void buscar() {
        try {
            
            TipoDocumentoIdentidad tipoDocIdentidad_selected = (TipoDocumentoIdentidad) clienteView.cbxTDocClienteFiltro.getSelectedItem();
            int idtipoDocIdentidad = tipoDocIdentidad_selected.getIdTipoDocIdentidad();
            String numeroDocIdentidad = clienteView.txtDocumentoFiltro.getText().trim();
            
            List<Cliente> lstCliente = clienteDAO.getListaCliente(idtipoDocIdentidad, numeroDocIdentidad);
            
            DefaultTableModel clienteviewTblModel = (DefaultTableModel) clienteView.tblClientes.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(clienteviewTblModel);

            if (lstCliente.size() > 0) {
                for (Cliente cliente : lstCliente) {
                    clienteviewTblModel.addRow(new Object[] {
                        cliente.getIdCliente(),
                        cliente.getDocumentoIdentidad().getTipoDocumentoIdentidad().getNombre(),
                        cliente.getDocumentoIdentidad().getNumero(),
                        cliente.getNombres() + " " + cliente.getApellidos(),
                        cliente.getTelefono(),
                        cliente.getEstado().getNombre()
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
            int selectedRow = clienteView.tblClientes.getSelectedRow();
            if (selectedRow != -1) {
                int IdCliente = (int) clienteView.tblClientes.getValueAt(selectedRow, 0);
                Cliente cliente = clienteDAO.getCliente(IdCliente);
                
                IdCliente_edit = cliente.getIdCliente();
                clienteView.txtNombres.setText(cliente.getNombres());
                clienteView.txtApellidos.setText(cliente.getApellidos());
                clienteView.txtTelefono.setText(cliente.getTelefono());
                clienteView.cbxTDocCliente.getModel().setSelectedItem(cliente.getDocumentoIdentidad().getTipoDocumentoIdentidad());
                clienteView.txtDocumento.setText(cliente.getDocumentoIdentidad().getNumero());
                
                // obtener lista de direcciones
                direccionClienteController.setIdCliente_edit(IdCliente_edit);
                direccionClienteController.buscar();
                
                
                this.op = Constants.OP_EDIT;
                clienteView.btnEditarDireccion.setEnabled(true);
                clienteView.btnAgregarDireccion.setEnabled(true);
                clienteView.lblOpCliente.setText("( EDITAR )");
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
    
    public void obtenerDireccion() {
        try {
            int selectedRow = clienteView.tblClientesDirecciones.getSelectedRow();
            if (selectedRow != -1) {
                int IdDireccion = (int) clienteView.tblClientesDirecciones.getValueAt(selectedRow, 0);
                direccionClienteController.setIdCliente_edit(IdCliente_edit);
                direccionClienteController.setIdDireccionCliente_edit(IdDireccion);
                direccionClienteController.obtener();
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
                "Error obtener direccion: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void nuevo() {
        try {
            limpiarForm();
            IdCliente_edit = 0;
            this.op = Constants.OP_NEW;
            clienteView.btnEditarDireccion.setEnabled(false);
            clienteView.btnAgregarDireccion.setEnabled(false);
            clienteView.lblOpCliente.setText("( NUEVO )");
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
    
    public void nuevoDireccionCliente() {
        try {
            direccionClienteController.setIdCliente_edit(IdCliente_edit);
            direccionClienteController.nuevo();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error nuevoDireccionCliente: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void insertar() {
        try {
            Cliente cliente = new Cliente();
            cliente.setNombres(clienteView.txtNombres.getText());
            cliente.setApellidos(clienteView.txtApellidos.getText());
            cliente.setTelefono(clienteView.txtTelefono.getText());
            cliente.setDocumentoIdentidad(new DocumentoIdentidad(
                    0,
                    clienteView.txtDocumento.getText(),
                    (TipoDocumentoIdentidad) clienteView.cbxTDocCliente.getSelectedItem()
                )
            );
            int IdCliente = clienteDAO.insertarCliente(cliente);
            cliente.setIdCliente(IdCliente);
            IdCliente_edit = IdCliente;
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
            Cliente cliente = new Cliente();
            cliente.setIdCliente(IdCliente_edit);
            cliente.setNombres(clienteView.txtNombres.getText());
            cliente.setApellidos(clienteView.txtApellidos.getText());
            cliente.setTelefono(clienteView.txtTelefono.getText());
            cliente.setDocumentoIdentidad(new DocumentoIdentidad(
                    0,
                    clienteView.txtDocumento.getText(),
                    (TipoDocumentoIdentidad) clienteView.cbxTDocCliente.getSelectedItem()
                )
            );
            
            clienteDAO.modificarCliente(cliente);
            
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
        if (clienteView.txtNombres.getText().trim().equals(""))
            msgValidacion += "- Ingresar nombre de cliente.\n";
        if (clienteView.txtApellidos.getText().trim().equals(""))
            msgValidacion += "- Ingresar apellidos de cliente.\n";
        if (clienteView.txtTelefono.getText().trim().equals(""))
            msgValidacion += "- Ingresar teléfono de cliente.\n";
        if (clienteView.txtDocumento.getText().trim().equals(""))
            msgValidacion += "- Ingresar Nro. documento de cliente.\n";
        
        
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
        
        this.op = Constants.OP_EDIT;
        clienteView.btnEditarDireccion.setEnabled(true);
        clienteView.btnAgregarDireccion.setEnabled(true);
        clienteView.lblOpCliente.setText("( EDITAR )");
        
        buscar();
    }
    
}
