package Game;
import java.awt.Point;
import java.util.ArrayList;

public class PathFinder {

	private Actor actor;
	private World world;
	private Point toPoint;

	/**
	 * an object used to find the shortest passable path between an actor and a Point
	 */
	public PathFinder(World w){
		world = w;
	}

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

		//if(p.x>19 || p.y>19){ System.out.println("ERROR INVALID POINT"+ p.x+ ", " + p.y); }

		//sets the shortest path of all tiles to null
		for(Tile[] TA: w.getTiles()){
			for(Tile t: TA){
				t.setLastTile(null);
			}
		}

		actor = a;
		world = w;
		toPoint = p;
		Tile current = world.getTiles()[actor.getX()][actor.getY()];
		Tile last = null;
		ArrayList<Tile> stack = new ArrayList<Tile>();
		ArrayList<Tile> exploredTiles = new ArrayList<Tile>();

		stack.add(current);
		current.setLastTile(current);
		while(stack.size()>0){
			last = current;
			current = stack.remove(stack.size()-1);
			//if unexplored
			if(!exploredTiles.contains(current)){

				ArrayList<Tile> adjTiles = adjacentPassabeTiles(current);

				exploredTiles.add(current);
				int shortPath = 99999999;
				Tile shortTile = null;
				//finds adjacent tile with the shortest path
				for(Tile t: adjTiles){

					if(t.getLastTile()==null){
						t.setLastTile(current);
					}

					if(t.getPoint().x==0 && t.getPoint().y==0){
						//System.out.println("PATH LENGTH OF TILE t: " + t.pathLength() + "######################################");	
					}

					if(t.pathLength()<shortPath && t.pathLength() != 0){
						shortTile = t;
						shortPath = t.pathLength();
					}
				}

				//System.out.println(shortTile);
				if(current.getLastTile()==null ||(shortPath<current.pathLength() && shortTile != null)){
					current.setLastTile(shortTile);
				}
				//PF 2 END

				for(Tile t: adjacentPassabeTiles(current)){
					//if(current.point.x==8 && current.point.y==4){ System.out.println("FOUND IT1"); }
					stack.add(t);
					//System.out.println("fount it");
				}

			}
		}
		return world.getTiles()[toPoint.x][toPoint.y].getShortestPath2();
	}

	/**
	 * 
	 * @param t Tile
	 * @return ArrayList of adjacent passable tiles
	 */
	public ArrayList<Tile> adjacentPassabeTiles(Tile t){
		Tile[][] worldTiles = world.getTiles();
		ArrayList<Tile> adj = new ArrayList<Tile>();
		int x = t.getPoint().x;
		int y = t.getPoint().y;

		Point[] adjs = {
				new Point(x-1,y-1),
				new Point(x,y-1),
				new Point(x+1,y-1),
				new Point(x-1,y),
				new Point(x+1,y),
				new Point(x-1,y+1),
				new Point(x,y+1),
				new Point(x+1,y+1)};

		for(int i=0; i<8; i++){


			//if the tile is within the bounds of the map	
			if(adjs[i].x>=0 && adjs[i].y>=0   &&   adjs[i].x<worldTiles.length && adjs[i].y<worldTiles[0].length){

				//if the actor's tile is adjacent, add it
				if((adjs[i].x==actor.getX() && adjs[i].y==actor.getY()) || (adjs[i].x==toPoint.x && adjs[i].y==toPoint.y)){
					adj.add(worldTiles [adjs[i].x] [adjs[i].y] );
				}

				//if it is passable and no other actor is on the tile
				if(worldTiles[adjs[i].x][adjs[i].y].isPassable() && !worldTiles[adjs[i].x][adjs[i].y].isActorOnTile()){
					adj.add(worldTiles[adjs[i].x][adjs[i].y]);
				}
			}
		}

		return adj;
	}

}
