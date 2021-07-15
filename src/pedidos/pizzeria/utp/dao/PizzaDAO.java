/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pedidos.pizzeria.utp.model.Pizza;
import pedidos.pizzeria.utp.model.TamanhoPizza;
import pedidos.pizzeria.utp.model.TipoPizza;

/**
 *
 * @author wilderlizama
 */
public class PizzaDAO {
    
    public List<Pizza> getListaPizzasXNombre(String nombrepizza) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Pizza> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PizzaListaxNombre ( ? )}");
        cs.setString("nombrepizza", nombrepizza);

        cs.execute();

        ResultSet rs = cs.getResultSet();

        while(rs.next()) {
            
            lstResult.add(new Pizza(
                rs.getInt("IdPizza"),
                rs.getString("nombre"),
                rs.getDouble("precio"),
                new TamanhoPizza(
                    rs.getInt("IdTamanho"),
                    rs.getString("tamanho"),
                    rs.getInt("cantidadPorciones")
                ),
                new TipoPizza(
                    rs.getInt("IdTipoPizza"),
                    rs.getString("tipoPizza"),
                    null
                )
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
}
