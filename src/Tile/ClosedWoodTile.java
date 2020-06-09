package Tile;

import Graphics.Assets;

public class ClosedWoodTile extends Tiles {

	public ClosedWoodTile(int id) {
		super(Assets.ClosedWood, id);
	}

	@Override
	public boolean IsSolid() {
		return true;
	}
	
}
