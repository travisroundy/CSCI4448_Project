
public class PieceClient {

	public boolean isStationary;
	public boolean isActive;
	private int currentX;
	private int currentY;
	private int currentTheta;
	
	
	public int getCurrentX() {
		return currentX;
	}

	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}
	
	public int getCurrentY() {
		return currentY;
	}

	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}
	
	public boolean checkBorder(Piece piece) {
		return true;
	}
	
	public void dropPiece() {
		int newY = 2; //CHANGE, CHECK FOR STATIONARY PIECE BORDER
		this.setCurrentY(newY);
	}
	
	public void movePiece(int x) {
		this.setCurrentX(this.getCurrentX() + x);
	}
	
	public void rotatePiece(int theta) {
		this.currentTheta = (currentTheta + theta) % 360;
	}
	
	public boolean pausePiece(Piece piece) {
		return true;
	}
	
	public boolean makeStationary(Piece piece) {
		return true;
	}
}
