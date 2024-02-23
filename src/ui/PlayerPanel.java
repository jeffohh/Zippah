package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import main.Player;

public class PlayerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8981572335198324398L;
	
	private Player player;

	private JLabel playerName;
	private JLabel playerPoints;

	/**
	 * Create the panel.
	 */
	public PlayerPanel() {
		setBackground(Color.GRAY);
		RelativeLayout rl1 = new RelativeLayout(RelativeLayout.X_AXIS);
		rl1.setFill(true);
		rl1.setBorderGap(5);
		setLayout(rl1);
		
		playerName = new JLabel();
		playerName.setForeground(Color.WHITE);
		playerName.setFont(new Font("Apple Color Emoji", Font.BOLD, 18));
		add(playerName, (float) 0.7);
		
		playerPoints = new JLabel("0");
		playerPoints.setForeground(Color.WHITE);
		playerPoints.setHorizontalAlignment(SwingConstants.CENTER);
		playerPoints.setFont(new Font("Apple Color Emoji", Font.BOLD, 18));
		add(playerPoints, (float) 0.25);
	}
	
	public void initPlayerPanel(Player player) {
		this.player = player;
		
		playerName.setText(player.getName());
		playerPoints.setText(player.getPoints() + "");
		
		setBorder(new LineBorder(player.getColor(), 5));
		playerName.setForeground(player.getColor());
		playerPoints.setForeground(player.getColor());
	}
	
	public void updatePoints() {
		playerPoints.setText(player.getPoints() + "");
	}

}
