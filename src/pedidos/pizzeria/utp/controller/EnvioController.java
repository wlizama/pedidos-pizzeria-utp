/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pedidos.pizzeria.utils.Constants;
import pedidos.pizzeria.utils.Helpers;
import pedidos.pizzeria.utp.dao.EnvioDAO;
import pedidos.pizzeria.utp.dao.EnvioDetalleDAO;
import pedidos.pizzeria.utp.dao.EstadoDAO;
import pedidos.pizzeria.utp.dao.PersonaDAO;
import pedidos.pizzeria.utp.model.DetalleEnvio;
import pedidos.pizzeria.utp.model.Envio;
import pedidos.pizzeria.utp.model.Estado;
import pedidos.pizzeria.utp.model.Pedido;
import pedidos.pizzeria.utp.model.Persona;
import pedidos.pizzeria.utp.model.Repartidor;
import pedidos.pizzeria.utp.view.EnvioBusquedaPedidoView;
import pedidos.pizzeria.utp.view.EnvioListaView;
import pedidos.pizzeria.utp.view.EnvioView;

/**
 *
 * @author wilderlizama
 */
public class EnvioController implements BaseControllerInterface {
    
    String op;
    String op_detalle;
    int IdEnvio_edit;
    int IdDetalleEnvio_edit;
    Pedido pedidoDetalle;
    

    EnvioView envioView;

    EnvioListaController envioListaController;
    
    EnvioDAO envioDAO;
    EnvioDetalleDAO envioDetalleDAO;
    
    JDesktopPane mainContainer;

    public EnvioController(EnvioListaController envioListaController, JDesktopPane mainContainer) {
        this.envioListaController = envioListaController;
        this.envioDAO = new EnvioDAO();
        this.envioDetalleDAO = new EnvioDetalleDAO();
        
        this.mainContainer = mainContainer;
        
        envioView = new EnvioView();
        
        // eventos
        envioView.btnGuardar.addActionListener((ae) -> {
            guardar();
        });
        
        envioView.btnCancelar.addActionListener((ae) -> {
            regresar();
        });
        
        envioView.btnEnvdatos.addActionListener((ae) -> {
            enviarDatos();
        });
        
        // detalle
        envioView.btnModificarDetalle.addActionListener((ae) -> {
            obtenerDetalle();
        });
        
        envioView.btnAgregarDetalle.addActionListener((ae) -> {
            nuevoDetalle();
        });
        
        envioView.btnEliminarDetalle.addActionListener((ae) -> {
            eliminarDetalle();
        });
        
        envioView.btnBuscarPedido.addActionListener((ae) -> {
            mostrarBuscarPedido();
        });
        
        envioView.btnGuardarDetalle.addActionListener((ae) -> {
            guardarDetalle();
        });
        
        initCombos();
    }
    
