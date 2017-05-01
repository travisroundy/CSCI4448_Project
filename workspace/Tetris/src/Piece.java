import java.awt.Point;
import java.awt.Color;

public interface Piece {
	
	public int minX();
	public int minY();
	public Tetromino rotatePiece();
	public Color getColor();
	public Point[] getPoints();
}
