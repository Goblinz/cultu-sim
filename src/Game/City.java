package Game;

public class City extends Actor {
	public City(int factionid,int x,int y, World world){
		super(x,y,world,0,factionid,ActorType.CITY);
	}
}
