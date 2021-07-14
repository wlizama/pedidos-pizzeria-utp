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
/**
 *
 * @author wilderlizama
 */
public class EstadoDAO {
    
    public List<Estado> getListaEstado(int IdTipoEstado) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Estado> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_EstadoLista (?)}");
        cs.setInt("IdTipoEstado", IdTipoEstado);

        cs.execute();

        ResultSet rs = cs.getResultSet();

        while(rs.next()) {
            lstResult.add(new Estado(
                rs.getInt("IdEstado"),
                rs.getString("nombre")
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
    public Estado getEstado(int IdEstado) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_Estado (?)}");
        cs.setInt("IdEstado", IdEstado);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        Estado estado = null;
        if (rs.next()) {
            estado = new Estado(
                rs.getInt("IdEstado"),
                rs.getString("nombre")
            );
        }
        
        MySqlConexion.close(con);
        
        return estado;
    }
    
}
