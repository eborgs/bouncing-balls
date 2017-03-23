package bouncingballs;

import java.awt.Color;

public class Ball {

	public float x;
	public float y;
	
	public float speedX;
	public float speedY;
	
	public int radius;
	
	public Color color;

	public Ball(float x, float y, float speedX, float speedY, int radius, Color color) {
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.radius = radius;
		this.color = color;
	}
	
}
