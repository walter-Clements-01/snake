package snake;

import javax.swing.*;

public class GameStatus
{
    private  boolean running;
    private String diff;
    private Timer timer;
    public GameStatus()
    {
        running = false;
        diff="";
    }
    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }
    public void setRunning(boolean running)
    {
        this.running=running;
    }
    public boolean getRunning()
    {
        return running;
    }
    public void setTimer(Timer timer)
    {
        this.timer=timer;
    }
    public Timer getTimer()
    {
        return timer;
    }
}
