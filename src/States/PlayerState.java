package States;

import java.awt.Color;
import java.awt.Graphics;
import Graphics.Assets;
import Main.Handler;
import UI.ClickListener;
import UI.UIImageButton;
import UI.UIManager;

//Klasa zawieraj¹ca menu wyboru postaci
public class PlayerState extends State {
	
	private UIManager uiManagerP;
	private int SquareWidth = 344;
	private int SquareHeight = 340; 
	
	public PlayerState(Handler handler){
		super(handler);
		uiManagerP = new UIManager(handler);
		handler.getMouseManager().setUImanager(uiManagerP);
		
		// Przyciski 
		
		uiManagerP.addObject(new UIImageButton(365,361,128,128,Assets.PacManButton, new ClickListener() {
			@Override
			public void OnClick() {
				SquareWidth = 344;
				SquareHeight = 340;
				Assets.UpdatePlayer(0, 0);
			}}));
		uiManagerP.addObject(new UIImageButton(526,361,128,128,Assets.HoboButton, new ClickListener() {
			@Override
			public void OnClick() {
				SquareWidth = 505;
				SquareHeight = 340;
				Assets.UpdatePlayer(4, 0);
			}}));
		uiManagerP.addObject(new UIImageButton(687,361,128,128,Assets.CatManButton, new ClickListener() {
			@Override
			public void OnClick() {
				SquareWidth = 666;
				SquareHeight = 340;
				Assets.UpdatePlayer(4, 2);
			}}));
		uiManagerP.addObject(new UIImageButton(848,361,128,128,Assets.CookieMButton, new ClickListener() {
			@Override
			public void OnClick() {
				SquareWidth = 827;
				SquareHeight = 340;
				Assets.UpdatePlayer(0, 2);
			}}));


		uiManagerP.addObject(new UIImageButton(422,550,500,100,Assets.buttonBack, new ClickListener() {
			@Override
			public void OnClick() {
				handler.getMouseManager().setUImanager(null);
				State.setState(handler.getGame().optionstate);
			}}));
	}
	
	@Override
	public void update() {
		handler.getMouseManager().setUImanager(uiManagerP);
		uiManagerP.update();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Background,322,100,700,600, null);

		// Kwadrat oznaczaj¹cy wybór
		g.drawImage(Assets.BlueFloor,SquareWidth,SquareHeight,170,170,null);
		g.drawImage(Assets.buttonPlayer[0],422,100,500,200,null);
		uiManagerP.render(g);
	}
}