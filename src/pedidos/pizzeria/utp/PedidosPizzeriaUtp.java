/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp;

import pedidos.pizzeria.utp.view.LoginView;

/**
 *
 * @author wilderlizama
 */
public class PedidosPizzeriaUtp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Iniciando app");
        
        LoginView loginView = new LoginView();
        loginView.setLocationRelativeTo(null);
        loginView.setVisible(true);

    }
    
}
