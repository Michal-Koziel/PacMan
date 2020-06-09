package Tile;

import Graphics.Assets;

public class GrassDoorTile extends Tiles {

	public GrassDoorTile(int id) {
		super(Assets.GrassDoor, id);
	}
	

	@Override
	public boolean IsSolid() {
		return true;
	}
	
}
