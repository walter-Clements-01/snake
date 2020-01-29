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
        snakeCoords.add(new Point(-20, -20));
    }
    public boolean isValid()
    {
        boolean isValid = false;
        List<Point> temp = new ArrayList<>(snakeCoords);
        temp.remove(0);
        if(!temp.contains(getHead()) && !isOverLimit())
            isValid=true;
        return(isValid);
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
    public Point getHead()
    {
        return getCoords(0);
    }
    public void clear()
    {
        snakeCoords.clear();
    }
    //TODO update field width
    public boolean isOverLimit()
    {
        if(getHead().getX()>600 || getHead().getX()<0 || getHead().getY()>600 || getHead().getY()<0)
            return true;
        else
            return false;
    }
}

