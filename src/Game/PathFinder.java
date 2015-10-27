package Game;
import java.awt.Point;
import java.util.ArrayList;

//TEST POST
public class PathFinder {

	private Actor actor;
	private World world;
	private Point toPoint;
	
	/**
	 * an object used to find the shortest passable path between an actor and a Point
	 */
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
		
		ArrayList<Tile> path = getPathToAL(w, a, p);
		Tile[] toReturn = new Tile[path.size()];
		
		for(int i=0; i<toReturn.length; i++){
			toReturn[i] = path.get(i);
		}

		return toReturn;	
	}
	
	/**
	 * 
	 * @param w the world object
	 * @param a the actor
	 * @param p the point to move to
	 * @return an ArrayList of Tiles that is the shortest not obstructed path
	 */
	public ArrayList<Tile> getPathToAL(World w, Actor a, Point p){
		actor = a;
		world = w;
		toPoint = p;
		Tile current = world.getTiles()[actor.getX()][actor.getY()];
		ArrayList<Tile> stack = new ArrayList<Tile>();
		ArrayList<Tile> exploredTiles = new ArrayList<Tile>();
		
		stack.add(current);
		while(stack.size()>0){
			current = stack.remove(stack.size()-1);
			if(!exploredTiles.contains(current)){
				exploredTiles.add(current);
				for(Tile t: adjacentPassabeTiles(current)){
					stack.add(t);
					if(toPoint==t.getPoint()){
						return stack;
					}
				}
				
			}
		}
		return null;
	}
	
	public ArrayList<Tile> adjacentPassabeTiles(Tile t){
		Tile[][] worldTiles = world.getTiles();
		ArrayList<Tile> adj = new ArrayList<Tile>();
		
		Point[] adjs = {new Point(t.getPoint().x-1,t.getPoint().y-1),
				new Point(t.getPoint().x,t.getPoint().y-1),
				new Point(t.getPoint().x+1,t.getPoint().y+1),
				new Point(t.getPoint().x-1,t.getPoint().y),
				new Point(t.getPoint().x+1,t.getPoint().y),
				new Point(t.getPoint().x-1,t.getPoint().y-1),
				new Point(t.getPoint().x+1,t.getPoint().y),
				new Point(t.getPoint().x+1,t.getPoint().y+1)};
		
		for(int i=0; i<8; i++){
			//System.out.println(adjs[i].x + ", " + adjs[i].y);

			if(adjs[i].x>=0 && adjs[i].y>=0   &&   adjs[i].x<worldTiles.length && adjs[i].y<worldTiles[0].length){
				if(worldTiles[adjs[i].x][adjs[i].y].isPassable() && !worldTiles[adjs[i].x][adjs[i].y].hasActorOnTile()){
					adj.add(worldTiles[adjs[i].x][adjs[i].y]);
				}
			}
		}

		return adj;
	}
	
}
