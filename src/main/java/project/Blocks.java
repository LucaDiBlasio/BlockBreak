package project;

import java.awt.*;
import java.util.Random;

public class Blocks extends Rectangle
{
    int dx;
    int dy;
    int speed=8;
    int row;
    int column;




    Blocks(int row,int column,int blocksWidth,int blocksHeight)
    {
        super(((row*blocksWidth)+(row+1)),(column*blocksHeight)+(column+1)+(blocksHeight*3),blocksWidth,blocksHeight);
        this.row=row;
        this.column =column;
    }

    public void setDeltaY(int yDirection) {
        dy = yDirection*speed;
    }

    public void setDeltaX(int xDirection) {
        dx = xDirection*speed;
    }

    public void move() {
        y = y + dy;
        x = x + dx;
    }
    public void draw(Graphics gr)
    {
        if (this.row==0 || this.row%4==0)
        {
            if(this.column%4==0)
            {
                gr.setColor(Color.green);
            }
            else
            {
                if(this.column%4==1)
                {
                    gr.setColor(Color.yellow);
                }
                else
                {
                    if(this.column%2==0)
                    {
                        gr.setColor(Color.red);
                    }
                    else
                    {
                        gr.setColor(Color.blue);
                    }
                }
            }

        }
        if(this.row==1||this.row%4==1)
        {
            if(this.column%4==0)
            {
                gr.setColor(Color.yellow);
            }
            else
            {
                if(this.column%4==1)
                {
                    gr.setColor(Color.red);
                }
                else
                {
                    if(this.column%2==0)
                    {
                        gr.setColor(Color.blue);
                    }
                    else
                    {
                        gr.setColor(Color.green);
                    }
                }
            }
        }
        if(this.row==2||this.row%4==2)
        {
            if(this.column%4==0)
            {
                gr.setColor(Color.blue);
            }
            else
            {
                if(this.column%4==1)
                {
                    gr.setColor(Color.green);
                }
                else
                {
                    if(this.column%2==0)
                    {
                        gr.setColor(Color.yellow);
                    }
                    else
                    {
                        gr.setColor(Color.red);
                    }
                }
            }
        }
        if(this.row==3||this.row%4==3)
        {
            if(this.column%4==0)
            {
                gr.setColor(Color.red);
            }
            else
            {
                if(this.column%4==1)
                {
                    gr.setColor(Color.blue);
                }
                else
                {
                    if(this.column%2==0)
                    {
                        gr.setColor(Color.green);
                    }
                    else
                    {
                        gr.setColor(Color.yellow);
                    }
                }
            }
        }

        gr.fillRect(x,y,width,height);
    }

}
