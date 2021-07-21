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
import pedidos.pizzeria.utp.model.*;

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
        cs = con.prepareCall("{call SP_PersonaLista (?,?)}");
        cs.setInt("idtipoDocIdentidad", idtipoDocIdentidad);
        cs.setString("Numero", numero);
        cs.execute();

        ResultSet rs = cs.getResultSet();
        Persona persona = null;
        while(rs.next()) {
            lstResult.add(new Persona(
                rs.getInt("IdPersona"),
                rs.getString("nombres"),"",
                rs.getString("telefono"),null,null,
                new Estado(0, rs.getString("estado"))
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
}
