package Game;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class SimpleFactionAI implements FactionAI {
	TileType[] buildQueue = { TileType.FOREST, TileType.FERTILELAND, TileType.MINE };
	int buildQueueIndex = 0;
	int curID = 1;
	int buildCounter = 0;
	FactionAIUtil util;
	public SimpleFactionAI(){
		util = new FactionAIUtil();
	}

	public void FactionAct(Faction self, World world,
			ArrayList<Faction> factions) {
		TileType tileSearch = buildQueue[buildQueueIndex];
		self.techPoints++;
		//research tech if possible
		//System.out.format("looking at %s\n",self.techTree);
		//System.out.format("looking at %s\n",self.techTree.getPossibleTechs());
		for(Tech t: self.techTree.getPossibleTechs()){
			if(t.cost<=self.techPoints){
				self.techPoints = self.techTree.buyTech(t, self.techPoints);
				System.out.format("%s has researched %s granting %s\n",self.name,t.name,t.desc);
			}
		}
		
		//spawn resources collection
		if (self.resources.get("Food").getQuantity() >= 50
				&& self.resources.get("Wood").getQuantity() >= 50 
					&& buildCounter%6!=5) {
			self.resources.get("Food").addQuantity(-50);
			self.resources.get("Wood").addQuantity(-50);
			Point targetPoint = util.findNearest(tileSearch, self.cityloc, world);
			Point actorSpawn = util.findNearestEmpty(self.cityloc, world);
			if (targetPoint != null && actorSpawn != null) {
				new Structure(self.ID, curID, targetPoint.x, targetPoint.y,
						world);
				Unit temp = new Unit(self.ID, curID, actorSpawn.x,
						actorSpawn.y, world);
				curID++;
				MoveGather path = new MoveGather();
				Point[] points = { targetPoint, self.cityloc };
				path.setPath(points);
				temp.recieveOrder(path);
				buildQueueIndex = (buildQueueIndex + 1) % buildQueue.length;
				buildCounter++;
			}
		}
		
		//spawn warriors
		if (self.resources.get("Food").getQuantity() >= 100
				&& self.resources.get("Wood").getQuantity() >= 55
					&& self.resources.get("Metal").getQuantity() >= 50) {
			self.resources.get("Food").addQuantity(-50);
			self.resources.get("Wood").addQuantity(-5);
			self.resources.get("Metal").addQuantity(-50);
			Point actorSpawn = util.findNearestEmpty(self.cityloc, world);
			if(actorSpawn!=null){
				buildCounter++;
				Unit temp = new Unit(self.ID, curID, actorSpawn.x,
						actorSpawn.y, world);
				curID++;
				temp.combatValue += 2;
				MoveCombat path = new MoveCombat();
				ArrayList<Faction> enemyFactions = new ArrayList<Faction>();
				for(Faction f: factions){
					if(f.ID != self.ID && !f.dead)
						enemyFactions.add(f);
				}
				Random rand = new Random();
				Point[] points = {enemyFactions.get(rand.nextInt(enemyFactions.size())).cityloc , self.cityloc };
				path.setPath(points);
				temp.recieveOrder(path);
				
				//change immage
				
				try {
					temp.image = ImageIO.read(getClass().getResourceAsStream("/Unit2.png"));
					temp.setColor();
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("file not found");
				}
				
			}
		}
		
	}

	
}
