package org.tyranos.freezer.test;

import org.tyranos.freezer.Table;
import org.tyranos.freezer.annotations.Column;
import org.tyranos.freezer.annotations.DataType;

/**
 * 
 * @author lanstat
 *
 */
public class Proveedor extends Table{

	@Column (PrimaryKey=true)
	public int Code;
	
	@Column (Type=DataType.Varchar, Accuracy=20)
	public String nombre;
}
