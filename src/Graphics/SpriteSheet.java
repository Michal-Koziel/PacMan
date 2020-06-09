package Graphics;

import java.awt.image.BufferedImage;

// Zawiera jakiœ obraz, umo¿liwia przypisywanie jego czêœci innym obrazom.
public class SpriteSheet {

	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;	
	}
	
	public BufferedImage crop(int x,int y,int width,int height) {
		return sheet.getSubimage(x,y,width,height);
	}
	
	
	
	
}
