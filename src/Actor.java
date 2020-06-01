import java.util.*;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;


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
	
	public <A extends Actor> boolean pixelPerfectColl(java.lang.Class<A> cls) {
		A actor = getOneIntersectingObject(cls);
//		if(actor != null) {
//			for(int i = (int) actor.getX(); i <= actor.getX() + actor.getWidth(); i++) {
//				for(int j = (int) actor.getY(); j <= actor.getY() + actor.getHeight(); j++) {
//					PixelReader pr = getImage().getPixelReader();
//					PixelReader actorPr = actor.getImage().getPixelReader();
//					if(pr.getColor(i, j).getOpacity() != 0 && actorPr.getColor(i, j).getOpacity() != 0) {
//						return true;
//					}
//				}
//			}
//		}
		
		if(actor != null) {
			PixelReader pr = getImage().getPixelReader();
			PixelReader actorPr = actor.getImage().getPixelReader();
			for(int i = 0; i < getImage().getWidth(); i++) {
				for(int j = 0; j < getImage().getHeight(); j++) {
					if(pr.getColor(i, j).getOpacity() != 0) {
						int tempX = i + (int) getX(); //true location of pixel
						int tempY = j + (int) getY();
						
						int platX = (int) (tempX - actor.getX()); //translate true location to platform surface
						int platY = (int) (tempY - actor.getY());
						
						if(platX >= 0 && platY >= 0 && platX < actor.getWidth() && platY < actor.getHeight() && actorPr.getColor(platX, platY).getOpacity() != 0) 
							return true;
					}
				}
			}
		}
		
		return false; //if not intersecting or not a single bit of the two actors are touching, false
	}
}
