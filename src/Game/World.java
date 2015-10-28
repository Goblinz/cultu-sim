package Game;

import java.util.ArrayList;
import java.util.Random;

public class World {
	
	private int DEBUG_WORLD_SIZE = 10;
	
	//beststartpos will later be determines by looking at resource areas and choosing the area with the most plentiful amount
	//of resources
	private String bestStartPos = "5,5";
	Random noiseGen;
	
	
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
				worldTiles[i][j] = new Tile(i,j,"ROCK",true,noiseGen.nextInt(100));
			}
		}
		worldTiles[3][4].setType("FERTILELAND");
		worldTiles[5][7].setType("FERTILELAND");
		worldTiles[3][6].setType("MINE");
		worldTiles[4][8].setType("MINE");
		worldTiles[8][4].setType("FOREST");
		worldTiles[5][1].setType("FOREST");
	}
	
	public World(long seed, int worldSizex,int worldSizey){
		noiseGen = new Random(seed);
		worldTiles = new Tile[worldSizex][worldSizey];
		for(int i = 0; i < DEBUG_WORLD_SIZE; i++){
			for(int j = 0; j < DEBUG_WORLD_SIZE;j++){
				worldTiles[i][j] = new Tile(i,j,"ROCK",true,noiseGen.nextInt(100));
				if(worldTiles[i][j].getNoise() <= 15){
					worldTiles[i][j].setType("FOREST");
				}
				if(worldTiles[i][j].getNoise() <= 30 && worldTiles[i][j].getNoise() >= 28){
					worldTiles[i][j].setType("MINE");
				}
				if(worldTiles[i][j].getNoise() <= 50 && worldTiles[i][j].getNoise() >= 45){
					worldTiles[i][j].setType("FERTILELAND");
				}
				if(worldTiles[i][j].getNoise() <= 80 && worldTiles[i][j].getNoise() >= 71){
					worldTiles[i][j].setType("WATER");
					worldTiles[i][j].setPassable(false);
				}
			}
		}
	}
	
	public String getBestStartingPos(){
		return bestStartPos;
	}
	
	public String findResourceTiles(){
		//Made for the debug world will change to an actual algorithm later
		String derp = "Mines at 3,6 and 4,4.  Forests at 6,4 and 5,4.  Furtile Land at 5,7 and 3,4.";
		return derp;
	}
	
	public Tile[][] getTiles(){ return worldTiles; }
}
