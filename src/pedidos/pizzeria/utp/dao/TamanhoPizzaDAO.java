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
import pedidos.pizzeria.utp.model.TamanhoPizza;

/**
 *
 * @author Percy
 */
public class TamanhoPizzaDAO {
    
    public List<TamanhoPizza> getListaTamanhoPizza() throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<TamanhoPizza> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_TamanhoPizzaLista ()}");
        /* ejemplo SP con parametros
        cs = con.prepareCall("{call SP_PlanoInclinadoInserta ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        cs.setString("nombre", plano_inclinado.getNombre());
        cs.setDouble("masa", plano_inclinado.getObjeto().getMasa());
        */

        cs.execute();

        ResultSet rs = cs.getResultSet();

        while(rs.next()) {
            lstResult.add(new TamanhoPizza(
                rs.getInt("IdTamanho"),
                rs.getString("nombre"),
                rs.getInt("cantidadPorciones")
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
    
    public int insertarTamanhoPizza(TamanhoPizza tamanhopizza) throws Exception {
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
    
    public void modificarTamanhoPizza(TamanhoPizza tamanhopizza) throws Exception {
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
    
    public TamanhoPizza getTamanhoPizza(int IdTamanhoPizza) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_TamanhoPizza (?)}");        
        cs.setInt("IdTamanho", IdTamanhoPizza);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        TamanhoPizza tamanhopizza = null;
        if (rs.next()) {
            tamanhopizza = new TamanhoPizza(
                rs.getInt("IdTamanho"),
                rs.getString("nombre"),
                rs.getInt("cantidadPorciones")
            );
        }
        
        MySqlConexion.close(con);
        
        return tamanhopizza;
    }
    
}
