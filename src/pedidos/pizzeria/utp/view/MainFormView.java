/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.view;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageIcon icon = new ImageIcon(getClass().getResource("/pedidos/pizzeria/resources/images/bg-01.jpg"));
        Image image = icon.getImage();
        dpPrincipal = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g){
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
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

        menuBar.setBackground(new java.awt.Color(51, 51, 51));
        menuBar.setForeground(new java.awt.Color(255, 255, 255));

        mPedidos.setForeground(new java.awt.Color(255, 255, 255));
        mPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedidos/pizzeria/resources/icons/notes-24.png"))); // NOI18N
        mPedidos.setMnemonic('p');
        mPedidos.setText("Pedidos");
        mPedidos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        mPedidos.setPreferredSize(new java.awt.Dimension(150, 50));

        smLPedidos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        smLPedidos.setMnemonic('l');
        smLPedidos.setText("Lista Pedidos");
        smLPedidos.setPreferredSize(new java.awt.Dimension(150, 30));
        smLPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smLPedidosActionPerformed(evt);
            }
        });
        mPedidos.add(smLPedidos);

        smComprobantes.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        smComprobantes.setMnemonic('c');
        smComprobantes.setText("Comprobantes");
        smComprobantes.setPreferredSize(new java.awt.Dimension(150, 30));
        smComprobantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smComprobantesActionPerformed(evt);
            }
        });
        mPedidos.add(smComprobantes);

        menuBar.add(mPedidos);

        mDelivery.setForeground(new java.awt.Color(255, 255, 255));
        mDelivery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedidos/pizzeria/resources/icons/delivery-man-24.png"))); // NOI18N
        mDelivery.setMnemonic('d');
        mDelivery.setText("Delivery");
        mDelivery.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        mDelivery.setPreferredSize(new java.awt.Dimension(150, 50));

        smEnvios.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        smEnvios.setMnemonic('e');
        smEnvios.setText("Envios");
        smEnvios.setToolTipText("");
        smEnvios.setPreferredSize(new java.awt.Dimension(150, 30));
        smEnvios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smEnviosActionPerformed(evt);
            }
        });
        mDelivery.add(smEnvios);

        menuBar.add(mDelivery);

        mMantenimiento.setForeground(new java.awt.Color(255, 255, 255));
        mMantenimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedidos/pizzeria/resources/icons/settings-24.png"))); // NOI18N
        mMantenimiento.setMnemonic('m');
        mMantenimiento.setText("Mantenimiento");
        mMantenimiento.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        mMantenimiento.setPreferredSize(new java.awt.Dimension(190, 50));

        smClientes.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        smClientes.setMnemonic('c');
        smClientes.setText("Clientes");
        smClientes.setPreferredSize(new java.awt.Dimension(150, 30));
        smClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smClientesActionPerformed(evt);
            }
        });
        mMantenimiento.add(smClientes);

        smPersonal.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        smPersonal.setMnemonic('p');
        smPersonal.setText("Personal");
        smPersonal.setPreferredSize(new java.awt.Dimension(150, 30));
        smPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smPersonalActionPerformed(evt);
            }
        });
        mMantenimiento.add(smPersonal);

        smCobertura.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        smCobertura.setMnemonic('b');
        smCobertura.setText("Cobertura");
        smCobertura.setPreferredSize(new java.awt.Dimension(150, 30));
        smCobertura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smCoberturaActionPerformed(evt);
            }
        });
        mMantenimiento.add(smCobertura);

        smPizzas.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        smPizzas.setMnemonic('z');
        smPizzas.setText("Pizzas");
        smPizzas.setPreferredSize(new java.awt.Dimension(150, 30));
        smPizzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smPizzasActionPerformed(evt);
            }
        });
        mMantenimiento.add(smPizzas);

        menuBar.add(mMantenimiento);

        mReportes.setForeground(new java.awt.Color(255, 255, 255));
        mReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedidos/pizzeria/resources/icons/trend-24.png"))); // NOI18N
        mReportes.setMnemonic('r');
        mReportes.setText("Reportes");
        mReportes.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        mReportes.setPreferredSize(new java.awt.Dimension(150, 50));

        smRepVentas.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        smRepVentas.setMnemonic('v');
        smRepVentas.setText("Reporte de Ventas");
        smRepVentas.setPreferredSize(new java.awt.Dimension(150, 30));
        smRepVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smRepVentasActionPerformed(evt);
            }
        });
        mReportes.add(smRepVentas);

        smRepPizzas.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        smRepPizzas.setMnemonic('p');
        smRepPizzas.setText("Reporte de Pizzas");
        smRepPizzas.setPreferredSize(new java.awt.Dimension(150, 30));
        smRepPizzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smRepPizzasActionPerformed(evt);
            }
        });
        mReportes.add(smRepPizzas);

        smRepCobertura.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        smRepCobertura.setMnemonic('c');
        smRepCobertura.setText("Reporte de Cobertura");
        smRepCobertura.setPreferredSize(new java.awt.Dimension(150, 30));
        smRepCobertura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smRepCoberturaActionPerformed(evt);
            }
        });
        mReportes.add(smRepCobertura);

        menuBar.add(mReportes);

        mSession.setForeground(new java.awt.Color(255, 255, 255));
        mSession.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedidos/pizzeria/resources/icons/user-24.png"))); // NOI18N
        mSession.setMnemonic('s');
        mSession.setText("Mi session");
        mSession.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        mSession.setPreferredSize(new java.awt.Dimension(150, 50));

        smLogout.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        smLogout.setMnemonic('l');
        smLogout.setText("Logout");
        smLogout.setPreferredSize(new java.awt.Dimension(150, 30));
        smLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smLogoutActionPerformed(evt);
            }
        });
        mSession.add(smLogout);

        menuBar.add(mSession);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
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

    private void smEnviosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smEnviosActionPerformed
        EnvioListaView listaEnviosView = new EnvioListaView();
        listaEnviosView.pack();
        dpPrincipal.add(listaEnviosView);
        Helpers.centerForm(dpPrincipal, listaEnviosView);
        listaEnviosView.setVisible(true);
    }//GEN-LAST:event_smEnviosActionPerformed
    
    private void smLogoutActionPerformed(java.awt.event.ActionEvent evt) {                                         
        this.dispose();
    }                                        

    private void smRepCoberturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smRepCoberturaActionPerformed
        ReporteCoberturaView reporteCoberturaView = new ReporteCoberturaView();
        reporteCoberturaView.pack();
        dpPrincipal.add(reporteCoberturaView);
        Helpers.centerForm(dpPrincipal, reporteCoberturaView);
        reporteCoberturaView.setVisible(true);
    }//GEN-LAST:event_smRepCoberturaActionPerformed

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
    }
                                        

    private void smComprobantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smComprobantesActionPerformed
        // TODO add your handling code here:
        ListaComprobanteView comprobantelistaView = new ListaComprobanteView();
        comprobantelistaView.pack();
        dpPrincipal.add(comprobantelistaView);
        Helpers.centerForm(dpPrincipal, comprobantelistaView);
        comprobantelistaView.setVisible(true);

    }//GEN-LAST:event_smComprobantesActionPerformed

    private void smLPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smLPedidosActionPerformed
        PedidoListaView pedidolistaView = new PedidoListaView();
        pedidolistaView.pack();
        dpPrincipal.add(pedidolistaView);
        Helpers.centerForm(dpPrincipal, pedidolistaView);
        pedidolistaView.setVisible(true);
    }//GEN-LAST:event_smLPedidosActionPerformed

    private void smRepVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smRepVentasActionPerformed
        // TODO add your handling code here:
        ReporteVentasView reporteVentasView = new ReporteVentasView();
        reporteVentasView.pack();
        dpPrincipal.add(reporteVentasView);
        Helpers.centerForm(dpPrincipal, reporteVentasView);
        reporteVentasView.setVisible(true);
    }//GEN-LAST:event_smRepVentasActionPerformed

    private void smRepPizzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smRepPizzasActionPerformed
        // TODO add your handling code here:
        ReportePizzasView reportePizzasView = new ReportePizzasView();
        reportePizzasView.pack();
        dpPrincipal.add(reportePizzasView);
        Helpers.centerForm(dpPrincipal, reportePizzasView);
        reportePizzasView.setVisible(true);
    }//GEN-LAST:event_smRepPizzasActionPerformed
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane dpPrincipal;
    public javax.swing.JMenu mDelivery;
    public javax.swing.JMenu mMantenimiento;
    public javax.swing.JMenu mPedidos;
    public javax.swing.JMenu mReportes;
    public javax.swing.JMenu mSession;
    private javax.swing.JMenuBar menuBar;
    public javax.swing.JMenuItem smClientes;
    public javax.swing.JMenuItem smCobertura;
    private javax.swing.JMenuItem smComprobantes;
    private javax.swing.JMenuItem smEnvios;
    private javax.swing.JMenuItem smLPedidos;
    private javax.swing.JMenuItem smLogout;
    public javax.swing.JMenuItem smPersonal;
    public javax.swing.JMenuItem smPizzas;
    private javax.swing.JMenuItem smRepCobertura;
    private javax.swing.JMenuItem smRepPizzas;
    private javax.swing.JMenuItem smRepVentas;
    // End of variables declaration//GEN-END:variables

}
