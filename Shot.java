import java.awt.Color;
import java.awt.Graphics;
/**
*
*/
public class Shot extends Objects  {
 
   private int shotSpeed = 10;
 
   private int SHOT_WIDTH = 2;
   private int SHOT_HEIGHT = 5;
 
  // private int x = 0;
 
   private int shotHeight = 0;
 
   boolean shotState = true;
 
   AlienArmy alienArmy = null;
   
   private Control control;
   
   
 
   /**
    *
    */
   public Shot(int xVal, int yVal, Control control, AlienArmy aa) {
     super(xVal,yVal);
 	velX = 1;
	   //x = xVal;//Set the shot direction
       //shotHeight = yVal;
       alienArmy = aa;
 	
 	this.control = control;
    
 	
   }
 
   /**
    *
    */
   private boolean moveShot() {
 
       //Now we need to see if we've hit anything!
     /*  if (alienArmy.checkShot(x, shotHeight)) {
           //We hit something!
           System.out.println("We shot an alien!");
           shotState = false;
           return true;
       }*/
	   if (alienArmy.checkShot(x, y)) {
           //We hit something!
           System.out.println("We shot an alien!");
           shotState = false;
           control.removeObject(this);
           return true;
           
       }
 
      // shotHeight = shotHeight - 2;
       //We could have written this as
       //shotHeight -= 2;
	   //y-=2;
       //Now check we haven't gone off the screen
       /*if (shotHeight < 0) {
           shotState = false;
           return true;
       }*/
	   if (y< 0) {
           shotState = false;
           return true;
       }
 
       return false;
   }
 
   /**
    * Draw the image of the shot
    */
   public void draw(Graphics g) {
	  // g.setColor(Color.WHITE);
	   //System.out.println(x +" "+ y);
	   //g.drawRect(x, y, 100, 100);
	 //moveShot();
      if (shotState) {
           g.setColor(Color.white);
       } else {
           g.setColor(Color.black);
       }
      /* g.setColor(Color.white);*/
       g.fillRect(x, y, SHOT_WIDTH, SHOT_HEIGHT);
   }
 
   public boolean getShotState() {
       return shotState;
   }
 
   /**
    * The thread that moves the shot
    */
   

@Override
public void tick() {
	y-=velX;	
	//System.out.println(x+" "+y);
	moveShot();
	//x=0;
}


 
}
