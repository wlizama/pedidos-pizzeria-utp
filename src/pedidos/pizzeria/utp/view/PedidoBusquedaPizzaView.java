/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.view;

/**
 *
 * @author wilderlizama
 */
public class PedidoBusquedaPizzaView extends javax.swing.JDialog {

    /**
     * Creates new form PedidoBusquedaPizzaView
     */
    public PedidoBusquedaPizzaView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        btnSeleccionar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtNombrePizza = new javax.swing.JTextField();
        btnBuscarPizza = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListaPizza = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedidos/pizzeria/resources/icons/check-16.png"))); // NOI18N
        btnSeleccionar.setText("Seleccionar");

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedidos/pizzeria/resources/icons/undo-16.png"))); // NOI18N
        btnRegresar.setText("Regresar");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Pizza"));

        jLabel8.setText("Nombre");

        btnBuscarPizza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pedidos/pizzeria/resources/icons/search-16.png"))); // NOI18N
        btnBuscarPizza.setText("Buscar");

        tblListaPizza.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Pizza", "Tipo", "Tama??o", "Porciones", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListaPizza.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblListaPizza);
        if (tblListaPizza.getColumnModel().getColumnCount() > 0) {
            tblListaPizza.getColumnModel().getColumn(0).setResizable(false);
            tblListaPizza.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblListaPizza.getColumnModel().getColumn(1).setResizable(false);
            tblListaPizza.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblListaPizza.getColumnModel().getColumn(2).setResizable(false);
            tblListaPizza.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblListaPizza.getColumnModel().getColumn(3).setResizable(false);
            tblListaPizza.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblListaPizza.getColumnModel().getColumn(4).setResizable(false);
            tblListaPizza.getColumnModel().getColumn(4).setPreferredWidth(80);
            tblListaPizza.getColumnModel().getColumn(5).setResizable(false);
            tblListaPizza.getColumnModel().getColumn(5).setPreferredWidth(70);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombrePizza, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarPizza)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombrePizza, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarPizza))
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSeleccionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegresar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionar)
                    .addComponent(btnRegresar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBuscarPizza;
    public javax.swing.JButton btnRegresar;
    public javax.swing.JButton btnSeleccionar;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable tblListaPizza;
    public javax.swing.JTextField txtNombrePizza;
    // End of variables declaration//GEN-END:variables
}
