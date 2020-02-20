package snake;

import java.awt.*;
import java.awt.geom.*;
import java.io.IOException;
import java.util.function.DoubleToIntFunction;

public class Game extends Canvas
{
    private Rectangle2D snakeRect;
    private SnakeInfo snake;
    private Rectangle2D foodRect;
    private Point food;
    //private Timer timer;
    private double width;
    private StatePanel statePanel;
    private GameStatus gameStatus;
    public Game(SnakeInfo snake, double width, StatePanel statePanel, GameStatus gameStatus)
    {
        snakeRect =  new Rectangle2D.Double(-20, -20, 20, 20);
        this.snake = snake;
        foodRect =  new Rectangle2D.Double(-20, -20, 20, 20);
        food = new Point(0, 0);
        this.width=width;
        this.statePanel=statePanel;
        this.gameStatus =gameStatus;
    }
    public void setGame(double width)
    {
        this.width=width;
        snake.clear();
        snake.addBlock(new Point(300, 300));
        snake.addBlock(new Point(280, 300));
        snake.addBlock(new Point(260, 300));
        food.setLocation(500, 300);
    }
    public void paint(Graphics g)
    {
        Graphics2D g2D = (Graphics2D)g;
        if(!snake.isValid())
        {
            gameStatus.getTimer().stop();
            //loss=true;
            Snake.setPanel((int) ((width*1000)/ gameStatus.getTimer().getDelay()), (int) width, "game_to_GameOver",gameStatus.getDiff());
            try {statePanel.setFileRecord(gameStatus.getDiff());} catch (IOException e) {e.printStackTrace();}
            gameStatus.setRunning(false);
        }
        if(snake.getHead().equals(food))
        {

            snake.addBlock();
            statePanel.setLabels();
            do
            {
                //TODO fare in modo che divisione 600/width dia intero
                food.setLocation(Math.round(Math.random()*(600/width))*width, Math.round(Math.random()*(600/width))*width);
            }
            while(snake.isContained(food));
        }
        g2D.setColor(Color.white);
        foodRect.setRect(food.x, food.y, width, width);
        g2D.fill(foodRect);
        for(int i=0;i<snake.getSize();i++)
        {
            snakeRect.setRect(snake.getCoords(i).x, snake.getCoords(i).y, width, width);
            g2D.fill(snakeRect);
        }
    }
}