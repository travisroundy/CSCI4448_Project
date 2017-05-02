import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MainMenu {
	
	// Method checks inputed username and password and compares them to database
	private static void login(String username, String inputtedPassword) throws Exception {
		String checkUser = null;
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
			// Connect to Postgres SQL DB 
			dbConnect = DriverManager.getConnection("jdbc:postgresql://128.138.170.48:5432/tetris", "postgres", "postgres");
			
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		// If the connection is successful
		if (dbConnect != null) {
			try {
				dbQuery = dbConnect.createStatement();
				// Query the password and name based on the inputted username and store them to be checked
				String userQuery = String.format("SELECT * FROM users WHERE name = \'%s\';", username);
				ResultSet rs = dbQuery.executeQuery(userQuery);
				while (rs.next()) {
					checkUser = rs.getString("name");
					checkPassword = rs.getString("password");
				}
				 
			} catch (SQLException e) {
				System.out.println("Invalid Login. Contact an administrator to gain access.");
				String[] userpath = promptUser();
				login(userpath[0], userpath[1]);
			}
		} 
		
		else {
			System.out.println("Failed to make connection!");
		}
		dbConnect.close();
		
		//Check to be sure user is in database
		if (checkUser != null) {
			// Then check the passwords
			if (inputtedPassword.compareTo(checkPassword) == 0) {
				System.out.println("Logging in...");
				// Display the menu if successful
				displayMainMenu();
        	}
		
        	else {
        		//Otherwise display error and redisplay login
        		System.out.println("Invalid Login. Contact an administrator to gain access.");
        		System.out.println();
          		String[] userpath = promptUser();
          		login(userpath[0], userpath[1]);
			
        	}
		}
		//Otherwise display error and redisplay login
		else {
    		System.out.println("Invalid Login. Contact an administrator to gain access.");
    		System.out.println();
      		String[] userpath = promptUser();
      		login(userpath[0], userpath[1]);
		
    	}
	}
	
	// Main Menu Display
	private static void displayMainMenu() throws Exception {
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
		// Take user input and check the input
		Scanner userInput = new Scanner(System.in);
		System.out.print("Option: ");
		String option = userInput.next();
		switch (option) {
		//Start New Game
		case "1": 
			System.out.println("new Game started");
			Game game = new Game();
			game.setLocationRelativeTo(null);
			game.setVisible(true);
			break;
		// Eventually View Leaderboard with other users.
		case "2": 
			System.out.println("feature not implemented");
			displayMainMenu();
			break;
		// View Personal scores
		case "3": 
			System.out.println("feature not implemented");
			displayMainMenu();
			break;
		default:
			System.out.println("Invalid option. Please select an option.");
			
		}
	}
	
	// Method to prompt the user to sign in
	private static String[] promptUser() {
		System.out.println("======================================================");
		System.out.println("================== CSCI 4448 Tetris ==================");
		System.out.println("======================================================");
		System.out.println();
		String[] userpass = new String[2];
		// Waits for user input and then stores input to be passed into next function.
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
