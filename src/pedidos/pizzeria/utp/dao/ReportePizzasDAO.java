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
import java.util.Date;
import java.util.List;
import pedidos.pizzeria.utp.model.*;

/**
 *
 * @author jonas
 */
public class ReportePizzasDAO {
    
    public List<DetallePedido> getReportePizzas(java.sql.Date FechaInicio, java.sql.Date FechaFin) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<DetallePedido> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PizzasRpt (?,?)}");        
        cs.setDate("fechaCreacion_inicio", (java.sql.Date) FechaInicio);
        cs.setDate("fechaCreacion_fin", (java.sql.Date) FechaFin);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        DetallePedido detallePedido = null;
        while (rs.next()) {
            lstResult.add(new DetallePedido
                (0,
                new Pizza(0, 
                 "", 
                 rs.getDouble("preciototal"), 
                 new TamanhoPizza(0, rs.getString("tamanho"), 0),
                 new TipoPizza(0, rs.getString("tipoPizza"), ""),
                 null),
                null,
                rs.getInt("cantidad")
                )
            );
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
}
