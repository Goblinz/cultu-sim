package Game;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class SlightlyMoreComplicatedSimpleFactionAI implements FactionAI {
	
	TileType[] buildQueue = { TileType.FOREST, TileType.FERTILELAND, TileType.MINE };
	int[] numOfType = {0,0,0};
	int[] cap = {2,2,1};
	int buildQueueIndex = 0;
	int curID = 1;
	FactionAIUtil util;
	public SlightlyMoreComplicatedSimpleFactionAI(){
		util = new FactionAIUtil();
	}

	public void FactionAct(Faction self, World world,ArrayList<Faction> factions) {
		//tech
		self.techPoints++;
		for(Tech t: self.techTree.getPossibleTechs()){
			if(t.cost<=self.techPoints){
				self.techPoints = self.techTree.buyTech(t, self.techPoints);
				System.out.format("%s has researched %s granting %s\n",self.name,t.name,t.desc);
			}
		}
		TileType tileSearch = buildQueue[buildQueueIndex];
		
		if (self.resources.get("Food").getQuantity() >= 50	&& self.resources.get("Wood").getQuantity() >= 50 && numOfType[buildQueueIndex] <= cap[buildQueueIndex]) {
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
				numOfType[buildQueueIndex]++;
				MoveGather path = new MoveGather();
				Point[] points = { targetPoint, self.cityloc };
				path.setPath(points);
				temp.recieveOrder(path);
				buildQueueIndex = (buildQueueIndex + 1) % buildQueue.length;
			}
		}
		else{
			buildQueueIndex = (buildQueueIndex + 1) % buildQueue.length;
			if (self.resources.get("Food").getQuantity() >= 100
					&& self.resources.get("Wood").getQuantity() >= 55
						&& self.resources.get("Metal").getQuantity() >= 50) {
				self.resources.get("Food").addQuantity(-30);
				self.resources.get("Wood").addQuantity(-5);
				self.resources.get("Metal").addQuantity(-30);
				Point actorSpawn = util.findNearestEmpty(self.cityloc, world);
				if(actorSpawn!=null){
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

}
