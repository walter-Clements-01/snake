package snake;

import util.ScoresUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.*;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class StatePanel extends JPanel
{	
	private  JLabel foodEaten;
	private  JLabel trophy;
	private  JLabel record;
	private  JPanel statePanel; 	
	public StatePanel() throws IOException, FontFormatException
	{
		foodEaten= new JLabel("0");
		trophy= new JLabel(new ImageIcon("/trophy.png"));
		setLayout(new BorderLayout());	
		record = new JLabel();
		statePanel = setGridStatePanel();
		add(statePanel, "Center");
		this.setBorder(BorderFactory.createLineBorder(Color.white));
	}	
	private JPanel setGridStatePanel() throws IOException, FontFormatException
	{
		
		Font arcadeBold = FontManager.getFont("BOLD", 50);
		JPanel GridStatePanel = new JPanel();
		GridStatePanel.setBackground(Color.black);
				
		JPanel FE = new JPanel();
		FE.setBackground(Color.black);
		FE.add(foodEaten);
		
		JPanel bestScore = new JPanel();
		bestScore.setBackground(Color.black);
		bestScore.add(trophy);
		bestScore.add(record);

		foodEaten.setFont(arcadeBold);
		foodEaten.setForeground(Color.white);
		record.setFont(arcadeBold);
		record.setForeground(Color.white);
		trophy.setFont(arcadeBold);
		
		GridStatePanel.setLayout(new BorderLayout());
		GridStatePanel.add(FE, "West");
		GridStatePanel.add(bestScore, "East");
		
		return GridStatePanel;
	}
	public void setLabels()
	{
		foodEaten.setText(Integer.toString((Integer.parseInt(foodEaten.getText())+1)));
		if(Integer.parseInt(foodEaten.getText())>Integer.parseInt(record.getText())) {
			record.setText(foodEaten.getText());
		}
	}
//	public String getFoodEaten()
//	{
//		return foodEaten.getText();
//	}
	public  void setLabelRecord(String rec)
	{
		record.setText(rec);
		System.out.println("set record"+rec);
	}
	public void setFileRecord(String diff) throws IOException
	{
		Scores scores = ScoresUtil.load();
		Integer scoreToUpdate = Integer.parseInt(record.getText());
		switch(diff)
		{
			case "Slug": scores.setSlug(scoreToUpdate);break;
			case "Worm": scores.setWorm(scoreToUpdate);break;
			case "Python": scores.setPython(scoreToUpdate);break;
			case "Black Mamba": scores.setBlackMamba(scoreToUpdate);break;
		}
		ScoresUtil.save(scores);
	}
	public void resetFoodEaten()
	{
		foodEaten.setText("0");
	}	
}