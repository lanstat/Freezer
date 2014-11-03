/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyranos.freezer;

import java.lang.reflect.Field;
import java.sql.SQLException;

import org.tyranos.freezer.annotations.Column;

/**
 *
 * @author lanstat
 * @param <T>
 */
public class Table {
	public final String insert;
	
	public Table(){
		insert = generateSQLInsert();
	}
	
	public void createTable() throws SQLException, Exception{
		DBConnection.getInstance().executeStatement(generateSQLCreate());
	}
	
	public String generateSQLCreate() throws Exception{
		StringBuilder sql;
		Field[] fields;
		String type;
		Column annotation;
		
		sql = new StringBuilder();
		fields = this.getClass().getFields();
		for(Field field : fields){
			if(field.getAnnotation(Column.class) != null){
				annotation = field.getAnnotation(Column.class);
				sql.append(field.getName());
				sql.append(" ");
				switch (annotation.Type()) {
					case Int16:
						type = "SMALLINT";
						break;
					case Int32:
						type = "INT";
						break;
					case Date:
						type = "DATE";
						break;
					case Datetime:
						type = "DATETIME";
						break;
					case Int64:
						type = "LONG";
						break;
					case Text:
						type = "TEXT";
						break;
					case Varchar:
						type = "VARCHAR(" + annotation.Accuracy() + ")";
						break;
					default:
						type = "INT";
						break;
				}
				
				sql.append(type);
				
				if(annotation.PrimaryKey()){
					sql.append(" PRIMARY KEY");
				} else {
					sql.append(" DEFAULT ");
					sql.append(annotation.DefaultValue());
				}
				
				sql.append(", ");
			}
		}
		sql.delete(sql.length()-2, sql.length());
		sql.append(");");
		sql.insert(0, "CREATE TABLE "+this.getClass().getSimpleName()+"(");
		
		return sql.toString();
	}
	
	private String generateSQLInsert(){
		StringBuilder sql;
		Field[] fields;
		
		sql = new StringBuilder();
		fields = this.getClass().getFields();
		for(Field field : fields){
			if(field.getAnnotation(Column.class) != null){
				sql.append(field.getName());
				sql.append(", ");
			}
		}
		sql.delete(sql.length()-2, sql.length());
		sql.append(") VALUES (");
		sql.insert(0, "INSERT INTO "+this.getClass().getSimpleName()+"(");
		
		return sql.toString();
	}
	
	public void save(){
		String sql;
		Field[] fields;
		Column annotation;
		
		try {
			sql = insert;
			fields = this.getClass().getFields();
			for(Field field : fields){
				if(field.getAnnotation(Column.class) != null){
					annotation = field.getAnnotation(Column.class);
					if(!annotation.Nullable() && field.get(this) == null){
						throw new Exception("Elemento nulo en una columna sin soporte para nulos");
					}
					if(field.get(this) == null){
						sql += "NULL";
					}else{
						switch (annotation.Type()) {
						case Varchar:
						case Text:
						case Date:
						case Datetime:
							
								sql += "'" + field.get(this) + "'";
							break;
						default:
							sql += field.get(this);
							break;
						}
					}
					sql += ", ";
				}
			}
			sql = sql.substring(0, sql.length()-2);
			sql += ");";
			System.out.println(sql);
			//DBConnection.getInstance().executeStatement(insert);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
