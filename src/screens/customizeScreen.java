package screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.PlainDocument;

import main.Main;
import main.Player;
import ui.RelativeLayout;
import ui.intFill;

public class customizeScreen extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3283873293802734483L;
	private JPanel Center;
	
	private int maxPlayers;
	
	private Color[] colors = {
		Color.RED,
		Color.BLUE,
		Color.YELLOW,
		Color.GREEN,
		Color.PINK,
		Color.CYAN,
		Color.MAGENTA,
		Color.ORANGE,
	};
	
	private Player[] players = new Player[4];
	private ArrayList<JPanel> playerPanels = new ArrayList<JPanel>();
	
	public static int MAX_POINTS = 50;

	/**
	 * Create the panel.
	 */
	public customizeScreen() {
		setPreferredSize(new Dimension(600, 400));
		setLayout(new BorderLayout(0, 0));
		
		JPanel Right = new JPanel();
		add(Right, BorderLayout.EAST);
		
		JPanel pointsPanel = new JPanel();
		Right.add(pointsPanel);
		pointsPanel.setLayout(new GridLayout(0, 1, 0, 2));
		
		JLabel titleMaxPoints = new JLabel("MAX POINTS");
		titleMaxPoints.setFont(new Font("Apple LiGothic", Font.PLAIN, 16));
		pointsPanel.add(titleMaxPoints);
		
		JTextField textMaxPoints = new JTextField("50");
		textMaxPoints.setFont(new Font("Apple LiGothic", Font.PLAIN, 16));
		textMaxPoints.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textMaxPoints.getText().equals("")) {
					textMaxPoints.setText("50");
				}
				textMaxPoints.setText(Integer.parseInt(textMaxPoints.getText()) + "");
				MAX_POINTS = Integer.parseInt(textMaxPoints.getText());
			}
		});
		pointsPanel.add(textMaxPoints);
		
		PlainDocument doc = (PlainDocument) textMaxPoints.getDocument();
		doc.setDocumentFilter(new intFill());
		
		Center = new JPanel();
		add(Center, BorderLayout.CENTER);
		Center.setLayout(new GridLayout(2, 2, 6, 6));
		
		
		for (int index = 0; index < 4; index++) {
			JPanel playerPanel = newPlayerPanel(index);
			playerPanel.setVisible(false);
			playerPanels.add(playerPanel);
		}
		
		
		RelativeLayout rl2 = new RelativeLayout(RelativeLayout.Y_AXIS);
		
		JPanel Bottom = new JPanel();
		add(Bottom, BorderLayout.SOUTH);
		Bottom.setLayout(rl2);
		Bottom.setBorder(new EmptyBorder(5, 100, 5, 100));
		
		JButton btnStart = new JButton("START GAME!");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.changeState("PLAY_GAME");
			}
		});
		btnStart.setOpaque(true);
		btnStart.setBackground(Color.GREEN);
		Bottom.add(btnStart, (float) 0.6);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.changeState("SELECT_NUMBER_OF_PLAYERS");
			}
		});
		Bottom.add(btnBack, (float) 0.4);
		

	}
	
	public void setPlayers(int[] arg1) {
		for (JPanel panel : playerPanels) {
			panel.setVisible(false);
		}
		for (int num : arg1) {
			for (int i = 0; i < num; i++) {
				playerPanels.get(i).setVisible(true);
			}
			maxPlayers = num;
		}
	}
	
	public ArrayList<Player> getPlayersToGame() {
		ArrayList<Player> playing = new ArrayList<>();
		for (int i = 0; i < maxPlayers; i++) {
			playing.add(players[i]);
		}
		return playing;
	}

	private JPanel newPlayerPanel(int index) {
		Color defaultColor = colors[index];
		
		JPanel playerPanel = new JPanel();
		
		RelativeLayout rl1 = new RelativeLayout(RelativeLayout.Y_AXIS);
		rl1.setFill(true);
		
		playerPanel.setLayout(rl1);
		playerPanel.setBorder(new LineBorder(defaultColor, 10));
		Center.add(playerPanel);
		
		
		JTextField txtPlayer = new JTextField();
		//txtPlayer.setForeground(Color.WHITE);
		//txtPlayer.setBackground(defaultColor);
		txtPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayer.setBorder(null);
		txtPlayer.setFont(new Font("Apple LiGothic", Font.BOLD, 20));
		txtPlayer.setText("Player " + (index+1));
		playerPanel.add(txtPlayer, (float) 0.3);
		txtPlayer.setColumns(10);
		txtPlayer.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
			}

			@Override
			public void focusLost(FocusEvent e) {
				players[index].setName(txtPlayer.getText());
			}
		});
		
		JPanel colorPalette = new JPanel();
		//colorPalette.setBackground(Color.LIGHT_GRAY);
		playerPanel.add(colorPalette, (float) 0.7);
		colorPalette.setLayout(new GridLayout(2, 4, 4, 4));
		colorPalette.setBackground(defaultColor);
		
		for (Color color : colors) {
			JButton c = new JButton();
			c.setOpaque(true);
			c.setBorderPainted(false);
			c.setBackground(color);
			c.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					players[index].setColor(color);
					colorPalette.setBackground(color);
					playerPanel.setBorder(new LineBorder(color, 10));
				}
			});
			colorPalette.add(c);
		}
		
		players[index] = new Player(txtPlayer.getText(), defaultColor);
		return playerPanel;
	}

}



