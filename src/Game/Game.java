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
	}
	/*
	public View generateView(){
		
		return null;//FILLER TODO
	}
	*/
	public void tick(){
		//TODO
	}
}
