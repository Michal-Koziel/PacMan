package Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import UI.UIManager;

// Klasa zarz¹dzaj¹ca i wychwytuj¹ca przyciski oraz ruch/pozycjê myszki
public class MouseManager implements MouseListener, MouseMotionListener {

	private boolean leftPressed,rightPressed;
	private int MouseX, MouseY;
	private UIManager uiManager;
	
	public MouseManager() {
		
	}
	
	public void setUImanager(UIManager uiManager) {
		this.uiManager = uiManager;
	}
	
	// Getters
	
	public boolean isLeftPressed() {
		return leftPressed;
	}
	
	public boolean isRightPressed() {
		return rightPressed;
	}
	
	public int getMouseX() {
		return MouseX;
	}
	
	public int getMouseY() {
		return MouseY;
	}
	
	// Methods
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		if(e.getButton() == MouseEvent.BUTTON2)
			rightPressed = true;
	}
	

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		if(e.getButton() == MouseEvent.BUTTON2)
			rightPressed = false;	
		
		if(uiManager!=null)
			uiManager.OnMouseRelease(e);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
	MouseX = e.getX();
	MouseY = e.getY();
	
	if(uiManager!=null)
		uiManager.OnMouseMove(e);
	}
	
	// nieu¿ywane w projekcie metody
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	


}
