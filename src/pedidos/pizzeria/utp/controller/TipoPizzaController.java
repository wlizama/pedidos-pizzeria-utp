/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.controller;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import pedidos.pizzeria.utp.dao.TipoPizzaDAO;
import pedidos.pizzeria.utp.model.TipoPizza;
import pedidos.pizzeria.utp.view.ListaPizzasView;

/**
 *
 * @author wilderlizama
 */
public class TipoPizzaController {
    
    ListaPizzasView pizzasView = new ListaPizzasView();
    
    public void TipoPizzaController() {
        buscarTipoPizzaLista();
    }
    
    public void buscarTipoPizzaLista() {
        try {
            TipoPizzaDAO tpDAO = new TipoPizzaDAO();
            List<TipoPizza> lstTipoPizza = tpDAO.getListaTipoPizza();
            
            DefaultTableModel pizzasViewTblModel = (DefaultTableModel) pizzasView.tblListaTipo.getModel();
            // limpiar tabla antes de agregar
//            pizzasViewTblModel.
            System.out.println("hereee 01");
            if (lstTipoPizza.size() > 0) {
                System.out.println("hereee");
                for (TipoPizza tipoPizza : lstTipoPizza) {
                    pizzasViewTblModel.addRow(new Object[] {
                        tipoPizza.getIdTipoPizza(),
                        tipoPizza.getNombre()
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
