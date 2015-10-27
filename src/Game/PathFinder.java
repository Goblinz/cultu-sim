package Game;
import java.awt.Point;
import java.util.ArrayList;

//TEST POST
public class PathFinder {

	Actor actor;
	World world;
	Point toPoint;
	ArrayList<Tile> path;
	
	
	public PathFinder(){
		
	}
	
	/**
	 * 
	 * @param w the world object
	 * @param a the actor
	 * @param p the point to move to
	 * @return an array of Tiles that is the shortest not obstructed path
	 */
	public Tile[] getPathTo(World w, Actor a, Point p){
		actor = a;
		world = w;
		toPoint = p;
		return null;
	}
	
	public ArrayList<Tile> adjacentTiles(){
		return null;
	}
	
}
