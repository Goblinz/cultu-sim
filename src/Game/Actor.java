package Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.util.List;



public abstract class Actor {
	
	Ellipse2D.Double ellipse;
	
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
	
    public void draw(Graphics2D g2) {
    	//need to set ellipse
        ellipse = new Ellipse2D.Double(posX, posY, 40, 40);
        g2.setPaint(Color.blue);
        g2.fill(ellipse);
        //g2.setPaint(color);
        g2.draw(ellipse);
    }
}
