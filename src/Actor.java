import java.util.*;

import javafx.scene.image.ImageView;


public class Actor extends ImageView {

	public Actor() {
		// TODO Auto-generated constructor stub
	}
	
	public void act(long now) {
		
	}	
	
	public World getWorld() {
		World world = (World) getParent();
		return world;
	}

	public void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}
	
	public double getWidth() {
		return getBoundsInLocal().getWidth(); 
	}
	
	public double getHeight() {
		return getBoundsInLocal().getHeight(); 
	}
	
	public <A extends Actor> List<A> getIntersectingObjects(java.lang.Class<A> cls) {
		List<A> temp = new ArrayList<A>();
		List<A> allActors = getWorld().getObjects(cls);
		for(A a : allActors) {
			if(intersects(a.getBoundsInLocal()) && this != a) temp.add(a);
		}
		
		return temp;
	}
	
	public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
		List<A> allActors = getWorld().getObjects(cls);
		for(A a : allActors) {
			if(intersects(a.getBoundsInLocal())) return a;
		}
		
		return null;
	}
}
