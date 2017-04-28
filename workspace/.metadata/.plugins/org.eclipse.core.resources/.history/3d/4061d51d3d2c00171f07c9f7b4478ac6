import java.util.ArrayList;
import java.awt.Point;
import java.util.Random;
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
	private String color;
	private String letter;
	public Point[] m;
	public Point[] points;
	//private int theta;
	
	//have 1 concrete class for Tetrominoes
	public Tetromino(String letter) {
		this.letter = letter;

		if(letter == "I"){
			this.color = "Cyan";
			m = new Point[]{
				//set shapes based off x,y points
				new Point(0,-1), 
				new Point(0,0),
				new Point(0,1),
				new Point(0,2),
			};
		}
		else if(letter == "J"){
			this.color = "Blue";
			m = new Point[]{
					//set shapes based off x,y points
					new Point(-1,-1), 
					new Point(0,-1),
					new Point(0,0),
					new Point(0,1),
				};		
		}
		else if(letter == "L"){
			this.color = "Orange";			
			m = new Point[]{
					//set shapes based off x,y points
					new Point(1,-1), 
					new Point(0,-1),
					new Point(0,0),
					new Point(0,1),
				};			
		}		
		else if(letter == "O"){
			this.color = "Yellow";	
			m = new Point[]{
					//set shapes based off x,y points
					new Point(0,0), 
					new Point(1,0),
					new Point(1,1),
					new Point(0,1),
				};			
		}		
		else if(letter == "S"){
			this.color = "Green";			
			m = new Point[]{
					//set shapes based off x,y points
					new Point(-1,0), 
					new Point(0,0),
					new Point(0,1),
					new Point(1,1),
				};			
		}
		else if(letter == "Z"){
			this.color = "Red";		
			m = new Point[]{
					//set shapes based off x,y points
					new Point(-1,-1), 
					new Point(0,1),
					new Point(0,0),
					new Point(1,0),
				};			
		}
		else if(letter == "T"){
			this.color = "Purple";			
			m = new Point[]{
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
		
		this.points = m;		
	}
	
	public int minX() {
		int min = (int) this.points[0].getX();
		for (int i=1; i < 4; i++) {
			min = Math.min(min, (int) this.points[i].getX()); 
		}
		
		return min;
	}

	public int minY() {
		int min = (int) this.points[0].getY();
		for (int i=1; i < 4; i++) {
			min = Math.min(min, (int) this.points[i].getY()); 
		}
		
		return min;
	}	
	
	public boolean checkBorder(Piece piece){
		return false;
	}
	public void movePiece(int x, int y) {
		
	}
	
	public Tetromino rotatePiece() {
		for (int i = 0; i < 4; ++i) {
			int x = (int) this.points[i].getX();
			int y = (int) this.points[i].getY();
			this.points[i].move(y, -x);
		}
		
		return this;
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

}
