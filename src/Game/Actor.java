package Game;

import java.util.List;


public abstract class Actor {
	private List<Order> possibleOrders;
	private Order currentOrder;
	private int posX = 0;
	private int posY = 0;
	public static int factionID;
	public int getX(){ return posX; }
	
	public int getY(){ return posY; }
	
	public void setX(int x){ posX = x; }
	
	public void setY(int y){ posY = y; }
	public void recieveOrder(Order order){
		currentOrder = order;
	}
	public void act(World world,Faction faction,Actor[][] actors){
		
	}
	public List<Order> PossibleOrder(){
		return possibleOrders;
	}
	
}