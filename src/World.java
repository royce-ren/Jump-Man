import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public abstract class World extends Pane {
	private long time;
	private long prevTime;
	private AnimationTimer aniTimer;
	private Set<KeyCode> set;
	private boolean held;
	private boolean released;
	private boolean openBottom;
	
	protected Player player;
	protected BorderPane rootNode;
	
	final String dungeonPath = getClass().getClassLoader().getResource("images/dungeon.png").toString();
	protected final BackgroundImage dungeon = new BackgroundImage(new Image(dungeonPath),
	        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
	          BackgroundSize.DEFAULT);
	
	final String slimePath = getClass().getClassLoader().getResource("images/slime.png").toString();
	protected final BackgroundImage slime = new BackgroundImage(new Image(slimePath),
	        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
	          BackgroundSize.DEFAULT);
	
	final String dirtPath = getClass().getClassLoader().getResource("images/dirt.png").toString();
	protected final BackgroundImage dirt = new BackgroundImage(new Image(dirtPath),
	        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
	          BackgroundSize.DEFAULT);
	
	final String endPath = getClass().getClassLoader().getResource("images/end.png").toString();
	protected final BackgroundImage end = new BackgroundImage(new Image(endPath),
	        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
	          BackgroundSize.DEFAULT); 
	
	

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
		
		setSet(new HashSet<KeyCode>());
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

	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public BorderPane getRootNode() {
		return rootNode;
	}
	
	public void setRootNode(BorderPane rootNode) {
		this.rootNode = rootNode;
	}
	
	public void setCenter() {
		rootNode.setCenter(this);
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
		getSet().add(k);
		if(k == KeyCode.SPACE && !held) {
			prevTime = System.currentTimeMillis();
			released = false;
			held = true;
		}
	}
	
	public void removeKey(KeyCode k) {
		if(getSet().contains(k)) getSet().remove(k);
		if(k == KeyCode.SPACE) {
			time = System.currentTimeMillis() - prevTime;
			released = true;
			held = false;
		}
	}
	
	public boolean isKeyDown(KeyCode k) {
		if(getSet().contains(k)) return true;
		return false;
	}
	
	public void clearSet() {
		set.clear();
	}

	public Set<KeyCode> getSet() {
		return set;
	}

	public void setSet(Set<KeyCode> set) {
		this.set = set;
	}
}
