package States;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Main.Handler;
import World.World;


public class GameState extends State {
	private World W;
	private String MapChooser = "world1.txt";
	public boolean IsGameStopped = true;

	public GameState(Handler handler){
		super(handler);
		W = new World(handler,"res/worlds/" + MapChooser);
		handler.setWorld(W);
	}

	@Override
	public void update() {
		if( handler.getkeyMan().IsKeyPressed(KeyEvent.VK_ESCAPE))
			State.setState(handler.getGame().pausemenustate);	
		W.update();
	}

	@Override
	public void render(Graphics g) {
		W.render(g);
	}
	
	public void ChangeMap(String Map) {
		MapChooser = Map;
		W = new World(handler,"res/worlds/" + MapChooser);
		handler.setWorld(W);
	}
	


}
