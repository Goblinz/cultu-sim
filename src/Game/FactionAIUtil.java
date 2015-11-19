package Game;

import java.awt.Point;

public class FactionAIUtil {
	
	public Point findNearest(TileType type, Point orgin, World world) {
		Point Return = null;
		PathFinder pf =new PathFinder();
		for (int i = 0; i < world.getTiles().length; i++) {
			for (int j = 0; j < world.getTiles().length; j++) {
				if (world.getTiles()[i][j].getType() == type
						&& !world.getTiles()[i][j].isActorOnTile() && !pf.getPathToAL(world, world.getTiles()[orgin.x][orgin.y].actorOnTile(), new Point(i,j)).isEmpty()) {
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

	public Point findNearestEmpty(Point orgin, World world) {
		Point Return = null;

		for (int i = 0; i < world.getTiles().length; i++) {
			for (int j = 0; j < world.getTiles().length; j++) {
				if (!world.getTiles()[i][j].isActorOnTile() && world.getTiles()[i][j].isPassable()) {
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
	
	public boolean canSpawnBuilding(Faction self){
		return self.resources.get("Food").getQuantity() >= 50	&& self.resources.get("Wood").getQuantity() >= 50;
	}
	public boolean canSpawnWarrior(Faction self){
		return self.resources.get("Food").getQuantity() >= 100
				&& self.resources.get("Wood").getQuantity() >= 55
				&& self.resources.get("Metal").getQuantity() >= 50;
	}
	

}
