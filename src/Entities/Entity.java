package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import Main.Handler;

// klasa jednostek, zawiera najwa¿niejsze uogólnione dane o ka¿dej jednostce.
public abstract class Entity {

	protected Handler handler;
	protected float x,y;
	protected int width,height;
	protected Rectangle bounds;   // prostok¹t kolizji
	private boolean IsEntityActive = true;   // Sprawdzenie czy jednostka jest "aktywna"
	
	public Entity(Handler handler, float x,float y,int width,int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle(0,0,width,height);
	}
	
	public abstract void update();
	public abstract void render(Graphics G);
	
	// Wyliczenie  prostok¹tu kolizji
	public Rectangle getCollisionBounds(float xOffset,float yOffset) {
		return new Rectangle( (int) (x + xOffset + bounds.x), (int) (y + yOffset + bounds.y),
				bounds.width,bounds.height );
	}
	
	public boolean IsActive() {
		return IsEntityActive;
	}
	
	public void ChangeActivity() {
		IsEntityActive=!IsEntityActive;
	}
	
	// Sprawdzenie czy jednostka jest duchem
	public boolean IsGhost() {
		return false;
	}
	
	// Sprawdzenie czy jednostka jest jedzeniem
	public boolean IsFood() {
		return false;
	}
	
	// getters and setters x,y,width,height
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


}
