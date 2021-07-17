/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author wilderlizama
 */
public class LoginDAO {
    
    public int verificaUsuario(String nombreUsuario, String contrasenha) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_Usuario (?, ?)}");
        cs.setString("nombreUsuario", nombreUsuario);
        cs.setString("contrasenha", contrasenha);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        int IdUsuario = -1;
        if (rs.next())
            IdUsuario = rs.getInt("IdUsuario");
        
        MySqlConexion.close(con);
        
        return IdUsuario;
    }
}
