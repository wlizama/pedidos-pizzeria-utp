/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import java.util.List;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pedidos.pizzeria.utils.Constants;
import pedidos.pizzeria.utils.Helpers;
import pedidos.pizzeria.utp.dao.PersonaDAO;
import pedidos.pizzeria.utp.dao.RolesDAO;
import pedidos.pizzeria.utp.dao.TipoDocumentoIdentidadDAO;
import pedidos.pizzeria.utp.dao.TipoPersonaDAO;
import pedidos.pizzeria.utp.model.DocumentoIdentidad;
import pedidos.pizzeria.utp.model.Persona;
import pedidos.pizzeria.utp.model.Roles;
import pedidos.pizzeria.utp.model.TipoDocumentoIdentidad;
import pedidos.pizzeria.utp.model.TipoPersona;
import pedidos.pizzeria.utp.model.Usuario;
import pedidos.pizzeria.utp.view.ListaPersonalView;

/**
 *
 * @author jonas
 */
public class PersonaController implements BaseControllerInterface{
    
    String op;
    int IdPersona_edit;
    
    ListaPersonalView personalView;
    PersonaDAO personaDAO;    

    public PersonaController(ListaPersonalView personalView) {
        this.personalView = personalView;
        this.personaDAO = new PersonaDAO();
        
        // eventos de form
        personalView.cbxRol.addItemListener((ie) -> {
            cambiarCboRol();
        });
        
        personalView.btnBuscar.addActionListener((ae) -> {
            buscar();
        });
        
        personalView.btnEditar.addActionListener((ae) -> {
            obtener();
        });
        
        personalView.btnNuevo.addActionListener((ae) -> {
            nuevo();
        });
        
        personalView.btnGuardar.addActionListener((ae) -> {
            guardar();
        });
        
        initCombos();
        limpiarForm();
        buscar();
        this.op = Constants.OP_NEW;
        personalView.txtUsuario.setEnabled(false);
        personalView.txtContrasena.setEnabled(false);
        personalView.btnGuardar.setEnabled(false);
        personalView.lblOpPersona.setText(" ");
    }
    
    private void cambiarCboRol() {
        if (((Roles)personalView.cbxRol.getModel().getSelectedItem()).getIdRol() == Constants.ID_ROL_NINGUNO){
            personalView.txtUsuario.setEnabled(false);
            personalView.txtUsuario.setText("");
            personalView.txtContrasena.setEnabled(false);
            personalView.txtContrasena.setText("");
        }
        else {
            personalView.txtUsuario.setEnabled(true);
            personalView.txtContrasena.setEnabled(true);
        }
    }
    
