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
import pedidos.pizzeria.utp.model.Pizza;
import pedidos.pizzeria.utp.model.Estado;
import pedidos.pizzeria.utp.model.TamanhoPizza;
import pedidos.pizzeria.utp.model.TipoPizza;

/**
 *
 * @author Percy
 */
public class PizzaDAO {
    
    public List<Pizza> getPizzaCliente(int idTipoPizza) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        List<Pizza> lstResult = new ArrayList<>(); 
        System.out.println("llamada al SP " +  idTipoPizza );
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PizzaLista ( ? )}");
        cs.setInt("IdTipoPizza", idTipoPizza);

        cs.execute();

        ResultSet rs = cs.getResultSet();
        
        while(rs.next()) {
            System.out.println("mostrando el RS " +  rs.getInt("IdPizza"));
            lstResult.add(new Pizza(
                rs.getInt("IdPizza"),
                rs.getString("nombre"),
                rs.getDouble("precio"),
                new TamanhoPizza(
                    rs.getInt("IdTamanho"),
                    rs.getString("tamanho"),
                    0
                ),
                new TipoPizza(
                    rs.getInt("IdTipoPizza"),
                    rs.getString("tipoPizza"),
                    ""
                ),
                new Estado(
                    rs.getInt("IdEstado"),
                    rs.getString("estado")
                )                
            )
            );
        }
        
        MySqlConexion.close(con);
        
        return lstResult;
    }
    
    public Pizza getPizza(int IdPizza) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_Pizza (?)}");
        cs.setInt("IdPizza", IdPizza);
        
        cs.execute();
        
        ResultSet rs = cs.getResultSet();
        
        Pizza pizza = null;
        if (rs.next()) {
            pizza = new Pizza(
                rs.getInt("IdPizza"),
                rs.getString("nombre"),
                rs.getDouble("precio"),
                new TamanhoPizza(
                    rs.getInt("IdTamanho"),
                    rs.getString("tamanho"),
                    0
                ),
                new TipoPizza(
                    rs.getInt("IdTipoPizza"),
                    rs.getString("tipoPizza"),
                    ""
                ),
                new Estado(
                    rs.getInt("IdEstado"),
                    rs.getString("estado")
                )
            );
        }
        
        MySqlConexion.close(con);
        
        return pizza;
    }
    
    public int insertarPizza(Pizza pizza) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PizzaInserta ( ?, ?, ?, ?, ?, ? )}");
        cs.setString("nombre", pizza.getNombre());
        cs.setDouble("precio", pizza.getPrecio());
        cs.setInt("IdTipoPizza", pizza.getTipoPizza().getIdTipoPizza());
        cs.setInt("IdTamanho", pizza.getTamanhoPizza().getIdTamanhoPizza());
        cs.setInt("IdEstado", pizza.getEstado().getIdEstado());
        cs.execute();

        int IdPizza = cs.getInt("IdPizza");
        
        MySqlConexion.close(con);
        
        return IdPizza;
    }
    
    public void modificarPizza(Pizza pizza) throws Exception {
        Connection con = null;
        CallableStatement cs = null;
        
        System.out.println("IdPizza: " + pizza.getIdPizza() + " nombre: " + pizza.getNombre() 
                + " precio: " + pizza.getPrecio() + " IdTipoPizza: " + pizza.getTipoPizza().getIdTipoPizza() 
                + " IdTamanho: " + pizza.getTamanhoPizza().getIdTamanhoPizza() 
                + " IdEstado: " + pizza.getEstado().getIdEstado());
        
        con = MySqlConexion.getConexion();
        cs = con.prepareCall("{call SP_PizzaModifica ( ?, ?, ?, ?, ?, ? )}");
        cs.setInt("IdPizza", pizza.getIdPizza());
        cs.setString("nombre", pizza.getNombre());
        cs.setDouble("precio", pizza.getPrecio());
        cs.setInt("IdTipoPizza", pizza.getTipoPizza().getIdTipoPizza());
        cs.setInt("IdTamanho", pizza.getTamanhoPizza().getIdTamanhoPizza());
        cs.setInt("IdEstado", pizza.getEstado().getIdEstado());
        
        cs.execute();
        
        MySqlConexion.close(con);
    }
    
}
