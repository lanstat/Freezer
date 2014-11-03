package org.tyranos.freezer;

public class DBConnectionFactory {
	private static DBConnectionFactory instance;
	
	private DBConnectionFactory(){}
	
	public static DBConnectionFactory getInstance(){
    	if(instance == null)
    		instance = new DBConnectionFactory();
    	return instance;
    }
	
	public IConnection getConnection(Database database){
		IConnection connection;
		switch (database) {
		case MYSQL:
			connection = new DBConnectionMysql();
			break;
		case POSTGRESQL:
			connection = null;
			break;
		case SQLITE:
			connection = null;
			break;
		default:
			connection = null;
		}
		return connection;
		
	}
}
