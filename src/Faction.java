import java.util.Dictionary;


public class Faction {
	String name;
	int ID;
	Dictionary<String,Resource> resources;
	public Faction(int idNum,String factionName){
		ID = idNum;
		name = factionName;
	}
}
