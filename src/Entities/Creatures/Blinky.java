package Entities.Creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import Graphics.Animation;
import Graphics.Assets;
import Main.Handler;
import Tile.Tiles;
import Utilities.Utils;

//Duch Blinky z okreœlonym prostok¹tem kolizji oraz przypisan¹ animacj¹
public class Blinky extends Creature{
	
	//Animation
	private Animation AnimDown,AnimUp,AnimLeft,AnimRight;
	private Animation LastAnim;

	public Blinky(Handler handler, float x, float y) {
		super(handler, x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT);
		bounds.x = 2;
		bounds.y = 2;
		bounds.width = Creature.DEFAULT_CREATURE_WIDTH -4;
		bounds.height = Creature.DEFAULT_CREATURE_HEIGHT-4;
		//Animation
		AnimDown = new Animation(250,Assets.BlinkyDown);
		AnimUp = new Animation(250,Assets.BlinkyUp);
		AnimLeft = new Animation(250,Assets.BlinkyLeft);
		AnimRight = new Animation(250,Assets.BlinkyRight);
		LastAnim = new Animation(250,Assets.BlinkyRight);
	}
	
	@Override  // Zmieniona kolizja, duchy przechodz¹ przez drzwi
	protected boolean CollisionWithTile(int x, int y) {
		if(handler.getWorld().getTile(x, y) != Tiles.bluedoorTile && 
		   handler.getWorld().getTile(x, y) != Tiles.reddoorTile &&
		   handler.getWorld().getTile(x, y) != Tiles.grassDoorTile)
			return handler.getWorld().getTile(x, y).IsSolid();
		return false;
	}

	
	public void update() {
		//Animation
		AnimDown.update();
		AnimUp.update();
		AnimLeft.update();
		AnimRight.update();
		LastAnim.update();
		//Movement
		GetChaseMoves();
		Move();
	}

	@Override
	public void render(Graphics G) {
		G.drawImage(GetCurrentAnimationFrame(),(int) (x - handler.getGameCamera().getxOffset()),
				(int) (y-handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	
	// Wyliczenie ruchu goni¹cego Blinky'iego
	private void GetChaseMoves() {
		// Zapamiêtanie starego kierunku i wyzerowanie ruchu
		float OldXMove = xMove;
		float OldYMove = yMove;
		xMove=0;
		yMove=0;

		// wykrycie kolizji na drodze w prawo,lewo,dó³,górê
		boolean path1,path2,path3,path4; 
		path1 = CollisionWithTile((int) (x+speed + bounds.x + bounds.width)/Tiles.TILEWIDTH, (int) (y+bounds.y)/Tiles.TILEHEIGHT) || 
				CollisionWithTile((int) (x+speed + bounds.x + bounds.width)/Tiles.TILEWIDTH, (int) (y+bounds.y + bounds.height)/Tiles.TILEHEIGHT);
		path2 = CollisionWithTile((int) (x-speed + bounds.x)/Tiles.TILEWIDTH,(int) (y+bounds.y)/Tiles.TILEHEIGHT) || 
				CollisionWithTile((int) (x-speed + bounds.x)/Tiles.TILEWIDTH,(int) (y+bounds.y+bounds.height)/Tiles.TILEHEIGHT); 	
		path3 = CollisionWithTile((int) (x+ bounds.x + bounds.width)/Tiles.TILEWIDTH, (int) (y+bounds.y + bounds.height + speed)/Tiles.TILEHEIGHT) || 
				CollisionWithTile((int) (x+ bounds.x )/Tiles.TILEWIDTH,(int) (y+bounds.y + bounds.height + speed)/Tiles.TILEHEIGHT);
		path4 = CollisionWithTile((int) (x+ bounds.x + bounds.width)/Tiles.TILEWIDTH, (int) (y+bounds.y - speed)/Tiles.TILEHEIGHT) || 
				CollisionWithTile((int) (x+ bounds.x )/Tiles.TILEWIDTH,(int) (y+bounds.y - speed)/Tiles.TILEHEIGHT);
		
		// zmienne pomocnicze do Pozycji Playera
		float playerX = handler.getPlayer().getX()+handler.getPlayer().getWidth()/2;
		float playerY = handler.getPlayer().getY()+handler.getPlayer().getHeight()/2;
		
		// Tablice wszystkich dróg
		float[] AllPaths = new float[4];
		float[] AllPaths2 = new float[4];
		AllPaths2[0] = AllPaths[0] =  Utils.GetPath(playerX,playerY,(x+width/2+speed),(y+height/2));
		AllPaths2[1] =AllPaths[1] = Utils.GetPath(playerX,playerY,(x+width/2-speed),(y+height/2));
		AllPaths2[2] =AllPaths[2] = Utils.GetPath(playerX,playerY,(x+width/2),(y+speed+height/2));
		AllPaths2[3] =AllPaths[3] = Utils.GetPath(playerX,playerY,(x+width/2),(y-speed+height/2));

		Arrays.sort(AllPaths2);  		// Sortowanie dróg od najkrótszej

		// Sprawdzenie któr¹ drog¹ jest t¹ najkrótsza z ograniczeniami kolizji i nie zawracania
		int i=0;
		while(xMove==0 && yMove==0 && i<4) {
			if(AllPaths2[i]==AllPaths[0] && OldXMove!=-speed && (!path1 || x <  (int)((x+xMove + bounds.x + bounds.width)/Tiles.TILEWIDTH)* Tiles.TILEWIDTH - bounds.x - bounds.width))
				xMove = speed;
			if(AllPaths2[i]==AllPaths[1] && OldXMove!=speed && (!path2 || x >  (int)((x+xMove + bounds.x + bounds.width)/Tiles.TILEWIDTH)* Tiles.TILEWIDTH + Tiles.TILEWIDTH - bounds.x))
				xMove = -speed;
			if(AllPaths2[i]==AllPaths[2] && OldYMove!=-speed && (!path3 || y <  (int)((y+yMove + bounds.y + bounds.height)/Tiles.TILEHEIGHT)* Tiles.TILEHEIGHT - bounds.y - bounds.height))
				yMove = speed;
			if(AllPaths2[i]==AllPaths[3] && OldYMove!=speed && (!path4 || y >  (int)((y+yMove + bounds.y + bounds.height)/Tiles.TILEHEIGHT)* Tiles.TILEHEIGHT +Tiles.TILEHEIGHT - bounds.y))
				yMove = -speed;
			i++;
		}

	}
	
	private BufferedImage GetCurrentAnimationFrame() {
		if(xMove<0) {
			LastAnim=AnimLeft;
			return AnimLeft.GetCurrentFrame();
			}
		if(xMove>0) {
			LastAnim=AnimRight;
			return AnimRight.GetCurrentFrame();
			}
		if(yMove<0) {
			LastAnim=AnimUp;
			return AnimUp.GetCurrentFrame();
			}
		if(yMove>0) {
			LastAnim=AnimDown;
			return AnimDown.GetCurrentFrame();
			}
		return LastAnim.GetCurrentFrame();
	}
	@Override
	public boolean IsGhost() {
		return true;
	}

}
