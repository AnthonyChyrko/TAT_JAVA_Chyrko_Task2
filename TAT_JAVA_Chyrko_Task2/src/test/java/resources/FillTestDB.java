package test.java.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FillTestDB {
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
	String charset = "Utf-8";
	PreparedStatement ps;
	StringBuilder sb;
	List<String> commands = new ArrayList<>();
	public void execute(String hi){
		System.out.println("Hi!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		commands.add(INSERT_BOOKS);
//		commands.add(INSERT_AUTHORS);
//		commands.add(INSERT_GENRES);
//		commands.add(INSERT_M2M_B_A);
//		commands.add(INSERT_M2M_B_G);
//		commands.add(INSERT_USERS);
//		commands.add(INSERT_SUBSCRIPTIONS);
//		System.out.println(commands.get(0));
		
//		commands.add(CREATE_TRIGGER_ADD_B_QUANTITY);
//		commands.add(CREATE_TRIGGER_BOOK_AVAILABLE);
//		commands.add(CREATE_TRIGGER_CREATE_DATE);
//		commands.add(CREATE_TRIGGER_SUBSTRACT_B_QUANTITY);
//		for (int i = 0; i < commands.size(); i++) {
//			sb = Encodings.readFileWithCharset(commands.get(i), charset);			
//			System.out.println(""+sb);
//			try {
//				ps = connection.prepareStatement(""+sb);
//				ps.executeUpdate();
//			} catch (SQLException e) {
//				System.out.println("FROM HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+i);
//				e.printStackTrace();
//			}
//		}		
	}	
}
