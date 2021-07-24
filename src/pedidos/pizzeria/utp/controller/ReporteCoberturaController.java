/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pedidos.pizzeria.utils.Helpers;
import pedidos.pizzeria.utp.dao.DireccionClienteDAO;
import pedidos.pizzeria.utp.dao.DistritoDAO;
import pedidos.pizzeria.utp.dao.ReporteCoberturaDAO;
import pedidos.pizzeria.utp.model.DetallePedido;
import pedidos.pizzeria.utp.model.Distrito;
import pedidos.pizzeria.utp.view.*;

/**
 *
 * @author jonas
 */
public class ReporteCoberturaController {
    
    ReporteCoberturaView reporteCoberturaView;    
    
    ReporteCoberturaController reporteCoberturaController;
    
    ReporteCoberturaDAO reporteCoberturaDAO;
    
    public ReporteCoberturaController(ReporteCoberturaView reporteCoberturaView) {
        this.reporteCoberturaView = reporteCoberturaView;
        this.reporteCoberturaDAO = new ReporteCoberturaDAO();
        
        // eventos de form
        
        reporteCoberturaView.btnGenerarReporte.addActionListener((ae) -> {
            generar();
        });
        
        

        initCombo();
    }
    
    private void initCombo() {
        try {
            List<Distrito> distritos = new DistritoDAO().getListaDistrito();
            
            reporteCoberturaView.cboDistrito.setModel(new DefaultComboBoxModel());
            reporteCoberturaView.cboDistrito.addItem( new Distrito(0, "Seleccionar Distrito", true));
            for (Distrito distrito : distritos) {
                reporteCoberturaView.cboDistrito.addItem(distrito);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Error initCombo: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    
    private void generar() {
        
        try {
            double total=0.00;
            double totFormateado=0.00;
            int cantidad=0;
            Date fechai = reporteCoberturaView.dtFechaInicio.getDate();
            Date fechaf = reporteCoberturaView.dtFechaFin.getDate();
            int distrito = ((Distrito) reporteCoberturaView.cboDistrito.getModel().getSelectedItem()).getIdDistrito();
            java.sql.Date fechaini = convertJavaDateToSqlDate(fechai);
            java.sql.Date fechafin = convertJavaDateToSqlDate(fechaf);
            List<DetallePedido> lstComprobante = reporteCoberturaDAO.getReporteCobertura(fechaini,fechafin,distrito);
            
            DefaultTableModel detalleViewTblModel = (DefaultTableModel) reporteCoberturaView.tblCobertura.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(detalleViewTblModel);
            
            if (lstComprobante.size() > 0) {
                for (DetallePedido detallePedido : lstComprobante) {
                    detalleViewTblModel.addRow(new Object[] {
                        detallePedido.getPedido().getDireccionEnvio().getDistrito().getNombre(),
                        detallePedido.getCantidad(),
                        detallePedido.getPizza().getPrecio()
                    });
                    total =total+detallePedido.getPizza().getPrecio();
                    cantidad = cantidad + detallePedido.getCantidad();
                }
            }
            totFormateado = Math.round(total * Math.pow(10, 2)) / Math.pow(10, 2);
            reporteCoberturaView.lblPrecioTotal.setText("S/."+totFormateado);
            reporteCoberturaView.lblCantidad.setText(""+cantidad);
            
        } catch (Exception e) {
             e.getStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error buscar xxxx: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
        
    }

    public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }
    
}
