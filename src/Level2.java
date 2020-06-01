import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
public class Level2 extends World{
	
	private Level1 prevWorld;
	private Level3 nextWorld;
	
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
		} else if(isKeyDown(KeyCode.DIGIT3)) {
			stop();
			rootNode.setCenter(nextWorld);
			nextWorld.getChildren().add(player);
			this.getChildren().remove(player);
			nextWorld.clearSet();
			nextWorld.requestFocus();
			nextWorld.start();
			player.setX(110);
			player.setY(265);
		}
	}
	
	public Level2(BorderPane rootNode) {
		this.setPrefSize(400, 300);
		setOpenBottom(true);
		
		String brickWallShort = getClass().getClassLoader().getResource("images/brickWallShort.png").toString();
		Platform plat = new Platform(brickWallShort);
		plat.setX(50);
		plat.setY(290);
		
		String smallFlat = getClass().getClassLoader().getResource("images/BrickSmallFlat.png").toString();
		Platform plat2 = new Platform(smallFlat);
		plat2.setX(270);
		plat2.setY(280);
		
		Platform plat3 = new Platform(smallFlat);
		plat3.setX(350);
		plat3.setY(220);
		
		Platform plat4 = new Platform(smallFlat);
		plat4.setX(300);
		plat4.setY(160);
		
		String brickShort = getClass().getClassLoader().getResource("images/brickShort2.png").toString();
		Platform plat5 = new Platform(brickShort);
		plat5.setX(230);
		plat5.setY(-60);
		
		Platform plat6 = new Platform(smallFlat);
		plat6.setX(200);
		plat6.setY(150);
		
		String brickTiny = getClass().getClassLoader().getResource("images/brickTiny.png").toString();
		Platform plat7 = new Platform(brickTiny);
		plat7.setX(185);
		plat7.setY(290);
		
		Platform plat8 = new Platform(brickTiny);
		plat8.setX(-10);
		plat8.setY(140);
		
		Platform plat9 = new Platform(brickTiny);
		plat9.setX(200);
		plat9.setY(60);
		
		String brickSlantRight = getClass().getClassLoader().getResource("images/brickSlantRight.png").toString();
		SlantedPlatform plat10 = new SlantedPlatform(brickSlantRight, false);
		plat10.setX(20);
		plat10.setY(141);
		
		this.getChildren().addAll(plat, plat2, plat3, plat4, plat5, plat6, plat7, plat8, plat9, plat10);
		
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
	
	public Level2(BorderPane rootNode, Level1 lev1/*, Level3 lev3*/) {
		this.setPrefSize(400, 300);
		player = lev1.getPlayer();
		
		setOpenBottom(true);
		
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
	
	public void setPrevWorld(Level1 lev1) {
		prevWorld = lev1;
	}
	
	public void setNextWorld(Level3 lev3) {
		nextWorld = lev3;
	}
	
}
