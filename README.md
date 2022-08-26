# Final-Project
CS50x 2022

# SNAKE GAME

#### Video Demo:  <https://youtu.be/BFB8vodE2L4>

#### Description:

Below are the constants used in the game.
final int SCREEN_WIDTH = 700;
final int SCREEN_HEIGHT = 500;
final int DOT_SIZE = 20;
final int TOTAL_DOTS = 1000;
final int DELAY = 75;
SCREEN_WIDTH and SCREEN_HEIGHT determines the size of the screen.
DOT_SIZE is the size of the apple and each part of the snake.
TOTAL_DOTS decides the maximum number of dots that will be used on the game.
DELAY is how fast the snake moves in the game.

final int x[] = new int[TOTAL_DOTS]; This is going to store all the x coordinates of the snake.
final int y[] = new int[TOTAL_DOTS]; This is going to store all the y coordinates of the snake.
int dots = 5; This is the initial number of length (in dots) of the snake.

Declaring some integers which will be used in the game.
int apples;
int appleX;
int appleY;
Explanations of these integers will be explained below with the respective functions.

char direction = 'D';
boolean running = true;
Timer timer;
Random random;
Here we define the direction and the positioning of the snake and apple.

This will be the panel used in the game.
SnakePanel() {
	random = new Random();
	setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
	setBackground(Color.black);
	setFocusable(true);
	addKeyListener(new MyKeyAdapter());
	startGame();
}
It defines the screen size, colour, and if it should respond when a key is clicked.

public void startGame() {
	point();
	timer = new Timer(DELAY,this);
	timer.start();
}
Point is the apple; or the destination (point) the snake has to reach to score a point.
Timer and timer.start is used to run the game and defines how fast it is running.

public void paintComponent(Graphics g) {
	super.paintComponent(g);
	draw(g);

}
public void draw(Graphics g) {
	if(running) {

		g.setColor(Color.red);
		g.fillOval(appleX, appleY, DOT_SIZE, DOT_SIZE);

		for(int i=0;i<dots;i++) {
			if (i==0) {
				g.setColor(Color.green);
				g.fillOval(x[i], y[i], DOT_SIZE, DOT_SIZE);
			}
			else {
				g.setColor(Color.green);
				g.fillOval(x[i], y[i], DOT_SIZE, DOT_SIZE);
			}
		}
		g.setColor(Color.blue);
		g.setFont(new Font("Helvetica", Font.BOLD, 50));
		FontMetrics fonts1 = getFontMetrics(g.getFont());
		g.drawString("Score: " +apples, (SCREEN_WIDTH - fonts1.stringWidth("Score: " +apples))/2, g.getFont().getSize());
	}
If the game is running;
A red apple, a green snake and current score will appear on the screen.
g.setColor(Color.red); sets the colour of the apple to red.
g.fillOval(appleX, appleY, DOT_SIZE, DOT_SIZE); creates the apple in oval shape.
for(int i=0;i< dots;i++) { Iterate through the dots to create head and the body of the snake.
	if (i==0) { if i = 0 = head of the snake.
		g.setColor(Color.green); sets the colour of the snake's head to green.
		g.fillOval(x[i], y[i], DOT_SIZE, DOT_SIZE); creates the head of the snake in oval shape.
	}
	else { = body of the snake.
		g.setColor(Color.green); sets the body of the snake to green colour.
		g.fillOval(x[i], y[i], DOT_SIZE, DOT_SIZE); creates the body of the snake in oval shape.
	}
}
g.setColor(Color.blue);
	g.setFont(new Font("Helvetica", Font.BOLD, 50)); Text is bold, set the colour of the text to blue and font size to 50.
	FontMetrics fonts1 = getFontMetrics(g.getFont());
	g.drawString("Score: " +apples, (SCREEN_WIDTH - fonts1.stringWidth("Score: " +apples))/2, g.getFont().getSize());
	Sets the score in the top centre.
}
	else {
		gameOver(g); explained below 'public void gameOver(Graphics g)'
	}
}

public void point() {
	appleX = random.nextInt((int)(SCREEN_WIDTH/DOT_SIZE))*DOT_SIZE; This will have the apple appear randomly on the x-axis
	appleY = random.nextInt((int)(SCREEN_HEIGHT/DOT_SIZE))*DOT_SIZE; This will have the apple appear randomly on the y-axis
}

public void checkApple() {
	if((x[0] == appleX) && (y[0] == appleY)) { if head of the snake hits the apple.
		dots++; increase the length of the snake by one.
		apples++; apple count increase by one.
		point(); add a new apple.
	}
}
public void gameOver(Graphics g) {
	g.setColor(Color.red); Text colour is set to red.
	g.setFont(new Font("Helvetica", Font.BOLD, 50)); "Helvetica" bold font is used to display the message. Font size is 50.
	FontMetrics fonts = getFontMetrics(g.getFont());
	g.drawString("Game Over", (SCREEN_WIDTH - fonts.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2); "Game Over" will be displayed in the centre.
}
@Override
	public void actionPerformed(ActionEvent e) {

		if(running) { If the game is running.
			move(); Move the snake.
			checkApple(); Check if apple is eaten.
			checkCollision(); Check if snake collides with itself or any boarders.
		}
		repaint(); Repaint the screen.
	}

	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					if(direction != 'L') { if the snake is not moving to the left
					direction = 'R'; move to right
				}
				break;
				case KeyEvent.VK_LEFT:
					if(direction != 'R') { if the snake is not moving to the right
						direction = 'L'; move to left
					}
					break;
				case KeyEvent.VK_UP:
					if(direction != 'D') { if the snake is not going down
						direction = 'U'; go up
					}
					break;
				case KeyEvent.VK_DOWN:
					if(direction != 'U') { if the snake is not going up
						direction = 'D'; go down
					}
					break;
			}

		}
	}
}
