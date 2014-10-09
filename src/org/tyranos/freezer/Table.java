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
 * @author mauricio
 * @param <T>
 */
public class Table<T> {

    /*
     Atributos escenciales, el tipo de Dato "Tabla"
     Y la conexión
     */
    private final DBConnection Connection;
    private final T Entity;

    private static final String SELECT = "Select ";
    private static final String UPDATE = "Update ";
    private static final String DELETE = "Delete ";
    private static final String FROM = " From ";
    private static final String SEMICOLON = ";";
    private static final String ALL = " * ";
    private static final String WHERE = " Where ";
    private static final String COLON = " , ";

    private static final String INSERT = "Insert into ";
    private static final String VALUES = " values( ";
    private static final String END_VALUES = ");";
    private static final String SET = " set ";

    private static final String[] OPERATIONS = {" = ", " != ", "< ", " > "};

    public static final int OPERATION_EQUALS = 0;
    public static final int OPERATION_DIFFERENT = 1;
    public static final int OPERATION_GREATER = 2;
    public static final int OPERATION_LESSER = 3;

    public Table(DBConnection c, T t) {
        Connection = c;
        Entity = t;
    }

    private String buildSelectQuery() {
        String s = "";
        s += SELECT + ALL + FROM + Entity.getClass().getSimpleName() + SEMICOLON;
        return s;
    }

    private String buildSelectQuery(String columnName, int operationCode, Object Value) {
        String s = "";
        s += SELECT + ALL + FROM + Entity.getClass().getSimpleName() + WHERE;
        if (Value instanceof String || Value instanceof Date
                    || Value instanceof Time || Value instanceof Character) {
            s += columnName + OPERATIONS[operationCode] + surroundWithQuotes(Value) + SEMICOLON;
        } else {
        s += columnName + OPERATIONS[operationCode] + Value + SEMICOLON;
        }
        return s;
    }

    private String buildInsertQuery(T t) throws IllegalArgumentException, IllegalAccessException {
        String s = "";
        s += INSERT + Entity.getClass().getSimpleName() + VALUES;
        Object o = (Object) t;
        Field[] fs = t.getClass().getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            Object o1 = f.get(o);
            if (o1 instanceof String || o1 instanceof Date
                    || o1 instanceof Time || o1 instanceof Character) {
                s += surroundWithQuotes(o1);
            } else {
                if (o1 == null) {
                    s += "null";
                } else {
                    s += o1;
                }
            }
            if (i < fs.length - 1) {
                s += COLON;
            }
        }
        s += END_VALUES;
        return s;
    }
    
    private String buildUpdateQuery(T t, String columnName, int operationCode, Object Value) throws IllegalArgumentException, IllegalAccessException{
        String s = "";
        s += UPDATE + Entity.getClass().getSimpleName() + SET;
        Object o = (Object) t;
        Field[] fs = t.getClass().getDeclaredFields();
         for (int i = 0; i < fs.length; i++) {
             Field f = fs[i];
             Object o1 = f.get(o);
             
            if(o1 != null){
                if (o1 instanceof String || o1 instanceof Date
                    || o1 instanceof Time || o1 instanceof Character) {
                s += f.getName() + OPERATIONS[OPERATION_EQUALS] + surroundWithQuotes(o1);
            }  else {
                s += f.getName() + OPERATIONS[OPERATION_EQUALS] + o1;
                }
            }
             if (i < fs.length - 1) {
                s += COLON;
            }
             
            }
                if (Value instanceof String || Value instanceof Date
                    || Value instanceof Time || Value instanceof Character) {
         s += WHERE + columnName + OPERATIONS[OPERATION_EQUALS] + surroundWithQuotes(Value) + SEMICOLON;
                } else {
                    s += WHERE + columnName + OPERATIONS[OPERATION_EQUALS] + Value + SEMICOLON;
                }
         return s;
    }
    
    private  ArrayList<T> resultSetToList(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException{
        ArrayList<T> list = new ArrayList();
        if (rs.first()) {
            do {
                Object o = Entity.getClass().newInstance();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    Object o1 = rs.getObject(i);
                    o.getClass().getFields()[i - 1].set(o,o1);
                }
                list.add((T) o);
            } while (rs.next());
        }
        return list;
    }
    
    public synchronized ArrayList<T> SelectAll() throws SQLException, InstantiationException, IllegalAccessException{
        ResultSet rs = Connection.executeQuery(buildSelectQuery());
        return resultSetToList(rs);
    }
    
    public synchronized ArrayList<T> Select(String columnName, int operationCode, Object Value) throws SQLException, InstantiationException, IllegalAccessException{
        String sql = buildSelectQuery(columnName, operationCode, Value);
        ResultSet rs = Connection.executeQuery(sql);
        return resultSetToList(rs);
    }
    
    public synchronized int Insert(T t) throws IllegalArgumentException, IllegalAccessException, SQLException{
        String sql = buildInsertQuery(t);
        return Connection.executeStatement(sql);
    }
    
    private static String surroundWithQuotes(Object o) {
        return "\'" + o + "\'";
    }

}
