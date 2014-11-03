package org.tyranos.freezer;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IConnection {
	public void init(String Host, String Username, String Password, String Database) throws SQLException;
	public ResultSet executeQuery(String sql) throws SQLException;
	public int executeStatement(String sql) throws SQLException;
}
