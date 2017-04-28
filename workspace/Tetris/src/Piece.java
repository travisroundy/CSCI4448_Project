
public interface Piece {
	
	public boolean checkBorder(Piece piece);
	public void movePiece(int x, int y);
	public Tetromino rotatePiece();
	public boolean pausePiece(Piece piece);
	public boolean makeStationary(Piece piece);
	
	public void attach(Observer o);
	public void detach(Observer o);
	public void notifyObserver();
}
