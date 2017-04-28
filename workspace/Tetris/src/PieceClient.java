public class PieceClient {
	/*
	 * Call to make new pieces here
	 */
	public boolean isStationary;
	public boolean isActive;
	private int currentX;
	private int currentY;
	private int currentTheta;
	
	private static final String letters[] = {"I", "J", "L", "O", "S", "T", "Z", " "};
	
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
	
	//while game on
		//Tetrominos tetrominos = (Tetrominos)PieceFactory.getPiece(getRandomLetter());
		//tetrominos.setTheta(getRandomTheta());
	
	
	//private static int getRandomTheta()
	
	//private static int getRandomLetter()
	
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

	/*
	public void rotatePiece() {
		//this.currentTheta = (currentTheta + theta) % 360;
		//or
		//rotate 90 degrees (left)
		if (tetromino.letter == "O")
			return this;
		
		Piece newRotation = new Piece();
		newRotation.tetromino = tetromino;
		
		for (int i =0; i < 4; ++i) {
			newRotation.setX(i, y(i));
			newRotation.setY(i, -x(i));
		}
	}
	*/
	
	public boolean pausePiece(Piece piece) {
		return true;
	}
	
	public boolean makeStationary(Piece piece) {
		return true;
	}
}
