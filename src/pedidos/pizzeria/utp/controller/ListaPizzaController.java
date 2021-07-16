/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pedidos.pizzeria.utils.Constants;
import pedidos.pizzeria.utils.Helpers;
import pedidos.pizzeria.utp.dao.EstadoDAO;
import pedidos.pizzeria.utp.dao.PizzaDAO;
import pedidos.pizzeria.utp.dao.TamanhoPizzaDAO;
import pedidos.pizzeria.utp.dao.TipoPizzaDAO;
import pedidos.pizzeria.utp.model.Estado;
import pedidos.pizzeria.utp.model.Pizza;
import pedidos.pizzeria.utp.model.TamanhoPizza;
import pedidos.pizzeria.utp.model.TipoPizza;
import pedidos.pizzeria.utp.view.ListaPizzasView;

/**
 *
 * @author Percy
 */
public class ListaPizzaController implements BaseControllerInterface {
    
    String op;
    int IdPizza_edit;
    
    ListaPizzasView pizzasView;
    TamanhoPizzaDAO tamanhopizzaDAO;
    TipoPizzaDAO tipoPizzaDAO;
    EstadoDAO estadoDAO;
    Estado estado;
    PizzaDAO pizzaDAO;
//    ListaPizzaDAO listapizzaDAO;
    
    public ListaPizzaController(ListaPizzasView pizzasView) {
        this.pizzasView = pizzasView;
        this.pizzaDAO = new PizzaDAO();
        this.tamanhopizzaDAO = new TamanhoPizzaDAO();
        
        // eventos de form
        pizzasView.btnBuscar.addActionListener((ae) -> {
            buscar();
        });
        
        // eventos de form
        pizzasView.btnEditar.addActionListener((ae) -> {
            obtener();
        });
        
        pizzasView.btnNuevo.addActionListener((ae) -> {
            nuevo();
        });
        
        pizzasView.btnGuardar.addActionListener((ae) -> {
            guardar();
        });
        
        limpiarForm();
        initCombos();
        buscar();
        this.op = Constants.OP_NEW;
        pizzasView.lblOpTamanoPizza.setText("( NUEVO )");
    }

    @Override
    public void limpiarForm() {
        pizzasView.txtNombre.setText("");
        pizzasView.txtPrecio.setText("");

    }

