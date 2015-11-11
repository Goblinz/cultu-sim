package Game;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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

	public World(int x, int y){
		//basic world gen

		worldTiles = new Tile[x][y];
		noiseGen = new Random();

		for(int i = 0; i < DEBUG_WORLD_SIZE; i++){
			for(int j = 0; j < DEBUG_WORLD_SIZE;j++){
				worldTiles[i][j] = new Tile(i,j,TileType.ROCK,true,noiseGen.nextInt(100),(i*4 + noiseGen.nextInt(20)));

				if(worldTiles[i][j].getTemp() < 18 && worldTiles[i][j].getNoise() < 20 ){
					worldTiles[i][j].setType(TileType.ROCK);
				}
				if(worldTiles[i][j].getTemp() < 28 && worldTiles[i][j].getNoise() > 60){
					worldTiles[i][j].setType(TileType.MINE);
				}
				if(worldTiles[i][j].getTemp() >=29){
					int num = noiseGen.nextInt(100);
					if(num <= 25){
						worldTiles[i][j].setType(TileType.FOREST);
					}
					if(num >= 25 && num <= 50){
						worldTiles[i][j].setType(TileType.FERTILELAND);
					}
					if(num >= 51 && num <= 79){
						worldTiles[i][j].setType(TileType.ICE);
					}
				}
			}
		}
	}

	public World(){
		//this is the current debug world 
		//will make algorithims for better world generation later
		worldTiles = new Tile[DEBUG_WORLD_SIZE][DEBUG_WORLD_SIZE];
		for(int i = 0; i < DEBUG_WORLD_SIZE; i++){
			for(int j = 0; j < DEBUG_WORLD_SIZE;j++){
				//worldTiles[i][j] = new Tile(i,j,"ROCK",true,noiseGen.nextInt(100));
				worldTiles[i][j] = new Tile(i,j,TileType.ROCK,true,0,0);
			}
		}
		worldTiles[3][4].setType(TileType.FERTILELAND);
		worldTiles[5][7].setType(TileType.FERTILELAND);
		worldTiles[13][14].setType(TileType.FERTILELAND);
		worldTiles[1][17].setType(TileType.FERTILELAND);
		worldTiles[13][16].setType(TileType.MINE);
		worldTiles[14][18].setType(TileType.MINE);
		worldTiles[3][6].setType(TileType.MINE);
		worldTiles[4][8].setType(TileType.MINE);
		worldTiles[18][14].setType(TileType.FOREST);
		worldTiles[15][11].setType(TileType.FOREST);
		worldTiles[8][4].setType(TileType.FOREST);
		worldTiles[5][1].setType(TileType.FOREST);
	}

	public World(String worldName) throws FileNotFoundException{
		Scanner scanner = new Scanner(new File("src/Worlds/" + worldName));

		scanner.useDelimiter(",");

		int count = 0;

		int x = scanner.nextInt();
		int y = scanner.nextInt();

		System.out.println("rows: " + x + " columns: " + y);

		worldTiles = new Tile[x][y];
		scanner.nextLine();
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y;j++){
				String foo = "";
				if(scanner.hasNext() == true){
					foo = scanner.next();
					System.out.println(foo);
					if (foo.compareTo("r") == 0){
						count++;
						System.out.format("adding to %d,%d\n",i,j);
						worldTiles[i][j] = new Tile(i,j,TileType.ROCK,true,0,0);
					}
					if (foo.compareTo("f") == 0){
						count++;
						System.out.format("adding to %d,%d\n",i,j);
						worldTiles[i][j] = new Tile(i,j,TileType.FOREST,true,0,0);
					}
					if (foo.compareTo("fl") == 0){
						count++;
						System.out.format("adding to %d,%d\n",i,j);
						worldTiles[i][j] = new Tile(i,j,TileType.FERTILELAND,true,0,0);
					}
					if (foo.compareTo("w") == 0){
						count++;
						System.out.format("adding to %d,%d\n",i,j);
						worldTiles[i][j] = new Tile(i,j,TileType.WATER,true,0,0);
					}
					if (foo.compareTo("m") == 0){
						count++;
						System.out.format("adding to %d,%d\n",i,j);
						worldTiles[i][j] = new Tile(i,j,TileType.MINE,true,0,0);
					}
					if (foo.compareTo("i") == 0){
						count++;
						System.out.format("adding to %d,%d\n",i,j);
						worldTiles[i][j] = new Tile(i,j,TileType.ICE,true,0,0);
					}
				}
			}
			if(scanner.hasNextLine() == true){
				scanner.nextLine();
			}
		}
		for(int i=0;i<20;i++){
			for(int j=0;j<20;j++)
				System.out.format("%s",worldTiles[i][j].getType());
			System.out.println();
		}
		System.out.println(count);
	}

	public World(long seed, int worldSizex,int worldSizey){
		noiseGen = new Random(seed);
		worldTiles = new Tile[worldSizex][worldSizey];
		for(int i = 0; i < worldSizex; i++){
			for(int j = 0; j < worldSizey;j++){
				point = new Point();
				worldTiles[i][j] = new Tile(i,j,TileType.ROCK,true,noiseGen.nextInt(100),0);
				if(worldTiles[i][j].getNoise() <= 15){
					worldTiles[i][j].setType(TileType.FOREST);
					point.setLocation(i, j);
					resourceNodes.add(point);
				}
				if(worldTiles[i][j].getNoise() <= 30 && worldTiles[i][j].getNoise() >= 28){
					worldTiles[i][j].setType(TileType.MINE);
					point.setLocation(i, j);
					resourceNodes.add(point);
				}
				if(worldTiles[i][j].getNoise() <= 50 && worldTiles[i][j].getNoise() >= 45){
					worldTiles[i][j].setType(TileType.FERTILELAND);
					point.setLocation(i, j);
					resourceNodes.add(point);
				}
				if(worldTiles[i][j].getNoise() <= 80 && worldTiles[i][j].getNoise() >= 71){
					worldTiles[i][j].setType(TileType.WATER);
					worldTiles[i][j].setPassable(false);
				}
			}
		}
		createBestStartCoords();
	}

	public void createBestStartCoords(){
		int check = 0;
		Point start = null;
		for(int i = 0; i < worldTiles.length;i++){
			for(int j = 0 ; j < worldTiles[i].length; j++){
				for(Point coord : resourceNodes){
					start = new Point();
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
		createBestStartCoords();
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