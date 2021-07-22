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
import pedidos.pizzeria.utp.model.Distrito;

/**
 *
 * @author wilderlizama
 */
public class DistritoDAO {
    
    public List<Distrito> getListaDistrito() throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Distrito> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_DistritoLista ()}");

        cs.execute();

        ResultSet rs = cs.getResultSet();

        while(rs.next()) {
            lstResult.add(new Distrito(
                rs.getInt("IdDistrito"),
                rs.getString("nombre"),
                rs.getInt("cobertura") == 1
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
    public Distrito getDistrito(int idDistrito) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_Distrito (?)}");
        cs.setInt("IdDistrito", idDistrito);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        Distrito distrito = null;
        if (rs.next()) {
            distrito = new Distrito(
                rs.getInt("IdDistrito"),
                rs.getString("nombre"),
                rs.getInt("cobertura") == 1
            );
        }
        
        MySqlConexion.close(con);
        
        return distrito;
    }
    
    public void modificarDistrito(Distrito distrito) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_DistritoModifica ( ?, ?, ? )}");
        cs.setInt("IdDistrito", distrito.getIdDistrito());
        cs.setString("nombre", distrito.getNombre());
        cs.setInt("cobertura", distrito.getCobertura() ? 1 : 0);
        
        cs.execute();
        
        MySqlConexion.close(con);
    }
}
