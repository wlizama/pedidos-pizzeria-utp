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
import pedidos.pizzeria.utp.model.TipoDocumentoIdentidad;

/**
 *
 * @author wilderlizama
 */
public class TipoDocumentoIdentidadDAO {
    
    public List<TipoDocumentoIdentidad> getListaTipoDocumentoIdentidad() throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<TipoDocumentoIdentidad> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_TipoDocumentoLista ()}");

        cs.execute();

        ResultSet rs = cs.getResultSet();

        while(rs.next()) {
            lstResult.add(new TipoDocumentoIdentidad(
                rs.getInt("IdTipoDocIdentidad"),
                rs.getString("nombre"),
                rs.getInt("cantidadCaracteres")
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
    public TipoDocumentoIdentidad getTipoDocumentoIdentidad(int IdTipoDocIdentidad) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_TipoDocumento (?)}");
        cs.setInt("IdTipoDocIdentidad", IdTipoDocIdentidad);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        TipoDocumentoIdentidad tipodocident = null;
        if (rs.next()) {
            tipodocident = new TipoDocumentoIdentidad(
                rs.getInt("IdTipoDocIdentidad"),
                rs.getString("nombre"),
                rs.getInt("cantidadCaracteres")    
            );
        }
        
        MySqlConexion.close(con);
        
        return tipodocident;
    }
}
