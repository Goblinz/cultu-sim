package Game;


import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Canvas;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Button;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;
	private Game game;

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
	 */
	public Main() {
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

		frame.setBounds(100, 100, 668, 536);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final GameView GV = new GameView(game.world.getTiles());
		//GameView GV = new GameView();
		GV.setBounds(20, 20, 300, 300);
		GV.addComponentListener(GV.cl);
		frame.getContentPane().add(GV);

		JLabel lblInspector = new JLabel("Inspector");
		lblInspector.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInspector.setBounds(497, 10, 104, 22);
		frame.getContentPane().add(lblInspector);
		
		JButton btnNextTurn = new JButton("Next Turn");
		btnNextTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.tick();
				GV.repaint();
			}
		});
		btnNextTurn.setBounds(208, 384, 117, 23);
		frame.getContentPane().add(btnNextTurn);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPlay.setBounds(109, 384, 89, 23);
		frame.getContentPane().add(btnPlay);
		
		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPause.setBounds(10, 384, 89, 23);
		frame.getContentPane().add(btnPause);
		
		JLabel lblPlacements = new JLabel("Placements");
		lblPlacements.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPlacements.setBounds(20, 418, 79, 14);
		frame.getContentPane().add(lblPlacements);
		
		JRadioButton rdbtnForest = new JRadioButton("Forest");
		rdbtnForest.setBounds(10, 439, 109, 23);
		frame.getContentPane().add(rdbtnForest);
		
		JRadioButton rdbtnIronDeposit = new JRadioButton("Iron deposit");
		rdbtnIronDeposit.setBounds(121, 439, 109, 23);
		frame.getContentPane().add(rdbtnIronDeposit);
		
		JRadioButton rdbtnCity = new JRadioButton("City");
		rdbtnCity.setBounds(232, 439, 109, 23);
		frame.getContentPane().add(rdbtnCity);
		
		JRadioButton rdbtnWater = new JRadioButton("Water");
		rdbtnWater.setBounds(10, 467, 109, 23);
		frame.getContentPane().add(rdbtnWater);
		
		JRadioButton rdbtnHuntingGround = new JRadioButton("Hunting ground");
		rdbtnHuntingGround.setBounds(121, 467, 109, 23);
		frame.getContentPane().add(rdbtnHuntingGround);
	}
}
