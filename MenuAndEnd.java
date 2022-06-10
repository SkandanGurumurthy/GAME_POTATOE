import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



public class MenuAndEnd extends MouseAdapter implements MouseListener, MouseMotionListener {

	private SpaceInvaders s;
	private Control control;
	
	public MenuAndEnd(SpaceInvaders s, Control control) {
		this.s =s;
		this.control = control;
	}
	
	
	
	
	public void draw(Graphics g) {
		if(s.state == 1) {
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
		}
		
		
	}
	
	public void update() {

	}
	
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX(); //get the x location of the mouse
		int my = e.getY(); //get the y location of the mouse
	if(s.state == 1) {
		System.out.println(s.state);
		if(mouseOver(mx,my, 20, 150, 200, 70)) {
			System.out.println("!");
			s.state=2;
		}
	}
}
	
	//Brandon gave me this code
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		System.out.println(s.state);

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




	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("!");		
	}




	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("!");
	}




	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
