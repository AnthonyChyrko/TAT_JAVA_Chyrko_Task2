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

public class TstBanUser {
	List<String> pathFillTestDB = new ArrayList<>();
	List<String> pathCleanTestDB = new ArrayList<>();
	Controller controller;
	ConnectionPool connectionPool;
	PreparedStatement ps;	
	ResultSet rs;
	Connection connection;
	StringBuilder sb;	 
	User user = User.getInstance();  

	
	@Test(dataProvider = "corLogCorSignInUserOut")
	  public void corLogCorSignInUserOut(String request, String expected) {
		  controller = new Controller();
		  user.nullifyUser();
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }	
	
	@Test(dataProvider = "corLogIncorSignInUserIn")
	  public void corLogIncorSignInUserIn(String request, String expected) {
		  controller = new Controller();
		  user.setUser(4, "Anton", "passWord4", "IN", "SA");
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }	
	
	@Test(dataProvider = "corLogCorSignInUserInAccessU")
	  public void corLogCorSignInUserInAccessU(String request, String expected) {
		  controller = new Controller();
		  user.setUser(4, "Petr", "passWord2", "IN", "U");
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }	
	
	@Test(dataProvider = "corLogCorSignInUserInAccessSABanYourself")
	  public void corLogCorSignInUserInAccessSABanYourself(String request, String expected) {
		  controller = new Controller();
		  user.setUser(4, "Anton", "passWord4", "IN", "SA");
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }	
	
	@Test(dataProvider = "corLogCorSignInUserInAccessABanSA")
	  public void corLogCorSignInUserInAccessABanSA(String request, String expected) {
		  controller = new Controller();
		  user.setUser(3, "Sema", "passWord3", "IN", "A");
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }	
	
	@Test(dataProvider = "corLogCorSignInUserInAccessUBan")
	  public void corLogCorSignInUserInAccessUBan(String request, String expected) {
		  controller = new Controller();
		  user.setUser(2, "Petr", "passWord2", "IN", "U");
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }	
	
	@Test(dataProvider = "corLogCorSignInUserInAccessABanNoExistedUser")
	  public void corLogCorSignInUserInAccessABanNoExistedUser(String request, String expected) {
		  controller = new Controller();
		  user.setUser(3, "Sema", "passWord3", "IN", "A");
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }	
	
	@Test(dataProvider = "corLogCorSignInUserInAccessABanExistedUser")
	  public void corLogCorSignInUserInAccessABanExistedUser(String request, String expected) {
		  controller = new Controller();
		  user.setUser(3, "Sema", "passWord3", "IN", "A");
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }	
	
	@Test(dataProvider = "corLogCorSignInUserInAccessABanExistedUserAlreadyBAN", dependsOnMethods = "corLogCorSignInUserInAccessABanExistedUser")
	  public void corLogCorSignInUserInAccessABanExistedUserAlreadyBAN(String request, String expected) {
		  controller = new Controller();
		  user.setUser(3, "Sema", "passWord3", "IN", "A");
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }	
	
	@Test(dataProvider = "corLogCorSignInUserInAccessSABanExistedUser")
	  public void corLogCorSignInUserInAccessSABanExistedUser(String request, String expected) {
		  controller = new Controller();
		  user.setUser(4, "Anton", "passWord4", "IN", "SA");
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }	
	@DataProvider
	public Object[][] corLogCorSignInUserInAccessSABanExistedUser() {
		return new Object[][] {
			new Object[] { "command=ban_user&login=Sema&signIn=BAN", "Profile is changed!" },     
		};
	} 
	
	@DataProvider
	public Object[][] corLogCorSignInUserInAccessABanExistedUserAlreadyBAN() {
		return new Object[][] {
			new Object[] { "command=ban_user&login=Petr&signIn=BAN", "User already BAN!" },     
		};
	} 
	
	@DataProvider
	public Object[][] corLogCorSignInUserInAccessABanExistedUser() {
		return new Object[][] {
			new Object[] { "command=ban_user&login=Petr&signIn=BAN", "Profile is changed!" },     
		};
	} 
	
	@DataProvider
	public Object[][] corLogCorSignInUserInAccessABanNoExistedUser() {
		return new Object[][] {
			new Object[] { "command=ban_user&login=NoSuchUser&signIn=BAN", "User is absent in db! Please registration!" },     
		};
	} 
	
	@DataProvider
	public Object[][] corLogCorSignInUserInAccessUBan() {
		return new Object[][] {
			new Object[] { "command=ban_user&login=Anton&signIn=BAN", "You do not have permission to BLOCK/UNLOCK user!" },     
		};
	} 
	
	@DataProvider
	public Object[][] corLogCorSignInUserInAccessABanSA() {
		return new Object[][] {
			new Object[] { "command=ban_user&login=Anton&signIn=BAN", "You can not block superadmin!" },     
		};
	} 
	
	@DataProvider
	public Object[][] corLogCorSignInUserInAccessSABanYourself() {
		return new Object[][] {
			new Object[] { "command=ban_user&login=Anton&signIn=BAN", "You can not ban yourself!" },     
		};
	} 
	
	@DataProvider
	public Object[][] corLogCorSignInUserInAccessU() {
		return new Object[][] {
			new Object[] { "command=ban_user&login=Anton&signIn=BAN", "You do not have permission to BLOCK/UNLOCK user!" },     
		};
	} 
	
	@DataProvider
	public Object[][] corLogIncorSignInUserIn() {
		return new Object[][] {
			new Object[] { "command=ban_user&login=Anton&signIn=BANT", "You can not change a parameter to this value!" },     
		};
	} 
	
	@DataProvider
	public Object[][] corLogCorSignInUserOut() {
		return new Object[][] {
			new Object[] { "command=ban_user&login=Anton&signIn=BAN", "You must be registered or SignIn!" },     
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
			try {
				ps = connection.prepareStatement(""+sb);
				ps.executeUpdate();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}	
  }
}
