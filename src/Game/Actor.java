package Game;

<<<<<<< HEAD
=======
import java.util.Dictionary;
import java.util.Hashtable;
>>>>>>> refs/remotes/origin/other
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;




public abstract class Actor {
	public int carryCapacity=100;
	protected int posX = 0;
	protected int posY = 0;
	public static int factionID;
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
	
    public void draw(Graphics2D g2) {
        g2.setPaint(Color.blue);
        g2.fill(rect);
        //g2.setPaint(color);
        g2.draw(rect);
    }
}
