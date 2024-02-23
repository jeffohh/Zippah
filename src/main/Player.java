package main;

import java.awt.Color;

public class Player {
	
	// ----- FIELDS -----
	private int points = 0;
	private String name;
	private Color color;
	
	// ----- CONSTRUCTOR -----
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
