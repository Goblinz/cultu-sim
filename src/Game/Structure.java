package Game;

import java.awt.Color;


public class Structure extends Actor {
	public Structure(int factionid,int id, int x,int y,World world){
		super(x,y,world,id,factionid,ActorType.STRUCTURE);
	}
	public void act(World world,Faction faction){
		Tile onTile = world.getTiles()[posX][posY];
		if(onTile.getType() == TileType.FERTILELAND)
			resources.get("Food").addQuantity(5);
		else if(onTile.getType() == TileType.MINE)
			resources.get("Metal").addQuantity(5);
		else if(onTile.getType() == TileType.FOREST)
			resources.get("Wood").addQuantity(5);
	}

}
