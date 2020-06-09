package Graphics;

import Entities.Entity;
import Main.Handler;
import Tile.Tiles;

// Klasa kontroluj¹ca która czêœæ mapy jest wyœwietlana
public class GameCamera {

	private Handler handler;
	private float xOffset,yOffset;
	
	public GameCamera(Handler handler,float xOffset,float yOffset) {
		this.handler = handler;
		this.xOffset=xOffset;
		this.yOffset=yOffset;
	}
	
	public void CheckBlankSpace() {
		// Czy kamera wychodzi na lewo
		if(xOffset<0)	
			xOffset=0;
		// do góry
		if(yOffset<0)  
			yOffset=0;
		// na prawo
		if(xOffset + handler.getWidth()  > handler.getWorld().getWidth()*Tiles.TILEWIDTH)
			xOffset = handler.getWorld().getWidth()*Tiles.TILEWIDTH - handler.getWidth();
		// w dó³
		if(yOffset + handler.getHeight()  > handler.getWorld().getHeight()*Tiles.TILEHEIGHT)
			yOffset = handler.getWorld().getHeight()*Tiles.TILEHEIGHT - handler.getHeight();
	}
	
	// Centuje kamerê na jednostce, zwykle Pac Manie
	public void CenterOnEntity(Entity E) {
		xOffset=E.getX() - handler.getWidth()/2 + E.getWidth()/2;
		yOffset=E.getY() - handler.getHeight()/2 + E.getHeight()/2;
		CheckBlankSpace();
	}
	
	public void move(float x,float y) {
		this.xOffset+=x;
		this.yOffset+=y;
		CheckBlankSpace();
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
	
	
	
	
}
