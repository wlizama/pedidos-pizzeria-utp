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
import pedidos.pizzeria.utp.dao.PedidoDetalleDAO;
import pedidos.pizzeria.utp.model.ComprobanteCabecera;
import pedidos.pizzeria.utp.model.DetallePedido;
import pedidos.pizzeria.utp.view.ComprobanteView;

/**
 *
 * @author Percy
 */
public class ComprobanteController {
    ListaComprobanteController listacomprobanteController;
    JDesktopPane mainContainer;
    ComprobanteView comprobanteView;
    ComprobanteCabecera comprobantepedidodetaleCabecera;
    ComprobanteDAO comprobanteDAO;
    
    ComprobanteCabecera comPedDetCab;
            
    public ComprobanteController(ListaComprobanteController listacomprobanteController, 
            JDesktopPane mainContainer ) {
        
        this.listacomprobanteController = listacomprobanteController;
        this.mainContainer = mainContainer;
        this.comprobanteView = new ComprobanteView(null, true);
        
        this.comprobanteDAO = new ComprobanteDAO();
        
    }
    
    public void mostrar(int idComprobante){         
        
        comprobanteView.lblNombreCliente.setText(Integer.toString(idComprobante));
        obtenerCabecera(idComprobante);
        
        Helpers.centerForm(listacomprobanteController.getListacomprobanteView(), comprobanteView);
        
        comprobanteView.setVisible(true);  
    }
    
    public void obtenerCabecera(int idComprobante){
        try {
            ComprobanteCabecera comprobantecabecera = comprobanteDAO.getComprobanteCabecera(idComprobante);
            comprobanteView.lblNroComprobante.setText(String.valueOf(comprobantecabecera.getNumero()));
            comprobanteView.lblNombreCliente.setText(comprobantecabecera.getCliente());
            comprobanteView.lblDocumento.setText(comprobantecabecera.getClientetipodocidentidad() + ": " + comprobantecabecera.getClientedocIdentidad());
            comprobanteView.lblDireccion.setText(comprobantecabecera.getDireccionenvio());
            comprobanteView.lblFechaComprobante.setText(comprobantecabecera.getFechacreacion().toString());
            comprobanteView.lblTotal.setText(String.valueOf(comprobantecabecera.getMonto()));
            obtenerDetalle(comprobantecabecera.getIdPedido());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error obtener: " + e.getMessage(), "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void obtenerDetalle(int IdPedido){
        try {
            
            List<DetallePedido> lstdetallePedido = new PedidoDetalleDAO().getListaDetallePedido(IdPedido);
            
            DefaultTableModel detallepedidotblModel = (DefaultTableModel) comprobanteView.tblComprobanteDetalle.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(detallepedidotblModel);
            
            if (lstdetallePedido.size() > 0) {
                for (DetallePedido detallepedido : lstdetallePedido) {
                    detallepedidotblModel.addRow(new Object[] {
                        detallepedido.getPizza().getNombre(),
                        detallepedido.getPizza().getTamanhoPizza(),
                        detallepedido.getCantidad(),
                        detallepedido.getPizza().getPrecio(),
                    });
                }
            }
        } catch (Exception e) {
             e.getStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error buscar: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
   
}
