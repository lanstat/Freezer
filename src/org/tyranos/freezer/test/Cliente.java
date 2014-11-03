/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tyranos.freezer.test;

import java.sql.Date;

import org.tyranos.freezer.Table;
import org.tyranos.freezer.annotations.Column;
import org.tyranos.freezer.annotations.DataType;

/**
 *
 * @author mauricio
 */
public class Cliente extends Table{
	
	@Column (PrimaryKey = true)
    public Integer Codigo;
	@Column (Type=DataType.Varchar, Accuracy=32) 
    public String Nombre;
	@Column (Type=DataType.Int32)
    public Integer Nit;
	@Column (Type=DataType.Int32)
    public Integer Telefono;
	@Column (Type=DataType.Datetime)
    public Date Fecha;

    @Override
    public String toString() {
        return "Cliente{" + "Codigo=" + Codigo + ", Nombre=" + Nombre + ", Nit=" + Nit + ", Telefono=" + Telefono + ", Fecha=" + Fecha + '}';
    }
    
    
}
