/**
 * Write a description of class Rectangle here.
 * 
 * @author Matthew Morfea
 * @version may 22, 2015
 */
import java.awt.*;
import java.applet.*;
import java.util.*;
public class Rectangle
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
    private int c;
    Graphics g;
    Random gen = new Random();
    
    /**
     * Default Constructor for objects of class Ball
     */
    public Rectangle(int cl)
    {
        width = 25;
        height = 25;
        x = gen.nextInt(750);
        y = gen.nextInt(550);
        moveX = gen.nextInt(6) + 1;
        moveY = gen.nextInt(6) + 1;
        right = x + width;
        bottom = y + height;
        if(cl == -1)
        {
            c = gen.nextInt(5);
        }else{
            c = cl;
        }
        }
    //alternate constructor
    public Rectangle(int rX,int rY, int w,int h, int mY,int mX)
    {
        width = w;
        height = h;
        x = rX;
        y = rY;
        moveX = mX;
        moveY = mY;
        right = x + width;
        bottom = y + height;
        c = gen.nextInt(25);
    }
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
    /*public void moveRectangle(int w, int h)
    {
        x = x + moveX;
        y = y + moveY;
        if(x+diameter >= w || x <= 0)
        {
            moveX = moveX*-1;
        }
        if(y+ diameter >= h || y <= 0)
        {
            moveY = moveY*-1;
        }
        update();
    }
    public void update()
    {
        centerX = x + radius;
        centerY = y + radius;
    }*/
    public void setColor(int cl)
    {
        c = cl;
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
    /*
    public void changeDirection()
    {
        moveX = moveX*-1;
        moveY = moveY*-1;
    }*/
     public void drawRect()//Use g as the graphics g.*******
    {
       
       if(c == 4 )
       {
           g.setColor(Color.green);
       }else{
           if(c  == 3)
       {
           g.setColor(Color.red);
       }else{
           if(c  == 2)
       {
           g.setColor(Color.cyan);
       }else{
           if(c  == 1)
       {
           g.setColor(Color.yellow);
       }else{
           g.setColor(Color.gray);
           }
          }
         }
       }
       g.fillRect(x,y,width,height);
       
    }
    public void paint(Graphics gr)
    {
      g = gr;
      drawRect();
    }    
        
    
}
