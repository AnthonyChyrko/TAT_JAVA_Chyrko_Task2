package test.java;

import org.testng.annotations.Test;

import com.epam.library.controller.Controller;
import com.epam.library.dao.pool.ConnectionPool;

import test.java.resources.Encodings;
import test.java.resources.FillTestDB;

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
	private static final String INSERT_BOOKS = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\insertData\\insertBooks.sql";
	private static final String INSERT_AUTHORS = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\insertData\\insertAuthors.sql";
	private static final String INSERT_GENRES = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\insertData\\insertGenres.sql";
	private static final String INSERT_USERS = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\insertData\\insertUsers.sql";
	private static final String INSERT_SUBSCRIPTIONS = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\insertData\\insertSubscriptions.sql";
	private static final String INSERT_M2M_B_A = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\insertData\\insertM2M_B_A.sql";
	private static final String INSERT_M2M_B_G = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\insertData\\insertM2M_B_G.sql";	
	private static final String CREATE_TRIGGER_ADD_B_QUANTITY = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\insertData\\createTriggerAddBQuantity.sql";
	private static final String CREATE_TRIGGER_BOOK_AVAILABLE = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\insertData\\createTriggerBookAvailability.sql";
	private static final String CREATE_TRIGGER_CREATE_DATE = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\insertData\\createTriggerCreateDate.sql";
	private static final String CREATE_TRIGGER_SUBSTRACT_B_QUANTITY = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\insertData\\createTriggerSubtractBQuantity.sql";
	
	private static final String DELETE_BOOKS = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\deleteData\\deleteBooks.sql";
	private static final String DELETE_AUTHORS = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\deleteData\\deleteAuthors.sql";
	private static final String DELETE_GENRES = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\deleteData\\deleteGenres.sql";
	private static final String DELETE_USERS = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\deleteData\\deleteUsers.sql";
	private static final String DELETE_SUBSCRIPTIONS = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\deleteData\\deleteSubscriptions.sql";
	private static final String DELETE_M2M_B_A = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\deleteData\\deleteM2M_B_A.sql";
	private static final String DELETE_M2M_B_G = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\deleteData\\deleteM2M_B_G.sql";	
	private static final String DELETE_TRIGGER_ADD_B_QUANTITY = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\deleteData\\deleteTriggerAddBQuantity.sql";
	private static final String DELETE_TRIGGER_BOOK_AVAILABLE = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\deleteData\\deleteTriggerBookAvailability.sql";
	private static final String DELETE_TRIGGER_CREATE_DATE = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\deleteData\\deleteTriggerCreateDate.sql";
	private static final String DELETE_TRIGGER_SUBSTRACT_B_QUANTITY = "c:\\gitTask\\TAT_JAVA_Chyrko_Task2\\DBLibrary\\fillInTestDB\\deleteData\\deleteTriggerSubtractBQuantity.sql";
	
	
	
	
	
	
	
	
	private static final String CHARSET = "windows-1251";
	
	List<String> pathFillTestDB = new ArrayList<>();
	List<String> pathCleanTestDB = new ArrayList<>();
	Controller controller;
	ConnectionPool connectionPool;
	PreparedStatement ps;	
	ResultSet rs;
	Connection connection;
	FillTestDB fillTestDB;
	StringBuilder sb;
	
	
	
	
  @Test(dataProvider = "dp1")
  public void userExistCorrectData(String request, String expected) {
	  controller = new Controller();
	  String actual = controller.executeTask(request);
	  assertEquals(actual, expected);
	  
	  
	  
  }

  
  
  
  @DataProvider
  public Object[][] dp1() {
    return new Object[][] {
      new Object[] { "command=sign_in&login=Anton&password=password4", "Welcom!" },
     
    };
  } 
  
  
  
  
  @BeforeTest
  public void beforeTest() {
	  
	  connectionPool = new ConnectionPool();
	  connection = connectionPool.getConnection();

	  pathFillTestDB.add(INSERT_BOOKS);
	  pathFillTestDB.add(INSERT_AUTHORS);
	  pathFillTestDB.add(INSERT_GENRES);
	  pathFillTestDB.add(INSERT_M2M_B_A);
	  pathFillTestDB.add(INSERT_M2M_B_G);
	  pathFillTestDB.add(INSERT_USERS);
	  pathFillTestDB.add(INSERT_SUBSCRIPTIONS);		
	  pathFillTestDB.add(CREATE_TRIGGER_ADD_B_QUANTITY);
	  pathFillTestDB.add(CREATE_TRIGGER_BOOK_AVAILABLE);
	  pathFillTestDB.add(CREATE_TRIGGER_CREATE_DATE);
	  pathFillTestDB.add(CREATE_TRIGGER_SUBSTRACT_B_QUANTITY);
		for (int i = 0; i < pathFillTestDB.size(); i++) {
			sb = Encodings.readFileWithCharset(pathFillTestDB.get(i), CHARSET);			
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

	  pathCleanTestDB.add(DELETE_M2M_B_A);
	  pathCleanTestDB.add(DELETE_M2M_B_G);
	  pathCleanTestDB.add(DELETE_SUBSCRIPTIONS);
	  pathCleanTestDB.add(DELETE_TRIGGER_ADD_B_QUANTITY);
	  pathCleanTestDB.add(DELETE_TRIGGER_SUBSTRACT_B_QUANTITY);
	  pathCleanTestDB.add(DELETE_TRIGGER_CREATE_DATE);
	  pathCleanTestDB.add(DELETE_TRIGGER_BOOK_AVAILABLE);
	  pathCleanTestDB.add(DELETE_AUTHORS);
	  pathCleanTestDB.add(DELETE_GENRES);
	  pathCleanTestDB.add(DELETE_USERS);
	  pathCleanTestDB.add(DELETE_BOOKS);	
	
		for (int i = 0; i < pathCleanTestDB.size(); i++) {
			sb = Encodings.readFileWithCharset(pathCleanTestDB.get(i), CHARSET);			
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
