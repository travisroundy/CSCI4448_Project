import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyStrokes {
		
	private ArrayList<Observer> observers = new ArrayList<Observer>();

	public KeyStrokes(Grid grid) {
		grid.addKeyListener(new KeyListener());
		System.out.println("Adding key listener");
	}

	public void attach(Observer o) {
		System.out.println("Adding new observer");
		observers.add(o);
	}
	
	public void detach(Observer o) {
		System.out.println("Removing an observer");
		observers.remove(o);
	}
	
	protected void notifyObserver(KeyEvent key) {
		this.observers.forEach(observer -> observer.onKeyStroke(key));
	}	

	
	class KeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent key) {
			notifyObserver(key);
		}
	}
}
