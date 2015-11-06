package Game;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.Point;

public class Faction {
	Color factionColor;
	String name;
	TechTree techTree;
	int ID;
	int techPoints=0;
	FactionAI brain;
	Dictionary<String,Resource> resources;
	Point cityloc;
	public Faction(int idNum,String factionName,FactionAI type){
		ID = idNum;
		name = factionName;
		brain = type;
		techTree = new TechTree();
		resources = new Hashtable<String,Resource>();
		Resource temp = new Resource("Food",100);
		resources.put(temp.getType(), temp);
		temp = new Resource("Metal",0);
		resources.put(temp.getType(), temp);
		temp = new Resource("Wood",100);
		resources.put(temp.getType(), temp);
	}
	public void act(World world,ArrayList<Faction> factions){
		//spawn stuff in
		System.out.println("faction " + ID + "acting");
		brain.FactionAct(this, world, factions);
	}
	public Dictionary<String,Resource> getResources(){
		return resources;
	}
	public void setCityLocation(Point loc){
		cityloc=loc;
	}
	public String toString(){
		return name + " " + ID;
	}
}
