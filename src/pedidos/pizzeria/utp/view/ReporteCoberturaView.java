/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.view;

import java.text.SimpleDateFormat;
import org.freixas.jcalendar.JCalendarCombo;
import pedidos.pizzeria.utp.controller.ReporteCoberturaController;

/**
 *
 * @author jonas
 */
public class ReporteCoberturaView extends javax.swing.JInternalFrame {
    
    ReporteCoberturaController reporteCoberturaController;
    /**
     * Creates new form ReporteCoberturaView
     */
    public ReporteCoberturaView() {
        initComponents();
        dtFechaInicio.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        dtFechaFin.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        reporteCoberturaController = new ReporteCoberturaController(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCobertura = new javax.swing.JTable();
        btnGenerarReporte = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        lblPrecioTotal = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cboDistrito = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        dtFechaInicio = new org.freixas.jcalendar.JCalendarCombo(JCalendarCombo.DISPLAY_DATE | JCalendarCombo.DISPLAY_TIME,
            true);
        jLabel3 = new javax.swing.JLabel();
        dtFechaFin = new org.freixas.jcalendar.JCalendarCombo(JCalendarCombo.DISPLAY_DATE | JCalendarCombo.DISPLAY_TIME,
            true);

        setClosable(true);
        setMaximizable(true);

        tblCobertura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Distritos", "Cantidad de Pedidos", "Precio Total"
            }
        ));
        jScrollPane1.setViewportView(tblCobertura);
        if (tblCobertura.getColumnModel().getColumnCount() > 0) {
            tblCobertura.getColumnModel().getColumn(0).setPreferredWidth(200);
        }

        btnGenerarReporte.setText("Generar");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setText("TOTALES");

        lblCantidad.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblCantidad.setText("000");

        lblPrecioTotal.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblPrecioTotal.setText("S/. 00.00");

        jLabel1.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        jLabel1.setText("Reporte de Cobertura");

        jLabel8.setText("Distrito");

        jLabel2.setText("Fecha Inicio");

        dtFechaInicio.setDate(new java.util.Date(1626366459000L));

        jLabel3.setText("Fecha Fin");

        dtFechaFin.setDate(new java.util.Date(1626366459000L));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboDistrito, 0, 185, Short.MAX_VALUE)
                            .addComponent(dtFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(btnGenerarReporte))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 312, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(53, 53, 53)
                        .addComponent(lblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(lblPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerarReporte)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(dtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cboDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecioTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(lblCantidad)))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnGenerarReporte;
    public javax.swing.JComboBox cboDistrito;
    public org.freixas.jcalendar.JCalendarCombo dtFechaFin;
    public org.freixas.jcalendar.JCalendarCombo dtFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblCantidad;
    public javax.swing.JLabel lblPrecioTotal;
    public javax.swing.JTable tblCobertura;
    // End of variables declaration//GEN-END:variables
}
