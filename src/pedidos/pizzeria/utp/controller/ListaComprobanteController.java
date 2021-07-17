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
import pedidos.pizzeria.utils.Constants;
import pedidos.pizzeria.utils.Helpers;
import pedidos.pizzeria.utp.dao.EstadoDAO;
import pedidos.pizzeria.utp.dao.PizzaDAO;
import pedidos.pizzeria.utp.dao.ComprobanteDAO;
import pedidos.pizzeria.utp.dao.TamanhoPizzaDAO;
import pedidos.pizzeria.utp.dao.TipoPizzaDAO;
import pedidos.pizzeria.utp.model.Estado;
import pedidos.pizzeria.utp.model.ListaComprobante;
import pedidos.pizzeria.utp.view.ComprobanteView;
import pedidos.pizzeria.utp.view.ListaComprobanteView;

/**
 *
 * @author Percy
 */
public class ListaComprobanteController implements BaseControllerInterface {
    
    String op;
    //int IdPizza_edit;
    
    ListaComprobanteView listacomprobanteView;
    TamanhoPizzaDAO tamanhopizzaDAO;
    TipoPizzaDAO tipoPizzaDAO;
    EstadoDAO estadoDAO;
    Estado estado;
    PizzaDAO pizzaDAO;
    ComprobanteDAO comprobanteDAO;
    ComprobanteView comprobanteDialogView;
    ComprobanteController comprobanteController;
    
    public ListaComprobanteController(ListaComprobanteView comprobanteView) {
        this.listacomprobanteView = comprobanteView;
        this.comprobanteDAO = new ComprobanteDAO();
        this.pizzaDAO = new PizzaDAO();
        this.tamanhopizzaDAO = new TamanhoPizzaDAO();
        
        // eventos de form
        comprobanteView.btnBuscarComprobante.addActionListener((ae) -> {
            buscar();
        });
        
        //
        comprobanteView.btnVerComprobante.addActionListener((ae) -> {
            obtener();
        });
        
        limpiarForm();
        comprobanteView.txtComprobante.setText("0");
        buscar();
        this.op = Constants.OP_NEW;
        //comprobanteView.lblOpTamanoPizza.setText("( NUEVO )");
    }

    @Override
    public void limpiarForm() {
        listacomprobanteView.txtComprobante.setText("");
        //comprobanteView.txtPrecio.setText("");
    }

    @Override
    public void buscar() {
        try {           
            int idComprobante = Integer.parseInt(listacomprobanteView.txtComprobante.getText());

            List<ListaComprobante> lstComprobante = comprobanteDAO.getListaComprobante(idComprobante);            
            
            DefaultTableModel pizzaviewTblModel = (DefaultTableModel) listacomprobanteView.tblListaComprobante.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(pizzaviewTblModel);
            
            if (lstComprobante.size() > 0) {
                for (ListaComprobante listacomprobante : lstComprobante) {
                    pizzaviewTblModel.addRow(new Object[] {
                        listacomprobante.getIdComprobante(),
                        listacomprobante.getNumero(),                        
                        listacomprobante.getNombres(),
                        listacomprobante.getMonto()
                    });
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error buscar Lista Percy: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void obtener() {   
        try {
            int selectedRow = listacomprobanteView.tblListaComprobante.getSelectedRow();
            if (selectedRow != -1) {
                int idcomprobante = (int) listacomprobanteView.tblListaComprobante.getValueAt(selectedRow, 0);
                
               JDesktopPane mainContainer = (JDesktopPane) listacomprobanteView.getParent();
                comprobanteController = new ComprobanteController(this, mainContainer);
                
                comprobanteController.mostrar(idcomprobante);
            }
            
            else {
                JOptionPane.showMessageDialog(
                    null,
                    "Debe seleccionar un item",
                    "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error obtener: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }   
    }

    @Override
    public void guardar() {
        // validaciones
    }
    
    @Override
    public void modificar() {
        // validaciones
    }

    @Override
    public void insertar() {
        // validaciones
    }    
    @Override
    public void nuevo() {
        // validaciones
    }        
    
    
}
