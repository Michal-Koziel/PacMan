package UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

//klasa przycisków, zmienia grafikê przy najechaniu na nie, umo¿liwia ich klikanie
public class UIImageButton extends UIObject {

	private BufferedImage[] images;
	private	ClickListener clicker;
	
	
	public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, 
	ClickListener clicker) {
		super(x, y, width, height);
		this.images=images;
		this.clicker=clicker;
	}
	

	@Override
	public void update() {

	}

	@Override
	public void render(Graphics G) {
		if(hovering)
			G.drawImage(images[1],(int)x,(int)y,width,height,null);
		else
			G.drawImage(images[0],(int)x,(int)y,width,height,null);
	}

	@Override
	public void OnClick() {
		clicker.OnClick();
	}

}
