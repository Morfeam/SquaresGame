/**
 * Write a description of class Rectangle here.
 * 
 * @author Matthew Morfea
 * @version May 22, 2015
 */
import java.awt.*;
import java.applet.*;
import java.util.*;
public class Paddle
{
    // instance variables - replace the example below with your own
    private int width;
    private int height;
    private int x;
    private int y;
    private int moveX;
    private int moveY;
    private int right;
    private int bottom;
    Graphics g;
    /**
     * Default Constructor for objects of class Ball
    */
    public Paddle()
    {
        width = 20;
        height = 20;
        x = 350;
        y = 575;
        moveX = 8;
        moveY = 8;
        right = x + width;
        bottom = y + height;
    }//end defualt constructor
    //alternate constructor
    public Paddle(int rX,int rY, int w,int h, int mY,int mX)
    {
        width = w;
        height = h;
        x = rX;
        y = rY;
        moveX = mX;
         moveY = mY;
        right = x + width;
        bottom = y + height;
       
    }//end aqlternate constructor
    /**
     * getMethods
     */
    public int getWidth()
    {
       return width;
    }
    public int getHeight()
    {
       return height;
    }
    public int getX()
    {
       return x;
    }
    public int getY()
    {
       return y;
    }
    public int getRight()
    {
       return right;
    }
    public int getBottom()
    {
       return bottom;
    }
    //logic methods that will allow us to move our Ball
    //objects and check for collisions
    public void movePaddle(int w)
    {
        x = x + moveX;
        if(x+width >= w || x <= 0)
        {
            moveX = moveX*-1;
        }
        update();
    }
    public void mMovePaddle(int mX,int mY)
    {
        x = mX;
        y = mY;
        update();
    }
    public void moveUp()
    {
        y = y - moveY;
        if(y + 20 <= 0)
        {
            y = 610;
        }
        update();
    }
    public void moveDown()
    {
        y = y + moveY;
        if(y >= 600)
        {
            y = -50; 
        }
        update();
    }
    public void moveRight()
    {
        x = x + moveX;
        if(x >= 820)
        {
            x = -50; 
        }
        update();
    }
    public void moveLeft()
    {
        x = x - moveX;
        
        if(x + 20 <= 0)
        {
            x = 820;
        }
        update();
    }
    public void update()
    {
        right = x + width;
        bottom = y + height;
    }
    
    public boolean ballCollision(Ball B)
    {
        boolean t = false;
        double dis;
        int bRadius = B.getRadius();
        int bCentX = B.getCenterX();
        int bCentY = B.getCenterY();
        
        //use distance formula
        int xsq,ysq;
        //check collision with the left (x, [y to y + height])
        xsq = (bCentX - x)*(bCentX - x);
        for(int i = y; i <= y+height;i++)
        {
            ysq = (bCentY - i)*(bCentY - i);
            dis = Math.sqrt(xsq + ysq);//distance formula
            if(dis <= bRadius)
            {
                B.changeXDirection();
                t = true;
                break;
            }
        }
        //Check Right side for collision (x+width, [y to y + height])
        xsq = (bCentX - (x + width))*(bCentX - (x + width));
        for(int i = y; i <= y+height;i++)
        {
            ysq = (bCentY - i)*(bCentY - i);
            dis = Math.sqrt(xsq + ysq);//distance formula
            if(dis <= bRadius)
            {
                B.changeXDirection();
                t = true;
                break;
            }
        }
        //check the top ([x to x + width], y)
        ysq = (bCentY - y)*(bCentY - y);
        for(int i = x; i <= x + width;i++)
        {
            xsq = (bCentX - i)*(bCentX - i);
            dis = Math.sqrt(xsq + ysq);//distance formula
            if(dis <= bRadius)
            {
                B.changeYDirection();
                t = true;
                break;
            }
        }
        //check bottom
        ysq = (bCentY - (y+height))*(bCentY - (y+height));
        for(int i = x; i <= x + width;i++)
        {
            xsq = (bCentX - i)*(bCentX - i);
            dis = Math.sqrt(xsq + ysq);//distance formula
            if(dis <= bRadius)
            {
                B.changeYDirection();
                t = true;
                break;
            }
        }
        return t;
    }
    public boolean rectCollision(Rectangle R)
    {
        boolean t = false;
        int x2 = R.getX();
        int y2 = R.getY();
        int w2 = R.getWidth();
        int h2 = R.getHeight();
        
        //top of r1 with bootom of r2
        for(int i = x; i < x + width; i++)
        {
            if(y2 + h2 > y && y2 < y + height)
            {
                if(i > x2 && i < x2+w2)
                {
                    //changeYDirection();
                   // R.changeYDirection();
                   t = true;
                }
            }
        }
        for(int i = x; i < x + width; i++)
        {
            if(y2 < y + height && y2 + h2 > y + height)
            {
                if(i > x2 && i < x2+width)
                {
                    //changeYDirection();
                   // R.changeYDirection();
                   t = true;
                }
            }
        }
         for(int i = y; i < y + height; i++)
        {
            if(x2  + w2 > x && x2 < x + width)
            {
                if(i > y2 && i < y2+h2)
                {
                   // changeXDirection();
                   // R.changeXDirection();
                   t = true;
                }
            }
        }
         for(int i = y; i < y + height; i++)
        {
            if(x2 < x + width && x2 + w2 > x + width)
            {
                if(i > y2 && i < y2+width)
                {
                   // changeXDirection();
                   // R.changeXDirection();
                   t = true;
                }
            }
        }
        return t;
    }
     public void drawBall()//Use g as the graphics g.*******
    {
       g.setColor(Color.orange);
       g.fillOval(x,y,width,height);
    }//end drawRect
    public void paint(Graphics gr)
    {
      g = gr;
      drawBall();
    }//end paint    
}//end class 
