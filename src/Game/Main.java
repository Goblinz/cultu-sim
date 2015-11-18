package Game;


import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Canvas;
import java.awt.Component;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Button;
import java.awt.Point;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;
	private Game game;
	public JLabel lblInspector;
	
	//game view
	private GameView GV;
	//game state buttons
	private JButton btnNextTurn = new JButton("Next Turn");
	private JButton btnPlay = new JButton("Play");
	private JButton btnPause = new JButton("Pause");
	private JButton btnSelector = new JButton("Selector");
	//placement buttons
	private ButtonGroup placementGroup = new ButtonGroup();
	private JLabel lblPlacements = new JLabel("Placements");
	private JRadioButton rdbtnForest = new JRadioButton("Forest");
	private JRadioButton rdbtnCity = new JRadioButton("City");
	private JRadioButton rdbtnWater = new JRadioButton("Water");
	private JRadioButton rdbtnFertileLand = new JRadioButton("Fertile Land");
	private JRadioButton rdbtnMine = new JRadioButton("Iron deposit");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//Game game;
		//game = new Game();
		//game.generateView();
		//this code displays a selectable grid
		/*
        GameView GV = new GameView();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(GV);
        f.setSize(400,400);
        f.setLocation(100,100);
        f.setVisible(true);
        GV.addComponentListener(GV.cl);
		 */

		//this code displays the buttons

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}

	/**
	 * Create the application.
	 * @throws FileNotFoundException 
	 */
	public Main() throws FileNotFoundException {
		game = new Game();
		//Point[] path = {new Point(8,8),new Point(3,4)};
		//game.spawnUnitMoveGather(6, 6,path);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {


		frame = new JFrame();

		frame.setBounds(200, 200, 668, 536);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		/*
		GV = new GameView(game.world.getTiles(), game);
		//GameView GV = new GameView();
		GV.setBounds(20, 20, 100, 100);
		//GV.setBounds(20, 20, (int) frame.getBounds().getWidth()-250, (int) frame.getBounds().getHeight()-180);
		GV.addComponentListener(GV.cl);
		GV.mainWindow = frame;
		frame.getContentPane().add(GV);
		*/

		//inspector
		lblInspector = new JLabel("Inspector");
		lblInspector.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInspector.setBounds(497, 10, 104, 22);
		frame.getContentPane().add(lblInspector);
		
		btnSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				placementGroup.clearSelection();
			}
		});
		
		btnSelector.setBounds(119, 418, 80, 23);
		frame.getContentPane().add(btnSelector);

		//game state buttons
		btnNextTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.tick();
				GV.repaint();
			}
		});
		
		btnNextTurn.setBounds(208, 384, 117, 23);
		frame.getContentPane().add(btnNextTurn);

		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//start game loop
				Thread thread = new Thread(GV);
				thread.start();
			}
		});
		frame.getContentPane().add(btnPlay);

		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//pause game loop
				GV.pause = true;
			}
		});
		frame.getContentPane().add(btnPause);

		//game state buttons
		btnNextTurn.setBounds(208, (int) frame.getBounds().getHeight()-150, 117, 23);
		btnPlay.setBounds(109, (int) frame.getBounds().getHeight()-150, 89, 23);
		btnPause.setBounds(10, (int) frame.getBounds().getHeight()-150, 89, 23);
		
		//placement buttons
		placementGroup.add(rdbtnForest);
		placementGroup.add(rdbtnCity);
		placementGroup.add(rdbtnWater);
		placementGroup.add(rdbtnFertileLand);
		placementGroup.add(rdbtnMine);
		
		lblPlacements.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPlacements.setBounds(20, 418, 79, 14);
		frame.getContentPane().add(lblPlacements);
		
		//move placement buttons
		frame.getContentPane().add(rdbtnForest);
		frame.getContentPane().add(rdbtnCity);
		frame.getContentPane().add(rdbtnWater);
		frame.getContentPane().add(rdbtnFertileLand);	
		frame.getContentPane().add(rdbtnMine);

		//resize and init 
		frame.addComponentListener(new ComponentAdapter() 
		{  
			public void componentResized(ComponentEvent evt) {
				//Component c = (Component)evt.getSource();
				//System.out.println("WINDOW RESIZED");
				
				//game view
				//TODO errors for days ↓
				//GV.setTilesAndGame(game.world.getTiles(), game);
				if(GV != null){
					GV.remove(GV);
					frame.remove(GV);
					frame.repaint();
				}
				GV = new GameView(game.world.getTiles(), game);
				GV.setBounds(20, 0, (int) frame.getBounds().getWidth()-215, (int) frame.getBounds().getHeight()-160);
				GV.addComponentListener(GV.cl);
				GV.mainWindow = frame;
				frame.getContentPane().add(GV);
				frame.repaint();
				
				//move inspector
				lblInspector.setBounds((int) frame.getBounds().getWidth()-200, 10, 104, 22);
				//TODO move dynamic desctiptions
				//move game state buttons
				btnSelector.setBounds(119,(int) frame.getBounds().getHeight() - 125, 89, 23);
				btnNextTurn.setBounds(208, (int) frame.getBounds().getHeight()-150, 117, 23);
				btnPlay.setBounds(109, (int) frame.getBounds().getHeight()-150, 89, 23);
				btnPause.setBounds(10, (int) frame.getBounds().getHeight()-150, 89, 23);
				//move placement buttons
				lblPlacements.setBounds(20, (int) frame.getBounds().getHeight()-120, 79, 14);
				rdbtnForest.setBounds(10, (int) frame.getBounds().getHeight()-100, 109, 23);
				rdbtnCity.setBounds(232, (int) frame.getBounds().getHeight()-100, 109, 23);
				rdbtnWater.setBounds(10, (int) frame.getBounds().getHeight()-72, 109, 23);
				rdbtnFertileLand.setBounds(121, (int) frame.getBounds().getHeight()-72, 109, 23);
				rdbtnMine.setBounds(121, (int) frame.getBounds().getHeight()-100, 109, 23);

				
			}
		});


	}

}
