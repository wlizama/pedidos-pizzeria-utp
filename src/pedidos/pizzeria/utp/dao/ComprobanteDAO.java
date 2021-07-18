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
import pedidos.pizzeria.utp.model.ComprobantePedidoDetalle_Cabecera;
import pedidos.pizzeria.utp.model.ComprobantePedidoDetalle_Lista;
import pedidos.pizzeria.utp.model.ListaComprobante;
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
    
    public List<ListaComprobante> getListaComprobante(int numero) throws Exception {
        Connection con = null;
        CallableStatement cs = null;        
        List<ListaComprobante> lstResult = new ArrayList<>();         
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_ComprobanteLista (?)}");
        cs.setInt("numero", numero);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        //ListaComprobante listacomprobante = null;
        while (rs.next()) {
            lstResult.add(new ListaComprobante(
                rs.getInt("IdComprobante"),
                rs.getInt("numero"),
                rs.getDouble("monto"),
                rs.getString("Nombres") + " " + rs.getString("Apellidos")
                //rs.getString("Apellidos")                
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
    public ComprobantePedidoDetalle_Cabecera getListaPedidoCabecera(int idcomprobante) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<ComprobantePedidoDetalle_Cabecera> lstResult = new ArrayList<>();         
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_ComprobantePedidoDetalle_cabecera (?)}");
        cs.setInt("idcomprobante", idcomprobante);        
        cs.execute();        
        ResultSet rs = cs.getResultSet();
        ComprobantePedidoDetalle_Cabecera comprobante = null;
        if (rs.next()) {
            comprobante = new ComprobantePedidoDetalle_Cabecera(
                rs.getInt("IdComprobante"),
                rs.getString("nombres") + " " + rs.getString("apellidos") ,
                rs.getInt("documentoIdentidad"),
                rs.getString("direccion"),
                rs.getDate("fechacreacion")
            ) ;
        }
        
        MySqlConexion.close(con);
        
        return comprobante;
    }
     
    public List<ComprobantePedidoDetalle_Lista> getListaPedidolista(int idcomprobante) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<ComprobantePedidoDetalle_Lista> lstResult = new ArrayList<>();         
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_ComprobantePedidoDetalle_cabecera (?)}");
        cs.setInt("idcomprobante", idcomprobante);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
               
        while (rs.next()) {
            lstResult.add(new ComprobantePedidoDetalle_Lista(
                rs.getInt("IdComprobante"),
                rs.getString("pizza") ,
                rs.getString("tamanho"),
                rs.getInt("cantidad"),
                rs.getDouble("precio")
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
          
     
}
