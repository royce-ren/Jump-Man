import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

public class Level8 extends World {

	private Level7 prevWorld;
	private Level9 nextWorld;
	
	@Override
	public void act(long now) {
		if(player.getY() + player.getHeight() > getHeight() && player.getDY() > 0) {
			double x = player.getX();
			
			stop();
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
		} else if(isKeyDown(KeyCode.DIGIT9)) {
			stop();
			rootNode.setCenter(nextWorld);
			nextWorld.getChildren().add(player);
			this.getChildren().remove(player);
			nextWorld.clearSet();
			nextWorld.requestFocus();
			nextWorld.start();
			player.setX(250); //120
			player.setY(40); //255
			player.clearDYDX();
		}
	}
	
	public Level8(BorderPane rootNode) {
		this.setPrefSize(400, 300);
		setOpenBottom(true);
		
		String smallFlat = getClass().getClassLoader().getResource("images/dirtSmallFlat.png").toString();
		Platform plat = new Platform(smallFlat);
		plat.setX(50);
		plat.setY(270);
		
		MovingPlatform plat2 = new MovingPlatform(smallFlat, 150, 260, 250, 220, 3);
		
		MovingPlatform plat3 = new MovingPlatform(smallFlat, 120, 200, 160, 160, 4);
		
		String dot = getClass().getClassLoader().getResource("images/dirtDot.png").toString();
		Platform plat4 = new Platform(dot);
		plat4.setX(380);
		plat4.setY(170);
		
		Platform plat5 = new Platform(smallFlat);
		plat5.setX(-30);
		plat5.setY(130);
		
		MovingPlatform plat6 = new MovingPlatform(dot, 40, 40, 10, 140, 2.5);
		
		String dirtThicc = getClass().getClassLoader().getResource("images/dirtSmallThicc.png").toString();
		Platform plat7 = new Platform(dirtThicc);
		plat7.setX(90);
		plat7.setY(110);
		
		String dirtLeft = getClass().getClassLoader().getResource("images/dirtLeft.png").toString();
		SlantedPlatform plat8 = new SlantedPlatform(dirtLeft, true);
		plat8.setX(plat7.getX() - plat8.getWidth());
		plat8.setY(111);
		
		Platform plat9 = new Platform(smallFlat);
		plat9.setX(180);
		plat9.setY(45);
		
		this.getChildren().addAll(plat, plat2, plat3, plat4, plat5, plat6, plat7, plat8, plat9);
		
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
	
	public void setPrevWorld(Level7 lev7) {
		prevWorld = lev7;
	}
	
	public void setNextWorld(Level9 lev9) {
		nextWorld = lev9;
	}
}
