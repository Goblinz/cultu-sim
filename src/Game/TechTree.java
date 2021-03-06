package Game;

import java.util.ArrayList;
import java.util.Dictionary;

public class TechTree {
	ArrayList<Tech> techs;
	Dictionary<String,Integer> rescourceBonusRate;
	int carryCapacity=30;
	public TechTree(){
		String[] temparray={};
		techs = new ArrayList<Tech>();
		Tech temp = new Tech("Grain Storage",temparray,10,"Improves food gather rate");
		techs.add(temp);
		temp = new Tech("Axes",temparray,10,"Improves wood gather rate");
		techs.add(temp);
		temp = new Tech("Shaft Mining",temparray,10,"Improves metal gather rate");
		techs.add(temp);
		temparray = new String[]{"Grain Storage"};
		temp = new Tech("Pottery",temparray,20,"Increases carry capicity");
		techs.add(temp);
		temparray = new String[]{"Shaft Mining"};
		temp = new Tech("Forging",temparray,20,"Increases melee Combat damage");
		techs.add(temp);
		temparray = new String[]{"Axes"};
		temp = new Tech("Broad Shields",temparray,20,"Increases melee defense");
		techs.add(temp);
	}
	
	public ArrayList<Tech> getPossibleTechs(){
		ArrayList<Tech> possibleTechs = new ArrayList<Tech>();
		for(Tech t:techs){
			boolean metReq = true;
			for(String reqs:t.getPreReq()){
				boolean foundMatch=false;
				for(Tech match:techs){
					if(match.name==reqs && match.isResearched())
						foundMatch = true;
				}
				if(!foundMatch)
					metReq = false;
			}
			if(metReq && !t.researched)
				possibleTechs.add(t);
		}
		return possibleTechs;
	}
	public ArrayList<Tech> getTechList(){
		return techs;
	}
	public ArrayList<Tech> getPathTo(Tech target){
		return techs;
	}
	public int buyTech(Tech target,int techPoints){
		if(techPoints>= target.cost){
			target.researched=true;
			techPoints = techPoints - target.cost;
		}
		return techPoints;
	}
	public boolean hasTech(String Name){
		boolean hastech=false;
		for(Tech t: techs){
			if(t.name==Name && t.isResearched())
				hastech=true;
		}
		return hastech;
	}
}