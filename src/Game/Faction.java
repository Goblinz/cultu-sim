package Game;
import java.util.ArrayList;
import java.util.Dictionary;
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
	}
	public void act(World world,ArrayList<Faction> factions){
		
	}
}
