package UI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Main.Handler;

// Zarz¹dca wszystkich UI Obieków. Aktualizuje je i wyœwietla
public class UIManager {

	private Handler handler;
	private ArrayList<UIObject> objects;
	
	public UIManager(Handler handler) {
		this.handler=handler;
		objects = new ArrayList<UIObject>();
	}
	
	public void update() {
		for(UIObject O:objects)
			O.update();
	}
	
	public void render(Graphics G) {
		for(UIObject O:objects)
			O.render(G);
	}
	
	public void OnMouseMove(MouseEvent e) {
		for(UIObject O:objects)
			O.OnMouseMove(e);
	}
	
	public void OnMouseRelease(MouseEvent e) {
		for(UIObject O:objects)
			O.OnMouseRelease(e);
	}
	
	public void addObject(UIObject O) {
		objects.add(O);		
	}
	
	public void removeObject(UIObject O) {
		objects.remove(O);		
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}

}
