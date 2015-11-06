package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Tile {
	
	//matts edit
	private final int row;
	private final int col;
	Rectangle2D.Double rect;
	Color color = new Color(140,200,160);
	Color bgColor = Color.orange;
	Color selColor = Color.red;
	private boolean isPassable = false;
	private boolean selected = false;
	public Point point;

	
	//nates stuff
	String name;
	public Actor onTile = null;
	private boolean actorOnTile = false;
	private Resource resource = null;
	private int noise;
	private int temp;
	public TileType type;
	
	public enum tileSpaces{
		FOREST,ROCK,WATER,FERTILELAND,ICE,MINE
	}
	
	public Tile(int X, int Y, TileType typ, boolean passable, int num, int temperature){
		row = X;
		col = Y;
		type = typ;
		point = new Point(X,Y);
		isPassable = passable;
		noise = num;
		temp = temperature;
	}
	
	public Tile(int r, int c, Rectangle2D.Double rect, TileType resource){
		row = r;
		col = c;
		this.rect = rect;
		type = resource;
		
	}
	
	public boolean isPassable(){
		return isPassable;
	}
	
	
	/**
	 * when an actor moves onto the tile
	 * @param actor
	 */
	public void onMove(Actor actor){
		onTile = actor;
		actorOnTile = true;
	}
	
	public void offMove(){
		onTile = null;
		actorOnTile = false;
	}
	
	public Actor actorOnTile(){ return onTile; }
	
	public boolean isActorOnTile(){
		return actorOnTile;
	}
	
	public Resource getResource(){ return resource; }
	public void setResource(Resource r){ resource = r; }
	
	public TileType getType(){ 
		return type; 
	}
	
	public void setType(TileType tp){
		type = tp;
	}
	
	//matts edit
	
    public void draw(Graphics2D g2) {
        //g2.setPaint(selected ? selColor : bgColor);
        if(type == TileType.ROCK){
        	g2.setPaint(Color.black);
        }
        else if (type == TileType.FOREST){
        	g2.setPaint(Color.green);
        }
        else if(type == TileType.MINE){
        	g2.setPaint(Color.gray);
        }
        else if(type == TileType.FERTILELAND){
        	g2.setPaint(Color.magenta);
        }
        else if(type == TileType.WATER){
        	g2.setPaint(Color.BLUE);
        }
        g2.fill(rect);
        g2.setPaint(color);
        g2.draw(rect);
    }
    
    public void setRect(Rectangle2D.Double rect){
    	this.rect = rect;
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public boolean isSelected() { return selected; }
    
    public String toString() {
        return "SQUARE[row:" + row + ", col:" + col +
                    ", selected:" + selected + " type: " + type +"Actor:" + onTile + "]";
    }
    
    public Point getPoint(){
    	return point;
    }
    
    public int getNoise(){
    	return noise;
    }
    
    public boolean getPassable(){
    	return isPassable;
    }
    
    public void setPassable(boolean pass){
    	isPassable = pass;
    }
    
    public String[] getInfo(){
    	String[] ret = null;
    	if(isActorOnTile()){
    		ret = new String [5];
    		ret[0] = "Tile type : " + type;
    		ret[1] = "Row : " + row;
    		ret[2] = "Col : " + col;
    		ret[3] = "Contains : " + onTile;
    		ret[4] = "Faction ID :" + onTile.factionID;
    	}
    	else{
    		ret = new String[3];
    		ret[0] = "Tile type : " + type;
    		ret[1] = "Row:" + row;
    		ret[2] = "Col:" + col;
    	}
    	return ret;
    }
    
        //Pathfinding stuff
    ArrayList<Tile> shortestPath = null;
    
    public ArrayList<Tile> getShortestPath(){ return shortestPath; }
    
    public void setShortestPath(ArrayList<Tile> s){ shortestPath = s; }

	public int getTemp() {
		// TODO Auto-generated method stub
		return temp;
	}
	
	//Pathfinding stuff

		private Tile last = null;
		public Tile getLastTile(){	return last; }
		
		public void setLastTile(Tile t){ last = t; }
		
		public int pathLength(){
			if(this.getLastTile()==this){
				return 1;
			}
			
			if(this.getLastTile()==null){
				return 0;
			}else{
				return this.getLastTile().pathLength()+1;
			}
		}
	
	public ArrayList<Tile> getShortestPath2(){
			ArrayList<Tile> toReturn = new ArrayList<Tile>();
			Tile temp = this;
			System.out.println("LT: " + this.getLastTile());
			
			for(int i=0; i<this.pathLength(); i++){
				toReturn.add(temp);
				temp = temp.getLastTile();
			}
			
			return toReturn;
			
		}
	}

