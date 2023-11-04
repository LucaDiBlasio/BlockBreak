package project;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle
{
    Random rnd;
    double ballSpeed=3.5;
    double dX;
    double dY;
    int vectorX;
    int vectorY;

    Ball(int x, int y, int width,int height)
    {
        super(x,y,width,height);
        rnd = new Random();

        vectorX=rnd.nextInt(2);
        if(vectorX ==0)
        {
            vectorX=-1;
        }
        setDX(vectorX);

        vectorY=rnd.nextInt(2);
        if(vectorY ==0)
        {
            vectorY=-1;
        }
        setDY(vectorY);
    }

    public void setDX(double vectorX)
    {
        dX=vectorX*ballSpeed;
    }

    public void setDY(double vectorY)
    {
        dY=vectorY*ballSpeed;
    }

    public void move()
    {
        x+=dX;
        y+=dY;
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillOval(x,y,width,height);
    }

}
