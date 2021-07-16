/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import java.sql.Time;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pedidos.pizzeria.utils.Constants;
import pedidos.pizzeria.utils.Helpers;
import pedidos.pizzeria.utp.dao.ClienteDAO;
import pedidos.pizzeria.utp.dao.ComprobanteDAO;
import pedidos.pizzeria.utp.dao.DireccionClienteDAO;
import pedidos.pizzeria.utp.dao.EstadoDAO;
import pedidos.pizzeria.utp.dao.PedidoDAO;
import pedidos.pizzeria.utp.dao.PedidoDetalleDAO;
import pedidos.pizzeria.utp.dao.TipoComprobanteDAO;
import pedidos.pizzeria.utp.dao.TipoDocumentoIdentidadDAO;
import pedidos.pizzeria.utp.model.Cliente;
import pedidos.pizzeria.utp.model.DetallePedido;
import pedidos.pizzeria.utp.model.Direccion;
import pedidos.pizzeria.utp.model.Estado;
import pedidos.pizzeria.utp.model.Pedido;
import pedidos.pizzeria.utp.model.Pizza;
import pedidos.pizzeria.utp.model.TipoComprobante;
import pedidos.pizzeria.utp.model.TipoDocumentoIdentidad;
import pedidos.pizzeria.utp.view.PedidoBusquedaPizzaView;
import pedidos.pizzeria.utp.view.PedidoListaView;
import pedidos.pizzeria.utp.view.PedidoView;

/**
 *
 * @author wilderlizama
 */
public class PedidoController implements BaseControllerInterface {
    
    String op;
    String op_detalle;
    int IdPedido_edit;
    int IdDetallePedido_edit;
    Pizza pizzaDetalle;
    int IdCliente = 0;

    PedidoView pedidoView;

    PedidoListaController pedidoListaController;
    
    PedidoDAO pedidoDAO;
    PedidoDetalleDAO pedidoDetalleDAO;
    
    JDesktopPane mainContainer;

    public PedidoController(PedidoListaController pedidoListaController, JDesktopPane mainContainer) {
        
        this.pedidoListaController = pedidoListaController;
        this.pedidoDAO = new PedidoDAO();
        this.pedidoDetalleDAO = new PedidoDetalleDAO();
        
        this.mainContainer = mainContainer;
        
        pedidoView = new PedidoView();
        
        // eventos de form
        pedidoView.btnBucarCliente.addActionListener((ae) -> {
            buscar();
        });
        
        pedidoView.btnGuardarPedido.addActionListener((ae) -> {
            guardar();
        });
        
        pedidoView.btnRegresar.addActionListener((ae) -> {
            regresar();
        });
        
        // detalle
        pedidoView.btnModificarDetalle.addActionListener((ae) -> {
            obtenerDetalle();
        });
        
        pedidoView.btnAgregarDetalle.addActionListener((ae) -> {
            nuevoDetalle();
        });
        
        pedidoView.btnEliminarDetalle.addActionListener((ae) -> {
            eliminarDetalle();
        });
        
        pedidoView.btnBuscarPizza.addActionListener((ae) -> {
            mostrarBuscarPizza();
        });
        
        pedidoView.btnGuardarDetalle.addActionListener((ae) -> {
            guardarDetalle();
        });
        

        initCombos();
    }
    
    private boolean isPedidoGenerado() {
        try {
            Pedido pedido = pedidoDAO.getPedido(IdPedido_edit);
            
            return pedido.getEstado().getIdEstado() == Constants.ID_ESTADO_GENERADO;
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Error initCombos: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
            
            return false;
        }
    }
    
    public void regresar() {
        
        JDesktopPane mainContainer = (JDesktopPane) pedidoView.getParent();
        mainContainer.remove(pedidoView);
        
        mainContainer.updateUI();
        
        PedidoListaView pedidoListaView = new PedidoListaView();
        pedidoListaView.pack();
        mainContainer.add(pedidoListaView);
        Helpers.centerForm(mainContainer, pedidoListaView);
        pedidoListaView.setVisible(true);
    }
    
