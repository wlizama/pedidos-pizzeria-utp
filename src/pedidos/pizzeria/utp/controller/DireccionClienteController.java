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
import pedidos.pizzeria.utp.dao.DireccionClienteDAO;
import pedidos.pizzeria.utp.dao.DistritoDAO;
import pedidos.pizzeria.utp.model.Cliente;
import pedidos.pizzeria.utp.model.Direccion;
import pedidos.pizzeria.utp.model.Distrito;
import pedidos.pizzeria.utp.view.DireccionClienteView;
import pedidos.pizzeria.utp.view.ListaClientesView;

/**
 *
 * @author wilderlizama
 */
public class DireccionClienteController implements BaseControllerInterface {
    
    String op;
    int IdCliente_edit;
    int IdDireccionCliente_edit;

    DireccionClienteView direccionClienteView;
    ListaClientesView clienteView;
    
    ClienteController clienteController;
    
    DireccionClienteDAO direccionclienteDAO;
    

    public DireccionClienteController(DireccionClienteView direccionclienteView, ClienteController clienteController) {
        this.direccionClienteView = direccionclienteView;
        this.clienteView = clienteController.getClienteView();
        
        this.clienteController = clienteController;
        this.direccionclienteDAO = new DireccionClienteDAO();
        
        // eventos de form
        
        direccionclienteView.btnGuardar.addActionListener((ae) -> {
            guardar();
        });
        
        direccionclienteView.btnCancelar.addActionListener((ae) -> {
            cancelar();
        });
        

        initCombo();
    }

    public void setIdCliente_edit(int IdCliente_edit) {
        this.IdCliente_edit = IdCliente_edit;
    }

    public void setIdDireccionCliente_edit(int IdDireccionCliente_edit) {
        this.IdDireccionCliente_edit = IdDireccionCliente_edit;
    }

    private void cancelar() {
        direccionClienteView.dispose();
    }
    
    private void initCombo() {
        try {
            List<Distrito> distritos = new DistritoDAO().getListaDistrito();
            
            direccionClienteView.cbxDistrito.setModel(new DefaultComboBoxModel());
            
            for (Distrito distrito : distritos) {
                direccionClienteView.cbxDistrito.addItem(distrito);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Error initCombo: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void limpiarForm() {
        direccionClienteView.txtDireccion.setText("");
        direccionClienteView.chkPorDefecto.setSelected(false);
        direccionClienteView.txtRef.setText("");
    }

    @Override
    public void buscar() {
        try {
            
            List<Direccion> lstdireccion = direccionclienteDAO.getListaDireccionCliente(IdCliente_edit);
            
            DefaultTableModel direccionviewTblModel = (DefaultTableModel) clienteView.tblClientesDirecciones.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(direccionviewTblModel);
            if (lstdireccion.size() > 0) {
                for (Direccion direccion : lstdireccion) {
                    direccionviewTblModel.addRow(new Object[] {
                        direccion.getIdDireccion(),
                        direccion.getDireccion(),
                        direccion.getDistrito().getNombre(),
                        direccion.getReferencia(),
                        direccion.getPrincipal() ? "SI" : "NO"
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
            Direccion direccion = direccionclienteDAO.getDireccionCliente(IdDireccionCliente_edit);

            direccionClienteView.txtDireccion.setText(direccion.getDireccion());
            direccionClienteView.cbxDistrito.getModel().setSelectedItem(direccion.getDistrito());
            direccionClienteView.chkPorDefecto.setSelected(direccion.getPrincipal());
            direccionClienteView.txtRef.setText(direccion.getReferencia());

            this.op = Constants.OP_EDIT;
            direccionClienteView.lblOperacion.setText("( EDITAR )");
            Helpers.centerForm(clienteView, direccionClienteView);
            direccionClienteView.setVisible(true);
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
            direccionClienteView.lblOperacion.setText("( NUEVO )");
            Helpers.centerForm(clienteView, direccionClienteView);
            direccionClienteView.setVisible(true);
            
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
            Direccion direccioncliente = new Direccion();
            direccioncliente.setDireccion(direccionClienteView.txtDireccion.getText());
            direccioncliente.setReferencia(direccionClienteView.txtRef.getText());
            direccioncliente.setCliente(new Cliente(IdCliente_edit));
            direccioncliente.setDistrito((Distrito) direccionClienteView.cbxDistrito.getModel().getSelectedItem());
            direccioncliente.setPrincipal(direccionClienteView.chkPorDefecto.isSelected());
            int IdDireccionCliente = direccionclienteDAO.insertarDireccion(direccioncliente);
            direccioncliente.setIdDireccion(IdDireccionCliente);
            IdDireccionCliente_edit = IdDireccionCliente;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error insertar direccion: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void modificar() {
        try {
            Direccion direccioncliente = new Direccion();
            direccioncliente.setIdDireccion(IdDireccionCliente_edit);
            direccioncliente.setDireccion(direccionClienteView.txtDireccion.getText());
            direccioncliente.setReferencia(direccionClienteView.txtRef.getText());
            direccioncliente.setCliente(new Cliente(IdCliente_edit));
            direccioncliente.setDistrito((Distrito) direccionClienteView.cbxDistrito.getModel().getSelectedItem());
            direccioncliente.setPrincipal(direccionClienteView.chkPorDefecto.isSelected());
            direccionclienteDAO.modificarDireccion(direccioncliente);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error modificar direccion: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void guardar() {
         // validaciones
        
        String msgValidacion = "";
        if (direccionClienteView.txtDireccion.getText().trim().equals(""))
            msgValidacion += "- Ingresar dirección.\n";
        
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
        
        cancelar();
        buscar();
    }
    
}
