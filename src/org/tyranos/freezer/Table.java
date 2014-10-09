/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyranos.freezer;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author lanstat
 * @param <T>
 */
public class Table {
	
	private String getTypeName(Class<?> classType){
		String typeName;
		
		if(classType.getName().equals("java.lang.Integer")){
			typeName = "INTEGER";
		} else if (classType.getName().equals("java.lang.String")) {
			typeName = "TEXT";
		} else if (classType.getName().equals("java.sql.Date")) {
			typeName = "DATE";
		} else {
			typeName = null;
		}
		
		return typeName;
	}
	
	public void save(){}
	
	public String generateSQLCreate() throws Exception{
		StringBuilder sql;
		Field[] fields;
		String type;
		
		sql = new StringBuilder();
		fields = this.getClass().getFields();
		for(Field field : fields){
			if(field.getAnnotation(Column.class) != null || field.getAnnotation(PrimaryKey.class) != null){
				type = getTypeName(field.getType());
				if(type != null){
					sql.append(field.getName());
					sql.append(" ");
					sql.append(type);
					if(field.getAnnotation(PrimaryKey.class) != null){
						sql.append(" PRIMARY KEY");
					}
					sql.append(", ");
				} else {
					throw new Exception(type+" no es tipo de dato primitivo");
				}
			}
		}
		sql.delete(sql.length()-2, sql.length());
		sql.append(");");
		sql.insert(0, "CREATE TABLE "+this.getClass().getSimpleName()+"(");
		
		return sql.toString();
	}
}
