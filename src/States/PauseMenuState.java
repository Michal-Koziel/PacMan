package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Graphics.Assets;
import Main.Handler;
import UI.ClickListener;
import UI.UIImageButton;
import UI.UIManager;
import World.World;

//Klasa pauzy w trakcie gry, umo¿liwia powrót do g³ownego menu lub z powrotem do gry
public class PauseMenuState extends State {
	private World W;
	private UIManager uiManagerP;
	
	public PauseMenuState(Handler handler){
		super(handler);
		uiManagerP = new UIManager(handler);
		handler.getMouseManager().setUImanager(uiManagerP);
		W = handler.getWorld();
		
		uiManagerP.addObject(new UIImageButton(422,400,500,100,Assets.buttonBack, new ClickListener() {
			@Override
			public void OnClick() {
				handler.getMouseManager().setUImanager(null);
				State.setState(handler.getGame().gamestate);
			}}));
		
		uiManagerP.addObject(new UIImageButton(422,550,500,100,Assets.buttonEnd, new ClickListener() {
			@Override
			public void OnClick() {
				handler.getMouseManager().setUImanager(null);
				State.setState(handler.getGame().menustate);	
			}}));
	}

	@Override
	public void update() {
		handler.getMouseManager().setUImanager(uiManagerP);
		uiManagerP.update();
		W = handler.getWorld();
	}

	@Override
	public void render(Graphics g) {
		W.render(g);
		g.drawImage(Assets.Background,322,100,700,600, null);
		uiManagerP.render(g);
		g.setFont(new Font("CooperBlack", Font.BOLD, 90));
		g.setColor(Color.black);
		g.drawString("Game Paused",380,250);
	}

}