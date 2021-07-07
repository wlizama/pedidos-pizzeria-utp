/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utils;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author wilderlizama
 */
public class Helpers {
 
    
    public static void clearTable(DefaultTableModel tableModel) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tableModel.removeRow(i);
            i = i - 1;
        }
    }
}
