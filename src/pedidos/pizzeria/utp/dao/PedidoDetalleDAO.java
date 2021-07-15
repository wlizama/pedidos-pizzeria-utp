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
import pedidos.pizzeria.utp.model.DetallePedido;
import pedidos.pizzeria.utp.model.Pedido;
import pedidos.pizzeria.utp.model.Pizza;
import pedidos.pizzeria.utp.model.TamanhoPizza;
import pedidos.pizzeria.utp.model.TipoPizza;
/**
 *
 * @author wilderlizama
 */
public class PedidoDetalleDAO {
    
    public List<DetallePedido> getListaDetallePedido(int idpedido) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<DetallePedido> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PedidoDetalleLista ( ? )}");
        cs.setInt("idpedido", idpedido);

        cs.execute();

        ResultSet rs = cs.getResultSet();

        while(rs.next()) {
            Pizza pizza = new Pizza();
            pizza.setIdPizza(rs.getInt("IdPizza"));
            pizza.setNombre(rs.getString("nombre"));
            pizza.setPrecio(rs.getDouble("precio"));
            pizza.setTamanhoPizza(
                new TamanhoPizza(
                    rs.getInt("IdTamanho"),
                    rs.getString("tamanho"),
                    rs.getInt("cantidadPorciones")
                )
            );
            pizza.setTipoPizza(
                new TipoPizza(
                    rs.getInt("IdTipoPizza"),
                    rs.getString("tipoPizza"),
                    null
                )
            );
            
            lstResult.add(new DetallePedido(
                rs.getInt("IdDetallePedido"),
                pizza,
                new Pedido(idpedido),
                rs.getInt("cantidad")
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
    public DetallePedido getDetallePedido(int iddetallepedido) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PedidoDetalle (?)}");
        cs.setInt("iddetallepedido", iddetallepedido);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        DetallePedido detallepedido = null;
        if (rs.next()) {
            Pizza pizza = new Pizza();
            pizza.setIdPizza(rs.getInt("IdPizza"));
            pizza.setNombre(rs.getString("nombre"));
            pizza.setPrecio(rs.getDouble("precio"));
            pizza.setTamanhoPizza(
                new TamanhoPizza(
                    rs.getInt("IdTamanho"),
                    rs.getString("tamanho"),
                    rs.getInt("cantidadPorciones")
                )
            );
            pizza.setTipoPizza(
                new TipoPizza(
                    rs.getInt("IdTipoPizza"),
                    rs.getString("tipoPizza"),
                    null
                )
            );
            
            detallepedido = new DetallePedido(
                rs.getInt("IdDetallePedido"),
                pizza,
                new Pedido(rs.getInt("IdPedido")),
                rs.getInt("cantidad")
            );
        }
        
        MySqlConexion.close(con);
        
        return detallepedido;
    }
    
    public Object[] insertarDetallePedido(DetallePedido detallepedido) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        DetallePedido detallepedido_out = detallepedido;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PedidoDetalleInserta ( ?, ?, ?, ?, ? )}");
        cs.setInt("cantidad", detallepedido.getCantidad());
        cs.setInt("idpedido", detallepedido.getPedido().getIdPedido());
        cs.setInt("idpizza", detallepedido.getPizza().getIdPizza());
        
        cs.execute();

        detallepedido_out.setIdDetallePedido(cs.getInt("iddetallepedido"));
        Double total = cs.getDouble("total");
        
        MySqlConexion.close(con);
        
        return new Object[]{ detallepedido_out, total };
    }
    
    public Double modificarDetallePedido(DetallePedido pedido) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PedidoDetalleModifica ( ?, ?, ?, ?, ?)}");
        cs.setInt("iddetallepedido", pedido.getIdDetallePedido());
        cs.setInt("cantidad", pedido.getCantidad());
        cs.setInt("idpedido", pedido.getPedido().getIdPedido());
        cs.setInt("idpizza", pedido.getPizza().getIdPizza());
        cs.execute();
        
        Double total = cs.getDouble("total");
        
        MySqlConexion.close(con);
        
        return total;
    }
    
    public Double eliminarDetallePedido(DetallePedido pedido) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PedidoDetalleElimina ( ?, ?, ? )}");
        cs.setInt("iddetallepedido", pedido.getIdDetallePedido());
        cs.setInt("idpedido", pedido.getPedido().getIdPedido());
        cs.execute();
        
        Double total = cs.getDouble("total");
        
        MySqlConexion.close(con);
        
        return total;
    }
}
