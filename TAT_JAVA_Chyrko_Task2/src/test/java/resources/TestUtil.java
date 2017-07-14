package test.java.resources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;

import org.apache.ibatis.jdbc.ScriptRunner;

public class TestUtil {
	
	
	public static void loadDB(String dbPath) {
		Reader reader;
		TestConnectionPool connectionPool;	
		connectionPool = new TestConnectionPool();
		Connection connection = connectionPool.getConnection();
		org.apache.ibatis.logging.LogFactory.useNoLogging();
		ScriptRunner sr = new ScriptRunner(connection);
		
			try {
				reader = new BufferedReader(new FileReader(dbPath));
				sr.runScript(reader);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
}
