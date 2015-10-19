package Game;
import java.util.Dictionary;


public class Faction {
	String name;
	int ID;
	FactionAI brain;
	Dictionary<String,Resource> resources;
	public Faction(int idNum,String factionName){
		ID = idNum;
		name = factionName;
	}
	public void giveOrder(Actor actor){
		//TODO
	}
}
