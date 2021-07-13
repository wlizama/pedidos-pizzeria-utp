/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utils;

import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
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
    
    public static void centerForm(JDesktopPane dp, JInternalFrame jif) {
        Dimension dimension = dp.getSize();
        Dimension fcalculo = jif.getSize();
        jif.setLocation((dimension.width -fcalculo.width)/2,(dimension.height -fcalculo.height)/2);
    }
    
    public static void centerForm(JInternalFrame jif, JDialog jd) {
        jd.setLocation(jif.getLocation().x*2, jif.getLocation().y*2);
    }
}
