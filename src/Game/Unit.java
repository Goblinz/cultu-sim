package Game;

import java.util.List;

public class Unit extends Actor {
	private List<Order> possibleOrders;
	private Order currentOrder;
	public Unit(int FactionID,int id){
		super();
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
		
	}
}
