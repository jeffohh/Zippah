package screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.Main;
import ui.RelativeLayout;

public class titleScreen extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1202234788241643851L;

	/**
	 * Create the panel.
	 */
	public titleScreen() {
		setPreferredSize(new Dimension(450, 300));
		RelativeLayout rl1 = new RelativeLayout(RelativeLayout.Y_AXIS);
		rl1.setFill(true);
		setLayout(rl1);
		
		
		
		JPanel Top = new JPanel();
		add(Top, (float) 0.25);
		Top.setLayout(new BorderLayout(0, 0));
		
		JLabel title = new JLabel("ZIPPAH");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Apple LiGothic", Font.PLAIN, 40));
		Top.add(title, BorderLayout.SOUTH);
		
		
		
		JPanel Bottom = new JPanel();
		add(Bottom, (float) 0.75);
		Bottom.setLayout(new BoxLayout(Bottom, BoxLayout.Y_AXIS));
		Bottom.setBorder(new EmptyBorder(50, 100, 50, 100));
		
		JPanel Center = new JPanel();
		Bottom.add(Center);
		Center.setLayout(new GridLayout(3, 0, 0, 6));
		
		
		
		JButton btnPlay = new JButton("PLAY!");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.changeState("SELECT_NUMBER_OF_PLAYERS");
			}
		});
		btnPlay.setBorderPainted(false);
		btnPlay.setOpaque(true);
		btnPlay.setBackground(Color.GREEN);
		btnPlay.setFont(new Font("AppleGothic", Font.PLAIN, 16));
		Center.add(btnPlay);
		
		JButton btnRules = new JButton("RULES & GAMEPLAY");
		btnRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Main.changeState("SELECT_NUMBER_OF_PLAYERS");
			}
		});
		btnRules.setBorderPainted(false);
		btnRules.setBackground(Color.LIGHT_GRAY);
		btnRules.setOpaque(true);
		btnRules.setFont(new Font("AppleGothic", Font.PLAIN, 16));
		Center.add(btnRules);
		
		JButton btnAbout = new JButton("EXIT");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnAbout.setBorderPainted(false);
		btnAbout.setBackground(Color.LIGHT_GRAY);
		btnAbout.setOpaque(true);
		btnAbout.setFont(new Font("AppleGothic", Font.PLAIN, 16));
		Center.add(btnAbout);
		

	}
}
