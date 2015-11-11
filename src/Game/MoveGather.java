package Game;

import java.awt.Point;
import java.util.ArrayList;

public class MoveGather implements Order {
	private Point[] path;
	int pathPointer = 0;
	int xdir;
	int temp;
	int ydir;

	public void OrderAct(World world, Faction faction, Unit self) {

		//TEST Checks actors on tile
		/**
		for(int x=0; x<world.getTiles().length; x++){
			for(int y=0; y<world.getTiles()[0].length; y++){
				String a;
				if(world.getTiles()[x][y].isActorOnTile()){
					a="X";
				}else{
					a=" ";
				}

				System.out.print("["+a+"]");
			}
			System.out.println();
		}
		*/


		PathFinder pf = new PathFinder();
		ArrayList<Tile> PFpath = pf.getPathToAL(world, self, path[pathPointer]);
		//System.out.println("ADJ PATH LENGTH: " + PFpath2.size() + ". (8,4) actor on:" + world.getTiles()[4][8].isActorOnTile());

		//System.out.println("(" + self.posX + "," + self.posY + ") Path Length: " + PFpath.size() + " Trying to get to point: " + path[pathPointer]);

		if(PFpath.size()>=2){
			xdir=PFpath.get(PFpath.size()-2).point.x-self.posX;
			ydir=PFpath.get(PFpath.size()-2).point.y-self.posY;
			//System.out.println("we are moving");
		}else{
			xdir=0;
			ydir=0;
			updatePoint();
			//System.out.println("halp we are stuck");
		}

		//System.out.format("actor trying to move in %d,%d heading twoards %s\n",xdir,ydir,path[pathPointer].toString());
		if (xdir == 0 && ydir == 0) {
			//System.out.println("updating move position");
			updatePoint();
		} else {
			if (world.getTiles()[self.getX() + xdir][self.getY() + ydir]
					.isActorOnTile()) {
				// Collision with actor

				// checking if colliding with destination 
				if(path[pathPointer].getX() == self.getX() + xdir && path[pathPointer].getY() == self.getY() + ydir){
					//transfer resources
					if(world.getTiles()[self.getX() + xdir][self.getY() + ydir].actorOnTile().getType() == ActorType.CITY)
						self.dumpResources(1, faction);
					else
						self.takeResource(world.getTiles()[self.getX() + xdir][self.getY() + ydir].actorOnTile());

					updatePoint();
				}
			} else {
				self.move(xdir, ydir, world);
				if(self.getX()==path[pathPointer].getX() && self.getY()==path[pathPointer].getY())
					updatePoint();
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
