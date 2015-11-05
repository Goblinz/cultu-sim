package Game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class World {
	
	private int DEBUG_WORLD_SIZE = 20;
	
	//beststartpos will later be determines by looking at resource areas and choosing the area with the most plentiful amount
	//of resources
	//private String bestStartPos = "5,5";
	Random noiseGen;
	ArrayList<Point> resourceNodes = new ArrayList<Point>();
	ArrayList<Point> startCoords = new ArrayList<Point>();
	Point point;
	public int buffer = 4;
	
	private Tile[][] worldTiles;
	
	//x and y are the size of the world in tiles
	public World(int x, int y){
		//basic world gen
		
		worldTiles = new Tile[x][y];
	}
	
	public World(){
		//this is the current debug world 
		//will make algorithims for better world generation later
		worldTiles = new Tile[DEBUG_WORLD_SIZE][DEBUG_WORLD_SIZE];
		for(int i = 0; i < DEBUG_WORLD_SIZE; i++){
			for(int j = 0; j < DEBUG_WORLD_SIZE;j++){
				//worldTiles[i][j] = new Tile(i,j,"ROCK",true,noiseGen.nextInt(100));
				worldTiles[i][j] = new Tile(i,j,"ROCK",true,0);
			}
		}
		worldTiles[3][4].setType("FERTILELAND");
		worldTiles[5][7].setType("FERTILELAND");
		worldTiles[13][14].setType("FERTILELAND");
		worldTiles[1][17].setType("FERTILELAND");
		worldTiles[13][16].setType("MINE");
		worldTiles[14][18].setType("MINE");
		worldTiles[3][6].setType("MINE");
		worldTiles[4][8].setType("MINE");
		worldTiles[18][14].setType("FOREST");
		worldTiles[15][11].setType("FOREST");
		worldTiles[8][4].setType("FOREST");
		worldTiles[5][1].setType("FOREST");
	}
	
	public World(long seed, int worldSizex,int worldSizey){
		noiseGen = new Random(seed);
		worldTiles = new Tile[worldSizex][worldSizey];
		for(int i = 0; i < worldSizex; i++){
			for(int j = 0; j < worldSizey;j++){
				point = new Point();
				worldTiles[i][j] = new Tile(i,j,"ROCK",true,noiseGen.nextInt(100));
				if(worldTiles[i][j].getNoise() <= 15){
					worldTiles[i][j].setType("FOREST");
					//System.out.format("bulding %d,%d \n",i,j);
					point.setLocation(i, j);
					resourceNodes.add(point);
				}
				if(worldTiles[i][j].getNoise() <= 30 && worldTiles[i][j].getNoise() >= 28){
					worldTiles[i][j].setType("MINE");
					point.setLocation(i, j);
					resourceNodes.add(point);
				}
				if(worldTiles[i][j].getNoise() <= 50 && worldTiles[i][j].getNoise() >= 45){
					worldTiles[i][j].setType("FERTILELAND");
					point.setLocation(i, j);
					resourceNodes.add(point);
				}
				if(worldTiles[i][j].getNoise() <= 80 && worldTiles[i][j].getNoise() >= 71){
					worldTiles[i][j].setType("WATER");
					worldTiles[i][j].setPassable(false);
				}
			}
		}
		createBestStartCoords();
	}
	
	public void createBestStartCoords(){
		int check = 0;
		Point start = new Point();
		for(int i = 0; i < worldTiles.length;i++){
			for(int j = 0 ; j < worldTiles[i].length; j++){
				start = new Point();
				for(Point coord : resourceNodes){
					if(coord.getX() - i < buffer && coord.getY() - j < buffer){
						start.setLocation(i, j);
						check++;
					}
				}
				if(check >= 2){
					startCoords.add(start);
					check = 0;
					start = null;
				}
				check = 0;
				start = null;
			}
		}
	}
	
	public Point getStartPos(){
		if(startCoords.size() == 0){
			System.out.println("It appears there aren't any resource tiles " + buffer + " tiles away from any point");
		}
		
		Random gen = new Random();
		return startCoords.get(gen.nextInt(startCoords.size()-1));
	}
	
	public void setBuffer(int x){
		buffer = x;
	}
	
	public Tile[][] getTiles(){ return worldTiles; }
}
