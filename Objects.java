import java.awt.Graphics;


public abstract class Objects {

	
	protected int x, y; 
	protected float velX, velY; 
	
	public Objects(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public abstract void tick(); 
	public abstract void draw(Graphics g);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	
}
