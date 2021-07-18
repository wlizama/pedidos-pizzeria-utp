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
import pedidos.pizzeria.utp.model.Cliente;
import pedidos.pizzeria.utp.model.DetalleEnvio;
import pedidos.pizzeria.utp.model.Direccion;
import pedidos.pizzeria.utp.model.Envio;
import pedidos.pizzeria.utp.model.Pedido;
/**
 *
 * @author wilderlizama
 */
public class EnvioDetalleDAO {
    
    public List<DetalleEnvio> getListaDetalleEnvio(int idenvio) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<DetalleEnvio> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_EnvioDetalleLista ( ? )}");
        cs.setInt("idenvio", idenvio);

        cs.execute();

        ResultSet rs = cs.getResultSet();

        while(rs.next()) {
            Envio envio = new Envio();
            envio.setIdEnvio(rs.getInt("IdEnvio"));
            
            Pedido pedido = new Pedido();
            pedido.setIdPedido(rs.getInt("IdPedido"));
            pedido.setNumero(rs.getInt("numero"));
            Cliente cliente = new Cliente();
            cliente.setIdCliente(rs.getInt("IdCliente"));
            cliente.setNombres(rs.getString("cliente"));
            pedido.setCliente(cliente);
            Direccion direccion = new Direccion();
            direccion.setIdDireccion(rs.getInt("IdDireccionEnvio"));
            direccion.setDireccion(rs.getString("direccion"));
            pedido.setDireccionEnvio(direccion);
            
            lstResult.add(new DetalleEnvio(
                rs.getInt("IdDetalleEnvio"),
                rs.getTimestamp("hora_fin"),
                envio,
                rs.getString("observaciones"),
                pedido
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
    public DetalleEnvio getDetalleEnvio(int iddetalleenvio) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_EnvioDetalle (?)}");
        cs.setInt("iddetalleenvio", iddetalleenvio);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        DetalleEnvio detalleenvio = null;
        if (rs.next()) {
            Envio envio = new Envio();
            envio.setIdEnvio(rs.getInt("IdEnvio"));
            
            Pedido pedido = new Pedido();
            pedido.setIdPedido(rs.getInt("IdPedido"));
            pedido.setNumero(rs.getInt("numero"));
            Cliente cliente = new Cliente();
            cliente.setIdCliente(rs.getInt("IdCliente"));
            cliente.setNombres(rs.getString("cliente"));
            pedido.setCliente(cliente);
            Direccion direccion = new Direccion();
            direccion.setIdDireccion(rs.getInt("IdDireccionEnvio"));
            direccion.setDireccion(rs.getString("direccion"));
            pedido.setDireccionEnvio(direccion);
            
            detalleenvio = new DetalleEnvio(
                rs.getInt("IdDetalleEnvio"),
                rs.getTimestamp("hora_fin"),
                envio,
                rs.getString("observaciones"),
                pedido
            );
        }
        
        MySqlConexion.close(con);
        
        return detalleenvio;
    }
    
    public int insertarDetalleEnvio(DetalleEnvio detalleenvio) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_EnvioDetalleInserta ( ?, ?, ?, ?, ? )}");
        cs.setTimestamp("hora_fin", detalleenvio.getHoraFin());
        cs.setString("observaciones", detalleenvio.getObservaciones());
        cs.setInt("idenvio", detalleenvio.getEnvio().getIdEnvio());
        cs.setInt("idpedido", detalleenvio.getPedido().getIdPedido());
        
        cs.execute();

        int IdDetalleEnvio = cs.getInt("IdDetalleEnvio");
        
        MySqlConexion.close(con);
        
        return IdDetalleEnvio;
    }
    
    public void modificarDetalleEnvio(DetalleEnvio detalleenvio) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_EnvioDetalleModifica ( ?, ?, ?, ?)}");
        cs.setInt("IdDetalleEnvio", detalleenvio.getIdDetalleEnvio());
        cs.setTimestamp("hora_fin", detalleenvio.getHoraFin());
        cs.setString("observaciones", detalleenvio.getObservaciones());
        cs.setInt("idpedido", detalleenvio.getPedido().getIdPedido());
        cs.execute();
        
        MySqlConexion.close(con);

    }
    
    public void eliminarDetalleEnvio(int iddetalleenvio) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_EnvioDetalleEliminar ( ? )}");
        cs.setInt("iddetalleenvio", iddetalleenvio);
        cs.execute();
        
        MySqlConexion.close(con);

    }
}
