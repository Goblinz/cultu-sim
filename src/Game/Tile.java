package Game;

import java.awt.Color;
import java.awt.Graphics2D;
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
	
	//nates stuff
	String name;
	private String type;
	private Actor onTile = null;
	private Resource resource = null;
	private int x,y;
	
	/*
	public Tile(int X, int Y, String t){
		x=X;
		y=Y;
		type = t;
	}
	*/
	public Tile(int r, int c, Rectangle2D.Double rect){
		row = r;
		col = c;
		this.rect = rect;
	}
	
	/**
	 * when an actor moves onto the tile
	 * @param actor
	 */
	public void onMove(Actor actor){ onTile = actor; }
	public void offMove(){ onTile = null; }
	
	public Actor actorOnTile(){ return onTile; }
	
	
	public int getX(){ return x; }
	public int getY(){ return y; }
	
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
        g2.setPaint(selected ? selColor : bgColor);
        g2.fill(rect);
        g2.setPaint(color);
        g2.draw(rect);
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public boolean isSelected() { return selected; }
}
