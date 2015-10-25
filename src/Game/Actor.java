package Game;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;


public abstract class Actor {
	
	protected int posX = 0;
	protected int posY = 0;
	public static int factionID;
	public Dictionary<String,Resource> resources;
	public Actor(){
		resources = new Hashtable<String,Resource>();
		Resource temp = new Resource("Food",0);
		resources.put(temp.getType(), temp);
		temp = new Resource("Metal",0);
		resources.put(temp.getType(), temp);
		temp = new Resource("Wood",0);
		resources.put(temp.getType(), temp);
	}
	public int getX(){ return posX; }
	
	public int getY(){ return posY; }
	
	public void setX(int x){ posX = x; }
	
	public void setY(int y){ posY = y; }
	
	public void act(World world,Faction faction){
		
	}
	
	
}
