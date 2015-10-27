package Game;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class Unit extends Actor {
	private List<Order> possibleOrders;
	private Order currentOrder;
	public Unit(int FactionID,int id){
		
		super();
		carryCapacity=30;
		factionID = FactionID;
		ID=id;
		type = ActorType.UNIT;
	}

	public boolean move(int xdir, int ydir, World world) {
		Tile target = world.getTiles()[posX + xdir][posY + ydir];
		Tile current = world.getTiles()[posX][posY];
		if (target.isActorOnTile())
			return false;
		else {
			posX = posX + xdir;
			posY = posY + ydir;
			target.onMove(this);
			current.offMove();
			return true;
		}
	}

	public List<Order> possibleOrder() {
		return possibleOrders;
	}

	public void recieveOrder(Order order) {
		currentOrder = order;
	}

	public void act(World world, Faction faction) {
		//System.out.println("unit acting");
		currentOrder.OrderAct(world, faction, this);
	}
	public void takeResource(Actor target){
		int resoucesLeftToTake = carryCapacity;
		Enumeration<Resource> i = target.resources.elements();
		Resource temp;
		while(i.hasMoreElements()){
			temp = i.nextElement();
			int moveAmount = Math.min(resoucesLeftToTake, temp.getQuantity());
			if(moveAmount!=0)
				System.out.format("Moving %d of %s from Actor at %d,%d to unit at %d,%d\n",
						moveAmount,temp.getType(),target.getX(),target.getY(),posX,posY);
			resoucesLeftToTake = resoucesLeftToTake - moveAmount;
			temp.addQuantity(-moveAmount);
			resources.get(temp.getType()).addQuantity(moveAmount);
			if(resoucesLeftToTake<=0)
				break;
		}
	}
	public void dumpResources(double effeciency,Faction faction){
		Enumeration<Resource> i = resources.elements();
		Resource temp;
		Dictionary<String,Resource> factionResouces = faction.getResources();
		while(i.hasMoreElements()){
			temp = i.nextElement();
			if(temp.getQuantity()>0)
				factionResouces.get(temp.getType()).addQuantity((int) (temp.getQuantity()*effeciency));
		}
	}
}
