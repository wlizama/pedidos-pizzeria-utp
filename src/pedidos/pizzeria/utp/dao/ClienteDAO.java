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
import pedidos.pizzeria.utp.model.TipoPersona;
import pedidos.pizzeria.utp.model.DocumentoIdentidad;
import pedidos.pizzeria.utp.model.Estado;
import pedidos.pizzeria.utp.model.Cliente;

/**
 *
 * @author wilderlizama
 */
public class ClienteDAO {
    
    public List<Cliente> getListaCliente(int idtipoDocIdentidad, String numero) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Cliente> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_ClienteLista ( ?, ? )}");
        cs.setInt("idtipoDocIdentidad", idtipoDocIdentidad);
        cs.setString("numero", numero);

        cs.execute();

        ResultSet rs = cs.getResultSet();

        while(rs.next()) {
            lstResult.add(new Cliente(
                rs.getInt("IdCliente"),
                rs.getInt("IdPersona"),
                rs.getString("nombres"),
                rs.getString("apellidos"),
                rs.getString("telefono"),
                new TipoPersona(
                    rs.getInt("IdCliente"),
                    rs.getString("tipoPersona")
                ),
                new DocumentoIdentidad(
                    rs.getInt("IdDocumentoIdentidad"),
                    rs.getString("documentoIdentidad"),
                    new TipoDocumentoIdentidadDAO().getTipoDocumentoIdentidad(rs.getInt("IdTipoDocIdentidad"))
                ),
                new Estado(
                    rs.getInt("IdEstado"),
                    rs.getString("estado")
                )
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
    public Cliente getCliente(int IdCliente) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_Cliente (?)}");
        cs.setInt("IdCliente", IdCliente);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        Cliente cliente = null;
        if (rs.next()) {
            cliente = new Cliente(
                rs.getInt("IdCliente"),
                rs.getInt("IdPersona"),
                rs.getString("nombres"),
                rs.getString("apellidos"),
                rs.getString("telefono"),
                new TipoPersona(
                    rs.getInt("IdCliente"),
                    rs.getString("tipoPersona")
                ),
                new DocumentoIdentidad(
                    rs.getInt("IdDocumentoIdentidad"),
                    rs.getString("documentoIdentidad"),
                    new TipoDocumentoIdentidadDAO().getTipoDocumentoIdentidad(rs.getInt("IdTipoDocIdentidad"))
                ),
                new Estado(
                    rs.getInt("IdEstado"),
                    rs.getString("estado")
                )
            );
        }
        
        MySqlConexion.close(con);
        
        return cliente;
    }
    
    public Cliente getClienteXTipoDocNumero(int idtipoDocIdentidad, String numero) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_ClientexTipoDocNumero (?, ?)}");
        cs.setInt("idtipoDocIdentidad", idtipoDocIdentidad);
        cs.setString("numero", numero);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        Cliente cliente = null;
        if (rs.next()) {
            cliente = new Cliente(
                rs.getInt("IdCliente"),
                rs.getInt("IdPersona"),
                rs.getString("nombres"),
                rs.getString("apellidos"),
                rs.getString("telefono"),
                new TipoPersona(
                    rs.getInt("IdCliente"),
                    rs.getString("tipoPersona")
                ),
                new DocumentoIdentidad(
                    rs.getInt("IdDocumentoIdentidad"),
                    rs.getString("documentoIdentidad"),
                    new TipoDocumentoIdentidadDAO().getTipoDocumentoIdentidad(rs.getInt("IdTipoDocIdentidad"))
                ),
                new Estado(
                    rs.getInt("IdEstado"),
                    rs.getString("estado")
                )
            );
        }
        
        MySqlConexion.close(con);
        
        return cliente;
    }
    
    public int insertarCliente(Cliente cliente) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_ClienteInserta ( ?, ?, ?, ?, ?, ? )}");
        cs.setString("nombres", cliente.getNombres());
        cs.setString("apellidos", cliente.getApellidos());
        cs.setString("telefono", cliente.getTelefono());
        cs.setInt("IdTipoDocIdentidad", cliente.getDocumentoIdentidad().getTipoDocumentoIdentidad().getIdTipoDocIdentidad());
        cs.setString("numero", cliente.getDocumentoIdentidad().getNumero());
        cs.execute();

        int IdCliente = cs.getInt("IdCliente");
        
        MySqlConexion.close(con);
        
        return IdCliente;
    }
    
    public void modificarCliente(Cliente cliente) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_ClienteModifica ( ?, ?, ?, ?, ?, ? )}");
        cs.setInt("IdCliente", cliente.getIdCliente());
        cs.setString("nombres", cliente.getNombres());
        cs.setString("apellidos", cliente.getApellidos());
        cs.setString("telefono", cliente.getTelefono());
        cs.setInt("IdTipoDocIdentidad", cliente.getDocumentoIdentidad().getTipoDocumentoIdentidad().getIdTipoDocIdentidad());
        cs.setString("numero", cliente.getDocumentoIdentidad().getNumero());
        
        cs.execute();
        
        MySqlConexion.close(con);
    }
}
