
public class PieceClient {

	public boolean isStationary;
	public boolean isActive;
	public int currentX;
	public int currentY;
	public int currentTheta;
	
	private static final char letters[] = {"I", "J", "L", "O", "S", "T", "Z"};
	
	//while game on
		//Tetronomis tetronomis = (Tetronomis)PieceFactory.getPiece(getRandomLetter());
		//tetronomis.setTheta(getRandomTheta());
	
	
	//private static int getRandomTheta()
	
	public boolean checkBorder(Piece piece) {
		return true;
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
}
