package Tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

// Klasa p³ytek graficznych, czyli "t³a œwiata"/mapy
public class Tiles {
	
	// Statics
	public static Tiles[] tiles = new Tiles[256];
	
	public static Tiles bluefloorTile = new BlueFloorTile(0);
	public static Tiles bluebrickTile = new BlueBrickTile(1);
	public static Tiles bluebrokenbrickTile = new BlueBrokenBrickTile(2);
	public static Tiles bluebrickClosedTile = new BlueBrickClosedTile(3);
	public static Tiles redbrickTile = new RedBrickTile(4);
	public static Tiles redbrokenbrickTile = new RedBrokenBrickTile(5);
	public static Tiles redbrickClosedTile = new RedBrickClosedTile(6);
	public static Tiles bluedoorTile = new BlueDoorTile(7);
	public static Tiles reddoorTile = new RedDoorTile(8);
	public static Tiles redfloorTile = new RedFloorTile(9); 
	public static Tiles grassTile = new GrassTile(10);
	public static Tiles woodTile = new WoodTile(11); 
	public static Tiles closedwoodTile = new ClosedWoodTile(12); 
	public static Tiles grassDoorTile = new GrassDoorTile(13);
	
	
	//Rest of the class
	
	public static final int TILEWIDTH =64, TILEHEIGHT =64; 

	protected BufferedImage texture;
	protected final int id; 
	
	public Tiles(BufferedImage texture, int id ) {
		this.texture=texture;
		this.id=id;
		tiles[id]=this;
	}
	
	public void update() {
	}
	
	public void render(Graphics G, int x, int y) {
		G.drawImage(texture,x,y,TILEWIDTH,TILEHEIGHT,null);	
	}
	
	// Przez solidne p³ytki nie da siê przechodziæ
	public boolean IsSolid() {
		return false;
	}
	
	
	public int getid() { 
		return id;
	}
	
}
