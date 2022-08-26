import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.util.Random;

@SuppressWarnings("serial")

public class SnakePanel extends JPanel implements ActionListener {
	
    final int SCREEN_WIDTH = 700;
    final int SCREEN_HEIGHT = 500;
    final int DOT_SIZE = 20;
    final int TOTAL_DOTS = 1000;
    final int DELAY = 75;
    
    final int x[] = new int[TOTAL_DOTS];
    final int y[] = new int[TOTAL_DOTS];
    
    int dots = 5;
    int apples;
    int appleX;
    int appleY;
    
    char direction = 'D';
    boolean running = true;
    Timer timer;
    Random random; 
    
	SnakePanel() {
		random = new Random();
		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		setBackground(Color.black);
		setFocusable(true);
		addKeyListener(new MyKeyAdapter());
		startGame();
	}
	public void startGame() {
		point();
		timer = new Timer(DELAY,this);
		timer.start();
	}
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
		else {
			gameOver(g);
		}
	}
	public void point() {
		appleX = random.nextInt((int)(SCREEN_WIDTH/DOT_SIZE))*DOT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/DOT_SIZE))*DOT_SIZE;
	}
	public void checkApple() {
		if((x[0] == appleX) && (y[0] == appleY)) {
			dots++;
			apples++;
			point();
		}
	}
	public void move() {
		for(int i = dots;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		switch(direction) {
		case 'U':
			y[0] = y[0] - DOT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + DOT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + DOT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - DOT_SIZE;
			break;
			
		}
	}
	public void checkCollision() {
		// Check if head collides with the body
		for(int i = dots;i>0;i--) {
			if((x[0] == x[i])&& (y[0] == y[i])) {
				running = false;
			}
		}
		// Check if head collides with top boarder
		if(y[0] < 0) {
			running = false;
		}
		// Check if head collides with right boarder
		if(x[0] >= SCREEN_WIDTH) {
			running = false;
		}
		// Check if head collides with bottom boarder
		if(y[0] >= SCREEN_HEIGHT) {
			running = false;
		}
		// Check if head collides with left boarder
		if(x[0] < 0) {
			running = false;
		}
		if(!running) {
			timer.stop();
		}
		
		
	}
	public void gameOver(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("Helvetica", Font.BOLD, 50));
		FontMetrics fonts = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - fonts.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(running) {
			move();
			checkApple();
			checkCollision();
		}
		repaint();
	}

	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';	
				}
				break;
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';	
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';	
				}
				break;
			}
			
		}
	}
}
