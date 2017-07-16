package test.java;

import static org.testng.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.library.bean.User;
import com.epam.library.controller.Controller;
import com.epam.library.dao.pool.ConnectionPool;

import test.java.resources.Encodings;
import test.java.resources.PathCommand;

public class TstSignOut {
	List<String> pathFillTestDB = new ArrayList<>();
	List<String> pathCleanTestDB = new ArrayList<>();
	Controller controller;
	ConnectionPool connectionPool;
	PreparedStatement ps;	
	ResultSet rs;
	Connection connection;
	StringBuilder sb;	 
	User user = User.getInstance();
    
	@Test(dataProvider = "noUserInSystem")
	  public void noUserInSystem(String request, String expected) {
		  controller = new Controller();
		  user.nullifyUser();
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }
	
  @Test(dataProvider = "EmptyLoginNoUserInSystem")
  public void EmptyLoginNoUserInSystem(String request, String expected) {
	  controller = new Controller();
	  user.nullifyUser();
	  String actual = controller.executeTask(request);
	  assertEquals(actual, expected);	  
  } 

  @Test(dataProvider = "EmptyLoginUserInSystem")
  	public void EmptyLoginUserInSystem(String request, String expected) {
	  controller = new Controller();
	  user.setUser(4, "Anton", "password4", "IN", "SA");
	  String actual = controller.executeTask(request);
	  assertEquals(actual, expected);	  
  } 
  
  @Test(dataProvider = "userExistButAlreadyOUT")
  public void userExistButAlreadyOUT(String request, String expected) {
	  controller = new Controller();
	  user.setUser(4, "Anton", "password4", "OUT", "SA");
	  String actual = controller.executeTask(request);
	  assertEquals(actual, expected);	  
  } 
  
  @Test(dataProvider = "clearDataUserInSystem")
	public void clearDataUserInSystem(String request, String expected) {
	  controller = new Controller();
	  user.setUser(4, "Anton", "password4", "IN", "SA");
	  String actual = controller.executeTask(request);
	  assertEquals(actual, expected);	  
}   
  
  @Test(dataProvider = "userAlreadySignOut", dependsOnMethods = "clearDataUserInSystem")
  public void userAlreadySignOut(String request, String expected) {	  
	  controller = new Controller();
	  user.setUser(4, "Anton", "password4", "IN", "SA");
	  String actual = controller.executeTask(request);
	  assertEquals(actual, expected);	  
  }   


  @DataProvider
  public Object[][] clearDataUserInSystem() {
    return new Object[][] {
      new Object[] { "command=sign_out&login=Anton", "Good Bye!" },     
    };
  }
  
  @DataProvider
  public Object[][] noUserInSystem() {
    return new Object[][] {
      new Object[] { "command=sign_out&login=Anton", "No one user in system!" },     
    };
  } 
  
  @DataProvider
  public Object[][] EmptyLoginNoUserInSystem() {
    return new Object[][] {
      new Object[] { "command=sign_out&login=", "No one user in system!" },     
    };
  } 
  
  @DataProvider
  public Object[][] EmptyLoginUserInSystem() {
    return new Object[][] {
      new Object[] { "command=sign_out&login=", "Only the user can do SingOut!" },     
    };
  } 
  
  @DataProvider
  public Object[][] userExistButAlreadyOUT() {
    return new Object[][] {
      new Object[] { "command=sign_out&login=Anton", "You must be SignIn!" },     
    };
  }  
  
  @DataProvider
  public Object[][] userAlreadySignOut() {
    return new Object[][] {
      new Object[] { "command=sign_out&login=Anton", "User already SignOut!" },     
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
	  pathFillTestDB.add(PathCommand.INSERT_USERS_FOR_SIGN_OUT);
	  pathFillTestDB.add(PathCommand.INSERT_SUBSCRIPTIONS);		
	  pathFillTestDB.add(PathCommand.CREATE_TRIGGER_ADD_B_QUANTITY);
	  pathFillTestDB.add(PathCommand.CREATE_TRIGGER_BOOK_AVAILABLE);
	  pathFillTestDB.add(PathCommand.CREATE_TRIGGER_CREATE_DATE);
	  pathFillTestDB.add(PathCommand.CREATE_TRIGGER_SUBSTRACT_B_QUANTITY);
		for (int i = 0; i < pathFillTestDB.size(); i++) {
			sb = Encodings.readFileWithCharset(pathFillTestDB.get(i), PathCommand.CHARSET);			
//			System.out.println(""+sb);
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
//			System.out.println(""+sb);
			try {
				ps = connection.prepareStatement(""+sb);
				ps.executeUpdate();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}	
  }
}
