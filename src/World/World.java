package World;

import java.awt.Graphics;

import Entities.Entity;
import Entities.EntityManager;
import Entities.Creatures.Blinky;
import Entities.Creatures.Clyde;
import Entities.Creatures.Inky;
import Entities.Creatures.Pinky;
import Entities.Creatures.Player;
import Entities.Statics.Food;
import Graphics.Assets;
import Main.Handler;
import Tile.Tiles;
import Utilities.Utils;

// Klasa zawieraj¹ca ca³y œwiat
public class World {

	public static final int PointsPerFood = 10;
	private Handler handler;
	private int width,height;
	private int SpawnX,SpawnY;
	private int[][] Worldtiles;
	// Scoring
	private int points=0;
	private int maxPoints=0;
	//Entities
	private EntityManager entityManager;
	

	public World(Handler handler,String path) {
		this.handler = handler;
		this.LoadWorld(path);
		entityManager = new EntityManager(handler,new Player(handler,SpawnX,SpawnY));
		handler.setPlayer(entityManager.getPlayer());
		
		// generacja jedzenia
		for(int y=0;y<height;y++) {
			for(int x=0;x<width;x++) {
				if(Worldtiles[x][y] == 10 || Worldtiles[x][y] == 9 || Worldtiles[x][y] == 0) 
					// Wyklucza wnetrze domu duchow i punkt startowy paca
					if( !( (x >= SpawnX/Tiles.TILEWIDTH -3 && x <= SpawnX/Tiles.TILEWIDTH +3) && (y>=SpawnY/Tiles.TILEHEIGHT-6  && y<=SpawnY/Tiles.TILEHEIGHT)) ) {
						entityManager.addEntity(new Food(handler,x*Tiles.TILEWIDTH,y*Tiles.TILEHEIGHT));
						maxPoints+=1*PointsPerFood;
					}
			}
		}
		
		entityManager.addEntity(new Pinky(handler,SpawnX,SpawnY-3*Tiles.TILEHEIGHT));
		entityManager.addEntity(new Blinky(handler,SpawnX,SpawnY-2*Tiles.TILEHEIGHT));
		entityManager.addEntity(new Clyde(handler,SpawnX,SpawnY-3*Tiles.TILEHEIGHT));
		entityManager.addEntity(new Inky(handler,SpawnX,SpawnY-2*Tiles.TILEHEIGHT,entityManager.getEntities().get(3)));
	}
	
	public void update() {
		// Je¿eli GameIsStopped tylko pac man mo¿e siê ruszaæ, co "odpauzowuje gre"
		if(!handler.getGame().gamestate.IsGameStopped)
			entityManager.update();
		if(handler.getGame().gamestate.IsGameStopped)	
			handler.getPlayer().update();
	}
	
