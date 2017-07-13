package test.java;

import org.testng.annotations.Test;

import com.epam.library.controller.Controller;

import test.java.resources.ConnectionPool;
import test.java.resources.Encodings;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import static org.testng.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestSignIn {
	Controller controller;
	ConnectionPool connectionPool;
	PreparedStatement ps = null;	
	ResultSet rs;
	Connection connection;
	String file = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB.sql";
	
	String charset = "Utf-8";
	
  @Test(dataProvider = "dp1")
  public void userExistCorrectData(String request, String expected) {
	  controller = new Controller();
	  String actual = controller.executeTask(request);
	  assertEquals(actual, expected);
	  
	  
	  
  }

  
  
  
  @DataProvider
  public Object[][] dp1() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  } 
  
  
  
  
  @BeforeTest
  public void beforeTest() {
	  connectionPool = new ConnectionPool();
	  connection = connectionPool.getConnection();
	  StringBuilder sb = Encodings.readFileWithCharset(file, charset);
	  System.out.println(""+sb);
	  try {
		ps = connection.prepareStatement(""+sb);
		ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
	  
	  
  }

  @AfterTest
  public void afterTest() {
  }

}
