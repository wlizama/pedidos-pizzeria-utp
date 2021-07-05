/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.dao;

import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import pedidos.pizzeria.utp.model.TipoPizza;

/**
 *
 * @author wilderlizama
 */
public class TipoPizzaDAO {
    
    public List<TipoPizza> getListaTipoPizza() throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<TipoPizza> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_TipoPizzaLista ()}");
        /* ejemplo SP con parametros
        cs = con.prepareCall("{call SP_PlanoInclinadoInserta ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        cs.setString("nombre", plano_inclinado.getNombre());
        cs.setDouble("masa", plano_inclinado.getObjeto().getMasa());
        */

        cs.execute();

        ResultSet rs = cs.getResultSet();
        System.out.println("hereee 022");

        while(rs.next()) {
            lstResult.add(new TipoPizza(
                rs.getInt("IdTipoPizza"),
                rs.getString("nombre"),
                rs.getString("descripcion")
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
}
