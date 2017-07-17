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

public class TstRegister {
	List<String> pathFillTestDB = new ArrayList<>();
	List<String> pathCleanTestDB = new ArrayList<>();
	Controller controller;
	ConnectionPool connectionPool;
	PreparedStatement ps;	
	ResultSet rs;
	Connection connection;
	StringBuilder sb;	 
	User user = User.getInstance();
    
	@Test(dataProvider = "incorLogIncorPassUserIn")
	  public void incorLogIncorPassUserIn(String request, String expected) {
		  controller = new Controller();
		  user.setUser(4, "Anton", "password4", "IN", "SA");
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }
	
	@Test(dataProvider = "incorLogIncorPassUserOut")
	  public void incorLogIncorPassUserOut(String request, String expected) {
		  controller = new Controller();
		  user.nullifyUser();
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }
	
	@Test(dataProvider = "incorLogCorPassUserOut")
	  public void incorLogCorPassUserOut(String request, String expected) {
		  controller = new Controller();
		  user.nullifyUser();
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }
	
	@Test(dataProvider = "incorLogCorPassUserIn")
	  public void incorLogCorPassUserIn(String request, String expected) {
		  controller = new Controller();
		  user.setUser(4, "Anton", "password4", "IN", "SA");
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }
	
	@Test(dataProvider = "corLogIncorPassUserIn")
	  public void corLogIncorPassUserIn(String request, String expected) {
		  controller = new Controller();
		  user.setUser(4, "Anton", "password4", "IN", "SA");
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }
	
	@Test(dataProvider = "corLogIncorPassUserOut")
	  public void corLogIncorPassUserOut(String request, String expected) {
		  controller = new Controller();
		  user.nullifyUser();
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }
	
	@Test(dataProvider = "corLogCorPassUserIn")
	  public void corLogCorPassUserIn(String request, String expected) {
		  controller = new Controller();
		  user.setUser(4, "Anton", "password4", "IN", "SA");
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }
	
	 @Test(dataProvider = "emptyLoginNoUserInSystem")
	  public void EmptyLoginNoUserInSystem(String request, String expected) {
		  controller = new Controller();
		  user.nullifyUser();
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  } 
	 
	  @Test(dataProvider = "emptyPasswordNoUserInSystem")
	  public void EmptyPasswordNoUserInSystem(String request, String expected) {
		  controller = new Controller();
		  user.nullifyUser();
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  } 
	  
	  @Test(dataProvider = "suchLoginAlreadyExistNoUserInSystem")
	  public void suchLoginAlreadyExistNoUserInSystem(String request, String expected) {
		  controller = new Controller();
		  user.nullifyUser();
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  } 
	
	@Test(dataProvider = "corLogCorPassUserOut")
	  public void corLogCorPassUserOut(String request, String expected) {
		  controller = new Controller();
		  user.nullifyUser();
		  String actual = controller.executeTask(request);
		  assertEquals(actual, expected);	  
	  }	
	
	@DataProvider
	public Object[][] incorLogIncorPassUserIn() {
	  return new Object[][] {
	    new Object[] { "command=registration&login=IncPers&password=incorrectPassword", "Someone from the user already in the system!" },     
	  };
	}
	
	@DataProvider
	public Object[][] incorLogIncorPassUserOut() {
	  return new Object[][] {
	    new Object[] { "command=registration&login=InccorrectLogin!&password=incorrectPassword", "Incorrect LOGIN!" },     
	  };
	}
	
	@DataProvider
	public Object[][] incorLogCorPassUserOut() {
	  return new Object[][] {
	    new Object[] { "command=registration&login=InccorrectLogin!&password=corPass123", "Incorrect LOGIN!" },     
	  };
	}
	
	@DataProvider
	public Object[][] incorLogCorPassUserIn() {
	  return new Object[][] {
	    new Object[] { "command=registration&login=InccorrectLogin!&password=corPass123", "Someone from the user already in the system!" },     
	  };
	}
	
	@DataProvider
	public Object[][] corLogIncorPassUserIn() {
	  return new Object[][] {
	    new Object[] { "command=registration&login=correctLogin&password=incorPass", "Someone from the user already in the system!" },     
	  };
	}
	
	@DataProvider
	public Object[][] corLogIncorPassUserOut() {
	  return new Object[][] {
	    new Object[] { "command=registration&login=correctLogin&password=incorPass", "Incorrect PASSWORD!" },     
	  };
	}
	
	@DataProvider
	public Object[][] corLogCorPassUserIn() {
	  return new Object[][] {
	    new Object[] { "command=registration&login=correctLogin&password=corPass123", "Someone from the user already in the system!" },     
	  };
	}
	
	@DataProvider
	public Object[][] corLogCorPassUserOut() {
	  return new Object[][] {
	    new Object[] { "command=registration&login=correctLogin&password=corPass123", "New USER registered!" },     
	  };
	}	

	@DataProvider
  		public Object[][] emptyLoginNoUserInSystem() {
	  		return new Object[][] {
	  			new Object[] { "command=registration&login=&password=newPassword", "Field login or password can not be empty!" },     
	  		};
  	} 
	
	@DataProvider
		public Object[][] emptyPasswordNoUserInSystem() {
  		return new Object[][] {
  			new Object[] { "command=registration&login=NewPerson&password=", "Field login or password can not be empty!" },     
  		};
	} 
	
	@DataProvider
	public Object[][] suchLoginAlreadyExistNoUserInSystem() {
		return new Object[][] {
			new Object[] { "command=registration&login=Anton&password=newPassword123", "User with such login already exists! Think up another login!" },     
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
