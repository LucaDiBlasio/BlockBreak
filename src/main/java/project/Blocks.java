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
        Random rnd=new Random();
        int colorSelect= rnd.nextInt(4);

        switch(colorSelect)
        {
            case 0:
                gr.setColor(Color.blue);
                break;
            case 1:
                gr.setColor(Color.red);
                break;
            case 2:
                gr.setColor(Color.yellow);
                break;
            case 3:
                gr.setColor(Color.green);
                break;
            default:
                gr.setColor(Color.cyan);
                break;
        }
        gr.fillRect(x,y,width,height);
    }

}
