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
import pedidos.pizzeria.utp.model.TipoPersona;

/**
 *
 * @author wilderlizama
 */
public class TipoPersonaDAO {
    
    public List<TipoPersona> getListaTipoPersona() throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<TipoPersona> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_TipoPersonaLista ()}");

        cs.execute();

        ResultSet rs = cs.getResultSet();

        while(rs.next()) {
            lstResult.add(new TipoPersona(
                rs.getInt("IdTipoPersona"),
                rs.getString("nombre")
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
    public TipoPersona getTipoPersona(int IdTipoPersona) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_TipoPersona (?)}");
        cs.setInt("IdTipoPersona", IdTipoPersona);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        TipoPersona tipopersona = null;
        if (rs.next()) {
            tipopersona = new TipoPersona(
                rs.getInt("IdTipoPersona"),
                rs.getString("nombre")
            );
        }
        
        MySqlConexion.close(con);
        
        return tipopersona;
    }
    
    public int insertarTipoPersona(TipoPersona tipopersona) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_TipoPersonaInserta ( ?, ? )}");
        cs.setString("nombre", tipopersona.getNombre());
        
        cs.execute();

        int IdTipoPersona = cs.getInt("IdTipoPersona");
        
        MySqlConexion.close(con);
        
        return IdTipoPersona;
    }
    
    public void modificarTipoPersona(TipoPersona tipopersona) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_TipoPersonaModifica ( ?, ? )}");
        cs.setInt("IdTipoPersona", tipopersona.getIdTipoPersona());
        cs.setString("nombre", tipopersona.getNombre());;
        
        cs.execute();
        
        MySqlConexion.close(con);
    }
}
