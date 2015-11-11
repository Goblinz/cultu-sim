package Game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class MoveCombat implements Order {
	private Point[] path;
	int pathPointer = 0;
	int xdir;
	int temp;
	int ydir;
	Random rand =  new Random();
	 
	
	public void OrderAct(World world, Faction faction, Unit self) {
		
		PathFinder pf = new PathFinder();
		ArrayList<Tile> PFpath = pf.getPathToAL(world, self, path[pathPointer]);
		if(PFpath.size()>=2){
			xdir=PFpath.get(PFpath.size()-2).point.x-self.posX;
			ydir=PFpath.get(PFpath.size()-2).point.y-self.posY;
		}else{
			xdir=0;
			ydir=0;
			updatePoint();
		}

		if (xdir == 0 && ydir == 0) {
			updatePoint();
		} else {
			if (world.getTiles()[self.getX() + xdir][self.getY() + ydir].isActorOnTile()) {
				// Collision with actor
				
				// checking if colliding with destination 
				if(path[pathPointer].getX() == self.getX() + xdir && path[pathPointer].getY() == self.getY() + ydir){
					updatePoint();
				}
				
				
			} else {
				self.move(xdir, ydir, world);
				if(self.getX()==path[pathPointer].getX() && self.getY()==path[pathPointer].getY())
					updatePoint();
			}
		}
		
		//FIGHT random enemy
		if(self.adjacentEnemyActors().size() > 0){
			Tile t = self.adjacentEnemyActors().get(rand.nextInt(self.adjacentEnemyActors().size()));
			Actor winner = self.fight(self, t.actorOnTile());
			Actor loser;
			if(winner == self)
				loser = t.actorOnTile();
			else
				loser=self;
			loser.health--;
			if(loser.health<1)
				loser.onDie(winner, world);
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
