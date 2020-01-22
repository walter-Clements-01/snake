package snake;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class SnakeInfo
{
    private List<Point> snakeCoords;
    public SnakeInfo()
    {
        snakeCoords= new ArrayList<>();
    }
    public void addBlock(Point p)
    {
        snakeCoords.add(p);
    }
    public void addBlock()
    {
        snakeCoords.add(new Point(0, 0));
    }
    public boolean isValid()
    {
        List<Point> temp = new ArrayList<>(snakeCoords);
        temp.remove(0);
        return(!temp.contains(snakeCoords.get(0)));
    }
    public boolean isContained(Point p)
    {
        return(snakeCoords.contains(p));
    }
    public void move(String dir)
    {
        for(int i=snakeCoords.size()-1; i>0; i--)
            snakeCoords.set(i, snakeCoords.get(i-1));
        switch(dir)
        {
            case "right": snakeCoords.set(0, new Point(snakeCoords.get(0).x +20, snakeCoords.get(0).y));break;
            case "left": snakeCoords.set(0, new Point(snakeCoords.get(0).x -20, snakeCoords.get(0).y));break;
            case "up": snakeCoords.set(0, new Point(snakeCoords.get(0).x, snakeCoords.get(0).y -20));break;
            case "down": snakeCoords.set(0, new Point(snakeCoords.get(0).x, snakeCoords.get(0).y +20));break;
        }
    }
    public Point getCoords(int index)
    {
        return snakeCoords.get(index);
    }
    public int getSize()
    {
        return snakeCoords.size();
    }
    public void clear()
    {
        snakeCoords.clear();
    }
}

