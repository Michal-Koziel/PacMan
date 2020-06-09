package Main;

import Entities.Creatures.Player;
import Graphics.GameCamera;
import Input.KeyMenager;
import Input.MouseManager;
import World.World;

// Klasa zawieraja wa¿ne elementy gry i przekazuj¹ca je innym klasom
public class Handler {
	private World world;
	private Game game;
	private Player player;
	
	public Handler(Game game) {
		this.game=game;
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	
	public KeyMenager getkeyMan() {
		return game.getkeyMan();
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}


	
	

}
