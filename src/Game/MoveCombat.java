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
	Actor target = null; 
	
	public void OrderAct(World world, Faction faction, Unit self) {
		
		PathFinder pf = new PathFinder();
		ArrayList<Tile> PFpath = pf.getPathToAL(world, self, path[pathPointer]);
		//select ememy actor target
		if(target == null || target.dead){
			target = findEnemy(world,ActorType.UNIT,3,new Point(self.posX,self.posY),self.factionID);
			if(target == null){
				target = findEnemy(world,ActorType.CITY,3,new Point(self.posX,self.posY),self.factionID);
			}
		}
		if(target == null){
			//move twoards path
			PFpath = pf.getPathToAL(world, self, path[pathPointer]);
		}
		else{
			//move twords actor
			PFpath = pf.getPathToAL(world, self, new Point(target.posX,target.posY));
		}
		
		if(PFpath.size()<2){
			if(target == null  && path[pathPointer].distance(self.posX, self.posY) < 3)
				updatePoint();
		}
		else{
			xdir=PFpath.get(PFpath.size()-2).point.x-self.posX;
			ydir=PFpath.get(PFpath.size()-2).point.y-self.posY;
			if(world.getTiles()[self.posX+xdir][self.posY+ydir].isActorOnTile()){
				//colision
				updatePoint();
			}
			//else if(xdir==0 && ydir==0)
			//	updatePoint();
			else{
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
	private Actor findEnemy(World world,ActorType type,int distance,Point loc,int factionID){
		Actor nearest = null;
		int dist = 9999999;
		for(int i=-distance;i<distance;i++){
			for (int j=-distance;j<distance;j++){
				int x = loc.x+i;
				int y = loc.y+j;
				if(y>0 && y< world.getTiles()[0].length && x>0 && x< world.getTiles().length){
					if(world.getTiles()[x][y].isActorOnTile() && world.getTiles()[x][y].actorOnTile().factionID!=factionID && world.getTiles()[x][y].actorOnTile().type == type){
						if(loc.distance(x, y) < dist){
							dist = (int) loc.distance(x, y);
							nearest = world.getTiles()[x][y].actorOnTile();
						}
					}
				}
				
			}
		}
		
		return nearest;
	}

}
