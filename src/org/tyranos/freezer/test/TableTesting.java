/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tyranos.freezer.test;

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
			Proveedor p = new Proveedor();
			System.out.println(c.generateSQLCreate());
			c.save();
			System.out.println();
			//System.out.println(p.insert);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
}
