/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pedidos.pizzeria.utils.Constants;
import pedidos.pizzeria.utils.Helpers;
import pedidos.pizzeria.utp.dao.*;
import pedidos.pizzeria.utp.model.*;
import pedidos.pizzeria.utp.view.*;

/**
 *
 * @author jonas
 */
public class ReporteVentasController {
    
    String op;
    int IdTamanhoPizza_edit;
    
    ReporteVentasView reporteVentasView;
    ReporteVentasDAO reporteVentasDAO;
    
    public ReporteVentasController(ReporteVentasView reporteVentasView) {
        this.reporteVentasView = reporteVentasView;        
        this.reporteVentasDAO = new ReporteVentasDAO();
        
        // eventos de form
        reporteVentasView.btnGenerarReporte.addActionListener((ae) -> {
            generar();            
        });

        
    }
    
    
    public void generar() {

        try {
            double total=0.00;
            double totFormateado=0.00;            
            Date fechai = reporteVentasView.dtFechaInicio.getDate();
            Date fechaf = reporteVentasView.dtFechaFin.getDate();                        
            //java.sql.Date fechaini = convertJavaDateToSqlDate(fechai);
            //java.sql.Date fechafin = convertJavaDateToSqlDate(fechaf);                        

            List<Comprobante> lstComprobante = reporteVentasDAO.getReporteVentas(fechai,fechaf);
            
            DefaultTableModel comprobanteViewTblModel = (DefaultTableModel) reporteVentasView.tblVentas.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(comprobanteViewTblModel);
            
            if (lstComprobante.size() > 0) {
                for (Comprobante comprobante : lstComprobante) {
                    comprobanteViewTblModel.addRow(new Object[] {
                        comprobante.getNumero(),
                        comprobante.getTipoComprobante().getNombre(),
                        comprobante.getFechaEmision(),
                        comprobante.getMonto()        
                        
                    });
                    total = total +comprobante.getMonto();
                    System.out.println("1");
                }
            }
            totFormateado = Math.round(total * Math.pow(10, 2)) / Math.pow(10, 2);
            reporteVentasView.lblTotal.setText("S/."+totFormateado);
            
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
