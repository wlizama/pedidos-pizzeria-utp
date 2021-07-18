/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.view;

import pedidos.pizzeria.utils.Helpers;

/**
 *
 * @author wilderlizama
 */
public class MainFormView extends javax.swing.JFrame {

    /**
     * Creates new form MainFormView
     */
    public MainFormView() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dpPrincipal = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        mPedidos = new javax.swing.JMenu();
        smLPedidos = new javax.swing.JMenuItem();
        smComprobantes = new javax.swing.JMenuItem();
        mDelivery = new javax.swing.JMenu();
        smEnvios = new javax.swing.JMenuItem();
        mMantenimiento = new javax.swing.JMenu();
        smClientes = new javax.swing.JMenuItem();
        smPersonal = new javax.swing.JMenuItem();
        smCobertura = new javax.swing.JMenuItem();
        smPizzas = new javax.swing.JMenuItem();
        mReportes = new javax.swing.JMenu();
        smRepVentas = new javax.swing.JMenuItem();
        smRepPizzas = new javax.swing.JMenuItem();
        smRepCobertura = new javax.swing.JMenuItem();
        mSession = new javax.swing.JMenu();
        smLogout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedidos/pizzeria/resources/icons/notes-24.png"))); // NOI18N
        mPedidos.setMnemonic('p');
        mPedidos.setText("Pedidos");

