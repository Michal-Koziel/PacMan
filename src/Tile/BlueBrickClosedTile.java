package Tile;

import Graphics.Assets;

public class BlueBrickClosedTile extends Tiles {


	public BlueBrickClosedTile(int id) {
		super(Assets.BlueBrickClosed, id);
	}

	@Override
	public boolean IsSolid() {
		return true;
	}
}
