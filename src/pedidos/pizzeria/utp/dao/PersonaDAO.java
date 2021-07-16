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
import pedidos.pizzeria.utp.model.DocumentoIdentidad;
import pedidos.pizzeria.utp.model.Estado;
import pedidos.pizzeria.utp.model.Persona;
import pedidos.pizzeria.utp.model.TipoDocumentoIdentidad;
import pedidos.pizzeria.utp.model.TipoPersona;
/**
 *
 * @author wilderlizama
 */
public class PersonaDAO {
    
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
