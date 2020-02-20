package  snake;

import util.ScoresUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Listener implements KeyListener, ActionListener
{
    private char key, keep;
    private Game canvas;
    private boolean start;
    private SnakeInfo snake;
    private double width;
    private StatePanel statePanel;
    private GameStatus gameStatus;

    public Listener(/*Timer timer,*/ Game canvas, SnakeInfo snake, StatePanel statePanel, double width, GameStatus gameStatus)
    {
        key = ' ';
        keep = 'd';
        this.canvas = canvas;
        this.snake = snake;
        this.statePanel=statePanel;
        this.width=width;
        this.gameStatus = gameStatus;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_UP || event.getKeyChar() == 'w')
            key = 'w';

        if (event.getKeyCode() == KeyEvent.VK_DOWN || event.getKeyChar() == 's')
            key = 's';

        if (event.getKeyCode() == KeyEvent.VK_LEFT || event.getKeyChar() == 'a')
            key = 'a';

        if (event.getKeyCode() == KeyEvent.VK_RIGHT || event.getKeyChar() == 'd')
            key = 'd';

        //timer.start();
        gameStatus.getTimer().start();
        canvas.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameStatus.getRunning()) {
            switch (key) {
                case 'w': {
                    if (keep != 's') {
                        snake.move("up");
                        keep = 'w';
                    } else {
                        snake.move("down");
                        key = 's';
                        keep = 's';
                    }
                    start = false;
                }
                break;
                case 's': {
                    if (keep != 'w') {
                        snake.move("down");
                        keep = 's';
                    } else {
                        snake.move("up");
                        key = 'w';
                        keep = 'w';
                    }
                    start = false;
                }
                break;
                case 'a': {
                    if (keep != 'd') {
                        snake.move("left");
                        keep = 'a';
                    } else {
                        snake.move("right");
                        key = 'd';
                        keep = 'd';
                    }
                    start = false;
                }
                break;
                case 'd': {
                    if (keep != 'a') {
                        snake.move("right");
                        keep = 'd';
                    } else {
                        snake.move("left");
                        key = 'a';
                        keep = 'a';
                    }
                    start = false;
                }
                break;
                default: {
                    if (start) {
                        snake.move("right");
                        key = 'd';
                        keep = 'd';
                    }

                }
            }
            canvas.repaint();
        } else {
            gameStatus.setDiff(e.getActionCommand());
            switch (e.getActionCommand()) {
                case "Slug": {
                    gameStatus.setDiff(e.getActionCommand());
                    keep = 'd';
                    gameStatus.setRunning(true);
                    start = true;
                    //loss=false;
                    Snake.setPanel(100, 20, "levels_to_game", gameStatus.getDiff(), false);
                    try {
                        setInitRecord("Slug");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                break;
                case "Worm": {
                    gameStatus.setDiff(e.getActionCommand());
                    keep = 'd';
                    start = true;
                    gameStatus.setRunning(true);
                    //loss = false;
                    Snake.setPanel(250, 20, "levels_to_game", gameStatus.getDiff(), false);
                    try {
                        setInitRecord("Worm");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                break;
                case "Python": {
                    gameStatus.setDiff(e.getActionCommand());
                    keep = 'd';
                    start = true;
                    gameStatus.setRunning(true);
                    //loss = false;
                    Snake.setPanel(500, 20, "levels_to_game", gameStatus.getDiff(), false);
                    try {
                        setInitRecord("Python");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                break;
                case "Black Mamba": {
                    gameStatus.setDiff(e.getActionCommand());
                    keep = 'd';
                    start = true;
                    gameStatus.setRunning(true);
                    //loss = false;
                    Snake.setPanel(875, 20, "levels_to_game", gameStatus.getDiff(), false);
                    try {
                        setInitRecord("Black Mamba");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                break;
                case "Retry": {
                    keep = 'd';
                    start = true;
                    gameStatus.setRunning(true);
                    //boolean loss = false;
                    Snake.setPanel((int) ((width * 1000) / gameStatus.getTimer().getDelay()), (int) width, "GameOver_to_game", gameStatus.getDiff(), false);
                }
                break;
                case "Levels": {
                    gameStatus.setDiff("");
                    gameStatus.setRunning(false);
                    //loss = false;
                    Snake.setPanel(600, 20, "GameOver_to_levels", gameStatus.getDiff(), false);
                }
                break;
            }
        }
    }
    public void setInitRecord(String diff) throws IOException
    {
        Scores scores = ScoresUtil.load();
        switch(diff)
        {
            case "Slug": statePanel.setLabelRecord(scores.getSlug().toString());break;
            case "Worm": statePanel.setLabelRecord(scores.getWorm().toString());break;
            case "Python": statePanel.setLabelRecord(scores.getPython().toString());break;
            case "Black Mamba": statePanel.setLabelRecord(scores.getBlackMamba().toString());break;
        }
    }
}