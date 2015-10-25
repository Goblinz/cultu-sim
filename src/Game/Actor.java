package Game;

import java.util.List;


public abstract class Actor {
	
	protected int posX = 0;
	protected int posY = 0;
	public static int factionID;
	//public Dictionary  
	public int getX(){ return posX; }
	
	public int getY(){ return posY; }
	
	public void setX(int x){ posX = x; }
	
	public void setY(int y){ posY = y; }
	
	public void act(World world,Faction faction){
		
	}
	
	
}
