/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pedidos.pizzeria.utils.Helpers;
import pedidos.pizzeria.utp.dao.ComprobanteDAO;
import pedidos.pizzeria.utp.model.ComprobantePedidoDetalle_Cabecera;
import pedidos.pizzeria.utp.model.ComprobantePedidoDetalle_Lista;
import pedidos.pizzeria.utp.model.TipoPizza;
import pedidos.pizzeria.utp.view.ComprobanteDetalleView;

/**
 *
 * @author Percy
 */
public class ComprobanteController {
    ListaComprobanteController listacomprobanteController;
    JDesktopPane jdesktopPane;
    ComprobanteDetalleView comprobantedetalleView;
    ComprobantePedidoDetalle_Lista comprobantepedidodetaleLista;
    ComprobantePedidoDetalle_Cabecera comprobantepedidodetaleCabecera;
    ComprobanteDAO comprobanteDAO;
    
    ComprobantePedidoDetalle_Cabecera comPedDetCab;
            
    public ComprobanteController(ListaComprobanteController listacomprobanteController, 
            JDesktopPane jdesktopPane ) {
        
        this.listacomprobanteController = listacomprobanteController;
        this.jdesktopPane = jdesktopPane;
        this.comprobantedetalleView = new ComprobanteDetalleView(null, true);
        
        this.comprobanteDAO = new ComprobanteDAO();
        this.comprobantepedidodetaleLista = new ComprobantePedidoDetalle_Lista();
        
    }
    
    public void mostrar(int idComprobante){         
        
        comprobantedetalleView.lblNombreCliente.setText(Integer.toString(idComprobante));
        poblarCabeceraDetallePedido(idComprobante);
        
        comprobantedetalleView.setVisible(true);  
    }
    
    public void poblarCabeceraDetallePedido(int idComprobante){
       try {
            comprobantedetalleView.lblNombreCliente.setText("text");
                ComprobantePedidoDetalle_Cabecera cpdl = comprobanteDAO.getListaPedidoCabecera(idComprobante);
                comprobantedetalleView.lblNombreCliente.setText(cpdl.getNombres());
                comprobantedetalleView.lblDocumento.setText(Integer.toString(cpdl.getDocumento()));
                comprobantedetalleView.lblDireccion.setText(cpdl.getDireccion());
                comprobantedetalleView.lblFechaComprobante.setText(cpdl.getFecha().toString());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error obtener: " + e.getMessage(), "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void poblarListaDetallePedido(int idComprobante){
        
//        try {
//            
//            List<TipoPizza> lstTipoPizza = tipopizzasDAO.getListaTipoPizza();
//            
//            DefaultTableModel pizzasViewTblModel = (DefaultTableModel) pizzasView.tblListaTipo.getModel();
//            // limpiar tabla antes de agregar
//            Helpers.clearTable(pizzasViewTblModel);
//            
//            if (lstTipoPizza.size() > 0) {
//                for (TipoPizza tipoPizza : lstTipoPizza) {
//                    pizzasViewTblModel.addRow(new Object[] {
//                        tipoPizza.getIdTipoPizza(),
//                        tipoPizza.getNombre()
//                    });
//                }
//            }
//        } catch (Exception e) {
//             e.getStackTrace();
//            JOptionPane.showMessageDialog(
//                null,
//                "Error buscar xxxx: " + e.getMessage(),
//                "Excepción",
//                JOptionPane.ERROR_MESSAGE
//            );
//        }
        
        
        
    }
   
}
