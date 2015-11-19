package Game;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;

public class GameView extends JPanel implements Runnable{
	Tile[][] tiles;
	int PAD = 5;
	int ROWS = 20;
	int COLS = 20;
	private boolean start = true;
	public JFrame mainWindow;
	private Game game;
	public boolean pause = false;
	public ButtonGroup placements;
	
	private int w;
	private int h;
	double xInc;
	double yInc;
	
	private static ArrayList<JLabel> labels = new ArrayList<JLabel>();
	
	public GameView(){
		addMouseListener(ml);
	}
	
	public GameView(Tile[][] tiles, Game game){
		addMouseListener(ml);
		this.tiles = tiles;
		this.game = game;
		//initTiles();
	}
	
	public void setTilesAndGame(Tile[][] tiles, Game game){
		this.tiles = tiles;
		this.game = game;
	}
	
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		/*
		if(tiles == null){
			initTiles();
		}
		*/
		if(start){
			initTiles();
			start = false;
		}
		//Draw tiles
		g2.setPaint(Color.blue);
		for(int i = 0; i < ROWS; i++){
			for(int j = 0; j < COLS; j++){
				
				//System.out.println(tiles);
				double y = PAD + i * yInc;
				double x = PAD + j * xInc;
				tiles[i][j].setDimensions(x, y, xInc, yInc);
				tiles[i][j].draw(g2);
				if(tiles[i][j].onTile != null){
					//Draw actor on tile

					
					/*
					Ellipse2D.Double ellipse = new Ellipse2D.Double(x, y, xInc, yInc);
					tiles[i][j].onTile.ellipse = ellipse;
					*/
					tiles[i][j].onTile.setDimensions(x, y, xInc, yInc);
					tiles[i][j].onTile.draw(g2);
				}
			}
		}
	}
	
	private void initTiles(){
		
		/*
		tiles = new Tile[ROWS][COLS];
		int w = getWidth();
		int h = getHeight();
		double xInc = (double)(w - 2 * PAD)/ COLS;
		double yInc = (double)(h - 2 * PAD)/ ROWS;
        for(int i = 0; i < ROWS; i++) {
            double y = PAD + i*yInc;
            for(int j = 0; j < COLS; j++) {
                double x = PAD + j*xInc;
                Rectangle2D.Double r =
                    new Rectangle2D.Double(x, y, xInc, yInc);
                tiles[i][j] = new Tile(i, j, r,"ROCK");
            }
        }
        */
		
		
		w = getWidth();
		h = getHeight();
		xInc = (double)(w - 2 * PAD)/ COLS;
		yInc = (double)(h - 2 * PAD)/ ROWS;
        for(int i = 0; i < ROWS; i++) {
            double y = PAD + i*yInc;
            for(int j = 0; j < COLS; j++) {
                double x = PAD + j*xInc;
                Rectangle2D.Double r = new Rectangle2D.Double(x, y, xInc, yInc);
                //System.out.println(r);
                tiles[i][j].rect = r;
            }
        }
        
        
	}
	//AL for resizing
	ArrayList<JLabel> dispInfo = new ArrayList<JLabel>();
    private MouseListener ml = new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
        	
 
            Point p = e.getPoint();
            if(!isInGrid(p)) return;
            double xInc = (double)(getWidth() - 2*PAD)/COLS;
            double yInc = (double)(getHeight() - 2*PAD)/ROWS;
            int row = (int)((p.y-PAD)/yInc);
            int col = (int)((p.x-PAD)/xInc);
            System.out.println(tiles[row][col]);
            String newPlacement = null;
            
            if(placements.getSelection() != null){
            	newPlacement = placements.getSelection().getActionCommand();
            }
        	if(newPlacement != null){
        		if(newPlacement.equals("FOREST")){
        			tiles[row][col].setType(TileType.FOREST);
        		}
        		else if(newPlacement.equals("WATER")){
        			tiles[row][col].setType(TileType.WATER);
        		}
        	}
            
            //remove old components
            
            if(!labels.isEmpty()){
            	while(!labels.isEmpty()){
            		mainWindow.remove(labels.get(0));
            		labels.remove(0);
            		dispInfo = new ArrayList<JLabel>();
            	}
            }
            
            /*
            if(!dispInfo.isEmpty()){
            	while(!dispInfo.isEmpty()){
            		mainWindow.remove(dispInfo.get(0));
            		dispInfo.remove(0);
            		dispInfo = new ArrayList<JLabel>();
            	}
            }
        	*/
            
            //System.out.println(labels.isEmpty());
            
            ArrayList<String> info = tiles[row][col].getInfo();
            JLabel newLabel;
            int counter = 0;
            for(String item : info){
            	newLabel = new JLabel(item);
        		newLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        		newLabel.setBounds((int) (mainWindow.getBounds().getWidth()-200), 50 * (counter + 1), 200, 22);
        		mainWindow.getContentPane().add(newLabel);
        		labels.add(newLabel);
        		//dispInfo.add(newLabel);
        		counter++;
            }
            
            //resize
            mainWindow.addComponentListener(new ComponentAdapter(){  
				public void componentResized(ComponentEvent evt) {	
					int i=0;
					for(JLabel j : labels ){
						i++;
						j.setBounds((int) (mainWindow.getBounds().getWidth()-200), (i+0)*50, 200, 22);
					}
				}
			});
            
            /*
            String[] info = tiles[row][col].getInfo();
            	for(int i = 0; i < info.length; i++){
            		JLabel newLabel = new JLabel(info[i]);
            		newLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
            		newLabel.setBounds(400, 50 * (i + 1), 200, 22);
            		mainWindow.getContentPane().add(newLabel);
            		labels.add(newLabel);
            	}
            
            */
            
            //add new components
            /*
    		JLabel testLabel = new JLabel(tiles[row][col].type);
    		testLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
    		testLabel.setBounds(497, 50, 104, 22);
    		mainWindow.getContentPane().add(testLabel);
    		*/
            //JList list = new Jlist();
    		
    		mainWindow.revalidate();
    		mainWindow.repaint();
    		//end test
    		
            boolean isSelected = tiles[row][col].isSelected();
            tiles[row][col].setSelected(!isSelected);
            repaint();
        }
    };
	
    private boolean isInGrid(Point p) {
        Rectangle r = getBounds();
        r.grow(-PAD, -PAD);
        return r.contains(p);
    }
    
    public ComponentListener cl = new ComponentAdapter() {
        public void componentResized(ComponentEvent e) {
            //tiles = null;
            repaint();
        }
    };

	public void run() {
		// TODO Auto-generated method stub
		while(true){
			game.tick();
			repaint();
			if(pause){
				pause = false;
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
   
}
