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
import pedidos.pizzeria.utp.model.Direccion;
import pedidos.pizzeria.utp.model.Distrito;

/**
 *
 * @author wilderlizama
 */
public class DireccionClienteDAO {
    
    public List<Direccion> getListaDireccionCliente(int idcliente) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Direccion> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_ClienteDireccionLista ( ? )}");
        cs.setInt("idcliente", idcliente);

        cs.execute();

        ResultSet rs = cs.getResultSet();

        while(rs.next()) {
            lstResult.add(new Direccion(
                rs.getInt("idDireccion"),
                rs.getString("direccion"),
                rs.getString("referencia"),
                rs.getInt("principal") == 1,
                new Distrito(
                    rs.getInt("IdDistrito"),
                    rs.getString("distrito"),
                    rs.getInt("cobertura") == 1
                ),
                new Cliente(idcliente)
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
    public Direccion getDireccionCliente(int idDireccion) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_ClienteDireccion (?)}");
        cs.setInt("idDireccion", idDireccion);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        Direccion direccion = null;
        if (rs.next()) {
            direccion = new Direccion(
                rs.getInt("idDireccion"),
                rs.getString("direccion"),
                rs.getString("referencia"),
                rs.getInt("principal") == 1,
                new Distrito(
                    rs.getInt("IdDistrito"),
                    rs.getString("distrito"),
                    rs.getInt("cobertura") == 1
                ),
                new Cliente(rs.getInt("IdCliente"))
            );
        }
        
        MySqlConexion.close(con);
        
        return direccion;
    }
    
    public int insertarDireccion(Direccion direccion) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_ClienteDireccionInserta ( ?, ?, ?, ?, ?, ? )}");
        cs.setString("direccion", direccion.getDireccion());
        cs.setString("referencia", direccion.getReferencia());
        cs.setInt("idcliente", direccion.getCliente().getIdCliente());
        cs.setInt("iddistrito", direccion.getDistrito().getIdDistrito());
        cs.setInt("principal", direccion.getPrincipal() ? 1 : 0);
        cs.execute();

        int IdDireccion = cs.getInt("IdDireccion");
        
        MySqlConexion.close(con);
        
        return IdDireccion;
    }
    
    public void modificarDireccion(Direccion direccion) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_ClienteDireccionModifica ( ?, ?, ?, ?, ?, ? )}");
        cs.setInt("IdDireccion", direccion.getIdDireccion());
        cs.setString("direccion", direccion.getDireccion());
        cs.setString("referencia", direccion.getReferencia());
        cs.setInt("idcliente", direccion.getCliente().getIdCliente());
        cs.setInt("iddistrito", direccion.getDistrito().getIdDistrito());
        cs.setInt("principal", direccion.getPrincipal() ? 1 : 0);
        
        cs.execute();
        
        MySqlConexion.close(con);
    }
}
