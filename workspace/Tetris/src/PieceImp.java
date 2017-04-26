import java.util.ArrayList;

public class PieceImp implements Piece {
	private ArrayList<Observer> pieces = new ArrayList<Observer>();
	
	public boolean completeRow;
	public Object pieceGraphics;
	
	public PieceImp() {
	    
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
		pieces.add(o);
	}
	public void detach(Observer o) {
		pieces.remove(o);
	}
	public void notifyObserver() {
		// Notify pieces
	}
}
