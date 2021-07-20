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
public class RolesDAO {
    
    public List<Roles> getListaRoles() throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Roles> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_RolLista ()}");

        cs.execute();

        ResultSet rs = cs.getResultSet();

        while(rs.next()) {
            lstResult.add(new Roles(
                rs.getInt("IdRol"),
                rs.getString("nombre")
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
    public Roles getRol(int IdRol) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_Rol (?)}");
        cs.setInt("IdRol", IdRol);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        Roles roles = null;
        if (rs.next()) {
            roles = new Roles(
                rs.getInt("IdRol"),
                rs.getString("nombre")
            );
        }
        
        MySqlConexion.close(con);
        
        return roles;
    }
        
    public int insertarRoles(Roles roles) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_RolInserta ( ?, ? )}");
        cs.setString("nombre", roles.getNombre());
        
        cs.execute();

        int IdRol = cs.getInt("IdRol");
        
        MySqlConexion.close(con);
        
        return IdRol;
    }
    
    public void modificarRoles(Roles roles) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_RolModifica ( ?, ? )}");
        cs.setInt("IdRol", roles.getIdRol());
        cs.setString("nombre", roles.getNombre());
        
        cs.execute();
        
        MySqlConexion.close(con);
    }
    
    public void eliminaInsertarAccesosRoles(int IdRol, String idsformulario) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_RolAccesoEliminaInserta ( ?, ? )}");
        cs.setInt("IdRol", IdRol);
        cs.setString("idsformulario", idsformulario);
        
        cs.execute();
        
        MySqlConexion.close(con);
        
    }
    
}
