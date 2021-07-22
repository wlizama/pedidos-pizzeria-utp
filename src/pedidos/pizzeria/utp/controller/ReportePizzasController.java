/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pedidos.pizzeria.utils.Helpers;
import pedidos.pizzeria.utp.dao.ReportePizzasDAO;
import pedidos.pizzeria.utp.dao.ReporteVentasDAO;
import pedidos.pizzeria.utp.model.Comprobante;
import pedidos.pizzeria.utp.model.DetallePedido;
import pedidos.pizzeria.utp.view.ReportePizzasView;
import pedidos.pizzeria.utp.view.ReporteVentasView;

/**
 *
 * @author jonas
 */
public class ReportePizzasController {
    
    String op;
    int IdTamanhoPizza_edit;
    
    ReportePizzasView reportePizzasView;
    ReportePizzasDAO reportePizzasDAO;
    
    public ReportePizzasController(ReportePizzasView reportePizzasView) {
        this.reportePizzasView = reportePizzasView;        
        this.reportePizzasDAO = new ReportePizzasDAO();
        
        // eventos de form
        reportePizzasView.btnGenerarReporte.addActionListener((ae) -> {
            generar();            
        });

        
    }
    
    
    public void generar() {

        try {
            double total=0.00;
            double totFormateado=0.00;
            int cantidad=0;
            
            Date fechai = reportePizzasView.dtFechaInicio.getDate();
            Date fechaf = reportePizzasView.dtFechaFin.getDate();
                     
            java.sql.Date fechaini = convertJavaDateToSqlDate(fechai);
            java.sql.Date fechafin = convertJavaDateToSqlDate(fechaf);
            
            List<DetallePedido> lstComprobante = reportePizzasDAO.getReportePizzas(fechaini,fechafin);
            
            DefaultTableModel detalleViewTblModel = (DefaultTableModel) reportePizzasView.tblPizzas.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(detalleViewTblModel);
            
            if (lstComprobante.size() > 0) {
                for (DetallePedido detallePedido : lstComprobante) {
                    detalleViewTblModel.addRow(new Object[] {
                        detallePedido.getPizza().getTipoPizza().getNombre(),
                        detallePedido.getPizza().getTamanhoPizza().getNombre(),
                        detallePedido.getCantidad(),
                        detallePedido.getPizza().getPrecio()
                    });
                    cantidad = cantidad + detallePedido.getCantidad();
                    total =total+detallePedido.getPizza().getPrecio();
                }
            }
            totFormateado = Math.round(total * Math.pow(10, 2)) / Math.pow(10, 2);
            
            reportePizzasView.lblTotal.setText("S/."+totFormateado);
            reportePizzasView.lblCantidad.setText(""+cantidad);
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
