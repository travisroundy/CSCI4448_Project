import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MainMenu {
	public static void login(String username, String inputtedPassword) throws Exception {
		String checkPassword = null;
		Connection dbConnect = null;
		Statement dbQuery;

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("PostgreSQL JDBC Driver Not Found.");
			e.printStackTrace();
		}

		try {
			
			dbConnect = DriverManager.getConnection("jdbc:postgresql://128.138.170.48:5432/tetris", "postgres", "postgres");
			
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}

		if (dbConnect != null) {
			try {
				dbQuery = dbConnect.createStatement();
				String userQuery = String.format("SELECT password FROM users WHERE name = \'%s\';", username);
				ResultSet rs = dbQuery.executeQuery(userQuery);
				while (rs.next()) {
					checkPassword = rs.getString("password");
				}
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		} 
		
		else {
			System.out.println("Failed to make connection!");
		}
		
		if (inputtedPassword.compareTo(checkPassword) == 0) {
			System.out.println("Logging in...");
            displayMainMenu();
        }
		
        else {
          	System.out.println("Invalid Login. Contact an administrator to gain access.");
          	
        }
		//return success;
	}
	
	public static void displayMainMenu() throws Exception {
		System.out.flush(); 
		System.out.println();
		System.out.println("======================================================");
		System.out.println("================== CSCI 4448 Tetris ==================");
		System.out.println("======================================================");
		System.out.println("Main Menu:");
		System.out.println("Start a New Game (Press 1)");
		System.out.println("View Leaderboard (Press 2)");
		System.out.println("View your scores (Press 3)");
		System.out.println();
		Scanner userInput = new Scanner(System.in);
		System.out.print("Option: ");
		String option = userInput.next();
		switch (option) {
		case "1": 
			System.out.println("new Game started");
			Game game = new Game();
			game.setLocationRelativeTo(null);
			game.setVisible(true);
			break;
		case "2": 
			System.out.println("feature not implemented");
			displayMainMenu();
			break;
		case "3": 
			System.out.println("feature not implemented");
			displayMainMenu();
			break;
		default:
			System.out.println("Invalid option. Please select an option.");
			displayMainMenu();
		}
	}
	
	public static String[] promptUser() {
		System.out.println("======================================================");
		System.out.println("================== CSCI 4448 Tetris ==================");
		System.out.println("======================================================");
		System.out.println();
		String[] userpass = new String[2];
		Scanner userInput = new Scanner(System.in);
		System.out.print("Username: ");
		String username = userInput.next();
		userpass[0] = username;
		System.out.print("Password: ");
		String password = userInput.next();
		userpass[1] = password;
		return userpass;
	}
	
	public static void main(String[] argv) throws Exception {

		String[] userpath = promptUser();
		login(userpath[0], userpath[1]);
		
	}

}
