import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class Game extends JFrame{
	/*
	 * Class to set up and start a new game of tetris 
	 */
	private static final long serialVersionUID = 1L;
	
	public String currentPlayer;
	
	JLabel status;
	
	public Game() {
		status = new JLabel(" 0");
		add(status, BorderLayout.NORTH);
		Grid grid = new Grid(this);
		add(grid);
		grid.start(); 
		setSize(400, 600);
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
	
	public void showNextPiece()
	{
		
	}
	
}
