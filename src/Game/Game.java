package Game;
import java.awt.Point;
import java.util.ArrayList;


public class Game {
	public ArrayList<Resource> resources;
	public World world;
	private ArrayList<Faction> factions;
	//private View view = new View(world);
	
	public Game(){
		world = new World();
		//creating an actor 
		Actor temp = new Unit();
		Order patrol = new MoveGather();
		Point[] path = {new Point(5,5),new Point(5,0),new Point(0,5)};
		((MoveGather) patrol).setPath(path);
		((Unit) temp).recieveOrder(patrol);
		temp.setX(6);
		temp.setY(6);
		world.getTiles()[6][6].onMove(temp);
	}
	/*
	public View generateView(){
		
		return null;//FILLER TODO
	}
	*/
	public void tick(){
		Tile[][] tiles = world.getTiles();
		for(int i=0;i<tiles.length;i++){
			for(int j=0;j<tiles.length;j++){
				if(tiles[i][j].isActorOnTile() && !tiles[i][j].actorOnTile().hasActed()){
					System.out.format("actor at %d,%d is acting\n",i,j);
					tiles[i][j].actorOnTile().toggleActed();
					tiles[i][j].actorOnTile().act(world, null);
					
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
}
