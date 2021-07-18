/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import pedidos.pizzeria.utp.model.Envio;
import pedidos.pizzeria.utp.model.Estado;
import pedidos.pizzeria.utp.model.Repartidor;
/**
 *
 * @author wilderlizama
 */
public class EnvioDAO {
    
    public List<Envio> getListaEnvio(int estado) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Envio> lstResult = new ArrayList<>(); 
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_EnvioLista ( ? )}");
        cs.setInt("estado", estado);

        cs.execute();

        ResultSet rs = cs.getResultSet();

        while(rs.next()) {
            Repartidor repartidor = new Repartidor();
            repartidor.setIdPersona(rs.getInt("IdPersona"));
            repartidor.setNombres(rs.getString("personanombres"));
            repartidor.setApellidos(rs.getString("personaapellidos"));
            repartidor.setTelefono(rs.getString("personatelefono"));
            
            lstResult.add(new Envio(
                rs.getInt("IdEnvio"),
                rs.getInt("numero"),
                rs.getTimestamp("hora_inicio"),
                rs.getTimestamp("hora_fin"),
                repartidor,
                new Estado(
                    rs.getInt("IdEstado"),
                    rs.getString("estado")
                )
            ));
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
    public Envio getEnvio(int idEnvio) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_Envio (?)}");
        cs.setInt("idEnvio", idEnvio);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        Envio envio = null;
        if (rs.next()) {
            Repartidor repartidor = new Repartidor();
            repartidor.setIdPersona(rs.getInt("IdPersona"));
            repartidor.setNombres(rs.getString("personanombres"));
            repartidor.setApellidos(rs.getString("personaapellidos"));
            repartidor.setTelefono(rs.getString("personatelefono"));
            
            envio = new Envio(
                rs.getInt("IdEnvio"),
                rs.getInt("numero"),
                rs.getTimestamp("hora_inicio"),
                rs.getTimestamp("hora_fin"),
                repartidor,
                new Estado(
                    rs.getInt("IdEstado"),
                    rs.getString("estado")
                )
            );
        }
        
        MySqlConexion.close(con);
        
        return envio;
    }
    
    public Envio insertarEnvio(Envio envio) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        Envio envio_out = envio;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_EnvioInsertar ( ?, ?, ?, ?, ? )}");
        cs.setTimestamp("horainicio", (Timestamp) envio.getHoraInicio());
        cs.setTimestamp("horafin", (Timestamp) envio.getHoraFin());
        cs.setInt("IdPersona", envio.getRepartidor().getIdPersona());
        
        cs.execute();

        envio_out.setIdEnvio(cs.getInt("IdEnvio"));
        envio_out.setNumero(cs.getInt("numeroenvio"));
        
        MySqlConexion.close(con);
        
        return envio_out;
    }
    
    public void modificarEnvio(Envio envio) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_EnvioModificar ( ?, ?, ?, ?, ? )}");
        cs.setInt("idenvio", envio.getIdEnvio());
        cs.setTimestamp("horainicio", (Timestamp) envio.getHoraInicio());
        cs.setTimestamp("horafin", (Timestamp) envio.getHoraFin());
        cs.setInt("IdPersona", envio.getRepartidor().getIdPersona());
        cs.setInt("IdEstado", envio.getEstado().getIdEstado());
        cs.execute();
        
        MySqlConexion.close(con);
    }
}
