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
    public void addBlock(int x, int y) {
        snakeCoords.add(new Point(x, y));
    }
    public boolean isValid()
    {
        List<Point> temp = new ArrayList<>(snakeCoords);
        temp.remove(0);
        return(temp.contains(snakeCoords.get(0)));
    }
    public void moveRight(){
        addBlock(snakeCoords)
    }
}

