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
    
    
    public int insertarTipoPizza(TipoPizza tipopizza) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_TipoPizzaInserta ( ?, ?, ?)}");
        cs.setString("nombre", tipopizza.getNombre());
        cs.setString("descripcion", tipopizza.getDescripcion());
        
        cs.execute();

        int IdTipoPizza = cs.getInt("IdTipoPizza");
        
        MySqlConexion.close(con);
        
        return IdTipoPizza;
    }
    
    public void modificarTipoPizza(TipoPizza tipopizza) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_TipoPizzaModifica ( ?, ?, ?)}");
        cs.setInt("IdTipoPizza", tipopizza.getIdTipoPizza());
        cs.setString("nombre", tipopizza.getNombre());
        cs.setString("descripcion", tipopizza.getDescripcion());
        
        cs.execute();
        
        MySqlConexion.close(con);
    }
    
    public TipoPizza getTipoPizza(int IdTipoPizza) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_TipoPizza (?)}");
        cs.setInt("IdTipoPizza", IdTipoPizza);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        TipoPizza tipopizza = null;
        if (rs.next()) {
            tipopizza = new TipoPizza(
                rs.getInt("IdTipoPizza"),
                rs.getString("nombre"),
                rs.getString("descripcion")
            );
        }
        
        MySqlConexion.close(con);
        
        return tipopizza;
    }
    
}
