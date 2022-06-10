import java.awt.Graphics;
import java.util.ArrayList;

public class Control {

		
		public int spd = 5;
		
		ArrayList<Objects> object = new ArrayList<Objects>();

		public float angle;
		
		public void tick() {
			for(int i = 0; i < object.size(); i++) { 
				Objects temp = object.get(i); 
				temp.tick(); 
				
			}
		}
		
		public void render(Graphics g) {
			for(int i = 0; i < object.size(); i++) { 
				Objects temp = object.get(i); 
				temp.draw(g);
				
			}
		}
		
		public void addObject(Objects object) { //add objects
			this.object.add(object);
		}
		
		public void addPlayerObject(Objects object) { //add objects
			this.object.add(0,object);
		}
		
		public void removeObject(Objects object) { //remove objects 
			this.object.remove(object);
		}
		
		
		public void clearAll() {
			object.clear();
		}
		

			
	}