        smLPedidos.setMnemonic('l');
        smLPedidos.setText("Lista Pedidos");
        smLPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smLPedidosActionPerformed(evt);
            }
        });
        mPedidos.add(smLPedidos);

        smComprobantes.setMnemonic('c');
        smComprobantes.setText("Comprobantes");
        smComprobantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smComprobantesActionPerformed(evt);
            }
        });
        mPedidos.add(smComprobantes);

        menuBar.add(mPedidos);

        mDelivery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedidos/pizzeria/resources/icons/delivery-man-24.png"))); // NOI18N
        mDelivery.setMnemonic('d');
        mDelivery.setText("Delivery");

        smEnvios.setMnemonic('e');
        smEnvios.setText("Envios");
        smEnvios.setToolTipText("");
        smEnvios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smEnviosActionPerformed(evt);
            }
        });
        mDelivery.add(smEnvios);

        menuBar.add(mDelivery);

        mMantenimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedidos/pizzeria/resources/icons/settings-24.png"))); // NOI18N
        mMantenimiento.setMnemonic('m');
        mMantenimiento.setText("Mantenimiento");

        smClientes.setMnemonic('c');
        smClientes.setText("Clientes");
        smClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smClientesActionPerformed(evt);
            }
        });
        mMantenimiento.add(smClientes);

        smPersonal.setMnemonic('p');
        smPersonal.setText("Personal");
        smPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smPersonalActionPerformed(evt);
            }
        });
        mMantenimiento.add(smPersonal);

        smCobertura.setMnemonic('b');
        smCobertura.setText("Cobertura");
        smCobertura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smCoberturaActionPerformed(evt);
            }
        });
        mMantenimiento.add(smCobertura);

        smPizzas.setMnemonic('z');
        smPizzas.setText("Pizzas");
        smPizzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smPizzasActionPerformed(evt);
            }
        });
        mMantenimiento.add(smPizzas);

        menuBar.add(mMantenimiento);

        mReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedidos/pizzeria/resources/icons/trend-24.png"))); // NOI18N
        mReportes.setMnemonic('r');
        mReportes.setText("Reportes");

        smRepVentas.setMnemonic('v');
        smRepVentas.setText("Reporte de Ventas");
        mReportes.add(smRepVentas);

        smRepPizzas.setMnemonic('p');
        smRepPizzas.setText("Reporte de Pizzas");
        mReportes.add(smRepPizzas);

        smRepCobertura.setMnemonic('c');
        smRepCobertura.setText("Reporte de Cobertura");
        mReportes.add(smRepCobertura);

        menuBar.add(mReportes);

        mSession.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedidos/pizzeria/resources/icons/user-24.png"))); // NOI18N
        mSession.setMnemonic('s');
        mSession.setText("Mi session");

        smLogout.setMnemonic('l');
        smLogout.setText("Logout");
        mSession.add(smLogout);

        menuBar.add(mSession);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void smPizzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smPizzasActionPerformed

        ListaPizzasView pizzaView = new ListaPizzasView();
        pizzaView.pack();
        dpPrincipal.add(pizzaView);
        Helpers.centerForm(dpPrincipal, pizzaView);
        pizzaView.setVisible(true);
    }//GEN-LAST:event_smPizzasActionPerformed

    private void smPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smPersonalActionPerformed
        // TODO add your handling code here:
        ListaPersonalView personalView = new ListaPersonalView();
        personalView.pack();
        dpPrincipal.add(personalView);
        Helpers.centerForm(dpPrincipal, personalView);
        personalView.setVisible(true);
    }//GEN-LAST:event_smPersonalActionPerformed

    private void smClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smClientesActionPerformed
        ListaClientesView clientesView = new ListaClientesView();
        clientesView.pack();
        dpPrincipal.add(clientesView);
        Helpers.centerForm(dpPrincipal, clientesView);
        clientesView.setVisible(true);
    }//GEN-LAST:event_smClientesActionPerformed

    private void smCoberturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smCoberturaActionPerformed
        ListaCoberturaView coberturaView = new ListaCoberturaView();
        coberturaView.pack();
        dpPrincipal.add(coberturaView);
        Helpers.centerForm(dpPrincipal, coberturaView);
        coberturaView.setVisible(true);
    }//GEN-LAST:event_smCoberturaActionPerformed

    private void smLPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smLPedidosActionPerformed
        PedidoListaView pedidolistaView = new PedidoListaView();
        pedidolistaView.pack();
        dpPrincipal.add(pedidolistaView);
        Helpers.centerForm(dpPrincipal, pedidolistaView);
        pedidolistaView.setVisible(true);
    }//GEN-LAST:event_smLPedidosActionPerformed

    private void smEnviosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smEnviosActionPerformed
        EnvioListaView listaEnviosView = new EnvioListaView();
        listaEnviosView.pack();
        dpPrincipal.add(listaEnviosView);
        Helpers.centerForm(dpPrincipal, listaEnviosView);
        listaEnviosView.setVisible(true);
    }//GEN-LAST:event_smEnviosActionPerformed

    private void smComprobantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smComprobantesActionPerformed
        // TODO add your handling code here:
        ListaComprobanteView comprobantelistaView = new ListaComprobanteView();
        comprobantelistaView.pack();
        dpPrincipal.add(comprobantelistaView);
        Helpers.centerForm(dpPrincipal, comprobantelistaView);
        comprobantelistaView.setVisible(true);
        
    }//GEN-LAST:event_smComprobantesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFormView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFormView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFormView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFormView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFormView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane dpPrincipal;
    private javax.swing.JMenu mDelivery;
    private javax.swing.JMenu mMantenimiento;
    private javax.swing.JMenu mPedidos;
    private javax.swing.JMenu mReportes;
    private javax.swing.JMenu mSession;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem smClientes;
    private javax.swing.JMenuItem smCobertura;
    private javax.swing.JMenuItem smComprobantes;
    private javax.swing.JMenuItem smEnvios;
    private javax.swing.JMenuItem smLPedidos;
    private javax.swing.JMenuItem smLogout;
    private javax.swing.JMenuItem smPersonal;
    private javax.swing.JMenuItem smPizzas;
    private javax.swing.JMenuItem smRepCobertura;
    private javax.swing.JMenuItem smRepPizzas;
    private javax.swing.JMenuItem smRepVentas;
    // End of variables declaration//GEN-END:variables

}
