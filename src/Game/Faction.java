package Game;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.awt.Color;

public class Faction {
	Color factionColor;
	String name;
	int ID;
	FactionAI brain;
	Dictionary<String,Resource> resources;
	public Faction(int idNum,String factionName,FactionAI type){
		ID = idNum;
		name = factionName;
		brain = type;
		
		resources = new Hashtable<String,Resource>();
		Resource temp = new Resource("Food",0);
		resources.put(temp.getType(), temp);
		temp = new Resource("Metal",0);
		resources.put(temp.getType(), temp);
		temp = new Resource("Wood",0);
		resources.put(temp.getType(), temp);
	}
	public void act(World world,ArrayList<Faction> factions){
		
	}
	public Dictionary<String,Resource> getResources(){
		return resources;
	}
}
