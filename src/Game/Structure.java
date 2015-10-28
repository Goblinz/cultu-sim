package Game;

import java.awt.Color;


public class Structure extends Actor {
	public Structure(int FactionID,int id, int x,int y,World world){
		super(x,y,world);
		factionID = FactionID;
		ID=id;
		type = ActorType.STRUCTURE;
		color = new Color((factionID * 50) % 255,200,200);
	}
	public void act(World world,Faction faction){
		Tile onTile = world.getTiles()[posX][posY];
		if(onTile.getType() == "FERTILELAND")
			resources.get("Food").addQuantity(5);
		else if(onTile.getType() == "MINE")
			resources.get("Metal").addQuantity(5);
		else if(onTile.getType() == "FOREST")
			resources.get("Wood").addQuantity(5);
	}

}
