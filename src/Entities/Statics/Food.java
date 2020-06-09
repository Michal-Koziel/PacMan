package Entities.Statics;

import java.awt.Graphics;

import Graphics.Assets;
import Main.Handler;
import Tile.Tiles;

// Jedzenie Pac Mana z okreœlonym prostok¹tem kolizji oraz przypisan¹ grafik¹
public class Food extends StaticEntity {

	public Food(Handler handler, float x, float y) {
		super(handler, x, y, Tiles.TILEWIDTH, Tiles.TILEHEIGHT);
		
		bounds.x = (int)((12/32f) * Tiles.TILEWIDTH);
		bounds.y = (int) ((14/32f) * Tiles.TILEHEIGHT);
		bounds.width = (int) ((7/32f) * Tiles.TILEWIDTH) ;
		bounds.height = (int) ((4/32f) * Tiles.TILEHEIGHT) ;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics G) {
		G.drawImage(Assets.Cookie,(int) (x-handler.getGameCamera().getxOffset()),
				(int) (y-handler.getGameCamera().getyOffset()),width,height,null);
	}
	@Override
	public boolean IsFood() {
		return true;
	}

}
