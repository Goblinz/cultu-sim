package Game;

public class Tech {
	public String name;
	public String[] preReq;
	int cost=0;
	boolean researched = false;
	String desc;
	public Tech(String techName,String[] reqs,int techCost,String description){
		name = techName;
		preReq=reqs;
		cost = techCost;
		desc = description;
	}
	public void onGain(){
		
	}
	public boolean isResearched(){
		return researched;
	}
	public String[] getPreReq(){return preReq;}
}
