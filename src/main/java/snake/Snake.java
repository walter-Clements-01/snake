package snake;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FontFormatException;
import java.io.IOException;

public class Snake 
{
	private static JFrame frame;
	private static MyPanel myPanel;
	public static void main(String[] args) throws IOException, FontFormatException
	{		
		frame = new JFrame("Snake");
		StatePanel statePanel = new StatePanel();
		myPanel = new MyPanel(statePanel);
		frame.add(statePanel, BorderLayout.NORTH);	
		frame.setVisible(true);
		frame.setResizable(false);
		System.out.println(frame.getLayout().toString());
		setPanel(250,20,"null","", false);
	}
	public static void setPanel(int Speed, int Width, String key, String diff, boolean loss)
	{
		if(!diff.equals(""))
			myPanel.setPanel(Speed, Width, diff, loss);
		myPanel.setCurrentPanel(key);
		frame.add(myPanel);
		
		System.out.println(myPanel.getLayout().toString());

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}