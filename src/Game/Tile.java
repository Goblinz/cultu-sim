package Game;

public abstract class Tile {
	String name;
	int x,y;
	void onMove(Actor actor){ //triggered when an actor move on to it
		
	}
	public Resource getResource(){
		return null;
	}
}
