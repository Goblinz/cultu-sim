package Game;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Game {
	public ArrayList<Resource> resources;
	public World world;
	public ArrayList<Faction> factions;
	//private View view = new View(world);
	public String generateFactionName(){
		Random rand = new Random();
		String[] prefix = {"Ha","Fa","Go","Hoo","Da","Re","Ro","Wa"};
		String[] base = {"m","roo","gla","ko","jen","pra"};
		String[] suffix = {"im","ma","ichi","Qoa","koa","krim"};
		return prefix[rand.nextInt(prefix.length)] + base[rand.nextInt(base.length)] + suffix[rand.nextInt(suffix.length)];
	}
	
	public Game() throws FileNotFoundException{
		
		int width=20;
		int height = 20;
		long seed = System.currentTimeMillis();
		int numberOfFactions  = 2;
		String file = null;
		//Change to random gen by entering new World(x,y,z) where the world is an X * Y grid with seed z(long integer)
		//To load from a file put the name of the file as the parameter.  MAKE SURE THE FILE IS IN THE WORLDS FOLDER AND INCLUDE THE FILE EXTENSION
		//FORMAT FOR WORLD FILE: r=rock, w=water, fl=fertileland, f=forest, i=ice, m=mine
		Scanner scanner = new Scanner(new File("config"));
		//System.out.println("read file");
		
		while(scanner.hasNextLine()){
			//System.out.println("reading line");
			String line = scanner.nextLine();
			//System.out.println("read line");
			if(!line.startsWith("-")){ //ignoring lines with this
				String[] bits = line.split("=");
				if(bits[0].equals("width"))
					width = Integer.parseInt(bits[1]);
				else if(bits[0].equals("height"))
					height = Integer.parseInt(bits[1]);
				else if(bits[0].equals("numFaction"))
					numberOfFactions = Integer.parseInt(bits[1]);
				else if(bits[0].equals("seed"))
					seed = Integer.parseInt(bits[1]);
				else if(bits[0].equals("worldfile"))
					file = bits[1];
				
			}
		}
		if(seed==0)
			seed = System.currentTimeMillis();
		//System.out.format(":%s:\n",file);
		if(file.equals("null"))
			world = new World(seed,width,height);
		else
			world = new World(file);
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
		//int numberOfFactions  = rand.nextInt(3)+2;
		
		
		for(int i=0;i<numberOfFactions;i++){
			//Point pos = world.getStartPos();
			//System.out.format("adding city at %d,%d\n",pos.x,pos.y);
			Point pos = new Point(i*4+3,i*4+3);
			FactionAI foo = new SlightlyMoreComplicatedSimpleFactionAI();
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
