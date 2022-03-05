/**
 * Write a description of class Ball here.
 * 
 * @author Matthew Morfea 
 * @version May 22, 2015
 */
import java.awt.*;
import java.applet.*;
import java.util.*;
public class Ball
{
    // instance variables - replace the example below with your own
    private int diameter;
    private int radius;
    private int x;
    private int y;
    private int moveX;
    private int moveY;
    private int centerX;
    private int centerY;
    private int c;
    Graphics g;
    Random gen = new Random();
    
    /**
     * Default Constructor for objects of class Ball
     */
    public Ball(int cl)
    {
        diameter = 14;
        radius = diameter / 2;
        x = gen.nextInt(450);
        y = gen.nextInt(450)+100;
        moveX = gen.nextInt(8) + 1;
        moveY = gen.nextInt(8) + 1;
        centerX = x + radius;
        centerY = y + radius;
        if(cl == -1)
        {
            c = gen.nextInt(5);
        }else{
            c = cl;
        }
        }
    //alternate constructor
    public Ball(int di,int Cx, int Cy,int mX, int mY)
    {
        diameter = di;
        radius = diameter / 2;
        x = Cx;
        y = Cy;
        moveX = mX;
        moveY = mY;
        centerX = x + radius;
        centerY = y + radius;
         c = gen.nextInt(25);
    }
    /**
     * getMethods
     */
    public int getDiameter()
    {
       return diameter;
    }
    public int getRadius()
    {
       return radius;
    }
    public int getX()
    {
       return x;
    }
    public int getY()
    {
       return y;
    }
    public int getCenterX()
    {
       return centerX;
    }
    public int getCenterY()
    {
       return centerY;
    }
    //logic methods that will allow us to move our Ball
    //objects and check for collisions
    public boolean moveBall(int w, int h)
    {
        boolean t = false;
        x = x + moveX;
        y = y + moveY;
        if(x+diameter >= w || x <= 0)
        {
            moveX = moveX*-1;
        }
        if(y <= 0)//bounce of top
        {
            moveY = moveY*-1;
        }
        if(y + diameter >= h)//go through the bottom
        {
            moveY = moveY*-1;
            t = true;
        }
        update();
        return t;
    }
    public void update()
    {
        centerX = x + radius;
        centerY = y + radius;
    }
    public void setColor(int cl)
    {
        c = cl;
    }
    public int getColor()
    {
        return c;
    }
    public boolean ballCollision(Ball B)
    {
        boolean t = false;
        int bRadius = B.getRadius();
        int bCentX = B.getCenterX();
        int bCentY = B.getCenterY();
        
        //use distance formula
        int xsq,ysq;
        double dis = Math.sqrt((bCentX-centerX)*(bCentX-centerX)+(bCentY-centerY)*(bCentY-centerY));
        if(dis <= radius + bRadius)
        {
            changeDirection();
            B.changeDirection();  
            t = true;
        }
        return t;
    }
    public void changeDirection()
    {
        moveX = moveX*-1;
        moveY = moveY*-1;
    }
    public void changeXDirection()
    {
        moveX = moveX*-1;
    }
    public void changeYDirection()
    {
       moveY = moveY*-1;
    }
     public void drawBall()//Use g as the graphics g.*******
    {
       
           if(c  == 3)
       {
           g.setColor(Color.white);
        }else{
         if(c  == 2)
         { 
           g.setColor(Color.cyan);
         }else{
           if(c  == 1)
           {
            g.setColor(Color.red);
           }else{
               if(c  == 0)
               {
                   g.setColor(Color.blue);
               }
            }
         }
         
       }
       g.fillOval(x,y,diameter,diameter);
       
    }
    public void paint(Graphics gr)
    {
      g = gr;
      drawBall();
    }    
    
}
