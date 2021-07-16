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
import pedidos.pizzeria.utp.model.Comprobante;
import pedidos.pizzeria.utp.model.Pedido;
import pedidos.pizzeria.utp.model.TipoComprobante;
/**
 *
 * @author wilderlizama
 */
public class ComprobanteDAO {
    
    public Comprobante getComprobantexPedido(int IdPedido) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_ComprobanteXPedido (?)}");
        cs.setInt("IdPedido", IdPedido);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        Comprobante comprobante = null;
        if (rs.next()) {
            comprobante = new Comprobante(
                rs.getInt("IdComprobante"),
                rs.getInt("numero"),
                rs.getDate("fechaEmision"),
                rs.getDouble("monto"),
                new TipoComprobante(
                    rs.getInt("IdTipoComprobante"),
                    rs.getString("tipoComprobante")
                ),
                new Pedido(IdPedido)
            );
        }
        
        MySqlConexion.close(con);
        
        return comprobante;
    }
}
