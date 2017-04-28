import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

//import tetris.Shape.Tetrominoes;


public class Grid extends JPanel implements ActionListener {
	/*
	 * Where all the magic happens
	 * Store the stationary pieces on the grid 
	 * And move active piece along grid
	 */
	
	private static final long serialVersionUID = 1L;
	
	final int Width = 10;
	final int Height = 30;
	
	Timer timer;
	boolean isFalling = true; //is the piece still falling?
	boolean isStarted = false;
	boolean isPaused = false;
	int score = 0;
	int x = 0;
	int y = 0;
	JLabel status;
	Tetromino activeTetro;
	Tetromino[] grid;
	
	public Grid(Game parent) {
		setFocusable(true);
		activeTetro = (Tetromino)PieceFactory.getTetromino(getRandomLetter());
		timer = new Timer(400, this);
		timer.start();
		status = parent.getStatus();
		grid = new Tetromino[Width * Height];
		addKeyListener(new TAdapter());
		clearBoard();
	}
	
	public static String getRandomLetter() {
		return "L";
	}
	
	public void start() {
		if (isPaused)
			return;
		isStarted = true;
		isFalling = true;
		score = 0;
		clearBoard();
		newPiece();
		timer.start();
	}
	
	private void pause() {
		if (!isStarted)
			return;
		
		isPaused = !isPaused;
		if (isPaused) {
			timer.stop();
			status.setText("Game paused");
		}
		else {
			timer.start();
			status.setText(String.valueOf(score));
		}
		
		repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (!isFalling) {
			isFalling = true;
			newPiece();
		}
		else 
			down();
	}

	int squareSide() {
		return (int) getSize().getHeight() / Height;
	}
	
	Tetromino shapeAt(int x, int y) {
		return grid[(y*Width) + x];
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Dimension size = getSize();
		int gridTop = (int) size.getHeight() - Height * squareSide();
	}
	
	private void stationary() {
		for (int i = 0; i < 4; ++i) {
			int X = x + activeTetro.x(i);
			int Y = y - activeTetro.y(i);
			grid[(y*Width) + x] = activeTetro.getLetter();
		}
		
		removeFullLines();
		
		if (!isFalling)
			newPiece();
	}
	
	private void down() {
		if (!tryMove(activeTetro, x, y - 1))
			stationary();
	}
	
	private void clearBoard() {
		for (int i = 0; i < Width * Height; ++i)
			grid[i] = Tetromino.Empty
	}
	
	private void newPiece(){
		activeTetro = (Tetromino)PieceFactory.getTetromino(getRandomLetter()); 
		x = Width/2 + 1;
		y = Height - 1 + activeTetro.minY();
		
		if (!tryMove(activeTetro, x, y)) {
			//activeTetro.setShape(" ");
			timer.stop();
			isStarted = false;
			status.setText("Game Over");
		}
	}
	
    private boolean tryMove(Tetromino tetro, int newX, int newY)
    {
        for (int i = 0; i < 4; ++i) {
            int X = newX + tetro.x(i);
            int Y = newY - tetro.y(i);
            if (X < 0 || X >= Width || Y < 0 || Y >= Height)
                return false;
            if (pieceAt(X, Y) != " ")
                return false;
        }

        activeTetro = tetro;
        x = newX;
        y = newY;
        repaint();
        return true;
    }
	
    class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            if (!isStarted || activeTetro.getLetter() == Tetromino." ") {  
                return;
            }

            int keycode = e.getKeyCode();

            if (keycode == 'p' || keycode == 'P') {
                pause();
                return;
            }

            if (isPaused)
                return;

            switch (keycode) {
            case KeyEvent.VK_LEFT:
                tryMove(activeTetro, x - 1, y);
                break;
            case KeyEvent.VK_RIGHT:
                tryMove(activeTetro, x + 1, y);
                break;
            case KeyEvent.VK_UP:
                tryMove(activeTetro.rotatePiece(), x, y);
                break;                
            case KeyEvent.VK_DOWN:
            	tryMove(activeTetro, x, y - 1);
                break;    
            }

        }
    }
	
}
