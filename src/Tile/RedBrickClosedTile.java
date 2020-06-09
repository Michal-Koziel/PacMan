package Tile;

import Graphics.Assets;

public class RedBrickClosedTile extends Tiles {


	public RedBrickClosedTile(int id) {
		super(Assets.RedBrickClosed, id);
	}

	@Override
	public boolean IsSolid() {
		return true;
	}
}
