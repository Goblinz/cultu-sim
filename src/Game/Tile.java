package Game;

public abstract class Tile {
	private String type;
	private Actor onTile = null;
	String name;
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
	public void onMove(Actor actor){
		onTile = actor;
	}
	
	public Actor actorOnTile(){ return onTile; }
	
	public Resource getResource(){
		return null;
	}
	
	public int getX(){ return x; }
	public int getY(){ return y; }
	
	public String getType(){ return type; }
	public void setType(String tp){
		//TODO check type
		type = tp;
	}
}
