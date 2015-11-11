package Game;

import java.awt.Color;

public class Structure extends Actor {
	public Structure(int factionid, int id, int x, int y, World world) {
		super(x, y, world, id, factionid, ActorType.STRUCTURE);
		health =  5;
	}

	public void act(World world, Faction faction) {
		Tile onTile = world.getTiles()[posX][posY];
		if (onTile.getType() == TileType.FERTILELAND) {
			if(faction.techTree.hasTech("Grain Storage"))
				resources.get("Food").addQuantity(7);
			else
				resources.get("Food").addQuantity(5);
		} else if (onTile.getType() == TileType.MINE) {
			if(faction.techTree.hasTech("Shaft Mining"))
				resources.get("Metal").addQuantity(7);
			else
				resources.get("Metal").addQuantity(5);
		} else if (onTile.getType() == TileType.FOREST) {
			if(faction.techTree.hasTech("Axes"))
				resources.get("Wood").addQuantity(7);
			else
				resources.get("Wood").addQuantity(5);
		}
	}

}
