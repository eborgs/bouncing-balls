package bouncingballs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class BouncingBalls {

	private Display display;

	private ArrayList<Ball> balls = new ArrayList<>();

	public BouncingBalls() {
		display = new Display(800, 600);
		addBalls();
		mainLoop();
		display.destroy();
	}

	private void mainLoop() {
		while (!display.isCloseRequested()) {
			updatePhysics();
			draw(display.getGraphics());
			display.update();
			display.sync(60);
		}
	}

	private void addBalls() {
		int numberOfBalls = 10;
		Random random = new Random();

		for (int i = 0; i < numberOfBalls; i++) {

			int radius = random.nextInt(40) + 10;

			int x = random.nextInt(display.width - radius * 2) + radius;
			int y = random.nextInt(display.height - radius * 2) + radius;

			float speedX = random.nextFloat() * 10f + 3f;
			float speedY = random.nextFloat() * 10f + 3f;

			Color color;
			switch (random.nextInt(3)) {
			case 0:
				color = Color.red;
				break;
			case 1:
				color = Color.green;
				break;
			case 2:
				color = Color.yellow;
				break;
			default:
				color = Color.white;
				break;
			}

			Ball ball = new Ball(x, y, speedX, speedY, radius, color);
			balls.add(ball);
		}
	}

	private void updatePhysics() {
		for (Ball ball : balls) {

			ball.x += ball.speedX;
			ball.y += ball.speedY;

			if (ball.x - ball.radius < 0) {
				ball.speedX = Math.abs(ball.speedX);
			} else if (ball.x + ball.radius > display.width) {
				ball.speedX = -Math.abs(ball.speedX);
			}
			if (ball.y - ball.radius < 0) {
				ball.speedY = Math.abs(ball.speedY);
			} else if (ball.y + ball.radius > display.height) {
				ball.speedY = -Math.abs(ball.speedY);
			}
		}
	}

	private void draw(Graphics2D g) {
		g.setBackground(Color.black);
		g.clearRect(0, 0, display.width, display.height);

		for (Ball ball : balls) {
			g.setColor(ball.color);

			int x = (int) (ball.x - ball.radius);
			int y = (int) (ball.y - ball.radius);
			int size = ball.radius * 2;

			g.fillOval(x, y, size, size);
		}
	}

	public static void main(String[] args) {
		new BouncingBalls();
	}

}
