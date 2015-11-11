package Game;


import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public abstract class Actor {
	
	public Ellipse2D.Double ellipse;
	private boolean hasActed = false;
	int health = 1;
	public int carryCapacity=100;
	protected ActorType type;
	
	public Color color;
	public BufferedImage image;
	private int xInc;
	private int yInc;
	private int x;
	private int y;

	protected int posX = 0;
	protected int posY = 0;
	public int factionID;
	public int ID;
	World world;
	
	public Dictionary<String,Resource> resources;
	
	public Actor(int x,int y,World world,int id,int factionid, ActorType actorType){
		this.world = world;
		type=actorType;
		ID=id;
		factionID=factionid;
		resources = new Hashtable<String,Resource>();
		Resource temp = new Resource("Food",0);
		resources.put(temp.getType(), temp);
		temp = new Resource("Metal",0);
		resources.put(temp.getType(), temp);
		temp = new Resource("Wood",0);
		resources.put(temp.getType(), temp);
		color = new Color((100 * (factionID + 5)) % 255, (5 * (factionID + 5)) % 255, (80 * (factionID + 5)) % 255);
		posX=x;
		posY=y;
		//System.out.format("putting actor at %d,%d with faction ID of %d\n",x,y,factionID);
		world.getTiles()[x][y].onMove(this);
		if(actorType == actorType.CITY){
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/City.png"));
				setColor();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("file not found");
			}
		}
		if(actorType == actorType.UNIT){
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/Unit.png"));
				setColor();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("file not found");
			}
		}
		if(actorType == actorType.STRUCTURE){
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/Structure.png"));
				setColor();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("file not found");
			}
		}
	}
	
	public void setColor(){
		
		int width = image.getWidth();
		int height = image.getHeight();
		//loop through all pixels in the image
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				int p = image.getRGB(j, i);
				//if the pixel is white set the new color
				if(p == -1){
					image.setRGB(j, i, color.getRGB());
				}
			}
		}
	}
	
	public void setDimensions(double x, double y, double xInc, double yInc){
		this.x = (int)x;
		this.y = (int)y;
		this.xInc = (int)xInc;
		this.yInc = (int)yInc;
	}
	
	public int getX(){ return posX; }
	
	public int getY(){ return posY; }
	
	public void setX(int x){ posX = x; }
	
	public void setY(int y){ posY = y; }
	
	public void act(World world,Faction faction){
		//Tile onTile = world.getTiles()[posX][posY];
		//onTile.
	}
	public boolean hasActed(){return hasActed;}
	public void toggleActed(){hasActed = !hasActed;}
	//fuck this
    public void draw(Graphics2D g2) {
    	/*
    	if(factionID==1)
    		g2.setPaint(Color.RED);
    	else
    		g2.setPaint(Color.CYAN);
    	*/
    	//need to set ellipse
        //g2.setPaint(color);
        //g2.fill(ellipse);
        //g2.setPaint(color);
        //g2.draw(ellipse);
    	g2.drawImage(image, x, y, xInc, yInc, null);
    }
    public String toString(){
    	StringBuilder sb = new StringBuilder();
    	sb.append("Type:"+type.toString());
    	sb.append(",\n");
    	sb.append("Faction ID:" + factionID);
    	sb.append(",\n");
    	sb.append("ID:" + ID);
    	sb.append(",\n");
    	sb.append("Resources{");
    	sb.append("\n");
    	Enumeration<Resource> i = resources.elements();
    	Resource temp;
    	while(i.hasMoreElements()){
			temp = i.nextElement();
			sb.append("   "+temp.getType() + ":"+temp.getQuantity()+"\n");
		}
    	sb.append("}");
    	return sb.toString();
    }
    public ActorType getType(){
    	return type;
    }
    
    //COMBAT STUFF
	int combatValue = 5;

