package Entities.Creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import Graphics.Animation;
import Graphics.Assets;
import Main.Handler;
import Tile.Tiles;
import Utilities.Utils;

//Duch Pinky z okreœlonym prostok¹tem kolizji oraz przypisan¹ animacj¹
public class Pinky extends Creature{
	
	private String state;
	private int counter,returncounter = 0;
	private boolean CounterOn,ReturnCounterOn = false;
	
	//Animation
	private Animation AnimDown,AnimUp,AnimLeft,AnimRight,ChaseDown,ChaseUp,ChaseLeft,ChaseRight,ReturnDown,ReturnUp,ReturnLeft,ReturnRight,EscapeDown,EscapeUp,EscapeLeft,EscapeRight;
	private Animation LastAnim;

	public Pinky(Handler handler, float x, float y) {
		super(handler, x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT);
		bounds.x =2;
		bounds.y =2;
		bounds.width = Creature.DEFAULT_CREATURE_WIDTH -4 ;
		bounds.height = Creature.DEFAULT_CREATURE_HEIGHT -4;
		state = "Chase";
		//Animation
		ChaseDown = new Animation(250,Assets.PinkyDown);
		ChaseUp = new Animation(250,Assets.PinkyUp);
		ChaseLeft = new Animation(250,Assets.PinkyLeft);
		ChaseRight = new Animation(250,Assets.PinkyRight);
		ReturnDown = new Animation(250,Assets.ReturnDown);
		ReturnUp = new Animation(250,Assets.ReturnUp);
		ReturnLeft = new Animation(250,Assets.ReturnLeft);
		ReturnRight = new Animation(250,Assets.ReturnRight);
		EscapeDown = new Animation(250,Assets.EscapeDown);
		EscapeUp = new Animation(250,Assets.EscapeUp);
		EscapeLeft = new Animation(250,Assets.EscapeLeft);
		EscapeRight = new Animation(250,Assets.EscapeRight);
		AnimDown = ChaseDown;
		AnimUp = ChaseUp;
		AnimLeft = ChaseRight;
		AnimRight = ChaseLeft;
		LastAnim = ChaseRight;
	}
	
	@Override // Zmieniona kolizja, duchy przechodz¹ przez drzwi
	protected boolean CollisionWithTile(int x, int y) {
		if(handler.getWorld().getTile(x, y) != Tiles.bluedoorTile && 
		   handler.getWorld().getTile(x, y) != Tiles.reddoorTile &&
		   handler.getWorld().getTile(x, y) != Tiles.grassDoorTile)
			return handler.getWorld().getTile(x, y).IsSolid();
		return false;
	}

	@Override
	public void update() {
		//Animation
		AnimDown.update();
		AnimUp.update();
		AnimLeft.update();
		AnimRight.update();
		LastAnim.update();
		//Movement
		if(state=="Chase")
			GetChaseMoves();
		if(state=="Escape")
			GetEscapeMoves();
		if(state=="Return")
			GetReturnMoves();
		
		if( counter == 0 || state == "Escape" && counter%2 == 0)
			Move();
		
		// zmien na ucieczke po zjedzeniu power upa 
		if(Player.PowerUpOn == true && state != "Return") {
			counter =0 ;
			SwitchState("Escape");
			CounterOn=true;
		}
		
		// Jeœli duch zostaje zjedzony podczas ucieczki wraca do domu i przyznaje punkty pac manowi
			if(state == "Escape" && handler.getPlayer().getCollisionBounds(0f, 0f).intersects(getCollisionBounds(0f, 0f))) {
				handler.getWorld().addMaxPoints(100);
				handler.getWorld().addPoints(100);
				counter = 0; 
				CounterOn = false;
				SwitchState("Return");
				ReturnCounterOn = true;
			}
		
		// Jesli wróci³ do domu w³¹cz counter
		if(state =="Return" && x<handler.getWorld().getSpawnX()+10 && x> handler.getWorld().getSpawnX()-10 && y> handler.getWorld().getSpawnY()-2*Tiles.TILEHEIGHT -10 && y< handler.getWorld().getSpawnY()-2*Tiles.TILEHEIGHT +10) { 
			CounterOn = true;
			ReturnCounterOn = false;
			returncounter=0;
		}
		
		// zacznij gonic po ucieczce lub po zaczekaniu chwili w domu oraz wyzeruj licznik
		if(counter == 300 || state == "Return" && counter == 120) {
			SwitchState("Chase");
			counter = 0; 
			CounterOn = false;
		}
		
		// Jeœli duch ma problem z powrotem do domu przeteleportowuje go
		if(state == "Return" && returncounter == 420) {
			setX(handler.getWorld().getSpawnX());
			setY(handler.getWorld().getSpawnY()-2*Tiles.TILEHEIGHT);		
		}
		
		// Jeœli counter jest wlaczony mierz czas
		if(CounterOn)
			counter++;
		
		if(ReturnCounterOn)
			returncounter++;

	}

