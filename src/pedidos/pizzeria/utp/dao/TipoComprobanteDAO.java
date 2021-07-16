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
import pedidos.pizzeria.utp.model.TipoComprobante;
/**
 *
 * @author wilderlizama
 */
public class TipoComprobanteDAO {
    
    public List<TipoComprobante> getListaTipoComprobante() throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<TipoComprobante> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_TipoComprobanteLista ()}");

        cs.execute();

        ResultSet rs = cs.getResultSet();

        while(rs.next()) {
            lstResult.add(new TipoComprobante(
                rs.getInt("IdTipoComprobante"),
                rs.getString("nombre")
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
    public TipoComprobante getTipoComprobante(int IdTipoComprobante) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_TipoComprobante (?)}");
        cs.setInt("IdTipoComprobante", IdTipoComprobante);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        TipoComprobante tipocomprobante = null;
        if (rs.next()) {
            tipocomprobante = new TipoComprobante(
                rs.getInt("IdTipoComprobante"),
                rs.getString("nombre")
            );
        }
        
        MySqlConexion.close(con);
        
        return tipocomprobante;
    }
}
