public class SpaceInvaders extends JFrame implements Runnable, MouseListener {
 
   public static int WIDTH = 600;//The width of the frame
   public static int HEIGHT = 400;//The height of the frame
 
   private int gameSpeed = 100;//Try 500
 
 
   Ship ship = null;

   AlienArmy army;
   
   //if state equals 1 than menu 2 game three game over four win 
   public int state =1;

   
   
   private boolean paused = false;
 
   private int score = 0;
 
   Graphics offscreen_high;
   BufferedImage offscreen;
   
   private Control control;
 
  
   private Thread thread; //A thread is a thread of execution in a program
   
   private boolean running = false;
   private MenuAndEnd menu;


 
   /**
    * This is called a constructor.
    */
   public SpaceInvaders(String frameTitle) {
       super(frameTitle);
 
       control = new Control();
   /**
    * Exit the program if the window is closed.
    */
   addWindowListener (new java.awt.event.WindowAdapter() {
       @Override public void windowClosing(java.awt.event.WindowEvent windowEvent) { System.exit(0);}});
 
 
       //Create the ship to fight off the invading army!
       ship = new Ship(this, control);
       army = new AlienArmy(0,0,ship,this,null,control);
 
       //The ship will be controlled by the mouse
       addMouseListener(ship);
       //We also want mouse movement not just mouse clicks
       addMouseMotionListener(ship);
       this.addMouseListener(this);
       this.addMouseMotionListener(menu);
       this.addMouseWheelListener(menu);
       offscreen = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
       offscreen_high = offscreen.createGraphics();
 
       setBackground(Color.black);
       setSize(WIDTH, HEIGHT);
       setVisible(true);
       startGame();
       menu = new MenuAndEnd(this, control);
       control.addObject(army);
   }
 
   /**
    * As you move your mouse on and off the screen we want to pause
    * the game.
    */
   public void pauseGame(boolean state) {
       paused = state;
   }
 
   /**
    * Kill an alien and get 5 points!
    */
   public void hitAlienScore() {
      //Add 5 to the score
      score += 5;
      System.out.println("Current Score = "+score);
   }
 
   /**
    * Get shot and lose 20 points!
    */
   public void shotShip() {
      score -= 20;
      System.out.println("Current Score = "+score);
      if(score <=0) {
    	state = 3;
      }
      
   }
 
   /**
    *
    */
   public void startGame() {
       //These two lines may look confusing but basically they start the
       //game process, i.e. update the display screen every 100ms.
       Thread thread = new Thread(this);
       thread.start();
       running = true;
   }
   
   public synchronized void stop() {
      // Thread thread = new Thread(this);
	   try { //if we can do it do it if we can't do this code. Run an error bug 
			thread.join(); //stopping the thread 
			//System.out.println("!");
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
 
   /**
    *
    */
   public void draw() {
	   
	BufferStrategy bs = this.getBufferStrategy(); 
	if(bs == null) {
		this.createBufferStrategy(3); //don't go over three creating three buffers in the game
		return;
	}
	
	Graphics g = bs.getDrawGraphics();
 
 if(state ==2) {
	 control.render(g);
	 offscreen_high.setColor(Color.black);
     offscreen_high.fillRect(0,0, WIDTH, HEIGHT);

     army.drawArmy(offscreen_high);

     ship.drawShip(offscreen_high);
     g.drawImage(offscreen,0,0,this);
    // control.tick();

 }
 else if(state == 1) {
	 g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	 Font title = new Font("arial", 1,80);
		Font description = new Font("arial", 1,20);
		Font start = new Font("arial",1,55);
		
		g.setColor(Color.RED);
		g.setFont(title);
		g.drawString("ENTER TITLE", 0, 100);
		g.setColor(Color.RED);
		
		g.setFont(start);
		g.drawRect(20, 150, 200, 70);
		g.drawString("START", 30,200);
		
		g.setColor(Color.RED);
		g.setFont(description);
		g.drawString("Use your mouse to move", 300, 150);
		g.drawString("Click to move", 300, 180);
		g.drawString("Try to stay alive!!!", 300, 210);
		//menu.draw(g);
		//System.out.println("!");
	 }
 else if (state ==4){
	 Font over = new Font("arial", 1,80);
		Font words = new Font("arial", 1,20);
		Font tryagain = new Font("arial",1,55);
		 g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.red);
		g.setFont(over);
		g.drawString("You WIN", 5, 100);
	 //g.setColor(Color.BLACK);
	//g.fillRect(0, 0, WIDTH, HEIGHT);
 }
 else {
     //control.clearAll();
	 Font over = new Font("arial", 1,80);
		Font words = new Font("arial", 1,20);
		Font tryagain = new Font("arial",1,55);
		 g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.red);
		g.setFont(over);
		g.drawString("Game Over", 5, 100);
		
		g.setFont(tryagain);
		g.drawRect(20, 150, 270, 70);
		g.drawString("Try Again", 30,200);
 }
	 
 	
     g.dispose();
     bs.show();

   }
 
   public void update() {
      menu.update();
	   if(state==2) {
	   control.tick();
       }
	   //army.tick();
   }
 
   /**
    *
    */
   public void moveAliens() {
       army.moveArmy();
   }
 
   /**
    *
    */
   public void run() {
	   this.requestFocus();
		//link for code found online: https://stackoverflow.com/questions/18283199/java-main-game-loop
		//creating the game loop
		//popular game loop!!
		long lastTime = System.nanoTime(); // last time now and ns are used to calculate delta
		double amountOfTicks = 60.0; //amount of tics/seconds 
		double ns = 1000000000 / amountOfTicks; //amount of nanoseconds 
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				delta--;
			}
			if(running) {
				draw();
			}
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer+=1000;
				//System.out.println("FPS: "+frames); //displays around 200
				frames=0;
			}
		}
		stop();
   }
 
   /**
    * Get a reference to the alien army
    */
   public AlienArmy getAlienArmy() {
       return army;
   }
 
   /**
    * This is the program entry point
    */
   public static void main(String []args) {
       SpaceInvaders invaders = new SpaceInvaders("Space Invaders");
   }

@Override
public void mouseClicked(MouseEvent e) {
	int mx = e.getX(); //get the x location of the mouse
	int my = e.getY(); //get the y location of the mouse
if(state == 1) {
	if(mouseOver(mx,my, 20, 150, 200, 70)) {
		//System.out.println("!");
		state=2;
	}
}	
else if(state == 3) {
	if(mouseOver(mx,my, 20, 150, 270, 70)) {
		//System.out.println("!");
		state=1;
	}
}	
}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {

	if(mx > x && mx < x+width) {
		if(my > y && my < y + height) {
			return true;
		}
		else {
			return false;
		}
		
	}else {
		return false;
	}
}
 
}

