import java.util.HashMap;

public class PieceFactory {
	private static final HashMap<String, Piece> tetrominoMap = new HashMap();
		
	public static Piece getTetromino(String letter) {
		//attempt to get piece from map
		Tetromino tetromino = (Tetromino)tetrominoMap.get(letter);
		
		//if the piece does not already exist then create one
		if(tetromino == null){
			tetromino = new Tetromino(letter);
			tetrominoMap.put(letter, tetromino);
			System.out.println("Creating new tetromino : " + letter);
		}
		return tetromino;
	}
}
