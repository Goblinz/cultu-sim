package Game;

public class City extends Actor {
	
	public City(int factionid,int x,int y, World world){
		super(x,y,world,0,factionid,ActorType.CITY);
		health =  10;
	}
	public void onDie(Actor killer, World world){
		//remove faction stuff
		Tile[][] tiles = world.getTiles();
		for(int i=0;i<tiles.length;i++){
			for(int j=0;j<tiles.length;j++){
				if(tiles[i][j].isActorOnTile() && tiles[i][j].actorOnTile().factionID==factionID)
					tiles[i][j].offMove();
			}
		}
	}
}
