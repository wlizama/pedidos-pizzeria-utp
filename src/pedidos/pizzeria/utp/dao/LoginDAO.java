/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import pedidos.pizzeria.utp.model.Roles;
import pedidos.pizzeria.utp.model.Usuario;

/**
 *
 * @author wilderlizama
 */
public class LoginDAO {
    
    public Usuario verificaUsuario(String nombreUsuario, String contrasenha) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_Usuario (?, ?)}");
        cs.setString("nombreUsuario", nombreUsuario);
        cs.setString("contrasenha", contrasenha);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        Usuario usuario = null;
        if (rs.next()) {
            usuario = new Usuario(
                rs.getInt("IdUsuario"),
                rs.getString("nombreUsuario"),
                "***",
                new Roles(
                    rs.getInt("IdRol"),
                    rs.getString("rol")
                )
            );
            
        }
        
        MySqlConexion.close(con);
        
        return usuario;
    }
}
