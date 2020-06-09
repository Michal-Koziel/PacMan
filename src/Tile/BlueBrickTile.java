package Tile;

import Graphics.Assets;

public class BlueBrickTile extends Tiles {

	public BlueBrickTile(int id) {
		super(Assets.BlueBrick, id);
	}

	@Override
	public boolean IsSolid() {
		return true;
	}
	
}
