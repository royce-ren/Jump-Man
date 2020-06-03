import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

public class Level7 extends World {

	private Level6 prevWorld;
	private Level8 nextWorld;
	
	@Override
	public void act(long now) {
		if(player.getY() + player.getHeight() > getHeight() && player.getDY() > 0) {
			double x = player.getX();
			
			stop();
			rootNode.setBackground(new Background(slime));
			rootNode.setCenter(prevWorld);
			prevWorld.getChildren().add(player);
			this.getChildren().remove(player);
			prevWorld.clearSet();
			prevWorld.requestFocus();
			player.setX(x);
			player.setY(0);
			prevWorld.start();
		} else if(player.getY() < 0 && player.getDY() < 0) {
			double x = player.getX();
			
			stop();
			rootNode.setCenter(nextWorld);
			nextWorld.getChildren().add(player);
			this.getChildren().remove(player);
			nextWorld.clearSet();
			nextWorld.requestFocus();
			nextWorld.start();
			player.setX(x);
			player.setY(312);
		} else if(isKeyDown(KeyCode.DIGIT8)) {
			stop();
			rootNode.setCenter(nextWorld);
			nextWorld.getChildren().add(player);
			this.getChildren().remove(player);
			nextWorld.clearSet();
			nextWorld.requestFocus();
			nextWorld.start();
			player.setX(65);
			player.setY(245); 
			player.clearDYDX();
		}
	}
	
	public Level7(BorderPane rootNode) {
		this.setPrefSize(400, 300);
		setOpenBottom(true);
		
		String smallFlat = getClass().getClassLoader().getResource("images/dirtSmallFlat.png").toString();
		Platform plat = new Platform(smallFlat);
		plat.setX(-25);
		plat.setY(280);
		
		Platform plat2 = new Platform(smallFlat);
		plat2.setX(50);
		plat2.setY(215);
		
		String dot = getClass().getClassLoader().getResource("images/dirtDot.png").toString();
		Platform plat3 = new Platform(dot);
		plat3.setX(200);
		plat3.setY(230);
		
		Platform plat4 = new Platform(dot);
		plat4.setX(300);
		plat4.setY(250);
		
		Platform plat5 = new Platform(dot);
		plat5.setX(380);
		plat5.setY(180);
		
		Platform plat6 = new Platform(smallFlat);
		plat6.setX(220);
		plat6.setY(100);
		
		Platform plat7 = new Platform(dot);
		plat7.setX(75);
		plat7.setY(110);
		
		Platform plat8 = new Platform(smallFlat);
		plat8.setX(-30);
		plat8.setY(50);
		
		this.getChildren().addAll(plat, plat2, plat3, plat4, plat5, plat6, plat7, plat8);
		
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.SPACE && player.isGrounded()) addKey(event.getCode());
				else if(event.getCode() != KeyCode.SPACE) addKey(event.getCode());
			}
		});
		
		this.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.SPACE && player.isGrounded()) removeKey(event.getCode());
				else if(event.getCode() != KeyCode.SPACE) removeKey(event.getCode());
			}
		});
	}
	
	public void setPrevWorld(Level6 lev6) {
		prevWorld = lev6;
	}
	
	public void setNextWorld(Level8 lev8) {
		nextWorld = lev8;
	}
}
