/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import java.sql.Time;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import pedidos.pizzeria.utils.Constants;
import pedidos.pizzeria.utils.Helpers;
import pedidos.pizzeria.utp.dao.EnvioDAO;
import pedidos.pizzeria.utp.dao.EstadoDAO;
import pedidos.pizzeria.utp.dao.PersonaDAO;
import pedidos.pizzeria.utp.model.Envio;
import pedidos.pizzeria.utp.model.Estado;
import pedidos.pizzeria.utp.model.Persona;
import pedidos.pizzeria.utp.model.Repartidor;
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
//    Pizza pizzaDetalle;
    

    EnvioView envioView;

    EnvioListaController envioListaController;
    
    EnvioDAO envioDAO;
//    EnvioDetalleDAO envioDetalleDAO;
    
    JDesktopPane mainContainer;

    public EnvioController(EnvioListaController envioListaController, JDesktopPane mainContainer) {
        this.envioListaController = envioListaController;
        this.envioDAO = new EnvioDAO();
//        this.pedidoDetalleDAO = new PedidoDetalleDAO();
        
        this.mainContainer = mainContainer;
        
        envioView = new EnvioView();
        
        // eventos
        
        
        initCombos();
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
    

    @Override
    public void limpiarForm() {
        envioView.txtNroEnvio.setText("");
        
    }

    @Override
    public void buscar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void obtener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public void insertar() {
        try {
            
            Envio envio = new Envio();
            envio.setHoraInicio(new Time(envioView.dtHoraInicio.getDate().getTime()));
            envio.setHoraFin(new Time(envioView.dtHoraFin.getDate().getTime()));
            envio.setRepartidor(new Repartidor(((Persona) envioView.cboRepartidor.getModel().getSelectedItem()).getIdPersona()));
            
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

    @Override
    public void modificar() {
        try {
            Envio envio = new Envio();
            envio.setIdEnvio(IdEnvio_edit);
            envio.setHoraInicio(new Time(envioView.dtHoraInicio.getDate().getTime()));
            envio.setHoraFin(new Time(envioView.dtHoraFin.getDate().getTime()));
            envio.setRepartidor(new Repartidor(((Persona) envioView.cboRepartidor.getModel().getSelectedItem()).getIdPersona()));
            
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
        
        envioView.lblOpEnvio.setText("( EDITAR )");
//        limpiarFormDetalle();
    }
    
}
