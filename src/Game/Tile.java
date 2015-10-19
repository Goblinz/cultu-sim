package Game;

public class Tile {
	String name;
	private String type;
	private Actor onTile = null;
	private Resource resource = null;
	private int x,y;
	
	public Tile(int X, int Y, String t){
		x=X;
		y=Y;
		type = t;
	}
	
	/**
	 * when an actor moves onto the tile
	 * @param actor
	 */
	public void onMove(Actor actor){ onTile = actor; }
	public void offMove(){ onTile == null }
	
	public Actor actorOnTile(){ return onTile; }
	
	public Resource getResource(){
		return null;
	}
	
	public int getX(){ return x; }
	public int getY(){ return y; }
	
	public Resource getResource(){ return resource; }
	public void setResource(Resource r){ resource = r; }
	
	public String getType(){ return type; }
	public void setType(String tp){
		//TODO check type
		type = tp;
	}
	
	public boolean actorOnTile(){
		if(onTile==null){
			return false;
		}
		return true;
	}
}
