import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BadFrame extends JFrame implements ActionListener {

	private JTextField numberField;
	private JButton startButton;

	public BadFrame() {
		this.setSize(500,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		numberField = new JTextField("50",20);
		startButton = new JButton("Start");
		startButton.addActionListener(this);

		JPanel contentPanel = new JPanel();
		contentPanel.add(numberField);
		contentPanel.add(startButton);
		this.add(contentPanel);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		int number = Integer.parseInt(numberField.getText());
		for(int i = number;i >= 0;i--) {
			// Pause code for 200/1000 second
			// needs to catch InterruptedExceptions
			// (more on this later)
			try {
				Thread.sleep(200);
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			// Set the text every second
			numberField.setText(""+i);
			// System.out.println(i);
		}
	}

	public static void main(String[] args) {
		new BadFrame();
	}
}