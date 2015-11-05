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
	public int factionID;
	public int ID;
	World world;
	
	public Dictionary<String,Resource> resources;
	
	public Actor(int x,int y,World world,int id,int factionid, ActorType actorType){
		this.world = world;
		type=actorType;
		ID=id;
		factionID=factionid;
		resources = new Hashtable<String,Resource>();
		Resource temp = new Resource("Food",0);
		resources.put(temp.getType(), temp);
		temp = new Resource("Metal",0);
		resources.put(temp.getType(), temp);
		temp = new Resource("Wood",0);
		resources.put(temp.getType(), temp);
		posX=x;
		posY=y;
		//System.out.format("putting actor at %d,%d with faction ID of %d\n",x,y,factionID);
		world.getTiles()[x][y].onMove(this);
		//color = Color.CYAN;
	}
	public int getX(){ return posX; }
	
	public int getY(){ return posY; }
	
	public void setX(int x){ posX = x; }
	
	public void setY(int y){ posY = y; }
	
	public void act(World world,Faction faction){
		//Tile onTile = world.getTiles()[posX][posY];
		//onTile.
	}
	public boolean hasActed(){return hasActed;}
	public void toggleActed(){hasActed = !hasActed;}
	//fuck this
    public void draw(Graphics2D g2) {
    	if(factionID==0)
    		g2.setPaint(Color.RED);
    	else if(factionID==1)
    		g2.setPaint(new Color(200,50,50));
    	else if(factionID==2)
    		g2.setPaint(new Color(150,150,50));
    	else if(factionID==3)
    		g2.setPaint(new Color(50,150,50));
    	else if(factionID==4)
    		g2.setPaint(new Color(150,50,150));
    	
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
