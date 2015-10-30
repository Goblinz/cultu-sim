package Game;
import java.awt.Point;
import java.util.ArrayList;


public class Game {
	public ArrayList<Resource> resources;
	public World world;
	private ArrayList<Faction> factions;
	//private View view = new View(world);
	
	public Game(){
		//Change to random gen by entering new World(x,y,z) where the world is an X * Y grid with seed z(long integer)
		world = new World();
		
		factions = new ArrayList<Faction>();
		FactionAI foo = new SimpleFactionAI();
		Faction temp = new Faction(0,"chaKrim",foo);
		FactionAI foo2 = new SimpleFactionAI();
		Faction temp2 = new Faction(1,"hakoa",foo2);
		//System.out.println(temp.toString());
		//System.out.println(temp2.toString());
		temp.setCityLocation(new Point(0,0));
		
		temp2.setCityLocation(new Point(9,9));
		factions.add(temp);
		factions.add(temp2);
		Actor city = new City(0,0,0,world);
		//System.out.println(city.toString());
		Actor city2 = new City(1,9,9,world);
		//System.out.println(city.toString());
		//System.out.println(city2.toString());
		/*Structure testBuild = new Structure(0,0);
		testBuild.setX(3);
		testBuild.setY(4);
		world.getTiles()[3][4].onMove(testBuild);*/
	}
	/*
	public View generateView(){
		
		return null;//FILLER TODO
	}
	*/
	public void tick(){
		//TODO FACTION ACT
		for(Faction f:factions){
			f.act(world, factions);
		}
		Tile[][] tiles = world.getTiles();
		for(int i=0;i<tiles.length;i++){
			for(int j=0;j<tiles.length;j++){
				if(tiles[i][j].isActorOnTile() && !tiles[i][j].actorOnTile().hasActed()){
					//System.out.format("actor at %d,%d is acting\n",i,j);
					tiles[i][j].actorOnTile().toggleActed();
					System.out.println(tiles[i][j].actorOnTile().factionID);
					tiles[i][j].actorOnTile().act(world, factions.get(tiles[i][j].actorOnTile().factionID));
					
				}
			}
		}
		for(int i=0;i<tiles.length;i++){
			for(int j=0;j<tiles.length;j++){
				if(tiles[i][j].isActorOnTile() && tiles[i][j].actorOnTile().hasActed()){
					tiles[i][j].actorOnTile().toggleActed();
				}
			}
		}	
	}
	/*public void spawnUnitMoveGather(int x,int y,Point[] path){
		Actor temp = new Unit(0,0);
		Order patrol = new MoveGather();
		((MoveGather) patrol).setPath(path);
		((Unit) temp).recieveOrder(patrol);
		temp.setX(x);
		temp.setY(y);
		world.getTiles()[x][y].onMove(temp);
	}*/
}
