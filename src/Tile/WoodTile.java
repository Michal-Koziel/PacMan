package Tile;

import Graphics.Assets;

public class WoodTile extends Tiles {

	public WoodTile(int id) {
		super(Assets.Wood, id);
	}

	@Override
	public boolean IsSolid() {
		return true;
	}
	
}
