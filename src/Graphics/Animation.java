package Graphics;

import java.awt.image.BufferedImage;

// Klasa animacji, aktualizuje je co jakiœ czas, by grafika siê zmienia³a
public class Animation {

	private int speed,index;
	private long LastTime,timer;
	private BufferedImage[] frames;
	
	public Animation(int speed,BufferedImage[] frames) {
		this.speed=speed;
		this.frames=frames;
		index=0;
		timer=0;
		LastTime=System.currentTimeMillis();
	}
	
	public void update() {
		timer+=System.currentTimeMillis() - LastTime;
		LastTime=System.currentTimeMillis();		
		
		if(timer>speed) {
			index++;
			timer = 0;
			if(index>=frames.length)
				index=0;
		}
	}
	

	public BufferedImage GetCurrentFrame() {
		return this.frames[index];
	}
	
	
}

