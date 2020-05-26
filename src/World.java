import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class World extends Pane {
	private long time;
	private long prevTime;
	private AnimationTimer aniTimer;
	private Set<KeyCode> set;
	private boolean held;
	private boolean released;
	private boolean openBottom;

	public World() {
		aniTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				act(now);
				List<Actor> temp = getObjects(Actor.class);
				for(Actor a : temp) {
					a.act(now);
				}
			}
		};
		
		set = new HashSet<KeyCode>();
		released = false;
		openBottom = false;
		time = 0;
		prevTime = 0;
	}
	
	public void act(long now) {
		
	}	
	
	public void add(Actor actor) {
		getChildren().add(actor);
	}
	
	public void remove(Actor actor) {
		getChildren().remove(actor);
	}
	
	public void start() {
		aniTimer.start();
	}
	
	public void stop() {
		aniTimer.stop();
	}
	
	public long getTime() {
		return time;
	}
	
	public boolean isReleased() {
		return released;
	}
	
	public void setReleased(boolean isReleased) {
		released = isReleased;
	}
	
	public boolean isHeld() {
		return held;
	}
	
	public void setHeld(boolean isHeld) {
		held = isHeld;
	}
	
	public boolean isOpenBottom() {
		return openBottom;
	}
	
	public void setOpenBottom(boolean isOpenBottom) {
		openBottom = isOpenBottom;
	}

	public <A extends Actor> List<A> getObjects(java.lang.Class<A> cls) {
		List<A> temp = new ArrayList<A>();
		List<Node> children = getChildren();
		for(Node n : children) {
			if(cls.isInstance(n)) temp.add(cls.cast(n));
		}
		
		return temp;
	}
	
	public void addKey(KeyCode k) {
		set.add(k);
		if(k == KeyCode.SPACE && !held) {
			prevTime = System.currentTimeMillis();
			released = false;
			held = true;
		}
	}
	
	public void removeKey(KeyCode k) {
		if(set.contains(k)) set.remove(k);
		if(k == KeyCode.SPACE) {
			time = System.currentTimeMillis() - prevTime;
			System.out.println(time);
			released = true;
			held = false;
		}
	}
	
	public boolean isKeyDown(KeyCode k) {
		if(set.contains(k)) return true;
		return false;
	}
}
