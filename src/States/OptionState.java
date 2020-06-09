package States;

import java.awt.Color;
import java.awt.Graphics;
import Graphics.Assets;
import Main.Handler;
import UI.ClickListener;
import UI.UIImageButton;
import UI.UIManager;

//Klasa zawieraj¹ca menu opcji
public class OptionState extends State {
	
	private UIManager uiManagerO;
	
	public OptionState(Handler handler){
		super(handler);
		uiManagerO = new UIManager(handler);
		handler.getMouseManager().setUImanager(uiManagerO);
		
		// Przyciski 
		
		uiManagerO.addObject(new UIImageButton(422,150,500,100,Assets.buttonMap, new ClickListener() {
			@Override
			public void OnClick() {
				handler.getMouseManager().setUImanager(null);
				State.setState(handler.getGame().mapstate);
			}}));
		
		uiManagerO.addObject(new UIImageButton(422,300,500,200,Assets.buttonPlayer, new ClickListener() {
			@Override
			public void OnClick() {
				handler.getMouseManager().setUImanager(null);
				State.setState(handler.getGame().playerstate);
			}}));

		uiManagerO.addObject(new UIImageButton(422,550,500,100,Assets.buttonBack, new ClickListener() {
			@Override
			public void OnClick() {
				handler.getMouseManager().setUImanager(null);
				State.setState(handler.getGame().menustate);
			}}));
	}
	
	@Override
	public void update() {
		handler.getMouseManager().setUImanager(uiManagerO);
		uiManagerO.update();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Background,322,100,700,600, null);
		uiManagerO.render(g);
	}
}
