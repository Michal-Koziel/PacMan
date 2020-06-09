package Tile;

import Graphics.Assets;

public class RedDoorTile extends Tiles {

	public RedDoorTile(int id) {
		super(Assets.RedDoor, id);
	}
	

	@Override
	public boolean IsSolid() {
		return true;
	}
	
}
