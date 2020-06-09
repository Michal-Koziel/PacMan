package Tile;

import Graphics.Assets;

public class BlueBrokenBrickTile extends Tiles {

	public BlueBrokenBrickTile(int id) {
		super(Assets.BlueBrokenBrick, id);
	}

	@Override
	public boolean IsSolid() {
		return true;
	}
	
}
