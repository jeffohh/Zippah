package screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.Main;
import main.Player;
import ui.JDieBox;
import ui.PlayerPanel;
import ui.RelativeLayout;

public class gameScreen extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4201879909545709156L;
	
	private int MAX_REROLL = 3;

	private ArrayList<Player> players = new ArrayList<Player>();
	private PlayerPanel[] playerPanels = new PlayerPanel[4];
	
	private int currentTurn = 0;
	private int rerollCount = MAX_REROLL;

	public static JButton btnRoll;
	public static JButton btnEnd;

	private JLabel playerText;

	private JPanel centerBottom;


	/**
	 * Create the panel.
	 */
	public gameScreen() {
		RelativeLayout rl1 = new RelativeLayout(RelativeLayout.X_AXIS);
		rl1.setFill(true);

		setPreferredSize(new Dimension(750, 425));
		setLayout(rl1);
		
		setBackground(Color.GRAY);

		initCenter();
		initRight();

	}

	private void initCenter() {
		RelativeLayout rl1 = new RelativeLayout(RelativeLayout.Y_AXIS);
		rl1.setFill(true);
		
		JPanel Center = new JPanel();
		Center.setLayout(rl1);
		add(Center, (float) 0.7);
		
		// dice containers and titles
		
		RelativeLayout rl2 = new RelativeLayout(RelativeLayout.Y_AXIS);
		rl2.setFill(true);
		JPanel centerTop = new JPanel();
		centerTop.setLayout(rl2);
		Center.add(centerTop, (float) 0.8);
		
		RelativeLayout rl3 = new RelativeLayout(RelativeLayout.X_AXIS);
		rl3.setAlignment(RelativeLayout.CENTER);
		JPanel title = new JPanel();
		title.setLayout(rl3);
		centerTop.add(title, (float) 0.2);
		
		playerText = new JLabel("Player 1's");
		playerText.setFont(new Font("Apple LiGothic", Font.BOLD, 36));
		playerText.setHorizontalAlignment(SwingConstants.TRAILING);
		title.add(playerText, (float) 1);
		
		JLabel actionText = new JLabel(" Turn . . . ");
		actionText.setFont(new Font("Apple LiGothic", Font.BOLD, 36));
		actionText.setHorizontalAlignment(SwingConstants.LEADING);
		title.add(actionText, (float) 1);
		
		
		RelativeLayout rl4 = new RelativeLayout(RelativeLayout.Y_AXIS);
		rl4.setAlignment(CENTER_ALIGNMENT);
		JPanel container = new JPanel();
		container.setLayout(rl4);
		centerTop.add(container, (float) 0.8);
		
		JPanel diceContainer = new JPanel();
		diceContainer.setLayout(new FlowLayout(FlowLayout.CENTER, JDieBox.DIE_PADDING, JDieBox.DIE_PADDING));
		diceContainer.setPreferredSize(new Dimension(399, 299));
		container.add(diceContainer, (float) 1);
		
		// roll and end turn button here
		
		centerBottom = new JPanel();
		centerBottom.setBorder(new EmptyBorder(10, 15, 10, 15));
		Center.add(centerBottom, (float) 0.2);
		centerBottom.setLayout(new GridLayout(0, 2, 15, 0));
		
		btnRoll = new JButton("ROLL");
		btnRoll.setFocusable(false);
		btnRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roll();
			}
		});
		centerBottom.add(btnRoll);
		
		btnEnd = new JButton("END TURN");
		btnEnd.setFocusable(false);
		btnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endTurn();
			}
		});
		centerBottom.add(btnEnd);
		
		// create the dice
		
		for (int i = 0; i < 5; i++) {
			JDieBox die = new JDieBox();
			diceContainer.add(die);
		}
		
		
	}

	private void initRight() {
		RelativeLayout rl1 = new RelativeLayout(RelativeLayout.Y_AXIS);
		rl1.setFill(true);

		JPanel Right = new JPanel();
		Right.setBorder(new EmptyBorder(5, 5, 5, 5));
		Right.setBackground(Color.LIGHT_GRAY);
		Right.setLayout(rl1);
		add(Right, (float) 0.3);

		JLabel lblLeaderboard = new JLabel("LEADERBOARD");
		lblLeaderboard.setHorizontalAlignment(SwingConstants.CENTER);
		Right.add(lblLeaderboard, (float) 0.15);
		lblLeaderboard.setFont(new Font("Apple Color Emoji", Font.BOLD, 22));

		JPanel panelLeaderboard = new JPanel();
		panelLeaderboard.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelLeaderboard.setBackground(Color.GRAY);
		Right.add(panelLeaderboard, (float) 0.85);
		panelLeaderboard.setLayout(new GridLayout(4, 1, 0, 5));

		for (int i = 0; i < playerPanels.length; i++) {
			PlayerPanel playerPanel = new PlayerPanel();
			panelLeaderboard.add(playerPanel);
			playerPanels[i] = playerPanel;
		}
	}

	public void initPlayers(ArrayList<Player> players) {
		this.players.clear();
		players.forEach(player -> this.players.add(player));
		for (JPanel panel : playerPanels) {
			panel.setVisible(false);
		}

		this.players.forEach(player -> player.setPoints(0));
		for (int i = 0; i < this.players.size(); i++) {
			playerPanels[i].setVisible(true);
			playerPanels[i].initPlayerPanel(this.players.get(i));
		}
		updateTurn();
	}
	
	public void roll() {
		JDieBox.dice.forEach(die -> {
			if (die.isSelected()) {
				die.roll();
				die.setSelected(false);
			}
		});
		setRolls(rerollCount - 1);
		if (rerollCount <= 0) {
			endTurn();
		}
	}
	
	public void endTurn() {
		// calc points
		int sum = calculatePoints();
		
		Player currentPlayer = players.get(currentTurn);
		currentPlayer.setPoints(currentPlayer.getPoints() + sum);
		playerPanels[currentTurn].updatePoints();
		
		String conclusion = currentPlayer.getName() + " got " + sum + " points. Their current points are now " + currentPlayer.getPoints() + ".";
		
		final JOptionPane pane = new JOptionPane(conclusion);
	    final JDialog d = pane.createDialog(null, "Message");
	    d.setLocationRelativeTo(centerBottom);
	    d.setVisible(true);
		
		// check for winner
		endGame();
		
		// shift turn
		shiftTurn();
	}
	
	public boolean endGame() {
		int maxPoints = customizeScreen.MAX_POINTS-1;
		int playerIndex = -1;
		boolean winner = false;
		boolean lastTurn = currentTurn == players.size() - 1;

		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int points = player.getPoints();

			if (points > maxPoints) {
				maxPoints = points;
				playerIndex = i;
				winner = true;
			} else if (points == maxPoints) {
				winner = false;
			}
		}

		if (lastTurn && winner) {

			JOptionPane.showMessageDialog(null,
					"Player " + (playerIndex + 1) + " reached " + customizeScreen.MAX_POINTS + " points and won the game!");
			Main.changeState("");
		}

		return false;
	}
	
	public void shiftTurn() {
		currentTurn++;
		currentTurn = (currentTurn >= players.size()) ? 0 : currentTurn;
		updateTurn();
	}
	
	
	public void updateTurn() {
		setRolls(3);
		
		Player currentPlayer = players.get(currentTurn);
		JDieBox.dice.forEach(die -> die.setColor(currentPlayer.getColor()));
		
		playerText.setForeground(currentPlayer.getColor());
		playerText.setText(currentPlayer.getName() + "'s");
		
		JDieBox.dice.forEach(die -> die.setSelected(true));
		JDieBox.dice.forEach(die -> die.setEnabled(false));
		btnEnd.setEnabled(false);
	}
	
	public int calculatePoints() {
		HashMap<Integer, Integer> diceMap = new HashMap<Integer, Integer>();
		JDieBox.dice.forEach(die ->{
			int num = die.getValue();
			if (!diceMap.containsKey(num)) {
				diceMap.put(num, 0);
			}
			diceMap.put(num, diceMap.get(num) + 1);
		});

		int sum = 0;
		for (int i : diceMap.keySet()) {
			if (diceMap.get(i) >= 3) {
				sum = i * diceMap.get(i);
			}
		}
		return sum;
	}

	public void setRolls(int value) {
		rerollCount = value;
		String rollText = (rerollCount == MAX_REROLL) ? "ROLL" : "RE-ROLL";
		btnRoll.setText(rollText + " (" + rerollCount + ")");
	}
}
