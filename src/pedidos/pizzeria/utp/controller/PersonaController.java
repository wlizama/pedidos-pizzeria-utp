/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import pedidos.pizzeria.utils.Constants;
import pedidos.pizzeria.utp.dao.*;
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
        
        limpiarForm();
        buscar();
        this.op = Constants.OP_NEW;
        personalView.lblOpPersona.setText("( NUEVO )");
    }

    @Override
    public void limpiarForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
