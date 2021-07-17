/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import javax.swing.JDesktopPane;
import pedidos.pizzeria.utp.view.ComprobanteDetalleView;

/**
 *
 * @author Percy
 */
public class ComprobanteController {
    ListaComprobanteController listacomprobanteController;
    JDesktopPane jdesktopPane;
    ComprobanteDetalleView comprobantedetalleView;
        
    public ComprobanteController(ListaComprobanteController listacomprobanteController, 
            JDesktopPane jdesktopPane ) {
        
        this.listacomprobanteController = listacomprobanteController;
        this.jdesktopPane = jdesktopPane;
        this.comprobantedetalleView = new ComprobanteDetalleView(null, true);
        
    }
    
    public void mostrar(int idComprobante){
        comprobantedetalleView.setVisible(true);   
        //obtener(idComprobante);
        
        comprobantedetalleView.lblNombreCliente.setText("Perecy");
        
    }
    
   
    
}
