package Game;


import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.util.List;



public abstract class Actor {
	
	public Ellipse2D.Double ellipse;
	private boolean hasActed = false;

	public int carryCapacity=100;
	protected ActorType type;
	
	public Color color;

	protected int posX = 0;
	protected int posY = 0;
	public static int factionID;
	public static int ID;
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
		Tile onTile = world.getTiles()[posX][posY];
		//onTile.
	}
	public boolean hasActed(){return hasActed;}
	public void toggleActed(){hasActed = !hasActed;}
	//fuck this
    public void draw(Graphics2D g2) {
    	//need to set ellipse
        g2.setPaint(color);
        g2.fill(ellipse);
        //g2.setPaint(color);
        g2.draw(ellipse);
    }
    public void onDie(Actor killer,World world){
    	
    }
    public String toString(){
    	StringBuilder sb = new StringBuilder();
    	sb.append("Type:"+type.toString());
    	sb.append(",\n");
    	sb.append("Faction ID:" + factionID);
    	sb.append(",\n");
    	sb.append("ID:" + ID);
    	sb.append(",\n");
    	sb.append("Resources{");
    	sb.append("\n");
    	Enumeration<Resource> i = resources.elements();
    	Resource temp;
    	while(i.hasMoreElements()){
			temp = i.nextElement();
			sb.append("   "+temp.getType() + ":"+temp.getQuantity()+"\n");
		}
    	sb.append("}");
    	return sb.toString();
    }
    public ActorType getType(){
    	return type;
    }
}
