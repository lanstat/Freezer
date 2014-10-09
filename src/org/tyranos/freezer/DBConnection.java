/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tyranos.freezer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mauricio
 */
public class DBConnection {
    
    private String Host;
    private int Port;
    private String URL;
    private String Database;
    
    private Connection DB;
    
    private static final String MYSQL_URL = "jdbc:mysql://";
    
    public DBConnection(String Host, String Username, String Password, String Database) throws SQLException{
        URL = MYSQL_URL + Host + ":" + 3306 + "/" + Database;
        DB = DriverManager.getConnection(URL, Username, Password);
        
    }
    
    protected ResultSet executeQuery(String sql) throws SQLException{
        Statement s = DB.createStatement();
        
        if(s.execute(sql)){
            
            return s.getResultSet();
        } else {
            return null;
        }
    }
    
       protected  int executeStatement(String sql) throws SQLException {
        Statement s = DB.createStatement();
        int afectadas = -1;
        if (!s.execute(sql)) {
            afectadas = s.getUpdateCount();
        } else {
            throw new SQLException("La sentencia devuelve un ResultSet, ejecute con el método: querySqlStatement()");
        }
        s.close();
        return afectadas;
    }

    
    @Override
    public String toString(){
        return URL;
    }
    
    
    
    
}
