package Game;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class GameView extends JPanel{
	Tile[][] tiles;
	final int PAD = 20;
	final int ROWS = 10;
	final int COLS = 10;
	private boolean start = true;
	
	public GameView(){
		addMouseListener(ml);
	}
	
	public GameView(Tile[][] tiles){
		addMouseListener(ml);
		this.tiles = tiles;
		//initTiles();
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
				tiles[i][j].draw(g2);
			}
		}
		//Draw Actors
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
		
		
		int w = getWidth();
		int h = getHeight();
		double xInc = (double)(w - 2 * PAD)/ COLS;
		double yInc = (double)(h - 2 * PAD)/ ROWS;
        for(int i = 0; i < ROWS; i++) {
            double y = PAD + i*yInc;
            for(int j = 0; j < COLS; j++) {
                double x = PAD + j*xInc;
                Rectangle2D.Double r = new Rectangle2D.Double(x, y, xInc, yInc);
                System.out.println(r);
                tiles[i][j].rect = r;
            }
        }
        
        
	}
	
    private MouseListener ml = new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            Point p = e.getPoint();
            if(!isInGrid(p)) return;
            double xInc = (double)(getWidth() - 2*PAD)/COLS;
            double yInc = (double)(getHeight() - 2*PAD)/ROWS;
            int row = (int)((p.y-PAD)/yInc);
            int col = (int)((p.x-PAD)/xInc);
            System.out.println(tiles[row][col]);
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
            tiles = null;
            repaint();
        }
    };
}
