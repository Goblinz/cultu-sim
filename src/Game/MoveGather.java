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
		if(temp!=0){
			xdir = temp / Math.abs(temp);
			xdir=0;
		}
		temp = (int) (path[pathPointer].getY() - self.getY());
		if(temp!=0){
			ydir = temp / Math.abs(temp);
			ydir=0;
			}
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
