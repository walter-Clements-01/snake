package util;

import snake.Scores;

import java.io.*;

public class ScoresUtil
{
public static Scores load()
{
    Scores scores;
    try
    {
        FileInputStream fileIn = new FileInputStream("scores.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        scores = (Scores) in.readObject();
        in.close();
        fileIn.close();
    }
    catch (IOException i)
    {
        scores= new Scores();
        i.printStackTrace();
    } catch (ClassNotFoundException c)
    {
        scores= new Scores();
        System.out.println("Employee class not found");
        c.printStackTrace();
    }
    return scores;
}
public static void save(Scores scores)
{
    try
    {
        FileOutputStream fileOut = new FileOutputStream("scores.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(scores);
        out.close();
        fileOut.close();
    }
    catch (IOException i)
    {
        i.printStackTrace();
    }
}
}