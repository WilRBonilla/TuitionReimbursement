package dev.bonilla.utility;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/*
 * AUTHOR: WIlliam Bonilla
 * 
 * This is my JDBC class that will allow connection to
 * my OSQL Database.
 * 
 * 
 */
public class JDBCConnection {
	private static Connection conn = null;

	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Properties props = new Properties();
			// Using class loader doesn't work. Suspect it has to do with my locations of the JVM and workspace
			InputStream is = JDBCConnection.class.getResourceAsStream("/connection.properties");
			props.load(is);
			
			String url = props.getProperty("url");				// AWS url
			String username = props.getProperty("username");	// DB login
			String password = props.getProperty("password");	// DB pass
			
			conn = DriverManager.getConnection(url, username, password);
			MyLogger.logger.info("CONNECTION TO DB SUCCESS");
			return conn;
			

		} catch (Exception e) {
			MyLogger.logger.error("CONNECTION TO DB FAILED");
			e.printStackTrace();
		}
		
		return null;

	}

}
