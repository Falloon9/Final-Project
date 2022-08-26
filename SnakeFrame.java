import javax.swing.JFrame;
import java.awt.EventQueue;

@SuppressWarnings("serial")

public class SnakeFrame extends JFrame{
	
	SnakeFrame(){ 
	
		this.add(new SnakePanel());
		this.setTitle("Snake Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new SnakeFrame();
            ex.setVisible(true);
        });
	}
}
