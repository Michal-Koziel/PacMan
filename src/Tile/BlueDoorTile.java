package Tile;

import Graphics.Assets;

public class BlueDoorTile extends Tiles {

	public BlueDoorTile(int id) {
		super(Assets.BlueDoor, id);
	}
	

	@Override
	public boolean IsSolid() {
		return true;
	}
	
}
