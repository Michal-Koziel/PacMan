package States;

import java.awt.Color;
import java.awt.Graphics;

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
		
		uiManagerP.addObject(new UIImageButton(387,420,600,100,Assets.buttonResume, new ClickListener() {
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
		g.drawImage(Assets.GamePaused,380,130,600,100,null);
		g.drawImage(Assets.CookieMButton[0],400,280,64,64,null);
		g.drawImage(Assets.Cookie,470,300,64,64,null);
		g.drawImage(Assets.Cookie,520,270,64,64,null);
		g.drawImage(Assets.Cookie,570,300,64,64,null);
		g.drawImage(Assets.Cookie,620,270,64,64,null);
		g.drawImage(Assets.Cookie,670,300,64,64,null);
		g.drawImage(Assets.Cookie,720,270,64,64,null);
		g.drawImage(Assets.Cookie,770,300,64,64,null);
		g.drawImage(Assets.Cookie,820,270,64,64,null);
		g.drawImage(Assets.Cookie,870,300,64,64,null);
	}

}