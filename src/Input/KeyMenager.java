package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Klasa zarz¹dzaj¹ca i wychwytuj¹ca klaiwsze klawiatury
public class KeyMenager implements KeyListener {

	private boolean[] keys;
	public boolean up,down,left,right;
	
	public KeyMenager() {
		keys=new boolean[256];
	}
	
	public void update() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}
	
	public boolean IsKeyPressed(int k) {
		return keys[k];
	}

}
