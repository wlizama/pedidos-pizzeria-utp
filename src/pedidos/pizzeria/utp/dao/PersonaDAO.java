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
import pedidos.pizzeria.utp.model.Persona;
import pedidos.pizzeria.utp.model.TipoPersona;
import pedidos.pizzeria.utp.model.DocumentoIdentidad;
import pedidos.pizzeria.utp.model.TipoDocumentoIdentidad;
import pedidos.pizzeria.utp.model.Estado;
import pedidos.pizzeria.utp.model.Roles;
import pedidos.pizzeria.utp.model.Usuario;


/**
 *
 * @author jonas
 */
public class PersonaDAO {
    
    public List<Persona> getListaPersona(int idtipoDocIdentidad, String numero) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Persona> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PersonaLista ( ?, ? )}");
        cs.setInt("idtipoDocIdentidad", idtipoDocIdentidad);
        cs.setString("numero", numero);

        cs.execute();

        ResultSet rs = cs.getResultSet();

        while(rs.next()) {
            lstResult.add(new Persona(
                rs.getInt("IdPersona"),
                rs.getString("nombres"),
                rs.getString("apellidos"),
                rs.getString("telefono"),
                new TipoPersona(
                    rs.getInt("IdTipoPersona"),
                    rs.getString("tipoPersona")
                ),
                new DocumentoIdentidad(
                    rs.getInt("IdDocumentoIdentidad"),
                    rs.getString("documentoIdentidad"),
                    new TipoDocumentoIdentidad(
                        rs.getInt("IdTipoDocIdentidad"),
                        rs.getString("tipoDocIdentidad"),
                        rs.getInt("tdicantidadCaracteres")
                    )
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
    
    public List<Persona> getListaPersonaXTipo(int IdTipoPersona) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Persona> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PersonaListaXTipo ( ? )}");
        cs.setInt("IdTipoPersona", IdTipoPersona);

        cs.execute();

        ResultSet rs = cs.getResultSet();

        while(rs.next()) {
            lstResult.add(new Persona(
                rs.getInt("IdPersona"),
                rs.getString("nombres"),
                rs.getString("apellidos"),
                rs.getString("telefono"),
                new TipoPersona(
                    rs.getInt("IdTipoPersona"),
                    rs.getString("tipoPersona")
                ),
                new DocumentoIdentidad(
                    rs.getInt("IdDocumentoIdentidad"),
                    rs.getString("documentoIdentidad"),
                    new TipoDocumentoIdentidad(
                        rs.getInt("IdTipoDocIdentidad"),
                        rs.getString("tipoDocumentoIdentidad"),
                        0
                    )
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
    
    public Persona getPersona(int idpersona) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_Persona (?)}");
        cs.setInt("idpersona", idpersona);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        Persona cliente = null;
        if (rs.next()) {
            cliente = new Persona(
                rs.getInt("IdPersona"),
                rs.getString("nombres"),
                rs.getString("apellidos"),
                rs.getString("telefono"),
                new TipoPersona(
                    rs.getInt("IdTipoPersona"),
                    rs.getString("tipoPersona")
                ),
                new DocumentoIdentidad(
                    rs.getInt("IdDocumentoIdentidad"),
                    rs.getString("documentoIdentidad"),
                    new TipoDocumentoIdentidad(
                        rs.getInt("IdTipoDocIdentidad"),
                        rs.getString("tipoDocIdentidad"),
                        rs.getInt("tdicantidadCaracteres")
                    )
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
    
    public Usuario getPersonaUsuario(int IdPersona) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PersonaUsuario (?)}");
        cs.setInt("IdPersona", IdPersona);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        Usuario usuario = null;
        if (rs.next()) {
            usuario = new Usuario(
                rs.getInt("IdUsuario"),
                rs.getString("nombreUsuario"),
                rs.getString("contrasenha"),
                new Roles(
                    rs.getInt("IdRol"),
                    rs.getString("rol")
                )
            );
        }
        
        MySqlConexion.close(con);
        
        return usuario;
    }
    
    public int insertarPersona(Persona persona, Usuario usuario) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PersonaInserta ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )}");
        cs.setString("nombres", persona.getNombres());
        cs.setString("apellidos", persona.getApellidos());
        cs.setString("telefono", persona.getTelefono());
        cs.setInt("IdTipoDocIdentidad", persona.getDocumentoIdentidad().getTipoDocumentoIdentidad().getIdTipoDocIdentidad());
        cs.setString("numero", persona.getDocumentoIdentidad().getNumero());
        cs.setInt("IdTipoPersona", persona.getTipoPersona().getIdTipoPersona());
        cs.setInt("IdRol", usuario.getRol().getIdRol());
        cs.setString("nombreUsuario", usuario.getNombreUsuario());
        cs.setString("contrasenha", usuario.getContrasenha());
        cs.execute();

        int IdPersona = cs.getInt("IdPersona");
        
        MySqlConexion.close(con);
        
        return IdPersona;
    }
    
    public void modificarPersona(Persona persona, Usuario usuario) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PersonaModifica ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )}");
        cs.setInt("IdPersona", persona.getIdPersona());
        cs.setString("nombres", persona.getNombres());
        cs.setString("apellidos", persona.getApellidos());
        cs.setString("telefono", persona.getTelefono());
        cs.setInt("IdTipoDocIdentidad", persona.getDocumentoIdentidad().getTipoDocumentoIdentidad().getIdTipoDocIdentidad());
        cs.setString("numero", persona.getDocumentoIdentidad().getNumero());
        cs.setInt("IdTipoPersona", persona.getTipoPersona().getIdTipoPersona());
        cs.setInt("IdRol", usuario.getRol().getIdRol());
        cs.setString("nombreUsuario", usuario.getNombreUsuario());
        cs.setString("contrasenha", usuario.getContrasenha());
        
        cs.execute();
        
        MySqlConexion.close(con);
    }
}