       private void initCombos() {
        try {
            List<TipoPizza> tipoPizzaDAO = new TipoPizzaDAO().getListaTipoPizza();
            
            pizzasView.cboTipoPizza.setModel(new DefaultComboBoxModel());
            
            pizzasView.cboTipoPizza.addItem(new TipoPizza(0, "Todos", "")); // para que busque todos         
            for (TipoPizza tipoPizza : tipoPizzaDAO) {
                pizzasView.cboTipoPizza.addItem(tipoPizza); // para lista filtro                
            }
            
            /*pizzasView.cbxTipo.addItem(new TipoPizza(0, "Todos", "")); // para que busque todos         */
            for (TipoPizza tipoPizza : tipoPizzaDAO) {
                pizzasView.cbxTipo.addItem(tipoPizza); // para lista filtro                
            }
            
            List<TamanhoPizza> tamanhoPizzaDAO = new TamanhoPizzaDAO().getListaTamanhoPizza();
            pizzasView.cboTamano.setModel(new DefaultComboBoxModel());
            /*pizzasView.cboTamano.addItem(new TamanhoPizza(0, "Todos", 0)); // para que busque todos   */      
            for (TamanhoPizza tamanhoPizza : tamanhoPizzaDAO) {
                pizzasView.cboTamano.addItem(tamanhoPizza); // para lista filtro                
            }
            
            List<Estado> estadoDAO = new EstadoDAO().getListaEstado(Constants.ID_TESTADO_PIZZA);
            pizzasView.cboEstado.setModel(new DefaultComboBoxModel());
            /*pizzasView.cboEstado.addItem(new Estado(0, "Todos")); // para que busque todos */        
            for (Estado estadoPizza : estadoDAO) {
                pizzasView.cboEstado.addItem(estadoPizza); // para lista filtro                
            }
                                               
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Error initCombos: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
       
    @Override
    public void buscar() {
        try {            
            TipoPizza tipoPizza_selected = (TipoPizza) pizzasView.cboTipoPizza.getSelectedItem();
            System.out.println("valor combobox inicializado: " +  tipoPizza_selected );
            int idtipoPizza = tipoPizza_selected.getIdTipoPizza();
            System.out.println("valor variable idtipoPizza: " + idtipoPizza + ", " );
            /*String numeroDocIdentidad = clienteView.txtDocumentoFiltro.getText().trim();*/

            List<Pizza> lstPizza = pizzaDAO.getPizzaCliente(idtipoPizza);
            
            DefaultTableModel pizzaviewTblModel = (DefaultTableModel) pizzasView.tblListaPizzas.getModel();
            // limpiar tabla antes de agregar
            Helpers.clearTable(pizzaviewTblModel);
            System.out.println("valores variable idtipoPizza: " + idtipoPizza + ", Tamaño lstPizza: " + lstPizza.size() );
            if (lstPizza.size() > 0) {
                for (Pizza pizza : lstPizza) {
                    pizzaviewTblModel.addRow(new Object[] {
                        pizza.getIdPizza(),
                        pizza.getNombre(),
                        pizza.getTipoPizza().getNombre(),
                        pizza.getTamanhoPizza().getNombre(),
                        pizza.getPrecio(),
                        pizza.getEstado().getNombre()
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
            int selectedRow = pizzasView.tblListaPizzas.getSelectedRow();
            if (selectedRow != -1) {
                int IdPizza = (int) pizzasView.tblListaPizzas.getValueAt(selectedRow, 0);
                Pizza pizza = pizzaDAO.getPizza(IdPizza);             
                
                IdPizza_edit = pizza.getIdPizza();
                pizzasView.txtNombre.setText(pizza.getNombre());
                pizzasView.txtPrecio.setText(pizza.getPrecio().toString());
                                
                /*pizzasView.cbxTipo.setSelectedIndex(pizza.getTipoPizza().getIdTipoPizza());*/
                pizzasView.cbxTipo.getModel().setSelectedItem(pizza.getTipoPizza());
                /*pizzasView.cboTamano.setSelectedIndex(pizza.getTamanhoPizza().getIdTamanhoPizza());*/
                pizzasView.cboTamano.getModel().setSelectedItem(pizza.getTamanhoPizza());
                /*pizzasView.cboEstado.setSelectedIndex(pizza.getEstado().getIdEstado());*/
                pizzasView.cboEstado.getModel().setSelectedItem(pizza.getEstado());
                
                
                this.op = Constants.OP_EDIT;
                pizzasView.lblOpPizza.setText("( EDITAR )");
            }
            else {
                JOptionPane.showMessageDialog(
                    null, "Debe seleccionar un item", "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null, "Error obtener: " + e.getMessage(), "Excepción",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void nuevo() {
        try {
            limpiarForm();
            pizzasView.cbxTipo.setSelectedIndex(0);
            pizzasView.cboTamano.setSelectedIndex(0);
            pizzasView.cboEstado.setSelectedIndex(0);
            this.op = Constants.OP_NEW;
            pizzasView.lblOpPizza.setText("( NUEVO )");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error nuevo: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void insertar() {
        try {
            Pizza pizza = new Pizza();
            pizza.setNombre(pizzasView.txtNombre.getText());
            pizza.setPrecio(Double.parseDouble(pizzasView.txtPrecio.getText()));
            
            /*pizza.setTipoPizza(new TipoPizza(pizzasView.cbxTipo.getSelectedIndex(),
                    pizzasView.cbxTipo.getSelectedItem().toString(),""));*/
            pizza.setTipoPizza((TipoPizza)pizzasView.cbxTipo.getModel().getSelectedItem()); 
            /*pizza.setTamanhoPizza(new TamanhoPizza(pizzasView.cboTamano.getSelectedIndex(),
                    pizzasView.cboTamano.getSelectedItem().toString(),0));*/
            pizza.setTamanhoPizza((TamanhoPizza)pizzasView.cboTamano.getModel().getSelectedItem()); 
            /*pizza.setEstado(new Estado(pizzasView.cboEstado.getSelectedIndex(),
                    pizzasView.cboEstado.getSelectedItem().toString())); */
            pizza.setEstado((Estado)pizzasView.cboEstado.getModel().getSelectedItem()); 
            
            
            int IdPizza = pizzaDAO.insertarPizza(pizza);
            pizza.setIdPizza(IdPizza);
            IdPizza_edit = IdPizza;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error insertar: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    @Override
    public void modificar() {
        try {
            Pizza pizza = new Pizza();
            pizza.setIdPizza(IdPizza_edit);
            pizza.setNombre(pizzasView.txtNombre.getText());
            pizza.setPrecio(Double.parseDouble(pizzasView.txtPrecio.getText()));
            
            /*pizza.setTipoPizza(new TipoPizza(pizzasView.cbxTipo.getSelectedIndex(),pizzasView.cbxTipo.getSelectedItem().toString(),""));*/
            pizza.setTipoPizza((TipoPizza)pizzasView.cbxTipo.getModel().getSelectedItem()); 
            /*pizza.setTamanhoPizza(new TamanhoPizza(pizzasView.cboTamano.getSelectedIndex(),pizzasView.cboTamano.getSelectedItem().toString(),0));*/
            pizza.setTamanhoPizza((TamanhoPizza)pizzasView.cboTamano.getModel().getSelectedItem()); 
            /*pizza.setEstado(new Estado(pizzasView.cboEstado.getSelectedIndex(),pizzasView.cboEstado.getSelectedItem().toString())); */
            pizza.setEstado((Estado)pizzasView.cboEstado.getModel().getSelectedItem()); 
            
            pizzaDAO.modificarPizza(pizza);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Error modificar: " + e.getMessage(),
                "Excepción",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void guardar() {
        // validaciones
        
        String msgValidacion = "";
        if (pizzasView.txtNombre.getText().trim().equals(""))
            msgValidacion += "- Ingresar nombre de pizza.\n";
        if (pizzasView.txtPrecio.getText().trim().equals(""))
            msgValidacion += "- Ingresar precio de pizza.\n";        
        
        if (!msgValidacion.equals("")) {
            JOptionPane.showMessageDialog(
                null,
                "Corregir los siguiente:\n" + msgValidacion,
                "Mensaje",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        if (this.op.equals(Constants.OP_NEW))
            insertar();
        else if (this.op.equals(Constants.OP_EDIT))
            modificar();
        
        this.op = Constants.OP_EDIT;
        /*pizzasView.btnEditarDireccion.setEnabled(true);
        pizzasView.btnAgregarDireccion.setEnabled(true);*/
        pizzasView.lblOpPizza.setText("( EDITAR )");
        
        buscar();
    }
    
}
