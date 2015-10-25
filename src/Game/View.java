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
	 * input the world to be displayed
	 * default view is the entire map
	 * 
	 * @param world the world object
	 */
	public View(World world){
		gameworld = world;
		allTiles = gameworld.getTiles();
		viewedTiles = allTiles;
	}
	
	public void setView(int sX, int sY, int eX, int eY) throws Exception{
		
		if(sX>eX || sY>eY || sX<0 || sY<0 || eX>allTiles.length || eY>allTiles[0].length){
			throw new Exception("coordinates are invalid");
		}
		
		endX = eX;
		endY = eY;
		startX = sX;
		startY = sY;

		Tile[][] tempTileArray = new Tile[eX-sX+1][eY-sY+1];
		for(int x=sX; x<=eX; x++){
			for(int y=sY; y<=eY; y++){
				tempTileArray[x-sX][y-sY] = allTiles[x][y];
			}
		}
		viewedTiles = tempTileArray;
	}
	
	
	public Faction getcurrentFaction(){	return currentFaction;	}
	public void setCurrentFaction(Faction f){ currentFaction = f; }
	
	//sets the tile that is currently being viewed
	public void setCurrentTile(int x, int y){ currentTile = allTiles[x][y]; }
	public void setCurrentTile(Tile t){ currentTile = t; }
	public Tile getCurrentTile(){ return currentTile; }
	
	public Tile[][] getViewedTiles(){ return viewedTiles; }

}
