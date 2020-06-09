package Tile;

import Graphics.Assets;

public class RedBrokenBrickTile extends Tiles {

	public RedBrokenBrickTile(int id) {
		super(Assets.RedBrokenBrick, id);
	}

	@Override
	public boolean IsSolid() {
		return true;
	}
	
}
