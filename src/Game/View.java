package Game;

import java.util.ArrayList;


public class View {
	World gameworld;
	
	private Faction currentFaction = null;
	private Tile currentTile = null;
	private Tile[][] viewedTiles;
	private Tile[][] allTiles;
	private int startX;
	private int startY;
	private int endX;
	private int endY;

	/**
	 * input the starting and ending coordinates to be displayed
	 * 
	 * @param sX 
	 * @param sY
	 * @param eX
	 * @param eY
	 * @param world the world object
	 */
	public View(int sX, int sY, int eX, int eY, World world){
		startX = sX;
		startY = sY;
		endX = eX;
		endY = eY;
		gameworld = world;
		allTiles = gameworld.getTiles();
		//for now
		viewedTiles = allTiles;
	}
	
	public void setEnd(int eX, int eY){
		endX = eX;
		endY = eY;
	}
	
	public void setStart(int sX, int sY){
		startX = sX;
		startY = sY;
	}
	
	
	public Faction getcurrentFaction(){	return currentFaction;	}
	public void setCurrentFaction(Faction f){ currentFaction = f; }
	
	//sets the tile that is currently being viewed
	public void setCurrentTile(int x, int y){ currentTile = allTiles[x][y]; }
	public void setCurrentTile(Tile t){ currentTile = t; }
	
	public Tile[][] getViewedTiles(){ return viewedTiles; }
}
