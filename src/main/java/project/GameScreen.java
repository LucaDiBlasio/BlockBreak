package project;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameScreen extends JPanel implements Runnable
{
    static final int GAME_WIDTH=560;
    static final int GAME_HEIGHT=600;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int HITTER_WIDTH=70;
    static final int HITTER_HEIGHT=9;
    static final int BALL_DIAMETER=12;

    static final int rows = 17;
    static final int columns=9;

    static final int blockWidth=32;
    static final int blockHeight=12;

    static final int DISTANZA = 20;

    int hits;
    int lives = 2;
    int blocksNum=rows*columns;
    boolean allCleared;
    boolean gameOver=false;
    boolean victory=false;

    Hitter hitter;
    Ball ball;
    Blocks[][] blocks;

    Thread thread;
    Graphics graphics;
    Random rand;

    GameScreen()
    {
        rand = new Random();
        blocks=new Blocks[rows][columns];
        newBall();
        newHitter();
        newBlocks();

        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        this.addKeyListener(new AL());

        thread=new Thread(this);
        thread.start();

    }

    public void newBall()
    {
        ball=new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),(GAME_HEIGHT/2)-(BALL_DIAMETER/2),BALL_DIAMETER,BALL_DIAMETER);
        ball.setDY(1);
    }

    public void newHitter()
    {
        hitter=new Hitter((GAME_WIDTH-HITTER_WIDTH)/2,GAME_HEIGHT-(HITTER_HEIGHT-DISTANZA/2)-50,HITTER_WIDTH,HITTER_HEIGHT);
    }

    public void newBlocks()
    {
        for (int i =0;i<rows;i++)
        {
            for (int j=0;j<columns;j++)
            {
                blocks[i][j]=new Blocks(i,j,blockWidth,blockHeight);
            }
        }
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        BufferedImage buffer = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
        graphics = buffer.getGraphics();
        draw(graphics);
        g.drawImage(buffer,0,0,this);
    }

    public void draw(Graphics g)
    {
        ball.draw(g);
        hitter.draw(g);
        for (int i =0;i<rows;i++)
        {
            for (int j=0;j<columns;j++)
            {
                if(blocks[i][j]!=null)
                {
                    blocks[i][j].draw(g);
                    allCleared=false;
                }

            }
        }
        if(gameOver)
        {
            String gameOver = "GAME OVER";
            String backToStart = "Press R to Restart or E to exit the game";
            graphics.setFont(new Font("Times New Roman",Font.ITALIC | Font.BOLD,72));
            graphics.setColor(Color.red);
            graphics.drawString(gameOver,(GAME_WIDTH/7)-10,(GAME_HEIGHT/2));
            graphics.setFont(new Font("Times New Roman",Font.BOLD,15));
            graphics.setColor(Color.white);
            graphics.drawString(backToStart,(GAME_WIDTH/3)-30,(GAME_HEIGHT/2)+17);
            repaint();
        }
        if(victory)
        {
            String win = "YOU WIN";
            String backToStart = "Press R to Restart or E to exit the game";
            graphics.setFont(new Font("Times New Roman",Font.ITALIC | Font.BOLD,72));
            graphics.setColor(Color.red);
            graphics.drawString(win,(GAME_WIDTH/5)+18,(GAME_HEIGHT/2)-15);
            graphics.setFont(new Font("Times New Roman",Font.BOLD,15));
            graphics.setColor(Color.white);
            graphics.drawString(backToStart,(GAME_WIDTH/3)-30,(GAME_HEIGHT/2)+4);
            repaint();

        }
        Toolkit.getDefaultToolkit().sync();
    }

    public void move()
    {
        ball.move();
        hitter.move();
    }

    public void collisions() {
        if (hitter.x >= GAME_WIDTH - HITTER_WIDTH) {
            hitter.x = GAME_WIDTH - HITTER_WIDTH;
        }

        if (hitter.x <= 0) {
            hitter.x = 0;
        }

        if (ball.y <= 0) {
            ball.dY = -ball.dY;
        }

        if (ball.x <= 0) {
            ball.dX = -ball.dX;
        }

        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
            ball.dY = -ball.dY;

            if (lives > 0) {
                lives--;
                newBall();
            } else {
                ball.dX = 0;
                ball.dY = 0;
                gameOver = true;
            }
        }

        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            ball.dX = -ball.dX;
        }

        if (ball.intersects(hitter)) {
            hits++;
            double inclination;

            if (ball.x + (BALL_DIAMETER / 2) <= hitter.x + (HITTER_WIDTH / 8)) {
                inclination = -1.6;
            } else {
                if (ball.x + (BALL_DIAMETER / 2) <= hitter.x + (HITTER_WIDTH / 8) * 2) {
                    inclination = -1.4;
                } else {
                    if (ball.x + (BALL_DIAMETER / 2) <= hitter.x + (HITTER_WIDTH / 8) * 3) {
                        inclination = -0.7;
                    } else {
                        if (ball.x + (BALL_DIAMETER / 2) <= hitter.x + (HITTER_WIDTH / 8) * 4) {
                            inclination = -0.55;
                        } else {
                            if (ball.x + (BALL_DIAMETER / 2) <= hitter.x + (HITTER_WIDTH / 8) * 5) {
                                inclination = 0.55;

                                if (rand.nextInt(2) == 0) {
                                    inclination *= -1;
                                }
                            } else {
                                if (ball.x + (BALL_DIAMETER / 2) <= hitter.x + (HITTER_WIDTH / 8) * 6) {
                                    inclination = 0.7;
                                } else {
                                    if (ball.x + (BALL_DIAMETER / 2) <= hitter.x + (HITTER_WIDTH / 8) * 7) {
                                        inclination = 1.4;
                                    } else {
                                        inclination = 1.6;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            ball.dY=-ball.dY;
            ball.setDX(inclination);
        }


                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        if (blocks[i][j] != null) {
                            if (ball.intersects(blocks[i][j])) {
                                ball.dY = -ball.dY;
                                blocks[i][j] = null;
                                blocksNum--;
                                if(blocksNum==0)
                                {
                                    ball.dX = 0;
                                    ball.dY = 0;
                                    victory=true;
                                }
                            }
                        }
                    }
                }
            }


    public class AL extends KeyAdapter
    {
        public void keyPressed(KeyEvent key)
        {
            if(key.getKeyCode()==KeyEvent.VK_LEFT)
            {
                hitter.setDeltaX(-1);
            }
            if(key.getKeyCode()==KeyEvent.VK_RIGHT)
            {
                hitter.setDeltaX(1);
            }

            if(gameOver)
            {
                if(key.getKeyCode()==KeyEvent.VK_R)
                {
                    newHitter();
                    newBall();
                    newBlocks();
                    gameOver=false;
                    lives=2;
                }
                if(key.getKeyCode()==KeyEvent.VK_E)
                {
                    System.exit(0);
                }
            }
            if(victory) {
                if (key.getKeyCode() == KeyEvent.VK_R) {
                    newHitter();
                    newBall();
                    newBlocks();
                    gameOver = false;
                    lives = 2;
                }
                if (key.getKeyCode() == KeyEvent.VK_E) {
                    System.exit(0);
                }
            }
        }

        public void keyReleased(KeyEvent key)
        {
            if(key.getKeyCode()==KeyEvent.VK_LEFT)
            {
                hitter.setDeltaX(0);
            }
            if(key.getKeyCode()==KeyEvent.VK_RIGHT)
            {
                hitter.setDeltaX(0);
            }

        }
    }

    public void run()
    {
        long lastTime = System.nanoTime();
        double ticks =60;
        double duration=1000000000/ticks;
        double delta=0;

        while (true)
        {
            long now = System.nanoTime();
            delta=delta+(now-lastTime)/duration;
            lastTime=now;

            if(delta>=1)
            {
                move();
                collisions();
                repaint();
                delta--;
            }
        }
    }
}
