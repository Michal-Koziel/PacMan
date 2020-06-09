package States;

import java.awt.Color;
import java.awt.Graphics;
import Graphics.Assets;
import Main.Handler;
import UI.ClickListener;
import UI.UIImageButton;
import UI.UIManager;

//Klasa zawieraj¹ca menu wyboru mapy
public class MapState extends State {
	
	private UIManager uiManagerMa;
	private int SquareWidth = 140;
	private int SquareHeight = 225; 
	
	public MapState(Handler handler){
		super(handler);
		uiManagerMa = new UIManager(handler);
		handler.getMouseManager().setUImanager(uiManagerMa);
		
		// Przyciski 
		
		uiManagerMa.addObject(new UIImageButton(165,250,300,300,Assets.BlueMap, new ClickListener() {
			@Override
			public void OnClick() {
				SquareWidth = 140;
				SquareHeight = 225;
				handler.getGame().gamestate.ChangeMap("world1.txt");
			}}));
		uiManagerMa.addObject(new UIImageButton(515,250,300,300,Assets.RedMap, new ClickListener() {
			@Override
			public void OnClick() {
				SquareWidth = 490;
				SquareHeight = 225;
				handler.getGame().gamestate.ChangeMap("world2.txt");
			}}));
		uiManagerMa.addObject(new UIImageButton(865,250,300,300,Assets.GreenMap, new ClickListener() {
			@Override
			public void OnClick() {
				SquareWidth = 840;
				SquareHeight = 225;
				handler.getGame().gamestate.ChangeMap("world3.txt");
			}}));
		


		uiManagerMa.addObject(new UIImageButton(422,550,500,100,Assets.buttonBack, new ClickListener() {
			@Override
			public void OnClick() {
				handler.getMouseManager().setUImanager(null);
				State.setState(handler.getGame().optionstate);
			}}));
	}
	
	@Override
	public void update() {
		handler.getMouseManager().setUImanager(uiManagerMa);
		uiManagerMa.update();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Background,115,100,1100,600, null);
		
		// Kwadrat oznaczaj¹cy wybór
		g.drawImage(Assets.BlueFloor,SquareWidth,SquareHeight,350,350,null);
		g.drawImage(Assets.buttonMap[0],422,125,500,100,null);
		uiManagerMa.render(g);
	}
}