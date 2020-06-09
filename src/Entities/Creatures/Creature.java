package Entities.Creatures;

import Entities.Entity;
import Main.Handler;
import Tile.Tiles;

//Klasa kreatur, chodz¹cych/¿ywych jednostek
public abstract class Creature extends Entity {
	
	public static final int DEFAULT_HEALTH = 3;
	public static final float DEFAULT_SPEED = 4.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64, 
							DEFAULT_CREATURE_HEIGHT = 64;
	protected int health;
	protected float speed;
	protected float xMove,yMove;
	
	public Creature(Handler handler, float x, float y,int width,int height) {
		super(handler, x, y,width,height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove=0;
		yMove=0;
	}
	
	// Funkcja ruchu
	public void Move() {
			MoveX();		
			MoveY();
	}
	
	// Ruch poziomy, z wykryciem kolizji oraz przejœciem z boku mapy
	public void MoveX() {
		if(xMove>0) {   // Moving right
			int tx = (int) (x+xMove + bounds.x + bounds.width)/Tiles.TILEWIDTH;		
			if(!CollisionWithTile(tx, (int) (y+bounds.y)/Tiles.TILEHEIGHT) &&
				!CollisionWithTile(tx, (int) (y+bounds.y+bounds.height)/Tiles.TILEHEIGHT)){
				x+=xMove;
				
				// umo¿liwia przejœcie na œrodku w prawo
				if(x>= (handler.getWorld().getWidth()*Tiles.TILEWIDTH) )  
					x=(-width); 
				
			}else {
				x= tx * Tiles.TILEWIDTH - bounds.x - bounds.width -1;
			}
			
		} else if(xMove<0) {   // Moving left
			int tx = (int) (x+xMove + bounds.x)/Tiles.TILEWIDTH;			
			if(!CollisionWithTile(tx, (int) (y+bounds.y)/Tiles.TILEHEIGHT) &&
					!CollisionWithTile(tx, (int) (y+bounds.y+bounds.height)/Tiles.TILEHEIGHT)){
					x+=xMove;
					
					 // umo¿liwia przejœcie na œrodku w lewo
					if(x<= (-width))    
						x=(handler.getWorld().getWidth()*Tiles.TILEWIDTH);
					
			}else {
				x= tx * Tiles.TILEWIDTH + Tiles.TILEWIDTH - bounds.x;
			}	
		}	
	}
	
	// Ruch pionowy, z wykryciem kolizji
	public void MoveY() {
		if(yMove>0) {   // Moving down
			int ty = (int) (y+yMove + bounds.y + bounds.height)/Tiles.TILEHEIGHT;		
			if(!CollisionWithTile((int) (x+bounds.x)/Tiles.TILEWIDTH,ty) &&
				!CollisionWithTile((int) (x+bounds.x+bounds.width)/Tiles.TILEWIDTH,ty)){
				 // uniemo¿liwia chodzenie na dó³ poza map¹
				if(x>0 && x<(handler.getWorld().getWidth()*Tiles.TILEWIDTH)) 
					y+=yMove;  // W³aœciwy ruch
			}else{
				y = ty * Tiles.TILEHEIGHT - bounds.y - bounds.height -1;			
			}
			
		} else if(yMove<0) {   // Moving up
			int ty = (int) (y+yMove + bounds.y)/Tiles.TILEHEIGHT;			
			if(!CollisionWithTile((int) (x+bounds.x)/Tiles.TILEWIDTH,ty) &&
					!CollisionWithTile((int) (x+bounds.x+bounds.width)/Tiles.TILEWIDTH,ty)){
				 // uniemo¿liwia chodzenie do góry poza map¹
				if(x>0 && x<(handler.getWorld().getWidth()*Tiles.TILEWIDTH)) 
					y+=yMove; // W³aœciwy ruch
			}else {
				y= ty * Tiles.TILEHEIGHT +Tiles.TILEHEIGHT - bounds.y;
			}
		}	
	}
	
	// Wykrycie kolizji z "p³ytkami" grafiki
	protected boolean CollisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).IsSolid();
	}
	
	// Getters and Setters
	
	public float getxMove() {
		return xMove;
	}


	public void setxMove(float xMove) {
		this.xMove = xMove;
	}


	public float getyMove() {
		return yMove;
	}


	public void setyMove(float yMove) {
		this.yMove = yMove;
	}



	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
