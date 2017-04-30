import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

//import tetris.Shape.Tetrominoes;


public class Grid extends JPanel implements ActionListener {
	/*
	 * The piece client
	 * Store the stationary pieces on the grid 
	 * And move active piece along grid
	 */
	
	private static final long serialVersionUID = 1L;
	
	final int Width = 10;
	final int Height = 20;
	private static final String letters[] = {"I", "J", "L", "O", "S", "T", "Z"};
	
	Timer timer;
	boolean isFalling = true; //is the piece still falling?
	boolean isStarted = false;
	boolean isPaused = false;
	int score = 0;
	int x = 0;
	int y = 0;
	JLabel status;
	Tetromino activeTetro;
	Color[][] grid;
	
	public Grid(Game parent) {
		setFocusable(true);
		activeTetro = (Tetromino)PieceFactory.getTetromino(getRandomLetter());
		timer = new Timer(400, this);
		timer.start();
		status = parent.getStatus();
		grid = new Color[Width][Height];
		addKeyListener(new TAdapter());
		clearBoard();
	}
	
	public static String getRandomLetter() {
		int rndIndex = new Random().nextInt(letters.length);
		return letters[rndIndex];
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
			status.setText("Game paused, current score: " + String.valueOf(score));
		}
		else {
			timer.start();
			status.setText("Score: " + String.valueOf(score));
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

	int blockHeight() {
		return (int) getSize().getHeight() / Height;
	}
	
	int blockWidth() {
		return (int) getSize().getWidth() / Width;
	}
	
	Color blockAt(int x, int y) {
		return grid[x][y];
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		int w = blockWidth();
		int h = blockHeight();
		Dimension size = getSize();
		int gridTop = (int) size.getHeight() - Height * h;
		
		//paint the stationary pieces 
		for (int i = 0; i < Width; ++i) {
			for (int j = 0; j < Height; ++j) {
				Color color = blockAt(i,j);
				if (color != Color.BLACK){
					System.out.println("Drawing at y: " + gridTop+j*h);					
					drawBlock(g, i*w, gridTop+(Height-j-1)*h, color);
				}
				else 
					fillBlack(g, i*w, gridTop+(Height-j-1)*h);
			}
		}
		
		//paint active piece
		if (activeTetro != null) {
			Color color = activeTetro.getColor();
			Point[] points = activeTetro.getPoints();
			for (int i = 0; i < 4; ++i) {
				int X = x + points[i].x;
				int Y = y - points[i].y;
				drawBlock(g, X*w, gridTop+(Height-Y-1)*h, color);
			}
				
		}
	}
	
	private void drawBlock(Graphics g, int x, int y, Color color) { 
		
		int w = blockWidth();
		int h = blockHeight();
		g.setColor(color);
		//y = Height - y;
		g.fillRect(x+1, y+1, w-2, h-2);
		
		//outline the blocks
		g.setColor(color.brighter());
		g.drawLine(x, y+h-1, x, y);
		g.drawLine(x, y, x+w-1, y);
		
		g.setColor(color.darker());
		g.drawLine(x+1, y+h-1, x+w-1, y+h-1);
		g.drawLine(x+w-1, y+h-1, x+w-1, y+1);
	}
	
	private void fillBlack(Graphics g, int x, int y) { 
		int w = blockWidth();
		int h = blockHeight();
		g.setColor(Color.BLACK);
		g.fillRect(x+1, y+1, w-1, h-1);
	}
		
	private void makeStationary() {
		Color color = activeTetro.getColor();
		Point[] points = activeTetro.getPoints();
		for (int i = 0; i < 4; ++i) {
			int X = x + points[i].x;
			int Y = y - points[i].y;
			grid[X][Y] = color;
			System.out.println("Storing x: " + X);
			System.out.println("Storing y: " + Y);	
		}
		
		completeRow();
		
		//if (!isFalling)
		newPiece();
	}
	
	private void down() {
		if (!tryMove(activeTetro, x, y - 1))
			makeStationary();
	}
	
	private void clearBoard() {
		for (int i = 0; i < Width; ++i) {
			for (int j = 0; j < Height; ++j)
				grid[i][j] = Color.BLACK;
		}
	}
	
	private void completeRow() {
		int numRows = 0;
		for (int i = Height - 1; i >= 0; --i) {
			boolean fullRow = true;
			
			for (int j = 0; j < Width; ++j) {
				if (blockAt(j,i) == Color.BLACK) {
					fullRow = false;
					break;
				}
			}
			
			if (fullRow) {
				++numRows;
				//replace row with blocks above
				for (int k = i; k < Height - 1; ++k) {
					for (int j = 0; j < Width; ++j)
						grid[j][k] = blockAt(j, k+1);
				}
			}
		}
		
		if (numRows > 0) {
			score += numRows^2 * 100; //scale points to number of rows completed at once
			status.setText("Score: " + String.valueOf(score));
			isFalling = false;
			activeTetro = null;
			repaint();
		}
	}
	
	private void newPiece(){
		activeTetro = (Tetromino)PieceFactory.getTetromino(getRandomLetter()); 
		x = Width/2 + 1;
		y = Height - 1 + activeTetro.minY();
		activeTetro.setX(x);
		
		if (!tryMove(activeTetro, x, y)) {
			activeTetro = null;
			timer.stop();
			isStarted = false;
			System.out.println("Final x: " + x);
			System.out.println("Final y: " + y);
			status.setText("Game Over, final score: " + String.valueOf(score));
		}
	}
	
    private boolean tryMove(Tetromino tetro, int newX, int newY)
    {
    	Point[] points = tetro.getPoints();
        for (int i = 0; i < 4; ++i) {
            int X = newX + points[i].x;
            int Y = newY - points[i].y;
            if (X < 0 || X >= Width || Y < 0 || Y >= Height)
                return false;
            if (blockAt(X, Y) != Color.BLACK)
                return false;
        }

        activeTetro = tetro;
        x = newX;
        y = newY;
        activeTetro.setX(x);
        repaint();
        return true;
    }
	
    class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            if (!isStarted || activeTetro == null) {  
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
