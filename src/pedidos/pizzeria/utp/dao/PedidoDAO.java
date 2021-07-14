/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import pedidos.pizzeria.utp.model.Cliente;
import pedidos.pizzeria.utp.model.Direccion;
import pedidos.pizzeria.utp.model.Distrito;
import pedidos.pizzeria.utp.model.Estado;
import pedidos.pizzeria.utp.model.Pedido;
/**
 *
 * @author wilderlizama
 */
public class PedidoDAO {
    
    public List<Pedido> getListaPedido(int idtipoDocIdentidad, String numero) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Pedido> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PedidoLista ( ?, ? )}");
        cs.setInt("idtipoDocIdentidad", idtipoDocIdentidad);
        cs.setString("numero", numero);

        cs.execute();

        ResultSet rs = cs.getResultSet();

        while(rs.next()) {
            Cliente cliente = new Cliente(
                rs.getInt("IdCliente")
            );
            cliente.setNombres(rs.getString("cliente"));
            
            lstResult.add(new Pedido(
                rs.getInt("IdPedido"),
                rs.getInt("numero"),
                rs.getInt("numerocomprobante"),
                rs.getDate("fechacreacion"),
                rs.getTime("horacreacion"),
                new Direccion(
                    rs.getInt("IdDireccionEnvio"),
                    rs.getString("direccionEnvio"),
                    null,
                    null,
                    new Distrito(
                        rs.getInt("IdDistritoEnvio"),
                        rs.getString("distritoEnvio"),
                        rs.getInt("coberturaEnvio") == 1
                    ),
                    null
                ),
                rs.getString("observaciones"),
                cliente,
                new Estado(
                    rs.getInt("IdEstado"),
                    rs.getString("estado")
                )
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
    public Pedido getPedido(int idPedido) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_Pedido (?)}");
        cs.setInt("idpedido", idPedido);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        Pedido pedido = null;
        if (rs.next()) {
            Cliente cliente = new ClienteDAO().getCliente(rs.getInt("IdCliente"));
            
            pedido = new Pedido(
                rs.getInt("IdPedido"),
                rs.getInt("numero"),
                rs.getInt("numerocomprobante"),
                rs.getDate("fechacreacion"),
                rs.getTime("horacreacion"),
                new Direccion(
                    rs.getInt("IdDireccionEnvio"),
                    rs.getString("direccionEnvio"),
                    null,
                    null,
                    new Distrito(
                        rs.getInt("IdDistritoEnvio"),
                        rs.getString("distritoEnvio"),
                        rs.getInt("coberturaEnvio") == 1
                    ),
                    null
                ),
                rs.getString("observaciones"),
                cliente,
                new Estado(
                    rs.getInt("IdEstado"),
                    rs.getString("estado")
                )
            );
        }
        
        MySqlConexion.close(con);
        
        return pedido;
    }
    
    public Pedido insertarPedido(Pedido pedido, int idtipocomprobante) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        Pedido pedido_out = pedido;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PedidoInsertar ( ?, ?, ?, ?, ?, ?, ?, ?, ? )}");
        cs.setDate("fechacreacion", (Date) pedido.getFechaCreacion());
        cs.setTime("horacreacion", (Time) pedido.getHoracreacion());
        cs.setInt("iddireccionenvio", pedido.getDireccionEnvio().getIdDireccion());
        cs.setInt("idcliente", pedido.getCliente().getIdCliente());
        cs.setInt("idtipocomprobante", idtipocomprobante);
        cs.setString("observaciones", pedido.getObservaciones());
        cs.execute();

        pedido_out.setIdPedido(cs.getInt("idpedido"));
        pedido_out.setNumero(cs.getInt("numeropedido"));
        pedido_out.setNumeroComprobante(cs.getInt("numerocomprobante"));
        
        MySqlConexion.close(con);
        
        return pedido_out;
    }
    
    public void modificarPedido(Pedido pedido, int idtipocomprobante) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PedidoModifica ( ?, ?, ?, ?, ?, ? )}");
        cs.setInt("idpedido", pedido.getIdPedido());
        cs.setInt("iddireccionenvio", pedido.getDireccionEnvio().getIdDireccion());
        cs.setInt("idcliente", pedido.getCliente().getIdCliente());
        cs.setInt("idtipocomprobante", idtipocomprobante);
        cs.setString("observaciones", pedido.getObservaciones());
        cs.setInt("idestado", pedido.getEstado().getIdEstado());
        cs.execute();
        
        MySqlConexion.close(con);
    }
}
