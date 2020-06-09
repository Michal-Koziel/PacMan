package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Graphics.Assets;
import Main.Handler;
import World.World;

//Klasa stanu po œmierci Pac Mana, wyœwietla wynik resetuje mapê,punkty itd. i wraca do g³ównego menu
public class DeadState extends State {
	private World W;
	private int Counter = 0;  // Liczy czas, ka¿de +1 to jedna sekunda (Przy 60 FPS)
	private String points;
	private int PointsRenderHelper =0;
	
	public DeadState(Handler handler){
		super(handler);
		W = handler.getWorld();
	}

	@Override
	public void update() {
		W = handler.getWorld();
		points = Integer.toString(handler.getWorld().getPoints());
		if(handler.getWorld().getPoints()>=10) {
			PointsRenderHelper =20;
			if(handler.getWorld().getPoints()>=100) {
				PointsRenderHelper =40;
				if(handler.getWorld().getPoints()>=1000)
					PointsRenderHelper =60;
			}
		}
		if((Counter>=180 && handler.getkeyMan().IsKeyPressed(KeyEvent.VK_ENTER)) || Counter>= 3000) {
			Counter = 0;
			PointsRenderHelper =0;
			handler.getWorld().ResetWorld();
			State.setState(handler.getGame().menustate);	
		}
		Counter++;
	}

	@Override
	public void render(Graphics g) {
		W.render(g);
		g.setFont(new Font("CooperBlack", Font.BOLD, 70));
		
		if(Counter>= 60 && Counter <= 3000 ) {	
			g.drawImage(Assets.Background,322,100,700,600, null);
		}		
		if(Counter>= 70 && Counter <= 3000 ) {		
			g.drawImage(Assets.GameOver,375,130,600,100, null);		
		}
		if(Counter>=120 && Counter <= 3000 ) {
			g.drawImage(Assets.YourScore,375,280,600,200, null);	
			g.setColor(Color.black);
			g.drawString(points,650-PointsRenderHelper,430);
		}
		
		if(Counter>=180 && Counter <= 3000 ) {
			g.drawImage(Assets.MenuReturn,375,490,600,200, null);		
		}
	}

}
