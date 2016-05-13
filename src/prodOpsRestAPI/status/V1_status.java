package prodOpsRestAPI.status;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.*;
import prodOps.dao.*;

/**
 * This is the root path for our restful api service
 * In the web.xml file, we specified that /api/ need to be in the URL to get into this class.
 * @author Aurora Bravo
 */
@Path("/v1/status/") //route to java class
public class V1_status {
	
	//version of the api
	private static final String api_version= "00.01.00";
	
	/**
	 * This method sits at the root of the api. It will return the
	 * name of this api.
	 * @return name String
	 */
	@GET //HTTP verb require to access method
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle(){
		return "<p>Java Web Service </p> ";
	}
	
	/**
	 * This method will return the version number of the api
	 * Note: this is nested one down from the root.
	 * @return version String
	 */
	@Path("/version") //route to specific method
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion(){
		return "<p>Version: </p>" + api_version;
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		
		try {
			conn = MySQL_db.MysSqlSourceConn().getConnection();
			query = conn.prepareStatement("select to_char(sysdate, YYYY-MM-DD HH23:MI:SS') DATETIME " + "from sys.dual");
			ResultSet rs = query.executeQuery();
			
			while (rs.next()) {
				// /*Debug*/ System.out.println(rs.getString("DATETIME"));
				myString = rs.getString("DATETIME");
			}
			
			query.close(); //close connection
			
			returnString = "<p>Database Status </p>" + "<p>Database Date/Time return: " + myString  + "/p>";
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) conn.close();
		}
		
		return returnString;
	}
	

}
