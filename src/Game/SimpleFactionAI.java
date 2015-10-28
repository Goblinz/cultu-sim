package Game;

import java.util.ArrayList;

public class SimpleFactionAI implements FactionAI {
	String[] buildQueue = {"FOREST","FERTILELAND","MINE"};
	int buildQueueIndex=0;
	public void FactionAct(Faction self, World world,ArrayList<Faction> factions) {
		String tileSearch = buildQueue[buildQueueIndex];
	}

}