    private void enviarDatos() {
        try {
            List<DetalleEnvio> lstDetalleEnvio = envioDetalleDAO.getListaDetalleEnvio(IdEnvio_edit);
            if (lstDetalleEnvio.size() > 0) {
                String nroTelefono = ((Persona) envioView.cboRepartidor.getModel().getSelectedItem()).getTelefono();
                String msgText = "";
                for (DetalleEnvio detalleEnvio : lstDetalleEnvio) {
                    msgText += "*Nro. Pedido*: " + detalleEnvio.getPedido().getNumero() + "," +
                            "*Cliente*: " + detalleEnvio.getPedido().getCliente().getNombres() + "," 
                            + "*Dirección*: " + detalleEnvio.getPedido().getDireccionEnvio().getDireccion() + "|";
                }

                Helpers.openWebpage(new URI("https://wa.me/" + nroTelefono + "/?text="+ URLEncoder.encode(msgText, StandardCharsets.UTF_8.toString())));
            }
            else {
                JOptionPane.showMessageDialog(
                    null,
                    "No existen datos en el detalle del Envio",
                    "Excepción",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error enviar Datos: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void setPedidoDetalle(Pedido pedidoDetalle) {
        this.pedidoDetalle = pedidoDetalle;
    }
    
    public void mostrarBuscarPedido() {
        try {
            
            if(isEnvioGenerado()) {
                EnvioBusquedaPedidoView enviobusqView = new EnvioBusquedaPedidoView(null, true);
                EnvioBusquedaPedidoController enviobusqController = new EnvioBusquedaPedidoController(
                    enviobusqView,
                    this
                );
                Helpers.centerForm(envioView, enviobusqView);
                enviobusqController.mostrar();
            }
            else {
                JOptionPane.showMessageDialog(
                    null,
                    "El Envio no se encuentra en estado generado.",
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

    public void setIdEnvio_edit(int IdEnvio_edit) {
        this.IdEnvio_edit = IdEnvio_edit;
    }
    
    private boolean isEnvioGenerado() {
        try {
            Envio envio = envioDAO.getEnvio(IdEnvio_edit);
            
            return envio.getEstado().getIdEstado() == Constants.ID_ESTADO_GENERADO;
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Error isPedidoGenerado: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
            
            return false;
        }
    }
    
    private boolean isEnvioEnCamino() {
        try {
            Envio envio = envioDAO.getEnvio(IdEnvio_edit);
            
            return envio.getEstado().getIdEstado() == Constants.ID_ESTADO_ENCAMINO;
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Error isEnvioEnCamino: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
            
            return false;
        }
    }
    
    private void initCombos() {
        try {
            List<Persona> lstRepartidor = new PersonaDAO().getListaPersonaXTipo(Constants.ID_TPERSONA_DELIVERY);
            List<Estado> lstEstado = new EstadoDAO().getListaEstado(Constants.ID_TESTADO_ENVIO);
            
            envioView.cboRepartidor.setModel(new DefaultComboBoxModel());
            envioView.cboEstadoenvio.setModel(new DefaultComboBoxModel());
                        
            for (Persona repartidor : lstRepartidor) {
                envioView.cboRepartidor.addItem(repartidor);
            }
            
            for (Estado estado : lstEstado) {
                if (estado.getIdEstado() != Constants.ID_ESTADO_LISTOENTREGA) // excluir estado listo para entrega
                    envioView.cboEstadoenvio.addItem(estado); // para lista filtro
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
    
    public void regresar() {
        
        JDesktopPane mainContainer = (JDesktopPane) envioView.getParent();
        mainContainer.remove(envioView);
        
        mainContainer.updateUI();
        
        EnvioListaView pedidoListaView = new EnvioListaView();
        pedidoListaView.pack();
        mainContainer.add(pedidoListaView);
        Helpers.centerForm(mainContainer, pedidoListaView);
        pedidoListaView.setVisible(true);
    }

    @Override
    public void limpiarForm() {
        envioView.txtNroEnvio.setText("");
    }
    
    public void limpiarFormDetalle() {
        envioView.txtNroPedido.setText("");
        envioView.txtCliente.setText("");
        envioView.txtDireccion.setText("");
        envioView.txaObservaciones.setText("");
        envioView.txtNroPedido.setText("");
        envioView.lblOpEnvioDetalle.setText("");
    }

    @Override
    public void buscar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void buscarDetalle() {
        try {
            
            List<DetalleEnvio> lstDetalleEnvio = envioDetalleDAO.getListaDetalleEnvio(IdEnvio_edit);
            
            DefaultTableModel pedidodetalleviewTblModel = (DefaultTableModel) envioView.tblDetalleEnvio.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(pedidodetalleviewTblModel);

            if (lstDetalleEnvio.size() > 0) {
                for (DetalleEnvio detallenvio : lstDetalleEnvio) {
                    pedidodetalleviewTblModel.addRow(new Object[] {
                        detallenvio.getIdDetalleEnvio(),
                        detallenvio.getPedido().getNumero(),
                        detallenvio.getPedido().getCliente().getNombres(),
                        detallenvio.getPedido().getDireccionEnvio().getDireccion(),
                        detallenvio.getHoraFin()
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
            
            limpiarForm();
            this.op = Constants.OP_EDIT;
            
            Envio envio = envioDAO.getEnvio(IdEnvio_edit);
            envioView.txtNroEnvio.setText(String.valueOf(envio.getNumero()));
            envioView.dtHoraInicio.setDate(envio.getHoraInicio());
            envioView.dtHoraFin.setDate(envio.getHoraFin());
            envioView.cboRepartidor.getModel().setSelectedItem(envio.getRepartidor());
            envioView.cboEstadoenvio.getModel().setSelectedItem(envio.getEstado());
            
            envioView.btnModificarDetalle.setEnabled(true);
            envioView.btnAgregarDetalle.setEnabled(true);
            envioView.btnEliminarDetalle.setEnabled(true);
            envioView.btnBuscarPedido.setEnabled(false);
            envioView.btnGuardarDetalle.setEnabled(false);
            envioView.cboEstadoenvio.setEnabled(true);
            envioView.lblOpEnvio.setText("( EDITAR )");
            
            buscarDetalle();
            
            envioView.pack();
            mainContainer.add(envioView);
            Helpers.centerForm(mainContainer, envioView);
            envioView.setVisible(true);
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

    public void obtenerPedidoDetalle() {
        try {
            envioView.txtNroPedido.setText(String.valueOf(pedidoDetalle.getNumero()));
            envioView.txtCliente.setText(pedidoDetalle.getCliente().getNombres());
            envioView.txtDireccion.setText(pedidoDetalle.getDireccionEnvio().getDireccion());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error obtener pedido Detalle: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void obtenerDetalle() {
        try {
            int selectedRow = envioView.tblDetalleEnvio.getSelectedRow();
            if (selectedRow != -1) {
                int IdDetalleEnvio = (int) envioView.tblDetalleEnvio.getValueAt(selectedRow, 0);
                
                DetalleEnvio detalleenvio = envioDetalleDAO.getDetalleEnvio(IdDetalleEnvio);
                Pedido pedido = detalleenvio.getPedido();
                setPedidoDetalle(pedido);
                envioView.txtNroPedido.setText(String.valueOf(pedido.getNumero()));
                envioView.txtCliente.setText(pedido.getCliente().getNombres());
                envioView.txtDireccion.setText(pedido.getDireccionEnvio().getDireccion());
                envioView.dtHoraFinPedido.setDate(detalleenvio.getHoraFin());
                envioView.txaObservaciones.setText(detalleenvio.getObservaciones());
                
                IdDetalleEnvio_edit = IdDetalleEnvio;
                op_detalle = Constants.OP_EDIT;
                envioView.lblOpEnvioDetalle.setText("( EDITAR )");
                envioView.btnBuscarPedido.setEnabled(true);
                envioView.btnGuardarDetalle.setEnabled(true);
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
            IdEnvio_edit = 0;
            this.op = Constants.OP_NEW;
            
            envioView.btnModificarDetalle.setEnabled(false);
            envioView.btnAgregarDetalle.setEnabled(false);
            envioView.btnEliminarDetalle.setEnabled(false);
            envioView.btnBuscarPedido.setEnabled(false);
            envioView.btnGuardarDetalle.setEnabled(false);
            envioView.cboEstadoenvio.setEnabled(false);
            envioView.lblOpEnvio.setText("( NUEVO )");
            envioView.lblOpEnvioDetalle.setText("");
            
            envioView.pack();
            mainContainer.add(envioView);
            Helpers.centerForm(mainContainer, envioView);
            envioView.setVisible(true);
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
            
            if(isEnvioGenerado()) {
                limpiarFormDetalle();
                IdDetalleEnvio_edit = 0;
                this.op_detalle = Constants.OP_NEW;
                envioView.lblOpEnvioDetalle.setText("( NUEVO )");
                envioView.btnBuscarPedido.setEnabled(true);
                envioView.btnGuardarDetalle.setEnabled(true);
            }
            else {
                JOptionPane.showMessageDialog(
                    null,
                    "El Envio no se encuentra en estado generado.",
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
            
            Envio envio = new Envio();
            envio.setHoraInicio(new Timestamp(envioView.dtHoraInicio.getDate().getTime()));
            envio.setHoraFin(new Timestamp(envioView.dtHoraFin.getDate().getTime()));
            Repartidor repartidor = new Repartidor();
            repartidor.setIdPersona(((Persona) envioView.cboRepartidor.getModel().getSelectedItem()).getIdPersona());
            envio.setRepartidor(repartidor);
            
            Envio envio_inserted = envioDAO.insertarEnvio(envio);
            IdEnvio_edit = envio_inserted.getIdEnvio();
            envioView.txtNroEnvio.setText(String.valueOf(envio_inserted.getNumero()));
            
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
            
            DetalleEnvio detalleenvio = new DetalleEnvio();
            detalleenvio.setHoraFin(new Timestamp(envioView.dtHoraFinPedido.getDate().getTime()));
            detalleenvio.setObservaciones(envioView.txaObservaciones.getText());
            detalleenvio.setEnvio(new Envio(IdEnvio_edit));
            detalleenvio.setPedido(new Pedido(pedidoDetalle.getIdPedido()));
            
            int IdDetalleEnvio = envioDetalleDAO.insertarDetalleEnvio(detalleenvio);
            
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
            Envio envio = new Envio();
            envio.setIdEnvio(IdEnvio_edit);
            envio.setHoraInicio(new Timestamp(envioView.dtHoraInicio.getDate().getTime()));
            envio.setHoraFin(new Timestamp(envioView.dtHoraFin.getDate().getTime()));
            Repartidor repartidor = new Repartidor();
            System.out.println("a>" + ((Repartidor) envioView.cboRepartidor.getModel().getSelectedItem()).getIdPersona());
            repartidor.setIdPersona(((Repartidor) envioView.cboRepartidor.getModel().getSelectedItem()).getIdPersona());
            envio.setRepartidor(repartidor);
            envio.setEstado((Estado) envioView.cboEstadoenvio.getModel().getSelectedItem());
            envioDAO.modificarEnvio(envio);
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
            DetalleEnvio detalleenvio = new DetalleEnvio();
            detalleenvio.setIdDetalleEnvio(IdDetalleEnvio_edit);
            detalleenvio.setHoraFin(new Timestamp(envioView.dtHoraFinPedido.getDate().getTime()));
            detalleenvio.setObservaciones(envioView.txaObservaciones.getText());
            detalleenvio.setPedido(new Pedido(pedidoDetalle.getIdPedido()));
            
            envioDetalleDAO.modificarDetalleEnvio(detalleenvio);
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
            if(!isEnvioGenerado()) {
                JOptionPane.showMessageDialog(
                    null,
                    "El Envio no se encuentra en estado generado.",
                    "Mensaje",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            int selectedRow = envioView.tblDetalleEnvio.getSelectedRow();
            if (selectedRow != -1) {
                int optSelected = JOptionPane.showConfirmDialog(
                    null,
                    "¿Realmente desea eliminar este item del detalle?",
                    "Confirmación",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

                if (optSelected == JOptionPane.OK_OPTION) {
                    int iddetalleenvio = (int) envioView.tblDetalleEnvio.getValueAt(selectedRow, 0);

                    envioDetalleDAO.eliminarDetalleEnvio(iddetalleenvio);
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
//        String msgValidacion = "";
//        if (IdPedido == 0)
//            msgValidacion += "- Ingresar cliente.\n";
//
//        if (!msgValidacion.equals("")) {
//            JOptionPane.showMessageDialog(
//                null,
//                "Corregir los siguiente:\n" + msgValidacion,
//                "Mensaje",
//                JOptionPane.WARNING_MESSAGE
//            );
//            return;
//        }
        
        if (this.op.equals(Constants.OP_NEW))
            insertar();
        else if (this.op.equals(Constants.OP_EDIT))
            modificar();
        
        this.op = Constants.OP_EDIT;
        this.op_detalle = null;
        envioView.btnModificarDetalle.setEnabled(true);
        envioView.btnAgregarDetalle.setEnabled(true);
        envioView.btnEliminarDetalle.setEnabled(true);
        envioView.btnBuscarPedido.setEnabled(false);
        envioView.btnGuardarDetalle.setEnabled(false);
        envioView.cboEstadoenvio.setEnabled(true);
        
        envioView.lblOpEnvio.setText("( EDITAR )");
        limpiarFormDetalle();
    }
    
    public void guardarDetalle() {
        
        // validaciones
        if(isEnvioGenerado()) {
            String msgValidacion = "";
            if (Objects.isNull(pedidoDetalle)) {
                msgValidacion += "- Debe seleccionar el pedido.\n";
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
            if(!isEnvioEnCamino()) {
                JOptionPane.showMessageDialog(
                    null,
                    "No puede modificar el detalle de Envio si el pedido esta finalizado.",
                    "Mensaje",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
        }
        
        
        if (this.op_detalle.equals(Constants.OP_NEW))
            insertarDetalle();
        else if (this.op_detalle.equals(Constants.OP_EDIT))
            modificarDetalle();
        
        this.op_detalle = Constants.OP_EDIT;
        
        envioView.btnBuscarPedido.setEnabled(false);
        envioView.btnGuardarDetalle.setEnabled(false);
        
        limpiarFormDetalle();
        buscarDetalle();
    }
    
}
