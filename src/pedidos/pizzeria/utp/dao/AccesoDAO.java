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
    
    public Acceso getAccesoRol(int IdRol) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_RolAccesoLista (?)}");
        cs.setInt("IdRol", IdRol);
        
        cs.execute();

        ResultSet rs = cs.getResultSet();
        Acceso acceso = null;
        while(rs.next()) {
            acceso = new Acceso(
                rs.getInt("IdAcceso"),
                new RolesDAO().getRol(rs.getInt("IdFormulario")),
                new Formulario(0,rs.getString("Formulario"))
            );
            
        }        
            
        MySqlConexion.close(con);
        
        return acceso;
    }
}
