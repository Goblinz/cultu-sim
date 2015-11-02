package Game;

public class Tech {
	public String name;
	public String[] preReq;
	int cost=0;
	public Tech(String techName,String[] reqs,int techCost){
		name = techName;
		preReq=reqs;
		cost = techCost;
	}
	public void onGain(){
		
	}
}
