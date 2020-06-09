package Tile;

import Graphics.Assets;

public class RedBrickTile extends Tiles {

	public RedBrickTile(int id) {
		super(Assets.RedBrick, id);
	}

	@Override
	public boolean IsSolid() {
		return true;
	}
	
}
