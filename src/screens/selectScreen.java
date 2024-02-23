package screens;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Main;
import ui.RelativeLayout;

import javax.swing.SwingConstants;

public class selectScreen extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -735396324483371684L;

	/**
	 * Create the panel.
	 */
	public selectScreen() {
		setPreferredSize(new Dimension(450, 300));
		RelativeLayout rl1 = new RelativeLayout(RelativeLayout.Y_AXIS);
		rl1.setFill(true);
		setLayout(rl1);
		
		JLabel Title = new JLabel("SELECT AMOUNT OF PLAYERS");
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setFont(new Font("Apple Color Emoji", Font.PLAIN, 22));
		add(Title, (float) 0.15);
		
		
		JPanel Top = new JPanel();
		add(Top, (float) 0.7);
		Top.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnTwo = new JButton("TWO");
		btnTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.changeState("CUSTOMIZE_PLAYERS", 2);
			}
		});
		btnTwo.setFont(new Font("Apple Color Emoji", Font.PLAIN, 35));
		Top.add(btnTwo);
		
		JButton btnThree = new JButton("THREE");
		btnThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.changeState("CUSTOMIZE_PLAYERS", 3);
			}
		});
		btnThree.setFont(new Font("Apple Color Emoji", Font.PLAIN, 35));
		Top.add(btnThree);
		
		JButton btnFour = new JButton("FOUR");
		btnFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.changeState("CUSTOMIZE_PLAYERS", 4);
			}
		});
		btnFour.setFont(new Font("Apple Color Emoji", Font.PLAIN, 35));
		Top.add(btnFour);
		
		JPanel Bottom = new JPanel();
		add(Bottom, (float) 0.15);
		Bottom.setLayout(new GridLayout(0, 1, 0, 0));
		Bottom.setBorder(new EmptyBorder(5, 100, 5, 100));
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.changeState("");
			}
		});
		Bottom.add(btnBack);
	}

}
