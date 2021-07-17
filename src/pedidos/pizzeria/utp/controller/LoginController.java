/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import pedidos.pizzeria.utp.dao.LoginDAO;
import pedidos.pizzeria.utp.view.LoginView;
import pedidos.pizzeria.utp.view.MainFormView;

/**
 *
 * @author wilderlizama
 */
public class LoginController {
    
    LoginView loginView;
    LoginDAO loginDAO;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.loginDAO = new LoginDAO();
        
        loginView.lblExit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("Bye bro");
                loginView.dispose();
            }
        });
        
        loginView.txtContrasenha.addActionListener((ae) -> {
            login();
        });
        
        loginView.btnIngresar.addActionListener((ae) -> {
            login();
        });
    }
    
    private void login() {
        try {
            String nombreUsuario = loginView.txtUsuario.getText().trim();
            String contrasenha = loginView.txtContrasenha.getText().trim();
            
            if (!nombreUsuario.equals("") && !contrasenha.equals("")) {
                int IdUsuario = loginDAO.verificaUsuario(nombreUsuario, contrasenha);
                if (IdUsuario > 0) {
                    MainFormController mainFormController = new MainFormController(new MainFormView(), IdUsuario);
                    loginView.dispose();
                    mainFormController.mostrar();
                }
                else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Usuario / Contraseña incorrectos",
                        "Mensaje",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
            else {
                JOptionPane.showMessageDialog(
                    null,
                    "Debe ingresar todos los datos (usuario/contraseña)",
                    "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        } catch (Exception e) {
            e.getStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error initCombos: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