	// Wyœwietlenie œwiata przy poprawce na offset kamery
	public void render(Graphics G) {	
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset()/Tiles.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth())/Tiles.TILEWIDTH+1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset()/Tiles.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight())/Tiles.TILEHEIGHT+1);
		
		for(int y=yStart;y<yEnd;y++) {
			for(int x=xStart;x<xEnd;x++) {
			this.getTile(x, y).render(G,(int) (x * Tiles.TILEWIDTH - handler.getGameCamera().getxOffset() )
					,(int) (y * Tiles.TILEHEIGHT - handler.getGameCamera().getyOffset() ));
			}
		}
		
		entityManager.render(G);
		
		// narysowanie zyc pac mana
		for(int i=0; i<handler.getPlayer().getHealth();i++) {
			G.drawImage(Assets.PlayerRight[1],50 + i*100,730,64,64, null);
		}
	}
	
	public Tiles getTile(int x,int y) {
		if(x<0 || y<0 || x>=width || y>= height)
			return Tiles.grassTile;
			
		Tiles t = Tiles.tiles[Worldtiles[x][y]];
		if (t==null) {
			return Tiles.bluebrickTile;
		}
		return t;
		}
	
	private void LoadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		SpawnX = Utils.parseInt(tokens[2])*Tiles.TILEWIDTH;
		SpawnY = Utils.parseInt(tokens[3])*Tiles.TILEHEIGHT;
		Worldtiles= new int[width][height];	
		
		for(int y=0;y<height;y++) {
			for(int x=0;x<width;x++) {
				Worldtiles[x][y]= Utils.parseInt(tokens[(x+width*y)+4]);
			}
		}
	}
	
	// Resetuje œwiat po œmierci Pac Mana
	public void ResetWorld() {
		//reset punktów
		points=0;
		maxPoints=0;
		// reset duchow i gracza
		handler.getPlayer().setX(SpawnX);
		handler.getPlayer().setY(SpawnY);
		handler.getPlayer().setHealth(3);
		for(Entity e :handler.getWorld().getEntityManager().getEntities() ) {
			if(e.IsGhost()) {
				e.setX(SpawnX);
				e.setY(SpawnY-2*Tiles.TILEHEIGHT);
			}
			//reset jedzenia
			if(e.IsFood()) {
				e.ChangeActivity();
			}
		}	
		
		// nowe jedzenie
		for(int y=0;y<height;y++) {
			for(int x=0;x<width;x++) {
				if(Worldtiles[x][y] == 10 || Worldtiles[x][y] == 9 || Worldtiles[x][y] == 0) 
					// Wyklucza wnetrze domu duchow i punkt startowy pac Mana
					if( !( (x >= SpawnX/Tiles.TILEWIDTH -3 && x <= SpawnX/Tiles.TILEWIDTH +3) && (y>=SpawnY/Tiles.TILEHEIGHT-6  && y<=SpawnY/Tiles.TILEHEIGHT)) ) {
						entityManager.addEntity(new Food(handler,x*Tiles.TILEWIDTH,y*Tiles.TILEHEIGHT));
						maxPoints+=1*PointsPerFood;
					}
			}
		}
	}
	
	// Przechodzi do nastêpnego poziomu
	public void NextLevel() {
		// reset duchow i gracza
		handler.getPlayer().setX(SpawnX);
		handler.getPlayer().setY(SpawnY);
		for(Entity e :handler.getWorld().getEntityManager().getEntities() ) {
			if(e.IsGhost()) {
				e.setX(SpawnX);
				e.setY(SpawnY-2*Tiles.TILEHEIGHT);
			}
			//reset jedzenia
			if(e.IsFood()) {
				e.ChangeActivity();
			}
		}	
		
		// nowe jedzenie
		for(int y=0;y<height;y++) {
			for(int x=0;x<width;x++) {
				if(Worldtiles[x][y] == 10 || Worldtiles[x][y] == 9 || Worldtiles[x][y] == 0) 
					// Wyklucza wnetrze domu duchow i punkt startowy paca
					if( !( (x >= SpawnX/Tiles.TILEWIDTH -3 && x <= SpawnX/Tiles.TILEWIDTH +3) && (y>=SpawnY/Tiles.TILEHEIGHT-6  && y<=SpawnY/Tiles.TILEHEIGHT)) ) {
						entityManager.addEntity(new Food(handler,x*Tiles.TILEWIDTH,y*Tiles.TILEHEIGHT));
						maxPoints+=1*PointsPerFood;
					}
			}
		}
	}
	
	public void ResetCreatures() {
		// reset duchow i gracza
		handler.getPlayer().setX(SpawnX);
		handler.getPlayer().setY(SpawnY);
		for(Entity e :handler.getWorld().getEntityManager().getEntities() ) {
			if(e.IsGhost()) {
				e.setX(SpawnX);
				e.setY(SpawnY-2*Tiles.TILEHEIGHT);
			}
		}	
	}
	
	public void SaveScore(String path) {
		int Score=points;  
		int swapHelper;
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		int[] HighScores = new int[10];
		file = "";
		for(int i=0;i<10;i++) {
			HighScores[i]=Utils.parseInt(tokens[i]);
			if(Score>HighScores[i]) {   // jeœli wynik jest wy¿szy od aktualnego zamieñ je
				swapHelper=HighScores[i];
				HighScores[i] = Score;
				Score = swapHelper;
			}
			file += Integer.toString(HighScores[i]) + " ";
		}
		
		Utils.saveToFile(path, file);
			
	}
	
	public void addPoints(int number) {
		points +=number;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getMaxPoints() {
		return maxPoints;
	}
	public int getHeight() {
		return height;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
