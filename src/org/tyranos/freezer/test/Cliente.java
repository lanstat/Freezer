/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tyranos.freezer.test;

import java.sql.Date;

/**
 *
 * @author mauricio
 */
public class Cliente {
    public Integer Codigo;
    public String Nombre;
    public Integer Nit;
    public Integer Telefono;
    public Date Fecha;

    @Override
    public String toString() {
        return "Cliente{" + "Codigo=" + Codigo + ", Nombre=" + Nombre + ", Nit=" + Nit + ", Telefono=" + Telefono + ", Fecha=" + Fecha + '}';
    }
    
    
}
