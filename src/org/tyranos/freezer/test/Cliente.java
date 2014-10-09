/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tyranos.freezer.test;

import java.sql.Date;

import org.tyranos.freezer.Column;
import org.tyranos.freezer.PrimaryKey;
import org.tyranos.freezer.Table;

/**
 *
 * @author mauricio
 */
public class Cliente extends Table{
	
	@PrimaryKey
    public Integer Codigo;
	@Column
    public String Nombre;
	@Column
    public Integer Nit;
	@Column
    public Integer Telefono;
	@Column
    public Date Fecha;

    @Override
    public String toString() {
        return "Cliente{" + "Codigo=" + Codigo + ", Nombre=" + Nombre + ", Nit=" + Nit + ", Telefono=" + Telefono + ", Fecha=" + Fecha + '}';
    }
    
    
}
