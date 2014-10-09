/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tyranos.freezer.test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tyranos.freezer.DBConnection;
import org.tyranos.freezer.Table;

/**
 *
 * @author mauricio
 */
public class TableTesting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            DBConnection c = new DBConnection("localhost","root","asdf","Lib_Ballivian");
            Table<Cliente> t = new Table(c, new Cliente());
            Cliente c1 = new Cliente();
            c1.Codigo = 10;
            c1.Nit = 343535;
            c1.Nombre = "Mauricio";
            c1.Fecha = new Date(System.currentTimeMillis());
            Time h = new Time(System.currentTimeMillis());
            System.out.println(System.currentTimeMillis());
            System.out.println(h);

            c1.Telefono = 75024328;
         //   t.Insert(c1);
            System.out.println(t.Select("Codigo", 1, 1));
        } catch (SQLException ex) {
            Logger.getLogger(TableTesting.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TableTesting.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TableTesting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
