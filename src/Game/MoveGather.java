package Game;

import java.awt.Point;

public class MoveGather implements Order {
	private Point[] path;
	int pathPointer=0;
	int xdir;
	int temp;
	int ydir;
	public void OrderAct(World world, Faction faction, Unit self) {
		temp = (int) (path[pathPointer].getX()-self.getX());
		xdir = temp/Math.abs(temp);
		temp = (int) (path[pathPointer].getY()-self.getY());
		ydir = temp/Math.abs(temp);
		if(world.getTiles()[self.getX()+xdir][self.getY()+ydir].isActorOnTile()){
			//collison with actor
		}
		else{
			
		}
	}

}