	@Override
	public void render(Graphics G) {
		G.drawImage(GetCurrentAnimationFrame(),(int) (x - handler.getGameCamera().getxOffset()),
				(int) (y-handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	private void GetEscapeMoves() {
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
				
		// Wybranie losowo jednej z dróg z ograniczeniami kolizji i nie zawracania
		int j =0;
		int i = 1;
		while(xMove==0 && yMove==0 && j<=50) {
			j++;
			i= Utils.RandomNumber.nextInt(4)+1; 
			if(i == 1 && OldXMove!=-speed && (!path1 || x <  (int)((x+xMove + bounds.x + bounds.width)/Tiles.TILEWIDTH)* Tiles.TILEWIDTH - bounds.x - bounds.width))
				xMove = speed;
			if(i == 2 && OldXMove!=speed && (!path2 || x >  (int)((x+xMove + bounds.x + bounds.width)/Tiles.TILEWIDTH)* Tiles.TILEWIDTH + Tiles.TILEWIDTH - bounds.x))
				xMove = -speed;
			if(i == 3 && OldYMove!=-speed && (!path3 || y <  (int)((y+yMove + bounds.y + bounds.height)/Tiles.TILEHEIGHT)* Tiles.TILEHEIGHT - bounds.y - bounds.height))
				yMove = speed;
			if(i == 4 && OldYMove!=speed && (!path4 || y >  (int)((y+yMove + bounds.y + bounds.height)/Tiles.TILEHEIGHT)* Tiles.TILEHEIGHT +Tiles.TILEHEIGHT - bounds.y))
				yMove = -speed;
		}			
	}
	
	private void GetReturnMoves() {	
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
				
				// zmienne pomocnicze do pozycji domu
				float HomeX = handler.getWorld().getSpawnX();
				float HomeY = handler.getWorld().getSpawnY()-2*Tiles.TILEHEIGHT;
				
				// Tablice wszystkich dróg
				float[] AllPaths = new float[4];
				float[] AllPaths2 = new float[4];
				AllPaths2[0] = AllPaths[0] =  Utils.GetPath(HomeX,HomeY,(x+width/2+speed),(y+height/2));
				AllPaths2[1] =AllPaths[1] = Utils.GetPath(HomeX,HomeY,(x+width/2-speed),(y+height/2));
				AllPaths2[2] =AllPaths[2] = Utils.GetPath(HomeX,HomeY,(x+width/2),(y+speed+height/2));
				AllPaths2[3] =AllPaths[3] = Utils.GetPath(HomeX,HomeY,(x+width/2),(y-speed+height/2));

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
	
	// Wyliczenie ruchu goni¹cego Pinkie'ego
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
		
		// zmienne pomocnicze do znalezienia Pozycji o 3 tilesy przed Playerem
		float InFrontOfPlayerX = handler.getPlayer().getX()+handler.getPlayer().getWidth()/2;
		if(handler.getPlayer().GetCurrentAnimation()==handler.getPlayer().getAnimLeft())
			InFrontOfPlayerX -= 3*Tiles.TILEWIDTH; 
		if(handler.getPlayer().GetCurrentAnimation()==handler.getPlayer().getAnimRight())
			InFrontOfPlayerX += 3*Tiles.TILEWIDTH; 
		float InFrontOfPlayerY = handler.getPlayer().getY()+handler.getPlayer().getHeight()/2;
		if(handler.getPlayer().GetCurrentAnimation()==handler.getPlayer().getAnimUp())
			InFrontOfPlayerY -= 3*Tiles.TILEHEIGHT; 
		if(handler.getPlayer().GetCurrentAnimation()==handler.getPlayer().getAnimDown())
			InFrontOfPlayerY += 3*Tiles.TILEHEIGHT; 
		
		// Tablice wszystkich dróg
		float[] AllPaths = new float[4];
		float[] AllPaths2 = new float[4];
		AllPaths2[0] = AllPaths[0] =  Utils.GetPath(InFrontOfPlayerX,InFrontOfPlayerY,(x+width/2+speed),(y+height/2));
		AllPaths2[1] =AllPaths[1] = Utils.GetPath(InFrontOfPlayerX,InFrontOfPlayerY,(x+width/2-speed),(y+height/2));
		AllPaths2[2] =AllPaths[2] = Utils.GetPath(InFrontOfPlayerX,InFrontOfPlayerY,(x+width/2),(y+speed+height/2));
		AllPaths2[3] =AllPaths[3] = Utils.GetPath(InFrontOfPlayerX,InFrontOfPlayerY,(x+width/2),(y-speed+height/2));

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
	
	@Override
	public boolean 	IsAgressive() {
		if(state=="Chase")
			return true;
		return false;
	}
	
	public void SwitchState(String state) {
		this.state = state;
		if(state=="Chase") {
			AnimDown = ChaseDown;
			AnimUp = ChaseUp;
			AnimLeft = ChaseRight;
			AnimRight = ChaseLeft;
			LastAnim = ChaseRight;
		}
		if(state=="Return") {
			AnimDown = ReturnDown;
			AnimUp = ReturnUp;
			AnimLeft = ReturnRight;
			AnimRight = ReturnLeft;
			LastAnim = ReturnRight;
		}
		if(state=="Escape") {
			AnimDown = EscapeDown;
			AnimUp = EscapeUp;
			AnimLeft = EscapeRight;
			AnimRight = EscapeLeft;
			LastAnim = EscapeRight;
		}		
	}

}
