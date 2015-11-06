package Game;

import java.awt.Color;


public class Structure extends Actor {
	public Structure(int factionid,int id, int x,int y,World world){
		super(x,y,world,id,factionid,ActorType.STRUCTURE);
	}
	public void act(World world,Faction faction){
		Tile onTile = world.getTiles()[posX][posY];
<<<<<<< .mine
		if(onTile.getType() == TileType.FERTILELAND)
||||||| .r115
		if(onTile.getType() == "FERTILELAND"){
=======
		if(onTile.getType() == "FERTILELAND")
>>>>>>> .r116
			resources.get("Food").addQuantity(5);
<<<<<<< .mine
		else if(onTile.getType() == TileType.MINE)
||||||| .r115
		}
		else if(onTile.getType() == "MINE"){
=======
		else if(onTile.getType() == "MINE")
>>>>>>> .r116
			resources.get("Metal").addQuantity(5);
<<<<<<< .mine
		else if(onTile.getType() == TileType.FOREST)
||||||| .r115
		}
		else if(onTile.getType() == "FOREST"){
=======
		else if(onTile.getType() == "FOREST")
>>>>>>> .r116
			resources.get("Wood").addQuantity(5);
	}

}
