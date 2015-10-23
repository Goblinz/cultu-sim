package Game;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Canvas;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Button;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Game game;
		game = new Game();
		game.generateView();
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
		
		Canvas canvas = new Canvas();
		canvas.setBounds(10, 10, 439, 368);
		frame.getContentPane().add(canvas);
		
		JLabel lblInspector = new JLabel("Inspector");
		lblInspector.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInspector.setBounds(497, 10, 104, 22);
		frame.getContentPane().add(lblInspector);
		
		JButton btnFastForward = new JButton("Fast Forward");
		btnFastForward.setBounds(208, 384, 117, 23);
		frame.getContentPane().add(btnFastForward);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(109, 384, 89, 23);
		frame.getContentPane().add(btnPlay);
		
		JButton btnPause = new JButton("Pause");
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
