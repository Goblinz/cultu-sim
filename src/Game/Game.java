package Game;
import java.awt.Point;
import java.io.FileNotFoundException;
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
	
	public Game() throws FileNotFoundException{
		//Change to random gen by entering new World(x,y,z) where the world is an X * Y grid with seed z(long integer)
		//To load from a file put the name of the file as the parameter.  MAKE SURE THE FILE IS IN THE WORLDS FOLDER AND INCLUDE THE FILE EXTENSION
		//FORMAT FOR WORLD FILE: r=rock, w=water, fl=fertileland, f=forest, i=ice, m=mine
		world = new World(300,20,20);
		Random rand = new Random();
		factions = new ArrayList<Faction>();
		/*FactionAI foo = new SimpleFactionAI();
		Faction temp = new Faction(0,"chaKrim",foo);
		FactionAI foo2 = new SimpleFactionAI();
		Faction temp2 = new Faction(1,"hakoa",foo2);
		temp.setCityLocation(new Point(5,5));
		temp2.setCityLocation(new Point(15,15));
		factions.add(temp);
		factions.add(temp2);
		Actor city = new City(0,5,5,world);
		Actor city2 = new City(1,15,15,world);*/
		int numberOfFactions  = rand.nextInt(3)+2;
		
		for(int i=0;i<numberOfFactions;i++){
			//Point pos = world.getStartPos();
			//System.out.format("adding city at %d,%d\n",pos.x,pos.y);
			Point pos = new Point(i*3+3,i*3+3);
			FactionAI foo = new SimpleFactionAI();
			Faction temp = new Faction(i,generateFactionName(),foo);
			temp.setCityLocation(pos);
			factions.add(temp);
			Actor city = new City(i,pos.x,pos.y,world);
			
		}
		
	}
	/*
	public View generateView(){
		
		return null;//FILLER TODO
	}
	*/
	public void tick(){
		
		
		//TEST Checks actors on tile
		/**
		for(int x=0; x<world.getTiles().length; x++){
			for(int y=0; y<world.getTiles()[0].length; y++){
				String a;
				if(world.getTiles()[x][y].isActorOnTile()){
					a="X";
				}else{
					a=" ";
				}
				
				System.out.print("["+a+"]");
			}
			System.out.println();
		}
		*/
		
		
		
		
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
