import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

public class Level3 extends World {
	private Level2 prevWorld;
	private Level4 nextWorld;
	
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
			rootNode.setBackground(new Background(slime));
			rootNode.setCenter(nextWorld);
			nextWorld.getChildren().add(player);
			this.getChildren().remove(player);
			nextWorld.clearSet();
			nextWorld.requestFocus();
			nextWorld.start();
			player.setX(x);
			player.setY(312);
		} else if(isKeyDown(KeyCode.DIGIT4)) {
			stop();
			rootNode.setBackground(new Background(slime));
			rootNode.setCenter(nextWorld);
			nextWorld.getChildren().add(player);
			this.getChildren().remove(player);
			nextWorld.clearSet();
			nextWorld.requestFocus();
			nextWorld.start();
			player.setX(130); 
			player.setY(260);
		}
	}
	
	public Level3(BorderPane rootNode) {
		this.setPrefSize(400, 300);
		setOpenBottom(true);
		
		String smallFlat = getClass().getClassLoader().getResource("images/BrickSmallFlat.png").toString();
		Platform plat = new Platform(smallFlat);
		plat.setX(95);
		plat.setY(300);
		
		String brickWallShort = getClass().getClassLoader().getResource("images/brickWallShort.png").toString();
		Platform plat2 = new Platform(brickWallShort);
		plat2.setX(230);
		plat2.setY(290);
		
		String brickTiny = getClass().getClassLoader().getResource("images/brickTiny.png").toString();
		Platform plat3 = new Platform(brickTiny);
		plat3.setX(355);
		plat3.setY(220);
		
		String brickThicc = getClass().getClassLoader().getResource("images/brickSmallThicc.png").toString();
		Platform plat4 = new Platform(brickThicc);
		plat4.setX(175);
		plat4.setY(200);
		
		String brickBigLeft = getClass().getClassLoader().getResource("images/brickBigLeft.png").toString();
		SlantedPlatform plat5 = new SlantedPlatform(brickBigLeft, true);
		plat5.setX(175 - plat5.getWidth());
		plat5.setY(201);
		
		Platform plat6 = new Platform(brickTiny);
		plat6.setX(-15);
		plat6.setY(160);
		
		Platform plat7 = new Platform(brickThicc);
		plat7.setX(175);
		plat7.setY(100);
		
		SlantedPlatform plat8 = new SlantedPlatform(brickBigLeft, true);
		plat8.setX(175 - plat8.getWidth());
		plat8.setY(101);
		
		Platform plat9 = new Platform(brickThicc);
		plat9.setX(250);
		plat9.setY(53);
		
		SlantedPlatform plat10 = new SlantedPlatform(brickBigLeft, true);
		plat10.setX(250 - plat10.getWidth());
		plat10.setY(54);
		
		Platform plat11 = new Platform(smallFlat);
		plat11.setX(340);
		plat11.setY(30);
		
		this.getChildren().addAll(plat, plat2, plat3, plat4, plat5, plat6, plat7, plat8, plat9, plat10, plat11);
		
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
	
	public void setPrevWorld(Level2 lev2) {
		prevWorld = lev2;
	}
	
	public void setNextWorld(Level4 lev4) {
		nextWorld = lev4;
	}
}
