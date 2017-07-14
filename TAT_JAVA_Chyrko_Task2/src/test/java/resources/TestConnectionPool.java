package test.java.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class TestConnectionPool {
//	 private String driver;
	 private String url;
	 private String user;
	 private String password;
	 private Connection instance;
	 
	 public TestConnectionPool(){		 
		 try {			
//			 this.driver = DBParameter.DB_DRIVER;
			 this.user =TestDBParameter.DB_USER;
	         this.url = TestDBParameter.DB_URL;
	         this.password = TestDBParameter.DB_PASSWORD;
			 instance = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			
		}		 
	 }
	 
	 public Connection getConnection(){
		 return instance;
	 }
	 
	 
}
