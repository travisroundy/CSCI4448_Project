import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class Game extends JFrame{
	/*
	 * Class to set up and start a new game of tetris 
	 */
	private static final long serialVersionUID = 1L;
	
	public int timer;
	public String currentPlayer;
	public int[] currentPieces;
	public int score;
	public final int windowHeight = 30;
	public final int windowWidth = 10;
	public boolean[][] border = new boolean[30][10]; 
	
	JLabel status;
	
	public Game() {
		status = new JLabel(" 0");
		add(status, BorderLayout.SOUTH);
		Grid grid = new Grid(this);
		add(grid);
		grid.start();
		
		setSize(200, 400);
		setTitle("Tetris");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public JLabel getStatus(){
		return status;
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.setLocationRelativeTo(null);
		game.setVisible(true);
	}
	
	
	//other crap to add in later
	
	public void startTimer(int timer)
	{
		
	}
	
	public void stopTimer(int timer)
	{
		
	}
	
	public void displayPauseMenu(boolean pressedPause)
	{
		
	}
	
	public void hidePauseMenu(boolean endPause)
	{
		
	}
	
	public int endGame(boolean gameOver)
	{
		return 1;
	}
	
	public int initializeScore()
	{
		//or nah
		return 1;
	}
	
	public void showNextPiece()
	{
		
	}
	
}
