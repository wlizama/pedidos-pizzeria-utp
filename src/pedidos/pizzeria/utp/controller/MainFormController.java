/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import static java.awt.Frame.MAXIMIZED_BOTH;
import java.util.List;
import javax.swing.JOptionPane;
import pedidos.pizzeria.utils.Constants;
import pedidos.pizzeria.utp.dao.AccesoDAO;
import pedidos.pizzeria.utp.model.Formulario;
import pedidos.pizzeria.utp.model.Usuario;
import pedidos.pizzeria.utp.view.MainFormView;

/**
 *
 * @author wilderlizama
 */
public class MainFormController {
    Usuario usuario;
    MainFormView mainFormView;

    public MainFormController(MainFormView mainFormView, Usuario usuario) {
        this.mainFormView = mainFormView;
        this.usuario = usuario;
    }
    
    public void mostrar() {
        
        mainFormView.setExtendedState(MAXIMIZED_BOTH);
        validarAccesos();
        mainFormView.setVisible(true);
    }
    
    private void validarAccesos() {
        try {
            // consultar lista y validar cada menu por su valor constante
            AccesoDAO accesoDAO = new AccesoDAO();
            List<Formulario> lstFormularios = accesoDAO.getListaAccesoFormulario(usuario.getIdUsuario());
            
            mainFormView.mPedidos.setVisible(tieneAcceso(lstFormularios, Constants.ID_MENU_PEDIDOS));
            mainFormView.mDelivery.setVisible(tieneAcceso(lstFormularios, Constants.ID_MENU_DELIVERY));
            mainFormView.mMantenimiento.setVisible(tieneAcceso(lstFormularios, Constants.ID_MENU_MANTENIMIENTO));
            mainFormView.mReportes.setVisible(tieneAcceso(lstFormularios, Constants.ID_MENU_REPORTES));
            mainFormView.mSession.setVisible(tieneAcceso(lstFormularios, Constants.ID_MENU_MISESSION));
            
            // aca valida a submenu
            if (usuario.getRol().getIdRol() == Constants.ID_ROL_CAJERO) {
                mainFormView.smPersonal.setVisible(false);
                mainFormView.smCobertura.setVisible(false);
                mainFormView.smPizzas.setVisible(false);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error validar acceso: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private boolean tieneAcceso(List<Formulario> lstFormularios, int IdFormulario) {
        boolean tieneAcceso = false;
        for (Formulario formulario : lstFormularios) {
            if(formulario.getIdFormulario() == IdFormulario)
                tieneAcceso = true;
        }
        return tieneAcceso;
    }
}
