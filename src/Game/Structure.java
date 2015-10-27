package Game;


public class Structure extends Actor {
	public Structure(int FactionID,int id){
		super();
		factionID = FactionID;
		ID=id;
		type = ActorType.STRUCTURE;
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
