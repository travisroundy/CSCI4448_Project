
public class Tetromino implements Piece {
	public boolean completeRow;
	public Object pieceGraphics;
	
	//ideas from Flyweight tutorials 
	private String color;
	private char letter;
	//private int theta;
	
	//have 1 concrete class for Tetrominos
	public Tetromino(char letter) {
		this.letter = letter;

		if(letter == 'I'){
			this.color = "Cyan";
			int [][] m = new int[][]{
				{0,0,0,0},
				{1,1,1,1}
			};
		}
		else if(letter == 'J'){
			this.color = "Blue";
			int [][] m = new int[][]{
				{1,0,0,0},
				{1,1,1,0}
			};			
		}
		else if(letter == 'L'){
			this.color = "Orange";			
			int [][] m = new int[][]{
				{0,0,1,0},
				{1,1,1,0}
			};
		}		
		else if(letter == 'O'){
			this.color = "Yellow";			
			int [][] m = new int[][]{
				{1,1,0,0},
				{1,1,0,0}
			};
		}		
		else if(letter == 'S'){
			this.color = "Green";			
			int [][] m = new int[][]{
				{0,0,1,1},
				{1,1,0,0}
			};
		}
		else if(letter == 'Z'){
			this.color = "Red";			
			int [][] m = new int[][]{
				{1,1,0,0},
				{0,1,1,0}
			};
		}
		else if(letter == 'T'){
			this.color = "Purple";			
			int [][] m = new int[][]{
				{0,1,0,0},
				{1,1,1,0}
			};
		}	
		else {
			System.out.println("Invalid letter choice for new Tetronimo");
		}
	}
	
	public boolean checkBorder(Piece piece){
		return false;
	}
	public void movePiece(int x, int y) {
		
	}
	public void rotatePiece(int theta) {
		
	}
	public boolean pausePiece(Piece piece) {
		return true;
	}
	public boolean makeStationary(Piece piece) {
		return true;
	}
	
	public void attach(Observer o) {
		
	}
	public void detach(Observer o) {
		
	}
	public void notifyObserver() {
		
	}
}
