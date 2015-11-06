package Game;

import java.awt.Point;
import java.util.ArrayList;

public class SimpleFactionAI implements FactionAI {
	TileType[] buildQueue = { TileType.FOREST, TileType.FERTILELAND, TileType.MINE };
	int buildQueueIndex = 0;
	int curID = 1;

	public void FactionAct(Faction self, World world,
			ArrayList<Faction> factions) {
		TileType tileSearch = buildQueue[buildQueueIndex];
		self.techPoints++;
		//research tech if possible
		//System.out.format("looking at %s\n",self.techTree.getPossibleTechs());
		/*for(Tech t: self.techTree.getPossibleTechs()){
			if(t.cost<=self.techPoints){
				self.techPoints = self.techTree.buyTech(t, self.techPoints);
				//System.out.format("%s has researched %s granting %s\n",self.name,t.name,t.desc);
			}
		}*/
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

	private Point findNearest(TileType type, Point orgin, World world) {
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
