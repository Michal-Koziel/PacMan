package States;

import java.awt.Font;
import java.awt.Graphics;
import Graphics.Assets;
import Main.Handler;
import World.World;

//Klasa stanu po przejœciu mapy, resetuje jedzenie i kreatury, przechodzi do nast. poziomu
public class LevelPassingState extends State {
	private World W;
	private int Counter = 0;
	
	public LevelPassingState(Handler handler){
		super(handler);
		W = handler.getWorld();
	}

	@Override
	public void update() {
		W = handler.getWorld();
		
		if(Counter>= 300) {
			Counter = 0;
			handler.getWorld().NextLevel();
			State.setState(handler.getGame().gamestate);	
		}
		Counter++;
	}

	@Override
	public void render(Graphics g) {
		W.render(g);
		g.setFont(new Font("CooperBlack", Font.BOLD, 70));
		
		if(Counter>= 60 && Counter <= 300 ) {	
			g.drawImage(Assets.Background,322,100,700,600, null);
		}	
		if(Counter>=80 && Counter <= 170 ) {
			g.drawImage(Assets.LevelCompleted,365,270,600,200, null);		
		}
		
		if(Counter>=190 && Counter <= 300 ) {
			g.drawImage(Assets.NextLevel,375,290,600,200, null);				
		}
	}

}