    private void initCombos() {
        try {
            List<TipoDocumentoIdentidad> lsttipoDocumentoIdentidad = new TipoDocumentoIdentidadDAO().getListaTipoDocumentoIdentidad();
            List<TipoPersona> lsttipoPersona = new TipoPersonaDAO().getListaTipoPersona();
            List<Roles> lstroles = new RolesDAO().getListaRoles();
            
            personalView.cbxTDocPersonaFiltro.setModel(new DefaultComboBoxModel());
            personalView.cbxTDocPersona.setModel(new DefaultComboBoxModel());
            personalView.cbxTipoPersona.setModel(new DefaultComboBoxModel());
            personalView.cbxRol.setModel(new DefaultComboBoxModel());
            
            personalView.cbxTDocPersonaFiltro.addItem(new TipoDocumentoIdentidad(0, "Todos", 0)); // para que busque todos
            
            personalView.cbxRol.addItem(new Roles(0, "Ninguno")); // personas sin usuario
            
            for (TipoDocumentoIdentidad tipoDocumentoIdentidad : lsttipoDocumentoIdentidad) {
                personalView.cbxTDocPersonaFiltro.addItem(tipoDocumentoIdentidad); // para lista filtro
                personalView.cbxTDocPersona.addItem(tipoDocumentoIdentidad);
            }
            
            for (TipoPersona tipoPersona : lsttipoPersona) {
                if (tipoPersona.getIdTipoPersona() != Constants.ID_TPERSONA_CLIENTE) // excluir clientes
                    personalView.cbxTipoPersona.addItem(tipoPersona);
            }
            
            for (Roles rol : lstroles) {
                personalView.cbxRol.addItem(rol);
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
        personalView.txtNombrePersona.setText("");
        personalView.txtApePersona.setText("");
        personalView.txtTelPersona.setText("");
        personalView.cbxTDocPersona.setSelectedIndex(0);
        personalView.txtDocPersona.setText("");
        personalView.cbxTipoPersona.setSelectedIndex(0);
        personalView.cbxRol.setSelectedIndex(0);
        personalView.txtUsuario.setText("");
        personalView.txtContrasena.setText("");
    }

    @Override
    public void buscar() {
        try {
            
            TipoDocumentoIdentidad tipoDocIdentidad_selected = (TipoDocumentoIdentidad) personalView.cbxTDocPersonaFiltro.getSelectedItem();
            int idtipoDocIdentidad = tipoDocIdentidad_selected.getIdTipoDocIdentidad();
            String numeroDocIdentidad = personalView.txtDocPersonaFiltro.getText().trim();
            
            List<Persona> lstCliente = personaDAO.getListaPersona(idtipoDocIdentidad, numeroDocIdentidad);
            
            DefaultTableModel clienteviewTblModel = (DefaultTableModel) personalView.tblListaPersonal.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(clienteviewTblModel);

            if (lstCliente.size() > 0) {
                for (Persona persona : lstCliente) {
                    clienteviewTblModel.addRow(new Object[] {
                        persona.getIdPersona(),
                        persona.getNombres() + " " + persona.getApellidos(),
                        persona.getTipoPersona().getNombre(),
                        persona.getDocumentoIdentidad().getTipoDocumentoIdentidad().getNombre(),
                        persona.getDocumentoIdentidad().getNumero(),
                        persona.getTelefono()
                    });
                }
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
    public void obtener() {
        try {
            int selectedRow = personalView.tblListaPersonal.getSelectedRow();
            if (selectedRow != -1) {
                int IdPersona = (int) personalView.tblListaPersonal.getValueAt(selectedRow, 0);
                Persona persona = personaDAO.getPersona(IdPersona);
                
                limpiarForm();
                
                IdPersona_edit = persona.getIdPersona();
                personalView.txtNombrePersona.setText(persona.getNombres());
                personalView.txtApePersona.setText(persona.getApellidos());
                personalView.txtTelPersona.setText(persona.getTelefono());
                personalView.cbxTDocPersona.getModel().setSelectedItem(persona.getDocumentoIdentidad().getTipoDocumentoIdentidad());
                personalView.txtDocPersona.setText(persona.getDocumentoIdentidad().getNumero());
                personalView.cbxTipoPersona.getModel().setSelectedItem(persona.getTipoPersona());
                
                personalView.txtUsuario.setEnabled(false);
                personalView.txtContrasena.setEnabled(false);
                
                Usuario usuario = personaDAO.getPersonaUsuario(IdPersona);
                if (!Objects.isNull(usuario)) {
                    Roles rol = new Roles(
                        usuario.getRol().getIdRol(),
                        usuario.getRol().getNombre()
                    );
                    personalView.cbxRol.getModel().setSelectedItem(rol);
                    personalView.txtUsuario.setEnabled(true);
                    personalView.txtUsuario.setText(usuario.getNombreUsuario());
                    personalView.txtContrasena.setEnabled(true);
                    personalView.txtContrasena.setText(usuario.getContrasenha());
                }
                this.op = Constants.OP_EDIT;
                personalView.btnGuardar.setEnabled(true);
                personalView.lblOpPersona.setText("( EDITAR )");
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
            IdPersona_edit = 0;
            this.op = Constants.OP_NEW;
            personalView.txtUsuario.setEnabled(false);
            personalView.txtContrasena.setEnabled(false);
            personalView.btnGuardar.setEnabled(true);
            personalView.lblOpPersona.setText("( NUEVO )");
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
            Persona persona = new Persona();
            persona.setNombres(personalView.txtNombrePersona.getText());
            persona.setApellidos(personalView.txtApePersona.getText());
            persona.setTelefono(personalView.txtTelPersona.getText());
            persona.setDocumentoIdentidad(new DocumentoIdentidad(
                    0,
                    personalView.txtDocPersona.getText(),
                    (TipoDocumentoIdentidad) personalView.cbxTDocPersona.getSelectedItem()
                )
            );
            persona.setTipoPersona((TipoPersona) personalView.cbxTipoPersona.getSelectedItem());
            Usuario usuario = new Usuario(
                0,
                personalView.txtUsuario.getText(),
                personalView.txtContrasena.getText(),
                (Roles)personalView.cbxRol.getSelectedItem()
            );
            
            int IdPersona = personaDAO.insertarPersona(persona, usuario);
            
            persona.setIdPersona(IdPersona);
            IdPersona_edit = IdPersona;
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
            Persona persona = new Persona();
            persona.setIdPersona(IdPersona_edit);
            persona.setNombres(personalView.txtNombrePersona.getText());
            persona.setApellidos(personalView.txtApePersona.getText());
            persona.setTelefono(personalView.txtTelPersona.getText());
            persona.setDocumentoIdentidad(new DocumentoIdentidad(
                    0,
                    personalView.txtDocPersona.getText(),
                    (TipoDocumentoIdentidad) personalView.cbxTDocPersona.getSelectedItem()
                )
            );
            persona.setTipoPersona((TipoPersona) personalView.cbxTipoPersona.getSelectedItem());
            Usuario usuario = new Usuario(
                0,
                personalView.txtUsuario.getText(),
                personalView.txtContrasena.getText(),
                (Roles)personalView.cbxRol.getSelectedItem()
            );
            
            personaDAO.modificarPersona(persona, usuario);
            
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
        if (personalView.txtNombrePersona.getText().trim().equals(""))
            msgValidacion += "- Ingresar nombre.\n";
        if (personalView.txtApePersona.getText().trim().equals(""))
            msgValidacion += "- Ingresar apellidos.\n";
        if (personalView.txtTelPersona.getText().trim().equals(""))
            msgValidacion += "- Ingresar teléfono.\n";
        if (personalView.txtDocPersona.getText().trim().equals(""))
            msgValidacion += "- Ingresar Nro. documento.\n";
        
        if (((Roles) personalView.cbxRol.getSelectedItem()).getIdRol() != Constants.ID_ROL_NINGUNO) {
            if (personalView.txtUsuario.getText().trim().equals(""))
                msgValidacion += "- Ingresar nombre de usuario.\n";
            
            if (personalView.txtContrasena.getText().trim().equals(""))
                msgValidacion += "- Ingresar contraseña de usuario.\n";
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
        
        if (this.op.equals(Constants.OP_NEW))
            insertar();
        else if (this.op.equals(Constants.OP_EDIT))
            modificar();
        
        this.op = Constants.OP_EDIT;
        personalView.lblOpPersona.setText("( EDITAR )");
        
        buscar();
    }
    
}
