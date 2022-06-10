import java.awt.Color;
import java.awt.Graphics;
 
/**
*
*/
public class AlienShot extends Objects {
 
 
   private int shotSpeed = 10;
 
   private int SHOT_WIDTH  = 2;
   private int SHOT_HEIGHT = 5;   
  
  // private int x = 0;
   //private int y = 0;
   
   private int velY;
 
   private int shotHeight = 0;
 
   boolean shotState = true;
 
   Ship ship = null;
   
   private Control control;
  
   /**
    *
    */
   public AlienShot(int posX, int posY, Control control, Ship s) {
       
	   super(posX,posY);
	   velY = 1;
	   this.control = control;
	   ship = s;
 //  Thread thread = new Thread(this);
   //thread.start();
   }
 
   /**
    *
    */
   private boolean moveShot() {
  
   //Now we need to see if the ship has been hit
   if (ship.checkShot(x, y)) {
           //We hit something!
           System.out.println("An alien shot the ship!");
       ship.hitByAlien();
       shotState = false;
       return true;
   }
 
       y = y + 2;
   //We could have written this as
   //shotHeight -= 2;
  
   //Now check we haven't gone off the screen
   if (y > SpaceInvaders.HEIGHT) {
       shotState = false;
       return true;
   }
  
      
   return false;
   }
 
   /**
    * Draw the image of the shot
    */   
   public void draw(Graphics g) {
   /*if (shotState) {
           g.setColor(Color.green);
   } else {
           g.setColor(Color.black);
   }
       g.fillRect(x, shotHeight, SHOT_WIDTH, SHOT_HEIGHT);*/
	   g.drawRect(x, y, SHOT_WIDTH, SHOT_HEIGHT);
   }
 
   public boolean getShotState() {
       return shotState;
   }

public void tick() {
	
	x+=0;
	y+=velY;
	moveShot();
	
}




 
  /* public void run() {
       while(true) {
           try {
               Thread.sleep(shotSpeed);
           } catch(InterruptedException ie) {
               //Ignore this exception
           }
      
       if (moveShot()) {
               break;
       }
 
   }
   }*/
 
  
 
}
