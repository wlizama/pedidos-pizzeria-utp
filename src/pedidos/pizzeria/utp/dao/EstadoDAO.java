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
 * @author Percy
 */
public class EstadoDAO {
     public List<Estado> getListaEstado(int idtipoestado) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Estado> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_EstadoLista ( ? )}");
        cs.setInt("IdTipoEstado", idtipoestado);

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
    
}
