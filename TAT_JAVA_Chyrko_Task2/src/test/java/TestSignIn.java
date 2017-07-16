package test.java;

import org.testng.annotations.Test;

import com.epam.library.controller.Controller;
import com.epam.library.dao.pool.ConnectionPool;

import test.java.resources.Encodings;
import test.java.resources.PathCommand;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import static org.testng.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestSignIn {

	List<String> pathFillTestDB = new ArrayList<>();
	List<String> pathCleanTestDB = new ArrayList<>();
	Controller controller;
	ConnectionPool connectionPool;
	PreparedStatement ps;	
	ResultSet rs;
	Connection connection;
	StringBuilder sb;	 
  
  @Test(dataProvider = "userExistButIncorrectPassword")
  public void userExistButIncorrectPassword(String request, String expected) {
	  controller = new Controller();
	  String actual = controller.executeTask(request);
	  assertEquals(actual, expected);	  
  }   
  
  @Test(dataProvider = "userExistButIncorrectLogin")
  public void userExistButIncorrectLogin(String request, String expected) {
	  controller = new Controller();
	  String actual = controller.executeTask(request);
	  assertEquals(actual, expected);	  
  } 
  
  @Test(dataProvider = "userIsNotExist")
  public void userIsNotExist(String request, String expected) {
	  controller = new Controller();
	  String actual = controller.executeTask(request);
	  assertEquals(actual, expected);	  
  }
  
  @Test(dataProvider = "userExistAndCorrectData")
  public void userExistCorrectData(String request, String expected) {
	  controller = new Controller();
	  String actual = controller.executeTask(request);
	  assertEquals(actual, expected);	  
  }
  
  @Test(dataProvider = "userExistEmptyString")
  public void userExistEmptyString(String request, String expected) {
	  controller = new Controller();
	  String actual = controller.executeTask(request);
	  assertEquals(actual, expected);	  
  } 
  
  @DataProvider
  public Object[][] userExistEmptyString() {
    return new Object[][] {
      new Object[] { "command=sign_in&login=&password=", "Incorrect login or password" },     
    };
  }
  
  @DataProvider
  public Object[][] userIsNotExist() {
    return new Object[][] {
      new Object[] { "command=sign_in&login=NoSuchUser&password=fictionalPassword", "Such a user does not exist yet! You can register!" },     
    };
  } 
  
  @DataProvider
  public Object[][] userExistAndCorrectData() {
    return new Object[][] {
      new Object[] { "command=sign_in&login=Anton&password=password4", "Welcom!" },     
    };
  } 
  
  @DataProvider
  public Object[][] userExistButIncorrectPassword() {
    return new Object[][] {
      new Object[] { "command=sign_in&login=Anton&password=wrongPassword", "Wrong login or password!" },     
    };
  }  
  
  @DataProvider
  public Object[][] userExistButIncorrectLogin() {
    return new Object[][] {
      new Object[] { "command=sign_in&login=WrongLogin&password=password4", "Such a user does not exist yet! You can register!" },     
    };
  }  
  
  @BeforeTest
  public void beforeTest() {
	  
	  connectionPool = new ConnectionPool();
	  connection = connectionPool.getConnection();

	  pathFillTestDB.add(PathCommand.INSERT_BOOKS);
	  pathFillTestDB.add(PathCommand.INSERT_AUTHORS);
	  pathFillTestDB.add(PathCommand.INSERT_GENRES);
	  pathFillTestDB.add(PathCommand.INSERT_M2M_B_A);
	  pathFillTestDB.add(PathCommand.INSERT_M2M_B_G);
	  pathFillTestDB.add(PathCommand.INSERT_USERS);
	  pathFillTestDB.add(PathCommand.INSERT_SUBSCRIPTIONS);		
	  pathFillTestDB.add(PathCommand.CREATE_TRIGGER_ADD_B_QUANTITY);
	  pathFillTestDB.add(PathCommand.CREATE_TRIGGER_BOOK_AVAILABLE);
	  pathFillTestDB.add(PathCommand.CREATE_TRIGGER_CREATE_DATE);
	  pathFillTestDB.add(PathCommand.CREATE_TRIGGER_SUBSTRACT_B_QUANTITY);
		for (int i = 0; i < pathFillTestDB.size(); i++) {
			sb = Encodings.readFileWithCharset(pathFillTestDB.get(i), PathCommand.CHARSET);			
			System.out.println(""+sb);
			try {
				ps = connection.prepareStatement(""+sb);
				ps.executeUpdate();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}		  
  }

  @AfterTest
  public void afterTest() {

	  connectionPool = new ConnectionPool();
	  connection = connectionPool.getConnection();

	  pathCleanTestDB.add(PathCommand.DELETE_M2M_B_A);
	  pathCleanTestDB.add(PathCommand.DELETE_M2M_B_G);
	  pathCleanTestDB.add(PathCommand.DELETE_SUBSCRIPTIONS);
	  pathCleanTestDB.add(PathCommand.DELETE_TRIGGER_ADD_B_QUANTITY);
	  pathCleanTestDB.add(PathCommand.DELETE_TRIGGER_SUBSTRACT_B_QUANTITY);
	  pathCleanTestDB.add(PathCommand.DELETE_TRIGGER_CREATE_DATE);
	  pathCleanTestDB.add(PathCommand.DELETE_TRIGGER_BOOK_AVAILABLE);
	  pathCleanTestDB.add(PathCommand.DELETE_AUTHORS);
	  pathCleanTestDB.add(PathCommand.DELETE_GENRES);
	  pathCleanTestDB.add(PathCommand.DELETE_USERS);
	  pathCleanTestDB.add(PathCommand.DELETE_BOOKS);	
	
		for (int i = 0; i < pathCleanTestDB.size(); i++) {
			sb = Encodings.readFileWithCharset(pathCleanTestDB.get(i), PathCommand.CHARSET);			
			System.out.println(""+sb);
			try {
				ps = connection.prepareStatement(""+sb);
				ps.executeUpdate();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}	
  }

}
