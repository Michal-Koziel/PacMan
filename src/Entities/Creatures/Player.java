package Entities.Creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Entities.Entity;
import Graphics.Animation;
import Graphics.Assets;
import Main.Handler;
import States.State;
import Tile.Tiles;
import World.World;

// Klasa Gracza
public class Player extends Creature {
	
	//Animation
	private Animation AnimDown,AnimUp,AnimLeft,AnimRight;
	private Animation LastAnim;
	
	public Player(Handler handler,float x, float y) {
		super(handler, x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT);
		//speed=5;
		bounds.x =2;
		bounds.y =2;
		bounds.width = Creature.DEFAULT_CREATURE_WIDTH -4 ;
		bounds.height = Creature.DEFAULT_CREATURE_HEIGHT -4;
		//Animation
		AnimDown = new Animation(250,Assets.PlayerDown);
		AnimUp = new Animation(250,Assets.PlayerUp);
		AnimLeft = new Animation(250,Assets.PlayerLeft);
		AnimRight = new Animation(250,Assets.PlayerRight);
		LastAnim = new Animation(250,Assets.PlayerRight);
	}

	@Override
	public void update() {
		 // Jeœli Pac Man opuœci miejsce staru gra wystartuje
		if(handler.getGame().gamestate.IsGameStopped) {  
			if(x>=10.5*Tiles.TILEWIDTH || x<=9.5*Tiles.TILEWIDTH || y>=12.1*Tiles.TILEHEIGHT)
			handler.getGame().gamestate.IsGameStopped = false;		
		}
		//Animation
		AnimDown.update();
		AnimUp.update();
		AnimLeft.update();
		AnimRight.update();
		LastAnim.update();
		//Movement
		getInput();
		Move();
		checkEntityCollision(0f,0f);
		handler.getGameCamera().CenterOnEntity(this);
	}
	
	// Pobranie ruchu z klawiatury
	public void getInput() {
	 	xMove=0;
		yMove=0;
		
		if(handler.getkeyMan().up) {
			yMove= -speed;
		}
		if(handler.getkeyMan().down) {
			yMove= speed;
		}
		if(handler.getkeyMan().left) {
			xMove= -speed;	
		}
		if(handler.getkeyMan().right) {
			xMove= speed;
		}
	}

	@Override
	public void render(Graphics G) {	
		G.drawImage(GetCurrentAnimationFrame(),(int) (x - handler.getGameCamera().getxOffset()),
				(int) (y-handler.getGameCamera().getyOffset()), width, height, null);
    }

	// sprawdzenie kolizji z innymi jednostkami
	public void checkEntityCollision(float xOffset,float yOffset) {
		// Tworzy pêtle o d³ugoœci entities, z ka¿dym przejœciem przypisuj¹c wartoœc entities[i] do Entity e
		for(Entity e :handler.getWorld().getEntityManager().getEntities() ) {
			
			// Jeœli spotka ducha, umiera
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)) && e.IsGhost() ) {
				Dying();
			}
			// Jeœli spotka jedzenie, punktuje
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)) && e.IsFood() ) {
			    Scoring(e);
			}	
		}
	}
	
	
	// Jeœli traci wszystkie ¿ycia zmienia stan gry, jeœli nie resetuje siebie i duchy
	private void Dying() {
		health-=1;
		//animacja jakaœ œmierci czy coœ
		handler.getGame().gamestate.IsGameStopped = true;
		if(health==0) {
			handler.getWorld().SaveScore("res/scores/scoretable.txt");
			State.setState(handler.getGame().deadstate);
		}
		else
			handler.getWorld().ResetCreatures();
	}
	
	// Dodaje punkty i usuwa zjedzone jedzonko, jeœli osi¹gnie max, przechodzi do nast. poziomu
	private void Scoring(Entity e) {
		if(e.IsActive())
			handler.getWorld().addPoints(World.PointsPerFood);
		e.ChangeActivity(); // usuniecie jedzona
		
		if(handler.getWorld().getPoints()>=	handler.getWorld().getMaxPoints()) {
			handler.getGame().gamestate.IsGameStopped = true;
			State.setState(handler.getGame().levelpassingstate);
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
	
	public Animation GetCurrentAnimation() {
		if(xMove<0) {
			return AnimLeft;
			}
		if(xMove>0) {
			return AnimRight;
			}
		if(yMove<0) {
			return AnimUp;
			}
		if(yMove>0) {
			return AnimDown;
			}
		return LastAnim;		
	}
	
	public Animation getAnimDown() {
		return AnimDown;
	}

	public Animation getAnimUp() {
		return AnimUp;
	}

	public Animation getAnimLeft() {
		return AnimLeft;
	}

	public Animation getAnimRight() {
		return AnimRight;
	}

}
