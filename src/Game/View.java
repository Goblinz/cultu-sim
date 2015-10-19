package Game;

import java.util.ArrayList;


public class View {
	World gameworld;
	
	private Faction currentFaction = null;
	private Tile currentTile = null;
	private ArrayList<ArrayList<Tile>> viewedTiles = new ArrayList<ArrayList<Tile>>();
	private ArrayList<ArrayList<Tile>> allTiles = new ArrayList<ArrayList<Tile>>();
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	
	public View(int sX, int sY, int eX, int eY, ArrayList<ArrayList<Tile>> gameboard){
		startX = sX;
		startY = sY;
		endX = eX;
		endY = eY;
		allTiles = gameboard'
	}
	
	public Faction getcurrentFaction(){	return currentFaction;	}
	public void setCurrentFaction(Faction f){ currentFaction = f; }
	
	//sets the tile that is currently being viewed
	public void setCurrentTile(int x, int y){ currentTile = tiles.get(x).get(y); }
	public void setCurrentTile(Tile t){ currentTile = t; }
	
	public ArrayList<ArrayList<Tile>> getViewedTiles(){ return viewedTiles; }
}
