package snake;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.io.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class MyPanel extends JPanel 
{
	private  double width;
	private  Listener listener;
	private  Timer timer;
	private  boolean loss;
	private  Game canvas;
	private GameStatus gameStatus;
	private  JPanel LevelPanel;
	private  JPanel GameOverPanel;
	private StatePanel statePanel;
	private SnakeInfo snakeInfo;
	public MyPanel(StatePanel statePanel) throws IOException, FontFormatException
	{
		this.statePanel=statePanel;
		snakeInfo = new SnakeInfo();
		gameStatus = new GameStatus();
		canvas = new Game(snakeInfo, width, statePanel, gameStatus);
		listener = new Listener(canvas, snakeInfo, statePanel, width, gameStatus);
		timer = new Timer(0,listener);
		gameStatus.setTimer(timer);
		loss= false;
		canvas.setBackground(Color.black);
		canvas.setPreferredSize(new Dimension(600,600));
		canvas.setFocusable(true);
		canvas.addKeyListener(listener);
		try 
		{
			LevelPanel = setLevelPanel();
			GameOverPanel = setGameOverPanel();
			GameOverPanel.setBackground(new Color(0,0,0,65));
		} catch (IOException | FontFormatException e) {
			System.out.println(e);
		}
		LevelPanel.setPreferredSize(new Dimension(600,600));
		GameOverPanel.setPreferredSize(new Dimension(600,600));
		add(LevelPanel);
		setBackground(Color.black);
	}
	private  JPanel setLevelPanel() throws IOException, FontFormatException
	{
		Font arcadeBold = FontManager.getFont("BOLD", 80);
		Font arcadePlain = FontManager.getFont("PLAIN", 40);
		Font GMSFont = FontManager.getFont("PLAIN", 35);
		
		JPanel Level= new JPanel();
		Level.setBackground(Color.black);
		
		JButton level = new JButton("Levels");
		level.setRequestFocusEnabled(false);
		level.setContentAreaFilled(false);
		level.setBorderPainted(false);
		level.setBackground(Color.black);
		level.setForeground(Color.white);
		level.setFont(arcadeBold);
		
		JButton slug = new JButton("Slug");
		slug.setRequestFocusEnabled(false);
		slug.setContentAreaFilled(false);
		slug.setBorderPainted(false);
		slug.setForeground(Color.white);
		slug.setFont(arcadePlain);
		slug.addActionListener(listener);
		
		JButton worm = new JButton("Worm");
		worm.setRequestFocusEnabled(false);
		worm.setContentAreaFilled(false);
		worm.setBorderPainted(false);
		worm.setForeground(Color.white);
		worm.setFont(arcadePlain);
		worm.addActionListener(listener);
		
		JButton python = new JButton("Python");
		python.setRequestFocusEnabled(false);
		python.setContentAreaFilled(false);
		python.setBorderPainted(false);
		python.setForeground(Color.white);
		python.setFont(arcadePlain);
		python.addActionListener(listener);
		
		JButton blackMamba = new JButton("Black Mamba");
		blackMamba.setRequestFocusEnabled(false);
		blackMamba.setContentAreaFilled(false);
		blackMamba.setBorderPainted(false);
		blackMamba.setForeground(Color.white);
		blackMamba.setFont(GMSFont);
		blackMamba.addActionListener(listener);
		
		Level.setLayout(new GridLayout(5,1));
		Level.add(level);
		Level.add(slug);
		Level.add(worm);
		Level.add(python);
		Level.add(blackMamba);
		
		return Level;
	}
	private JPanel setGameOverPanel() throws IOException, FontFormatException
	{
		Font arcadeBold = FontManager.getFont("BOLD", 75);
		Font arcadePlain = FontManager.getFont("PLAIN", 40);

		JPanel GameOverPanel= new JPanel();
		GameOverPanel.setBackground(new Color(255,255,255,65));
		
		JButton GameOver = new JButton("Game Over");
		GameOver.setFont(arcadeBold);
		GameOver.setRequestFocusEnabled(false);
		GameOver.setFocusPainted(false);
		GameOver.setContentAreaFilled(false);
		GameOver.setBorderPainted(false);
		GameOver.setBackground(Color.black);
		GameOver.setForeground(Color.white);
		
		JPanel restart = new JPanel();
		restart.setBackground(Color.black);
		
		JButton retry = new JButton("Retry");
		retry.setRequestFocusEnabled(false);
		retry.setFocusPainted(false);
		retry.setContentAreaFilled(false);
		retry.setBorderPainted(false);
		retry.setForeground(Color.white);
		retry.setFont(arcadePlain);
		retry.addActionListener(listener);
		
		JButton levels = new JButton("Levels");
		levels.setRequestFocusEnabled(false);
		levels.setContentAreaFilled(false);
		levels.setBorderPainted(false);
		levels.setForeground(Color.white);
		levels.setFont(arcadePlain);
		levels.addActionListener(listener);
		
		restart.add(retry);
		restart.add(levels);
		
		GameOverPanel.setLayout(new GridLayout(2,1));
		GameOverPanel.add(GameOver);
		GameOverPanel.add(restart);
		
		return GameOverPanel;
	}
	public void setPanel(double Speed, double width, String diff)
	{
		gameStatus.setDiff(diff); //TODO
		this.width=width;
		if(!loss)
		{
			canvas.setGame(width);
			canvas.repaint();
		}
		timer.setDelay((int)(width/(Speed/1000)));
	}
	public void setCurrentPanel(String key)
	{
		switch(key)
		{
			case "levels_to_game": 
			{
				remove(LevelPanel);
				revalidate();
				repaint();
				add(canvas);
				canvas.requestFocus();
				canvas.setFocusable(true);
			}break;
			case "levels_to_pers":
			{
				remove(LevelPanel);
				revalidate();
				repaint();
				//add(PersPanel);
			}break;
			case "game_to_GameOver":
			{
				setLayout(new CardLayout());
				
				add(GameOverPanel);
				add(canvas);
				canvas.requestFocus();
			}break;
			case "GameOver_to_game":
			{
				statePanel.resetFoodEaten();
				remove(GameOverPanel);
				revalidate();
				repaint();
				canvas.requestFocus();
				canvas.setFocusable(true);
			}break;
			case "GameOver_to_levels":
			{
				statePanel.resetFoodEaten();
				remove(GameOverPanel);
				revalidate();
				repaint();
				remove(canvas);
				revalidate();
				repaint();
				add(LevelPanel);
			}break;
		}
	}
}