/**
	 * 
	 * @return the combat value of the unit
	 */
	public int getCombatValue(){ return combatValue; }	
	
	/**
	 * sets the combat value of the unit
	 * @param combat int combat value
	 */
	public void setCombatValue(int combat){ combatValue = combat; } 

	/**
	 * 
	 * @param killer
	 * @param world
	 */
	public void onDie(Actor killer, World world){
		//TODO
		System.out.println("AHHHRGGG actor at (" + killer.getX()+ ", " + killer.getY() + ") killed me! my pos: (" + posX + ", " + posY + ")" );
		world.getTiles()[posX][posY].offMove(); //setting tile to null
		
		//TODO give resources to killing faction
		
	}
	
	public static void combat(World w){
		Tile[][] worldTiles = w.getTiles();
		ArrayList<Actor> fights = new ArrayList<Actor>();

		//for each tile
		for(int x=0; x<worldTiles.length; x++){
			for(int y=0; y<worldTiles[0].length; y++){
				//if the tile has a unit and that actor isn't already in a fight
				if(worldTiles[x][y].isActorOnTile() && !fights.contains(worldTiles[x][y].actorOnTile())){
					//if there is an adjacent enemy actor
					ArrayList<Tile> adjEnemyTiles = worldTiles[x][y].actorOnTile().adjacentEnemyActors();
					if(adjEnemyTiles != null){

						//removes tiles with adjacent enemy actors are already in a fight
						//for(Tile t: adjEnemyTiles){
						for(int i=0; i<adjEnemyTiles.size(); i++){
							if(fights.contains(adjEnemyTiles.get(i).actorOnTile())){
								adjEnemyTiles.remove(adjEnemyTiles.get(i));
							}
						}

						//if tiles are left in adjEnemyTiles
						if(adjEnemyTiles.size()>=1){
							fights.add(worldTiles[x][y].actorOnTile());
							fights.add(adjEnemyTiles.get(0).actorOnTile());
						}
					}
				}

			}
		}

		ArrayList<Actor> losers = new ArrayList<Actor>();
		ArrayList<Actor> winners = new ArrayList<Actor>();
		for(int i=0; i<fights.size()-1; i++){
			Actor winner = fight(fights.get(i), fights.get(i+1));
			Actor loser;
			if(winner == fights.get(i)){
				loser = fights.get(i+1);
			}else{
				loser = fights.get(i);
			}
			losers.add(loser);
			winners.add(winner);
		}

		for(int i=0; i<winners.size(); i++){
			losers.get(i).onDie(winners.get(i), w);
		}


	}

	/**
	 * 
	 * @param a1 actor 1
	 * @param a2 actor 2
	 * @return the winn
	 */
	public static Actor fight(Actor a1, Actor a2){

		double a1c = Math.random()+5;
		double a2c = Math.random()+5;

		if(a1.getCombatValue()*a1c>a2.getCombatValue()*a2c){
			return a1;
		}
		return a2;
	}

	public ArrayList<Tile> adjacentEnemyActors(){
		Tile[][] worldTiles = world.getTiles();
		ArrayList<Tile> enemies = new ArrayList<Tile>();
		int x = posX;
		int y = posY;

		Point[] adjs = {
				new Point(x-1,y-1),
				new Point(x,y-1),
				new Point(x+1,y-1),
				new Point(x-1,y),
				new Point(x+1,y),
				new Point(x-1,y+1),
				new Point(x,y+1),
				new Point(x+1,y+1)};

		for(int i=0; i<8; i++){
			if(adjs[i].x>=0 && adjs[i].y>=0   &&   adjs[i].x<worldTiles.length && adjs[i].y<worldTiles[0].length){
				if(worldTiles[adjs[i].x][adjs[i].y].isActorOnTile() && worldTiles[adjs[i].x][adjs[i].y].actorOnTile().factionID != factionID ){
					enemies.add(worldTiles[adjs[i].x][adjs[i].y]);
				}
			}
		}

		return enemies;
	}
}
