/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pedidos.pizzeria.utp.model.*;


/**
 *
 * @author jonas
 */
public class ReporteCoberturaDAO {        
    
    public List<DetallePedido> getReporteCobertura(java.sql.Date FechaInicio, java.sql.Date FechaFin, int Distrito) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<DetallePedido> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_CoberturaRpt (?,?,?)}");        
        cs.setDate("fechaCreacion_inicio", (java.sql.Date) FechaInicio);
        cs.setDate("fechaCreacion_fin", (java.sql.Date) FechaFin);
        cs.setInt("distrito_id", Distrito);
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        DetallePedido detallePedido = null;
        while (rs.next()) {
            lstResult.add(new DetallePedido
                (0,
                new Pizza(0, 
                    "", 
                    rs.getDouble("preciototal"), 
                    null,
                    null,
                    null),
                new Pedido(0, 0, 0, null, null, 
                    new Direccion(0, "", "", true, 
                        new Distrito(0,rs.getString("nomdistrito"),true),null
                    ),
                    "", null, null
                    ),               
                rs.getInt("cantidadtot")
                )
            );
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }        
    
}
