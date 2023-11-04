package project;

import java.awt.*;


public class Hitter extends Rectangle
{
    int Xvector;
    int Yvector;
    int speed=6;

    Hitter(int x,int y, int HITTER_WIDTH, int HITTER_HEIGHT)
    {
        super(x,y,HITTER_WIDTH,HITTER_HEIGHT);
    }

    public void setDeltaX(int direction)
    {
        Xvector = direction*speed;
    }

    public void setDeltaY(int direction)
    {
        Yvector=direction*speed;
    }

    public void move()
    {
        x+=Xvector;
        y+=Yvector;
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.CYAN);
        g.fillRect(x,y,width,height);
    }
}
