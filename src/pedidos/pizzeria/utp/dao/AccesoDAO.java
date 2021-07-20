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
import pedidos.pizzeria.utp.model.Acceso;
import pedidos.pizzeria.utp.model.Formulario;
import pedidos.pizzeria.utp.model.Roles;


/**
 *
 * @author jonas
 */
public class AccesoDAO {
    
    public List<Formulario> getListaAccesoFormulario(int IdUsuario) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Formulario> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_AccesoUsuario ( ? )}");
        cs.setInt("IdUsuario", IdUsuario);

        cs.execute();

        ResultSet rs = cs.getResultSet();

        while(rs.next()) {
            lstResult.add(new Formulario(
                rs.getInt("IdFormulario"),
                rs.getString("nombre")
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
    public List<Acceso> getListaAccesoRol(int IdRol) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Acceso> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_RolAccesoLista (?)}");
        cs.setInt("IdRol", IdRol);
        
        cs.execute();

        ResultSet rs = cs.getResultSet();
        while(rs.next()) {
            lstResult.add(new Acceso(
                rs.getInt("IdAcceso"),
                new Formulario(rs.getInt("IdFormulario"), rs.getString("Formulario")),
                new Roles(IdRol)
            ));
        }        
            
        MySqlConexion.close(con);
        
        return lstResult;
    }
}
