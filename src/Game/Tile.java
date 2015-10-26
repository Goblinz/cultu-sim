package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Tile {
	
	//matts edit
	private final int row;
	private final int col;
	Rectangle2D.Double rect;
	Color color = new Color(140,200,160);
	Color bgColor = Color.orange;
	Color selColor = Color.red;
	private boolean selected = false;
	public Point point;
	
	//nates stuff
	String name;
	private String type;
	public Actor onTile = null;
	private Resource resource = null;
	//private int x,y;
	
	public Tile(int X, int Y, String t){
		row = X;
		col = Y;
		//x=X;
		//y=Y;
		type = t;
		point = new Point(X,Y);
	}
	
	public Tile(int r, int c, Rectangle2D.Double rect, String resource){
		row = r;
		col = c;
		this.rect = rect;
		type = resource;
		
	}
	
	/**
	 * when an actor moves onto the tile
	 * @param actor
	 */
	public void onMove(Actor actor){ onTile = actor; }
	public void offMove(){ onTile = null; }
	
	public Actor actorOnTile(){ return onTile; }
	
	
	public Resource getResource(){ return resource; }
	public void setResource(Resource r){ resource = r; }
	
	public String getType(){ return type; }
	public void setType(String tp){
		//TODO check type
		type = tp;
	}
	
	public boolean isActorOnTile(){
		if(onTile==null){
			return false;
		}
		return true;
	}
	
	//matts edit
	
    public void draw(Graphics2D g2) {
        //g2.setPaint(selected ? selColor : bgColor);
        if(type == "ROCK"){
        	g2.setPaint(Color.black);
        }
        else if (type == "FOREST"){
        	g2.setPaint(Color.green);
        }
        else if(type == "MINE"){
        	g2.setPaint(Color.gray);
        }
        else if(type == "FERTILELAND"){
        	g2.setPaint(Color.magenta);
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
                    ", selected:" + selected + " type: " + type +"]";
    }
    
    public Point getPoint(){
    	return point;
    }
}