    private void initCombos() {
        try {
            List<TipoDocumentoIdentidad> lsttipoDocumentoIdentidad = new TipoDocumentoIdentidadDAO().getListaTipoDocumentoIdentidad();
            List<TipoComprobante> lsttipoComprobante = new TipoComprobanteDAO().getListaTipoComprobante();
            List<Estado> lstestado = new EstadoDAO().getListaEstado(Constants.ID_TESTADO_PEDIDO);
            
            pedidoView.cboTipoDocumento.setModel(new DefaultComboBoxModel());
            pedidoView.cboTipoComprobante.setModel(new DefaultComboBoxModel());
            pedidoView.cboEstado.setModel(new DefaultComboBoxModel());
                        
            for (TipoDocumentoIdentidad tipoDocumentoIdentidad : lsttipoDocumentoIdentidad) {
                pedidoView.cboTipoDocumento.addItem(tipoDocumentoIdentidad);
            }
            
            for (TipoComprobante tipoComprobante : lsttipoComprobante) {
                pedidoView.cboTipoComprobante.addItem(tipoComprobante);
            }
            
            for (Estado estado : lstestado) {
                pedidoView.cboEstado.addItem(estado);
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

    public void setIdPedido_edit(int IdPedido_edit) {
        this.IdPedido_edit = IdPedido_edit;
    }

    public void setPizzaDetalle(Pizza pizzaDetalle) {
        this.pizzaDetalle = pizzaDetalle;
    }
    
    public void mostrarBuscarPizza() {
        try {
            
            if(isPedidoGenerado()) {
                PedidoBusquedaPizzaView pedidobusqView = new PedidoBusquedaPizzaView(null, true);
                PedidoBusquedaController pedidobusqController = new PedidoBusquedaController(
                    pedidobusqView,
                    this
                );
                Helpers.centerForm(pedidoView, pedidobusqView);
                pedidobusqController.mostrar();
            }
            else {
                JOptionPane.showMessageDialog(
                    null,
                    "El Pedido no se encuentra en estado generado.",
                    "Mensaje",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error nuevo detalle: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    

    @Override
    public void limpiarForm() {
        
        pedidoView.txtNroPedido.setText("");
        pedidoView.txtNroComprobante.setText("");
//        pedidoView.cboTipoDocumento.setSelectedIndex(0);
        pedidoView.txtDocumento.setText("");
        pedidoView.txtNombre.setText("");
//        pedidoView.cboDireccion.setSelectedIndex(0);
        pedidoView.txtTelefonoContacto.setText("");
//        pedidoView.cboTipoComprobante.setSelectedIndex(0);
//        pedidoView.cboEstado.setSelectedIndex(0);
        pedidoView.txaObservacion.setText("");
    }
    
    public void limpiarFormDetalle() {
        pedidoView.txtNombrePizza.setText("");
        pedidoView.txtTipoPizza.setText("");
        pedidoView.txtTamanhoPizza.setText("");
        pedidoView.txtPorcionesPizza.setText("");
        pedidoView.txtPrecioPizza.setText("");
        pedidoView.txtCantidadPizza.setText("");
        pedidoView.lblOpPedidoDetalle.setText("");
    }

    @Override
    public void buscar() {
        try {
            TipoDocumentoIdentidad tipoDocIdentidad_selected = (TipoDocumentoIdentidad) pedidoView.cboTipoDocumento.getSelectedItem();
            int idtipoDocIdentidad = tipoDocIdentidad_selected.getIdTipoDocIdentidad();
            String numeroDocIdentidad = pedidoView.txtDocumento.getText().trim();
            
            Cliente cliente = new ClienteDAO().getClienteXTipoDocNumero(idtipoDocIdentidad, numeroDocIdentidad);
            
            if (!Objects.isNull(cliente)) {
                IdCliente = cliente.getIdCliente();
                pedidoView.txtNombre.setText(cliente.getNombres() + " " + cliente.getApellidos());
                pedidoView.txtTelefonoContacto.setText(cliente.getTelefono());
                
                List<Direccion> lstdireccion = new DireccionClienteDAO().getListaDireccionCliente(IdCliente);
            
                pedidoView.cboDireccion.setModel(new DefaultComboBoxModel());

                for (Direccion direccion : lstdireccion) {
                    pedidoView.cboDireccion.addItem(direccion);
                    if (direccion.getPrincipal())
                        pedidoView.cboDireccion.setSelectedItem(direccion);
                }
            }
            else {
                IdCliente = 0;
                pedidoView.txtNombre.setText("");
                pedidoView.txtTelefonoContacto.setText("");
                pedidoView.cboDireccion.removeAllItems();
                JOptionPane.showMessageDialog(
                    null,
                    "No existe cliente registrado con \"" + tipoDocIdentidad_selected.getNombre() + "\", número: \"" + numeroDocIdentidad + "\"",
                    "Mensaje",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error buscar cliente: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void buscarDetalle() {
        try {
            
            List<DetallePedido> lstDetallePedido = pedidoDetalleDAO.getListaDetallePedido(IdPedido_edit);
            
            DefaultTableModel pedidodetalleviewTblModel = (DefaultTableModel) pedidoView.tblDetallePedido.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(pedidodetalleviewTblModel);

            if (lstDetallePedido.size() > 0) {
                for (DetallePedido detallepedido : lstDetallePedido) {
                    pedidodetalleviewTblModel.addRow(new Object[] {
                        detallepedido.getIdDetallePedido(),
                        detallepedido.getPizza().getNombre(),
                        detallepedido.getPizza().getTamanhoPizza().getNombre(),
                        detallepedido.getCantidad(),
                        detallepedido.getPizza().getPrecio()
                    });
                }
                
                Double totaldetalle = new ComprobanteDAO().getComprobantexPedido(IdPedido_edit).getMonto();
                pedidoView.lblTotalDetallePedido.setText(String.valueOf(totaldetalle));
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
            
            limpiarForm();
            this.op = Constants.OP_EDIT;
            
            Pedido pedido = pedidoDAO.getPedido(IdPedido_edit);
            pedidoView.txtNroPedido.setText(String.valueOf(pedido.getNumero()));
            pedidoView.txtNroComprobante.setText(String.valueOf(pedido.getNumeroComprobante()));
            Cliente cliente = pedido.getCliente();
            IdCliente = cliente.getIdCliente();
            pedidoView.cboTipoDocumento.getModel().setSelectedItem(cliente.getDocumentoIdentidad().getTipoDocumentoIdentidad());
            pedidoView.txtDocumento.setText(cliente.getDocumentoIdentidad().getNumero());
            pedidoView.txtNombre.setText(cliente.getNombres() + " " + pedido.getCliente().getApellidos());
            pedidoView.txtTelefonoContacto.setText(cliente.getTelefono());
            pedidoView.txaObservacion.setText(pedido.getObservaciones());
            // tc blt
            List<Direccion> lstdireccion = new DireccionClienteDAO().getListaDireccionCliente(cliente.getIdCliente());
            pedidoView.cboDireccion.setModel(new DefaultComboBoxModel());
            for (Direccion direccion : lstdireccion) {
                pedidoView.cboDireccion.addItem(direccion);
            }
            pedidoView.cboDireccion.getModel().setSelectedItem(pedido.getDireccionEnvio());
            pedidoView.cboEstado.getModel().setSelectedItem(pedido.getEstado());
            
            pedidoView.btnModificarDetalle.setEnabled(true);
            pedidoView.btnAgregarDetalle.setEnabled(true);
            pedidoView.btnEliminarDetalle.setEnabled(true);
            pedidoView.btnBuscarPizza.setEnabled(false);
            pedidoView.btnGuardarDetalle.setEnabled(false);
            pedidoView.cboEstado.setEnabled(true);
            pedidoView.lblOpPedido.setText("( EDITAR )");
            
            buscarDetalle();
            
            pedidoView.pack();
            mainContainer.add(pedidoView);
            Helpers.centerForm(mainContainer, pedidoView);
            pedidoView.setVisible(true);
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
    
    public void obtenerDetalle() {
        try {
            int selectedRow = pedidoView.tblDetallePedido.getSelectedRow();
            if (selectedRow != -1) {
                int IdDetallePedido = (int) pedidoView.tblDetallePedido.getValueAt(selectedRow, 0);
                
                DetallePedido detallepedido = pedidoDetalleDAO.getDetallePedido(IdDetallePedido);
                Pizza pizza = detallepedido.getPizza();
                setPizzaDetalle(pizza);
                pedidoView.txtNombrePizza.setText(pizza.getNombre());
                pedidoView.txtTipoPizza.setText(pizza.getTipoPizza().getNombre());
                pedidoView.txtTamanhoPizza.setText(pizza.getTamanhoPizza().getNombre());
                pedidoView.txtPorcionesPizza.setText(String.valueOf(pizza.getTamanhoPizza().getCantidadPorciones()));
                pedidoView.txtPrecioPizza.setText(String.valueOf(pizza.getPrecio()));
                pedidoView.txtCantidadPizza.setText(String.valueOf(detallepedido.getCantidad()));
                IdDetallePedido_edit = IdDetallePedido;
                op_detalle = Constants.OP_EDIT;
                pedidoView.lblOpPedidoDetalle.setText("( EDITAR )");
                pedidoView.btnBuscarPizza.setEnabled(true);
                pedidoView.btnGuardarDetalle.setEnabled(true);
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
    
    public void obtenerPizzaDetalle() {
        try {
            pedidoView.txtNombrePizza.setText(pizzaDetalle.getNombre());
            pedidoView.txtTipoPizza.setText(pizzaDetalle.getTipoPizza().getNombre());
            pedidoView.txtTamanhoPizza.setText(pizzaDetalle.getTamanhoPizza().getNombre());
            pedidoView.txtPorcionesPizza.setText(String.valueOf(pizzaDetalle.getTamanhoPizza().getCantidadPorciones()));
            pedidoView.txtPrecioPizza.setText(String.valueOf(pizzaDetalle.getPrecio()));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error obtener Pizza Detalle: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void nuevo() {
        try {
            
            limpiarForm();
            IdPedido_edit = 0;
            IdCliente = 0;
            this.op = Constants.OP_NEW;
            
            pedidoView.btnModificarDetalle.setEnabled(false);
            pedidoView.btnAgregarDetalle.setEnabled(false);
            pedidoView.btnEliminarDetalle.setEnabled(false);
            pedidoView.btnBuscarPizza.setEnabled(false);
            pedidoView.btnGuardarDetalle.setEnabled(false);
            pedidoView.cboEstado.setEnabled(false);
            pedidoView.lblOpPedido.setText("( NUEVO )");
            pedidoView.lblOpPedidoDetalle.setText("");
            
            pedidoView.pack();
            mainContainer.add(pedidoView);
            Helpers.centerForm(mainContainer, pedidoView);
            pedidoView.setVisible(true);
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

    public void nuevoDetalle() {
        try {
            
            if(isPedidoGenerado()) {
                limpiarFormDetalle();
                IdDetallePedido_edit = 0;
                this.op_detalle = Constants.OP_NEW;
                pedidoView.lblOpPedidoDetalle.setText("( NUEVO )");
                pedidoView.btnBuscarPizza.setEnabled(true);
                pedidoView.btnGuardarDetalle.setEnabled(true);
            }
            else {
                JOptionPane.showMessageDialog(
                    null,
                    "El Pedido no se encuentra en estado generado.",
                    "Mensaje",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error nuevo detalle: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    @Override
    public void insertar() {
        try {
            Date curretDate = new Date(System.currentTimeMillis());
            
            Pedido pedido = new Pedido();
            pedido.setFechaCreacion(curretDate);
            pedido.setHoracreacion(new Time(curretDate.getTime()));
            pedido.setDireccionEnvio((Direccion) pedidoView.cboDireccion.getSelectedItem());
            pedido.setCliente(new Cliente(IdCliente));
            pedido.setObservaciones(pedidoView.txaObservacion.getText());
            
            Pedido pedido_inserted = pedidoDAO.insertarPedido(
                pedido,
                ((TipoComprobante) pedidoView.cboTipoComprobante.getSelectedItem())
                    .getIdTipoComprobante()
            );
            IdPedido_edit = pedido_inserted.getIdPedido();
            pedidoView.txtNroPedido.setText(String.valueOf(pedido_inserted.getNumero()));
            pedidoView.txtNroComprobante.setText(String.valueOf(pedido_inserted.getNumeroComprobante()));
            
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
    
    public void insertarDetalle() {
        try {
            
            DetallePedido detallepedido = new DetallePedido();
            detallepedido.setCantidad(Integer.valueOf(pedidoView.txtCantidadPizza.getText()));
            detallepedido.setPedido(new Pedido(IdPedido_edit));
            detallepedido.setPizza(pizzaDetalle);
            
            int iddetallepedido = pedidoDetalleDAO.insertarDetallePedido(detallepedido);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error insertar detalle: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void modificar() {
        try {
            Pedido pedido = new Pedido();
            pedido.setIdPedido(IdPedido_edit);
            pedido.setDireccionEnvio((Direccion) pedidoView.cboDireccion.getSelectedItem());
            pedido.setCliente(new Cliente(IdCliente));
            pedido.setObservaciones(pedidoView.txaObservacion.getText());
            pedido.setEstado((Estado) pedidoView.cboEstado.getModel().getSelectedItem());
            
            pedidoDAO.modificarPedido(
                pedido,
                ((TipoComprobante) pedidoView.cboTipoComprobante.getSelectedItem())
                    .getIdTipoComprobante()
            );
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
    
    public void modificarDetalle() {
        try {
            DetallePedido detallepedido = new DetallePedido();
            detallepedido.setIdDetallePedido(IdDetallePedido_edit);
            detallepedido.setCantidad(Integer.valueOf(pedidoView.txtCantidadPizza.getText()));
            detallepedido.setPedido(new Pedido(IdPedido_edit));
            detallepedido.setPizza(pizzaDetalle);
            
            pedidoDetalleDAO.modificarDetallePedido(detallepedido);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error modificar detalle: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void eliminarDetalle() {
        try {
            int selectedRow = pedidoView.tblDetallePedido.getSelectedRow();
            if (selectedRow != -1) {
                int optSelected = JOptionPane.showConfirmDialog(
                    null,
                    "¿Realmente desea eliminar este item del detalle?",
                    "Confirmación",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

                if (optSelected == JOptionPane.OK_OPTION) {
                    int IdDetallePedido = (int) pedidoView.tblDetallePedido.getValueAt(selectedRow, 0);

                    DetallePedido detallepedido = new DetallePedido();
                    detallepedido.setIdDetallePedido(IdDetallePedido);
                    detallepedido.setPedido(new Pedido(IdPedido_edit));
                    pedidoDetalleDAO.eliminarDetallePedido(detallepedido);
                    buscarDetalle();
                }
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
                "Error eliminar detalle: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void guardar() {
        
        // validaciones
        String msgValidacion = "";
        if (IdCliente == 0)
            msgValidacion += "- Ingresar cliente.\n";

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
        this.op_detalle = null;
        pedidoView.btnModificarDetalle.setEnabled(true);
        pedidoView.btnAgregarDetalle.setEnabled(true);
        pedidoView.btnEliminarDetalle.setEnabled(true);
        pedidoView.btnBuscarPizza.setEnabled(false);
        pedidoView.btnGuardarDetalle.setEnabled(false);
        pedidoView.cboEstado.setEnabled(true);
        
        pedidoView.lblOpPedido.setText("( EDITAR )");
        limpiarFormDetalle();
    }
    
    public void guardarDetalle() {
        
        // validaciones
        if(isPedidoGenerado()) {
            String msgValidacion = "";
            if (Objects.isNull(pizzaDetalle)) {
                msgValidacion += "- Debe seleccionar la pizza.\n";
            }
            else {
                if (pedidoView.txtCantidadPizza.getText().trim().equals("") ||
                    pedidoView.txtCantidadPizza.getText().trim().equals(0)
                   )
                    msgValidacion += "- Ingrese la cantidad.\n";
            }

            if (!msgValidacion.equals("")) {
                JOptionPane.showMessageDialog(
                    null,
                    "Corregir los siguiente:\n" + msgValidacion,
                    "Mensaje",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
        }
        else {
            JOptionPane.showMessageDialog(
                null,
                "El Pedido no se encuentra en estado generado.",
                "Mensaje",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        
        if (this.op_detalle.equals(Constants.OP_NEW))
            insertarDetalle();
        else if (this.op_detalle.equals(Constants.OP_EDIT))
            modificarDetalle();
        
        this.op_detalle = Constants.OP_EDIT;
        
        pedidoView.btnBuscarPizza.setEnabled(false);
        pedidoView.btnGuardarDetalle.setEnabled(false);
        
        limpiarFormDetalle();
        buscarDetalle();
    }
    
}
