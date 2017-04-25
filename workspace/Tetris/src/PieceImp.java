
public class PieceImp implements Piece {
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
		
	}
	public void detach(Observer o) {
		
	}
	public void notifyObserver() {
		
	}
}
