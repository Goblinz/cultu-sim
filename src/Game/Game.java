package Game;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;


public class Game {
	public ArrayList<Resource> resources;
	public World world;
	private ArrayList<Faction> factions;
	//private View view = new View(world);
	private String generateFactionName(){
		Random rand = new Random();
		String[] prefix = {"Ha","Fa","Go","Hoo","Da","Re","Ro","Wa"};
		String[] base = {"m","roo","gla","ko","jen","pra"};
		String[] suffix = {"im","ma","ichi","Qoa","koa","krim"};
		return prefix[rand.nextInt(prefix.length)] + base[rand.nextInt(base.length)] + suffix[rand.nextInt(suffix.length)];
	}
		
	public Game(){
		//Change to random gen by entering new World(x,y,z) where the world is an X * Y grid with seed z(long integer)
		world = new World(100,20,20);
		factions = new ArrayList<Faction>();
		/*FactionAI foo = new SimpleFactionAI();
		Faction temp = new Faction(0,"chaKrim",foo);
		FactionAI foo2 = new SimpleFactionAI();
		Faction temp2 = new Faction(1,"hakoa",foo2);
		//System.out.println(temp.toString());
		//System.out.println(temp2.toString());
		temp.setCityLocation(new Point(5,5));
		
		temp2.setCityLocation(new Point(15,15));
		factions.add(temp);
		factions.add(temp2);
		Actor city = new City(0,5,5,world);
		//System.out.println(city.toString());
		Actor city2 = new City(1,15,15,world);
		//System.out.println(city.toString());
		//System.out.println(city2.toString());
		/*Structure testBuild = new Structure(0,0);
		testBuild.setX(3);
		testBuild.setY(4);
		world.getTiles()[3][4].onMove(testBuild);*/
		
		int numberOfFactions = 4;
		//Point oldPoint=null;
		for(int i=0;i<numberOfFactions;i++){
			Point pos = world.getStartPos();
			FactionAI foo = new SimpleFactionAI();
			Faction temp = new Faction(i,generateFactionName(),foo);
			temp.setCityLocation(pos);
			factions.add(temp);
			Actor city = new City(i,pos.x,pos.y,world);
			new Structure(0,-1,5,5,world);
			new Structure(1,-1,6,6,world);
		}
	}
	/*
	public View generateView(){
		
		return null;//FILLER TODO
	}
	*/
	public void tick(){
		//TODO FACTION ACT
		
		//combat testing
		ArrayList<Tile> adjEnemyTiles = world.getTiles()[5][5].actorOnTile().adjacentEnemyActors();
		
		world.getTiles()[5][5].actorOnTile().combat(world);
		
		for(Tile t: adjEnemyTiles){
			//System.out.println(t.point);
		}
		
		//end combat testing
		
		for(Faction f:factions){
			f.act(world, factions);
		}
		Tile[][] tiles = world.getTiles();
		for(int i=0;i<tiles.length;i++){
			for(int j=0;j<tiles.length;j++){
				if(tiles[i][j].isActorOnTile() && !tiles[i][j].actorOnTile().hasActed()){
					//System.out.format("actor at %d,%d is acting\n",i,j);
					tiles[i][j].actorOnTile().toggleActed();
					//System.out.println(tiles[i][j].actorOnTile().factionID);
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
