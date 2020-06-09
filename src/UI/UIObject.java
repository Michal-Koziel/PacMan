package UI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

//klasa obiekt�w UserInerface'u. Sprawdza czy obiekt by� klikni�ty, "najechany" i wykonuj� zadan� akcj�.
public abstract class  UIObject {

	protected float x,y;
	protected int width,height;
	protected Rectangle bounds;
	protected boolean hovering = false;
	
	public UIObject(float x,float y, int width,int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;	
		bounds = new Rectangle((int)x,(int)y,width,height);
	}
	
	public abstract void update();
	
	public abstract void render(Graphics G);
	
	public abstract void OnClick();
	
	public void OnMouseMove(MouseEvent e) {  // sprawdza czy myszka jest nad obiektem
		if(bounds.contains(e.getX(),e.getY()))
			hovering = true;
		else 
			hovering = false;
	}
	
	public void OnMouseRelease(MouseEvent e) {
		if(hovering)
			OnClick();
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
	
	
	
}
