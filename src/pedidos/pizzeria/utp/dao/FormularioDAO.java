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
import pedidos.pizzeria.utp.model.*;

/**
 *
 * @author jonas
 */
public class FormularioDAO {
    
    
    public Formulario getFormulario(int IdFormulario) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_Formulario (?)}");
        cs.setInt("IdFormulario", IdFormulario);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        Formulario formulario = null;
        if (rs.next()) {
            formulario = new Formulario(
                rs.getInt("IdFormulario"),
                rs.getString("nombre")
            );
        }
        
        MySqlConexion.close(con);
        
        return formulario;
    }
}
