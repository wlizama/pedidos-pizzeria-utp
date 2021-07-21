/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import pedidos.pizzeria.utp.model.*;

/**
 *
 * @author jonas
 */
public class ReporteVentasDAO {
    
    public List<Comprobante> getReporteVentas(Date FechaInicio, Date FechaFin) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Comprobante> lstResult = new ArrayList<>(); 
        System.out.println("FechaInicio "+FechaInicio+"FechaFin "+FechaFin);
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_VentasRpt (?,?)}");        
        cs.setTimestamp("fechaEmision_inicio", new Timestamp(FechaInicio.getTime()));
        cs.setTimestamp("fechaEmision_fin", new Timestamp(FechaFin.getTime()));
        
        cs.execute();
        
        
        ResultSet rs = cs.getResultSet();
        
        Comprobante comprobante = null;
        while (rs.next()) {
            lstResult.add(new Comprobante
                (0,
                rs.getInt("numero"),
                rs.getDate("fechaEmision"),
                rs.getDouble("monto"),                
                new TipoComprobante(
                    0,
                    rs.getString("tipoComprobante")
                    ),
                null
                )
            );
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
}
