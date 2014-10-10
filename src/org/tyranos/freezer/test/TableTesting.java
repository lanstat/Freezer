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
			Cliente c = new Cliente();
			c.Codigo = 12;
			c.Nombre = "abc";
			c.save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
