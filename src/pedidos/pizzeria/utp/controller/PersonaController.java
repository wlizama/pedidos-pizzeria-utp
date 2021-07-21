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
import pedidos.pizzeria.utp.dao.*;
import pedidos.pizzeria.utp.model.Cliente;
import pedidos.pizzeria.utp.model.Persona;
import pedidos.pizzeria.utp.model.TipoDocumentoIdentidad;
import pedidos.pizzeria.utp.view.ListaPersonalView;

/**
 *
 * @author jonas
 */
public class PersonaController implements BaseControllerInterface{
    
    String op;
    int IdRol_edit;
    
    ListaPersonalView personalView;
    PersonaDAO personaDAO;    

    public PersonaController(ListaPersonalView personalView) {
        this.personalView = personalView;
        this.personaDAO = new PersonaDAO();
        
        // eventos de form
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
        this.op = Constants.OP_NEW;
        personalView.lblOpPersona.setText("( NUEVO )");
    }
    
    private void initCombos() {
        try {
            List<TipoDocumentoIdentidad> tipoDocumentoIdentidadDAO = new TipoDocumentoIdentidadDAO().getListaTipoDocumentoIdentidad();
            
            personalView.cboTipoDcoumentoIdentidad.setModel(new DefaultComboBoxModel());
            personalView.cboDocumento.setModel(new DefaultComboBoxModel()); 
            
            personalView.cboTipoDcoumentoIdentidad.addItem("" + new TipoDocumentoIdentidad(0, "Todos", 0)); 
            personalView.cboDocumento.addItem("" + new TipoDocumentoIdentidad(0, "Todos", 0)); 
            for (TipoDocumentoIdentidad tipoDocumentoIdentidad : tipoDocumentoIdentidadDAO) {
                personalView.cboTipoDcoumentoIdentidad.addItem(""+tipoDocumentoIdentidad);
                personalView.cboDocumento.addItem(""+tipoDocumentoIdentidad);
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
    public void buscar() {
        
        try {
            
            TipoDocumentoIdentidad tipoDocIdentidad_selected = (TipoDocumentoIdentidad) personalView.cboTipoDcoumentoIdentidad.getSelectedItem();
            int idtipoDocIdentidad = tipoDocIdentidad_selected.getIdTipoDocIdentidad();
            String numeroDocIdentidad = personalView.txtDocumento.getText().trim();
            
            List<Persona> lstPersona = personaDAO.getListaPersona(idtipoDocIdentidad, numeroDocIdentidad);
            
            DefaultTableModel personaviewTblModel = (DefaultTableModel) personalView.tblListaPersonal.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(personaviewTblModel);

            if (lstPersona.size() > 0) {
                for (Persona persona : lstPersona) {
                    personaviewTblModel.addRow(new Object[] {
                        persona.getIdPersona(),
                        persona.getNombres(),
                        persona.getTelefono(),
                        persona.getEstado()
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
    public void limpiarForm() {
        System.out.println("");
    }

    @Override
    public void obtener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nuevo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
