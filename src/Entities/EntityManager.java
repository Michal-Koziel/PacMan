package Entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import Entities.Creatures.Player;
import Main.Handler;

// Klasa zarz¹dzaj¹ca wszystkimi "jednostkami"
public class EntityManager {

	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	
	 // Comparator do sortowania wszystkich jednostek, niestety niedokoñczony, powoduje b³êdy
	/*private Comparator <Entity> renderSorter = new Comparator <Entity>() {

		@Override
		public int compare(Entity a, Entity b) {
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
				return -1;
			else
				return 1;
		}
				
	};*/
	
	public EntityManager(Handler handler,Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		entities.add(player);
	}
	
	// Aktualizacja wszystkich jednostek
	public void update() {
		for(int i=0;i<entities.size();i++) {
			Entity e = entities.get(i);
			// Je¿eli GameIsStopped tylko pac man mo¿e siê ruszaæ, co "odpauzowuje gre" (jedzenie te¿ mo¿e siê updatowaæ)
			if(!handler.getGame().gamestate.IsGameStopped || e.IsFood() || e.IsPowerUp() || e.IsPlayer())
			e.update();
			if(e.IsActive()==false)
				entities.remove(e);
		}
		//entities.sort(renderSorter);
	}
	
	// Wyœwietlenie wszystkich jednostek
	public void render(Graphics G) {
		for(int i=5;i<entities.size();i++) {
			Entity e = entities.get(i);
			e.render(G);
		}
		for(int i=0;i<5;i++) {
			Entity e = entities.get(i);
			e.render(G);
		}
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	
	// Getters and setters

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}


}

