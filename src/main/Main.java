package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import screens.customizeScreen;
import screens.gameScreen;
import screens.selectScreen;
import screens.titleScreen;
import ui.JDieBox;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6004818581819567563L;

	// ----- FIELDS -----
	private static JPanel contentPane;

	private static JPanel[] screens;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 300);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel titlePanel = new titleScreen();

		JPanel selectPanel = new selectScreen();

		JPanel customizePanel = new customizeScreen();
		
		JPanel gamePanel = new gameScreen();
		
		screens = new JPanel[] { titlePanel, selectPanel, customizePanel, gamePanel };
		for (JPanel screen : screens) {
			screen.addComponentListener(new ComponentAdapter() {
				public void componentShown(ComponentEvent e) {
					setSize(screen.getPreferredSize());
					setLocationRelativeTo(null);
				}
			});
		}
		changeState("");
		
		setFocusable(true);
		addKeyListener(new KeyListener() {
			@Override
		    public void keyTyped(KeyEvent e) {
		    }
		    
		    @Override
		    public void keyPressed(KeyEvent e) {
		    	if (gamePanel.isVisible()) {
		    		ArrayList<JDieBox> rollable = new ArrayList<JDieBox>();
		    		JDieBox.dice.forEach(die -> {
		    			if (die.isEnabled()) {
		    				rollable.add(die);
		    			}
		    		});
		    		
		    		int keyCode = e.getKeyCode();
		    		
		    		switch (keyCode) {
		    		case 13:
		    		case 10:
		    			if (gameScreen.btnRoll.isEnabled()) {
		    				((gameScreen) gamePanel).roll();
		    			}
		    			break;
		    		case 32:
		    			rollable.forEach(die -> die.setSelected(true));
		    			break;
		    		default:
		    			if (keyCode > 48 && keyCode <= 48+6) {
		    				rollable.forEach(die -> die.setSelected(false));
		    				rollable.forEach(die -> {
		    					if (die.getValue() != (keyCode - 48)) {
		    						die.setSelected(true);
		    					}
		    				});
		    				
		    			}
		    			break;
		    		}
		    	}
		    }
		    
		    @Override
	    	public void keyReleased(KeyEvent e) {
	    	}
		});
	}

	public static void changeState(String newState, int... arg1) {
		customizeScreen cPanel;

		switch (newState) {
		case "SELECT_NUMBER_OF_PLAYERS":
			setScreen(1);
			break;
		case "CUSTOMIZE_PLAYERS":
			setScreen(2);
			cPanel = (customizeScreen) screens[2];
			cPanel.setPlayers(arg1);
			break;
		case "PLAY_GAME":
			setScreen(3);
			cPanel = (customizeScreen) screens[2];
			gameScreen gPanel = (gameScreen) screens[3];
			gPanel.initPlayers(cPanel.getPlayersToGame());
			break;
		default:
			setScreen(0);
			break;
		}
	}

	private static void setScreen(int index) {
		for (JPanel screen : screens) {
			if (screen.isVisible()) {
				screen.setVisible(false);
			}
		}
		JPanel newScreen = screens[index];
		contentPane.add(newScreen, BorderLayout.CENTER);
		newScreen.setVisible(true);
	}

}
