import java.util.ArrayList;
import java.awt.Point;
import java.util.Random;
import java.awt.Color;
import java.lang.Math;

public class Tetromino implements Piece {
	/*
	 * Specify what type of pieces here
	 * Take care of setting initial values x, y, rotation, etc.
	 * 
	 */
	private ArrayList<Observer> pieces = new ArrayList<Observer>();
	
	public boolean completeRow;
	public Object pieceGraphics;
	
	//ideas from Flyweight tutorials 
	private Color color;
	private String letter;
	public Point[] points;
	public boolean isStationary;
	public boolean isActive;
	private int xOrigin;
	private int currentRotation;
	
	//have 1 concrete class for Tetrominoes
	public Tetromino(String letter) {
		this.letter = letter;

		if(letter == "I"){
			this.color = Color.CYAN;
			this.points = new Point[]{
				//set shapes based off x,y points
				new Point(0,-1), 
				new Point(0,0),
				new Point(0,1),
				new Point(0,2),
			};
		}
		else if(letter == "J"){
			this.color = Color.BLUE;
			this.points = new Point[]{
					//set shapes based off x,y points
					new Point(-1,-1), 
					new Point(0,-1),
					new Point(0,0),
					new Point(0,1),
				};		
		}
		else if(letter == "L"){
			this.color = Color.ORANGE;			
			this.points = new Point[]{
					//set shapes based off x,y points
					new Point(1,-1), 
					new Point(0,-1),
					new Point(0,0),
					new Point(0,1),
				};			
		}		
		else if(letter == "O"){
			this.color = Color.YELLOW;	
			this.points = new Point[]{
					//set shapes based off x,y points
					new Point(0,0), 
					new Point(1,0),
					new Point(1,1),
					new Point(0,1),
				};			
		}		
		else if(letter == "S"){
			this.color = Color.GREEN;			
			this.points = new Point[]{
					//set shapes based off x,y points
					new Point(-1,0), 
					new Point(0,0),
					new Point(0,1),
					new Point(1,1),
				};			
		}
		else if(letter == "Z"){
			this.color = Color.RED;		
			this.points = new Point[]{
					//set shapes based off x,y points
					new Point(-1,1), 
					new Point(0,1),
					new Point(0,0),
					new Point(1,0),
				};			
		}
		else if(letter == "T"){
			this.color = Color.MAGENTA;			
			this.points = new Point[]{
					//set shapes based off x,y points
					new Point(-1,0), 
					new Point(0,0),
					new Point(1,0),
					new Point(0,1),
				};			
		}	
		else {
			System.out.println("Invalid letter choice for new Tetronimo");
		}		
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public Point[] getPoints() {
		return this.points;
	}
	
	public int minX() {
		int min = this.points[0].x;
		for (int i=1; i < 4; i++) {
			min = Math.min(min, this.points[i].x); 
		}
		
		return min;
	}

	public int minY() {
		int min = this.points[0].y;
		for (int i=1; i < 4; i++) {
			min = Math.min(min, this.points[i].y); 
		}
		
		return min;
	}	
	
	public boolean checkBorder(Piece piece){
		return false;
	}
	public void dropPiece() {
		
	}
	
	public void movePiece(int x) {
		
	}
	
	public Tetromino rotatePiece() {
		//square does not need to be rotated
		if (this.letter == "O")
			return this;
		
		//check if attempting to rotate along border
		for (int i = 0; i < 4; ++i) {
			int newX = this.xOrigin + this.points[i].y;
			if (newX < 0 || newX >= 10)
				return this; //dont allow rotation and return current orientation
		}
		
		for (int i = 0; i < 4; ++i) {
			int x = this.points[i].x;
			int y = this.points[i].y;
			this.points[i].move(y, -x);
		}
		
		return this;
	}
	
	public int getX() {
		return this.xOrigin;
	}
	
	public void setX(int X) {
		this.xOrigin = X;
	}
	public boolean pausePiece(Piece piece) {
		return true;
	}
	public boolean makeStationary(Piece piece) {
		return true;
	}
	
	public void attach(Observer o) {
		pieces.add(o);
	}
	public void detach(Observer o) {
		pieces.remove(o);
	}
	public void notifyObserver() {
		// Notify pieces
	}

	@Override
	public void movePiece(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
