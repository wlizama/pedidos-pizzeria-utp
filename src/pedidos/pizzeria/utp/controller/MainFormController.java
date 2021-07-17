/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import static java.awt.Frame.MAXIMIZED_BOTH;
import pedidos.pizzeria.utp.view.MainFormView;

/**
 *
 * @author wilderlizama
 */
public class MainFormController {
    int IdUsuario;
    MainFormView mainFormView;

    public MainFormController(MainFormView mainFormView, int IdUsuario) {
        this.mainFormView = mainFormView;
        this.IdUsuario = IdUsuario;
    }
    
    public void mostrar() {
        
        mainFormView.setExtendedState(MAXIMIZED_BOTH);
        mainFormView.setVisible(true);
    }
}
