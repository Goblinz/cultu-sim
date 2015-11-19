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
