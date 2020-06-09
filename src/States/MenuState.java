package States;

import java.awt.Color;
import java.awt.Graphics;
import Graphics.Assets;
import Main.Handler;
import UI.ClickListener;
import UI.UIImageButton;
import UI.UIManager;

//Klasa zawieraj¹ca menu g³ówne
public class MenuState extends State {
	
	private UIManager uiManagerM;
	
	public MenuState(Handler handler){
		super(handler);
		uiManagerM = new UIManager(handler);
		handler.getMouseManager().setUImanager(uiManagerM);
		
		uiManagerM.addObject(new UIImageButton(422,150,500,100,Assets.buttonStart, new ClickListener() {
			@Override
			public void OnClick() {
				handler.getMouseManager().setUImanager(null);
				State.setState(handler.getGame().gamestate);
			}}));
		
		uiManagerM.addObject(new UIImageButton(422,283,500,100,Assets.buttonOption, new ClickListener() {
			@Override
			public void OnClick() {
				handler.getMouseManager().setUImanager(null);
				State.setState(handler.getGame().optionstate);
			}}));
		
		uiManagerM.addObject(new UIImageButton(422,416,500,100,Assets.buttonScore, new ClickListener() {
			@Override
			public void OnClick() {
				handler.getMouseManager().setUImanager(null);
				State.setState(handler.getGame().scorestate);
			}}));
		
		uiManagerM.addObject(new UIImageButton(422,550,500,100,Assets.buttonEnd, new ClickListener() {
			@Override
			public void OnClick() {
				System.exit(0);
			}}));
	}
	
	@Override
	public void update() {
		handler.getMouseManager().setUImanager(uiManagerM);
		uiManagerM.update();
	}

	@Override
	public void render(Graphics g) {
		// t³o menu
		
		// ramka menu
		g.drawImage(Assets.Background,322,100,700,600, null);
		//przyciski
		uiManagerM.render(g);
		
	}
}
