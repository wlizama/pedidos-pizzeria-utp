/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedidos.pizzeria.utp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author wilderlizama
 */
public class MySqlConexion {
    
//    private static final String SERVER = "b0qqwgziwlcqrz7trku7-mysql.services.clever-cloud.com";
//    private static final String DBNAME = "b0qqwgziwlcqrz7trku7";
//    private static final String USER = "uxqzl7sxtwqglx1p";
//    private static final String PWD = "i80EWvoraMIjSQNPGKZ2";
    
    private static final String SERVER = "localhost";
    private static final String DBNAME = "pedidos-pizzeria";
    private static final String USER = "root";
    private static final String PWD = "";
 
    public static Connection getConexion() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://" + SERVER + "/" + DBNAME,
                USER,
                PWD);
        
        return con;
    }
    
    public static void close(Connection con) throws SQLException {
        con.close();
    }
}
