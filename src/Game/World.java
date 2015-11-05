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
				worldTiles[i][j] = new Tile(i,j,"ROCK",true,noiseGen.nextInt(100));
				if(worldTiles[i][j].getNoise() <= 15){
					worldTiles[i][j].setType("FOREST");
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
	}
	
	public ArrayList<Point> getBestStartingPos(){
		int check = 0;
		Point start = null;
		for(int i = 0; i < worldTiles.length;i++){
			for(int j = 0 ; j < worldTiles[i].length; j++){
				for(Point coord : resourceNodes){
					if(coord.getX() - i < 4 && coord.getY() - j < 4){
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
		return startCoords;
	}
	
//	public String findResourceTiles(){
//		//Made for the debug world will change to an actual algorithm later
//		String derp = "Mines at 3,6 and 4,4.  Forests at 6,4 and 5,4.  Furtile Land at 5,7 and 3,4.";
//		return derp;
//	}
	
	public Tile[][] getTiles(){ return worldTiles; }
}
