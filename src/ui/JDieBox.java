package ui;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import screens.gameScreen;

public class JDieBox extends JCheckBox {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1059206019907198348L;
	
	public static ArrayList<JDieBox> dice = new ArrayList<JDieBox>();
	
	public static HashMap<Integer, ImageIcon> images = new HashMap<Integer, ImageIcon>();

	public static int BORDER_SELECTED_SIZE = 4;
	public static int DIE_PADDING = 0;
	public static int DIE_SIZE = 100;
	
	private int value = 1;
	
	private static Random rand = new Random();
	
	/**
	 * Create the panel.
	 */
	public JDieBox() {
		setHorizontalAlignment(SwingConstants.CENTER);
		setSize(DIE_SIZE + BORDER_SELECTED_SIZE, DIE_SIZE + BORDER_SELECTED_SIZE);
		
		addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					setBorderPainted(true);
				} else {
					setBorderPainted(false);
				}
				for (int i = 0; i < dice.size(); i++) {
					if (dice.get(i).isSelected()) {
						gameScreen.btnRoll.setEnabled(true);
						return;
					}
				}
				gameScreen.btnRoll.setEnabled(false);
				
			}
		});
		
		//setEnabled(false);
		setFocusable(false);
		setSelected(true);
		roll();
		dice.add(this);
	}
	
	public void roll() {
		this.value = 1 + rand.nextInt(6);
		if (!images.containsKey(this.value)) {
			images.put(this.value, new ImageIcon(JDieBox.class.getResource("/resources/images/die-" + this.value + ".png")));
		}
		setIcon(images.get(this.value));
		
		if (!isEnabled()) {
			setEnabled(true);
		}
		if (!gameScreen.btnEnd.isEnabled()) {
			gameScreen.btnEnd.setEnabled(true);
		}
	}
	
	public int getValue() {
		return this.value;
	}
	public void setColor(Color color) {
		setBorder(new LineBorder(color, BORDER_SELECTED_SIZE));
	}

}
