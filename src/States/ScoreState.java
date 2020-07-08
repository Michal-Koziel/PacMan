package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import Graphics.Assets;
import Main.Handler;
import UI.ClickListener;
import UI.UIImageButton;
import UI.UIManager;
import Utilities.Utils;

//Klasa zawieraj¹ca wyniki
public class ScoreState extends State {
	
	private UIManager uiManagerS;
	private String[] Scores;
	
	public ScoreState(Handler handler){
		super(handler);
		uiManagerS = new UIManager(handler);
		handler.getMouseManager().setUImanager(uiManagerS);
		
		uiManagerS.addObject(new UIImageButton(422,590,500,100,Assets.buttonBack, new ClickListener() {
			@Override
			public void OnClick() {
				handler.getMouseManager().setUImanager(null);
				State.setState(handler.getGame().menustate);
			}}));
		
	}
	
	@Override
	public void update() {
		GetScore("res/scores/scoretable.txt");
		handler.getMouseManager().setUImanager(uiManagerS);
		uiManagerS.update();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.Background,322,100,700,600, null);
		g.setColor(Color.BLACK);
		g.setFont(new Font("CooperBlack", Font.BOLD, 70));
		g.drawString("High Scores", 480, 180);
		g.setFont(new Font("CooperBlack", Font.BOLD, 35));
		g.drawString("Rank", 480, 235);
		g.drawString("Points", 780, 235);
		
		// Wypisanie numerów wyników
		for(int i=1;i<11;i++) {
			g.drawString(Integer.toString(i) + ".", 510, 235 + i*35);
		}
		// Wypisanie wyników
		for(int i=0;i<10;i++)
			g.drawString(Scores[i], 800, 280 + i*35);
		
		uiManagerS.render(g);
	}
	
	public void GetScore(String path) {
		String file = Utils.loadFileAsString(path);
		Scores = file.split("\\s+");
	}
}
