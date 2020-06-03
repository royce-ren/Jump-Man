import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

public class Level9 extends World {

	private Level8 prevWorld;
	private Level10 nextWorld;
	
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
			rootNode.setBackground(new Background(end));
			rootNode.setCenter(nextWorld);
			nextWorld.getChildren().add(player);
			this.getChildren().remove(player);
			nextWorld.clearSet();
			nextWorld.requestFocus();
			nextWorld.start();
			player.setX(x);
			player.setY(312);
		} else if(isKeyDown(KeyCode.DIGIT0)) {
			stop();
			rootNode.setBackground(new Background(end));
			rootNode.setCenter(nextWorld);
			nextWorld.getChildren().add(player);
			this.getChildren().remove(player);
			nextWorld.clearSet();
			nextWorld.requestFocus();
			nextWorld.start();
			player.setX(320);
			player.setY(265); 
			player.clearDYDX();
		}
	}
	
	public Level9() {
		this.setPrefSize(400, 300);
		setOpenBottom(true);
		
		String smallFlat = getClass().getClassLoader().getResource("images/dirtSmallFlat.png").toString();
		Platform plat = new Platform(smallFlat);
		plat.setX(100);
		plat.setY(280);
		
		String invis = getClass().getClassLoader().getResource("images/slimeBlank.png").toString();
		FlippingMovingPlatform plat2 = new FlippingMovingPlatform(smallFlat, invis, 4, 4, 160, 270, 270, 230, 4);
		
		String dot = getClass().getClassLoader().getResource("images/dirtDot.png").toString();
		Platform plat3 = new Platform(dot);
		plat3.setX(360);
		plat3.setY(180);
		
		FlippingPlatform plat4 = new FlippingPlatform(smallFlat, invis, 3, 1.5);
		plat4.setX(240);
		plat4.setY(160);
		
		FlippingMovingPlatform plat5 = new FlippingMovingPlatform(smallFlat, invis, 3.5, 3.5, 140, 20, 160, 100, 3.5);
		
		Platform plat6 = new Platform(dot);
		plat6.setX(250);
		plat6.setY(65);
		
		this.getChildren().addAll(plat, plat2, plat3, plat4, plat5, plat6);
		
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
	
	public void setPrevWorld(Level8 lev8) {
		prevWorld = lev8;
	}
	
	public void setNextWorld(Level10 lev10) {
		nextWorld = lev10;
	}
}
