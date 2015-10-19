package Game;
import java.util.ArrayList;


public class Game {
	public ArrayList<Resource> resources;
	private Actor[][] actors;
	private World world;
	private ArrayList<Faction> factions;
	public View generateView(){
		return null;//FILLER TODO
	}
	public void tick(){
		//TODO
		for(int i=0;i<actors.length;i++){
			for(int j=0;j<actors[i].length;j++){
				Actor tempActor = actors[i][j];
				Faction tempFaction = factions.get(tempActor.factionID);
				tempFaction.giveOrder(tempActor);
				tempActor.act(world,tempFaction,actors);
				
			}
		}
	}
}