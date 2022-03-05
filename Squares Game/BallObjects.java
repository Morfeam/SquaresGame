import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Write a description of class BallObjects here.
 * 
 * @author Matthew Morfea 
 * @version May 22, 2015
 */
import java.applet.*;
import java.util.*;
public class BallObjects extends Applet implements Runnable
{    
    Thread runner; 
    private Image Buffer;
    private Graphics gBuffer;
    int width,height;
    Ball B1;
    ArrayList ballList;
    Ball temp;
    ArrayList bricks;
    Rectangle rTemp;
    int bx,by,bw,bh;
    Rectangle R1,R2;
    Paddle p;
    int lives,points,count;
    String liv,pts;
    boolean rightKey;
    boolean leftKey;
    boolean upKey;
    boolean downKey;
    boolean spaceKey;

  public void init()
   {  
        width=this.getSize().width;
        height=this.getSize().height;
        Buffer=createImage(width,height);
        gBuffer=Buffer.getGraphics();
        addKeyListener(new MyKeyListener());
        points = 0;
        lives = 30;
        count = 0;
        B1 = new Ball(2);
        ballList = new ArrayList();
        //Load the array
        for(int i = 0; i < 15;i++)
        {
            temp = new Ball(-1);
            ballList.add(temp); 
        }
        bricks = new ArrayList();
        for(int i = 0; i < 99;i++)
        {
            rTemp = new Rectangle(0);
            bricks.add(rTemp); 
        }
        bx = 5;
        by = 5;
        bw = 20;
        bh = 10;
        p = new Paddle();
    }
   public boolean mouseDown(Event evt,int x,int y)
    { 
        return true;   
    }
    
    public boolean mouseMove(Event evt,int x,int y)
    {
       p.mMovePaddle(x,y);
       return true;  
    }
      private class MyKeyListener extends KeyAdapter{
       public void keyPressed(KeyEvent e){
           switch(e.getKeyCode()){
               case KeyEvent.VK_LEFT:
               leftKey = true;
               break;
               case KeyEvent.VK_RIGHT:
               rightKey = true;
               break;
               case KeyEvent.VK_UP:
               upKey = true;
               break;
               case KeyEvent.VK_DOWN:
               downKey = true;
               break;
               case KeyEvent.VK_SPACE:
               spaceKey = true;
               break;
            }
        }
       public void keyReleased(KeyEvent e){
           switch(e.getKeyCode()){
               case KeyEvent.VK_LEFT:
               leftKey = false;
               break;
               case KeyEvent.VK_RIGHT:
               rightKey = false;
               break;
               case KeyEvent.VK_UP:
               upKey = false;
               break;
               case KeyEvent.VK_DOWN:
               downKey = false;
               break;
               case KeyEvent.VK_SPACE:
               downKey = false;
               break;
            }
        }  
    }
    public void start()
    { if (runner == null)
        {   runner = new Thread (this);
            runner.start();
        }
    }
  
 public void stop()
    {  runner = null;
    }

 public void run() 
    { 
      while(true)
      {
            if(upKey){p.moveUp();}
            if(downKey){p.moveDown();}
            if(rightKey){p.moveRight();}
            if(leftKey){p.moveLeft();}
            
            repaint();
            
            try {runner.sleep(25);}
            catch (Exception e) { }
           
            //paint background black
            
            gBuffer.setColor(Color.black);
            gBuffer.fillRect(0,0,width,height);
            gBuffer.setColor(Color.cyan);
            gBuffer.setFont(new Font("Calibri",30,20));
            gBuffer.drawString("The Squares Game",20,20);
                    
                temp = (Ball)ballList.get(0);
                if(lives > 0)
                {
                    for(int j = 0; j < ballList.size();j++)
                    {
                        temp = (Ball)ballList.get(j);
                       if(p.ballCollision(temp))
                       {
                        if(temp.getColor() == 0)
                        {
                         lives--;
                        }
                       
                        if(temp.getColor() == 1)
                        {
                            lives--;                            
                         }
                            if(temp.getColor() == 2)
                            {
                                lives--;
                                lives--;
                                points++;
                            }
                            if(temp.getColor() == 3)
                            {
                                points--;
                            }
                    }
                }
            
            for(int i = 0; i < ballList.size();i++)
            {  
                temp = (Ball)ballList.get(i);
                temp.moveBall(width,height); 
            }
            for(int i = 0; i < ballList.size();i++)
            {  
                temp = (Ball)ballList.get(i);
                temp.paint(gBuffer);
            }
                rTemp = (Rectangle)bricks.get(0);
                rTemp.paint(gBuffer);
                if(p.rectCollision(rTemp))
                {
                    points++;
                    bricks.remove(0);
                }
            
            p.paint(gBuffer);
        }else{
            gBuffer.setColor(Color.orange);
            gBuffer.setFont(new Font("Times New Roman",30,80));
            gBuffer.drawString("GAME", 350,200);
            gBuffer.drawString("OVER!",350,300);
            
        }
            gBuffer.setFont(new Font("Calibri",20,20));
            gBuffer.setColor(Color.yellow);    
            liv = String.valueOf(lives);
            gBuffer.drawString("LIVES:",5,570);
            gBuffer.drawString(liv,5,585);
            pts = String.valueOf(points);
            gBuffer.drawString("POINTS:",740,570);
            gBuffer.drawString(pts,740,585);
            if(points >=15)
                {
                    gBuffer.setColor(Color.white);
                    gBuffer.setFont(new Font("Times New Roman",30,40));
                    gBuffer.drawString("You",180,180);
                    gBuffer.drawString("Win!",180,220);
                    gBuffer.setFont(new Font("Times New Roman",15,20));
                    gBuffer.setColor(Color.orange);
                    gBuffer.drawString("Made By: Matthew Morfea",280,400);
                    gBuffer.setFont(new Font("Times New Roman",15,20));
                    gBuffer.setColor(Color.red);
                    gBuffer.drawString("1) Play Again",20,100);
                    gBuffer.setColor(Color.blue);
                    gBuffer.drawString("2) Quit",20,130);
                    if(count == 1)
                    {
                        
                    }
                    if(count == 2)
                    {
                        lives = 0;
                    }
                    repaint();
                    break;
            }
            repaint();
            
         }//while(true)
      
    } //run    
   public void update(Graphics g)
       {  paint(g);
       }
    
  public void paint(Graphics g)
    {
        g.drawImage (Buffer,0,0, this);
    }
}
