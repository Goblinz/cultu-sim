package Game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class SimpleFactionAI implements FactionAI {
	String[] buildQueue = { "FOREST", "FERTILELAND", "MINE" };
	int buildQueueIndex = 0;
	int curID = 1;
	Random rand;
	public SimpleFactionAI(){
		rand = new Random();
	}
	public void FactionAct(Faction self, World world,
			ArrayList<Faction> factions) {
		self.techPoints++;
		//research tech if possible
		//System.out.format("looking at %s\n",self.techTree.getPossibleTechs());
		for(Tech t: self.techTree.getPossibleTechs()){
			if(t.cost<=self.techPoints){
				self.techPoints = self.techTree.buyTech(t, self.techPoints);
				System.out.format("%s has researched %s granting %s\n",self.name,t.name,t.desc);
			}
		}
		String tileSearch = buildQueue[buildQueueIndex];
		if (self.resources.get("Food").getQuantity() >= 50
				&& self.resources.get("Wood").getQuantity() >= 50) {
			self.resources.get("Food").addQuantity(-50);
			self.resources.get("Wood").addQuantity(-50);
			Point targetPoint = findNearest(tileSearch, self.cityloc, world);
			Point actorSpawn = findNearestEmpty(self.cityloc, world);
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
			}
		}
	}

	private Point findNearest(String type, Point orgin, World world) {
		Point Return = null;

		for (int i = 0; i < world.getTiles().length; i++) {
			for (int j = 0; j < world.getTiles().length; j++) {
				if (world.getTiles()[i][j].getType() == type
						&& !world.getTiles()[i][j].isActorOnTile()) {
					if (Return == null)
						Return = new Point(i, j);
					else {
						if (Return.distance(orgin) > orgin.distance(i, j))
							Return = new Point(i, j);
					}
				}
			}
		}
		return Return;
	}

	private Point findNearestEmpty(Point orgin, World world) {
		Point Return = null;

		for (int i = 0; i < world.getTiles().length; i++) {
			for (int j = 0; j < world.getTiles().length; j++) {
				if (!world.getTiles()[i][j].isActorOnTile()) {
					if (Return == null)
						Return = new Point(i, j);
					else {
						if (Return.distance(orgin) > orgin.distance(i, j))
							Return = new Point(i, j);
					}
				}
			}
		}
		return Return;
	}
}
