package prodOps.dao;

import javax.naming.*;
import javax.sql.*;

public class MySQL_db {
	
	private static DataSource MySqlSource = null;
	private static Context context = null;
	
	public static DataSource MysSqlSourceConn() throws Exception {
		
		if (MySqlSource != null) {
			return MySqlSource;
		}
		
		try {
			if (context == null) {
				context = new InitialContext();
			}
			MySqlSource = (DataSource) context.lookup("mysql_db_test");
		} catch (Exception e){
			e.printStackTrace();
			
		}
		return MySqlSource;
	}
	

}
