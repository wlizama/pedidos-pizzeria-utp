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
import pedidos.pizzeria.utp.model.Estado;
import pedidos.pizzeria.utp.model.TamanhoPizza;
import pedidos.pizzeria.utp.model.TipoPizza;
import pedidos.pizzeria.utp.model.Pizza;

/**
 *
 * @author Percy
 */
public class ListaPizzaDAO {
    
        public List<Pizza> getListaPizza(int IdTipoPizza) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Pizza> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PizzaLista (?)}");  
        cs.setInt("IdTipoPizza", IdTipoPizza);

        cs.execute();

        ResultSet rs = cs.getResultSet();

        while(rs.next()) {
            lstResult.add(new Pizza(
                rs.getInt("IdPizza"),
                rs.getString("nombre"),
                rs.getDouble("precio"),
                new TamanhoPizza(rs.getInt("IdTamanho"),rs.getString("tamanho"),0),
                new TipoPizza(rs.getInt("IdTipoPizza"),rs.getString("tipoPizza"),""),
                new Estado(rs.getInt("IdEstado"),rs.getString("estado"))
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
    
    public int insertarTamanhoPizza(ListaPizza tamanhopizza) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_TamanhoPizzaInserta ( ?, ?, ?)}");
        cs.setString("nombre", tamanhopizza.getNombre());
        cs.setInt("cantidadPorciones", tamanhopizza.getCantidadPorciones());
        
        cs.execute();

        int IdTamanhoPizza = cs.getInt("IdTamanho");
        
        MySqlConexion.close(con);
        
        return IdTamanhoPizza;
    }
    
    public void modificarTamanhoPizza(ListaPizza tamanhopizza) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_TamanhoPizzaModifica ( ?, ?, ?)}");
        cs.setInt("IdTamanho", tamanhopizza.getIdTamanhoPizza());
        cs.setString("nombre", tamanhopizza.getNombre());
        cs.setInt("cantidadPorciones", tamanhopizza.getCantidadPorciones());
        
        cs.execute();
        
        MySqlConexion.close(con);
    }
    
    public ListaPizza getTamanhoPizza(int IdTamanhoPizza) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_TamanhoPizza (?)}");        
        cs.setInt("IdTamanho", IdTamanhoPizza);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        ListaPizza tamanhopizza = null;
        if (rs.next()) {
            tamanhopizza = new ListaPizza(
                rs.getInt("IdTamanho"),
                rs.getString("nombre"),
                rs.getInt("cantidadPorciones")
            );
        }
        
        MySqlConexion.close(con);
        
        return tamanhopizza;
    }    
    
}
