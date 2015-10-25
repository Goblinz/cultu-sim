package Game;

import java.awt.Point;

public class MoveGather implements Order {
	private Point[] path;
	int pathPointer = 0;
	int xdir;
	int temp;
	int ydir;

	public void OrderAct(World world, Faction faction, Unit self) {
		temp = (int) (path[pathPointer].getX() - self.getX());
		xdir = temp / Math.abs(temp);
		temp = (int) (path[pathPointer].getY() - self.getY());
		ydir = temp / Math.abs(temp);
		if (xdir == 0 && ydir == 0) {
			updatePoint();
		} else {
			if (world.getTiles()[self.getX() + xdir][self.getY() + ydir]
					.isActorOnTile()) {
				// Collision with actor

				// checking if colliding with destination 
				if(path[pathPointer].getX() == self.getX() + xdir && path[pathPointer].getY() == self.getY() + ydir){
					//transfer resources
					updatePoint();
				}
			} else {
				self.move(xdir, ydir, world);
			}
		}
	}
	public void setPath(Point[] newPath){
		path = newPath;
	}
	private void updatePoint() {
		pathPointer++;
		if (pathPointer == path.length)
			pathPointer = 0;
	}

}
