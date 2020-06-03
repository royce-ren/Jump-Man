import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

public class Level6 extends World {
	
	private Level5 prevWorld;
	private Level7 nextWorld;
	
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
			rootNode.setBackground(new Background(dirt));
			rootNode.setCenter(nextWorld);
			nextWorld.getChildren().add(player);
			this.getChildren().remove(player);
			nextWorld.clearSet();
			nextWorld.requestFocus();
			nextWorld.start();
			player.setX(x);
			player.setY(312);
		} else if(isKeyDown(KeyCode.DIGIT7)) {
			stop();
			rootNode.setBackground(new Background(dirt));
			rootNode.setCenter(nextWorld);
			nextWorld.getChildren().add(player);
			this.getChildren().remove(player);
			nextWorld.clearSet();
			nextWorld.requestFocus();
			nextWorld.start();
			player.setX(0);
			player.setY(255);
			player.clearDYDX();
		}
	}
	
	public Level6(BorderPane rootNode) {
		this.setPrefSize(400, 300);
		setOpenBottom(true);
		
		String thicc = getClass().getClassLoader().getResource("images/slimeThicc.png").toString();
		Platform plat = new Platform(thicc);
		plat.setX(125);
		plat.setY(275);
		
		String chonk = getClass().getClassLoader().getResource("images/slimeChonky.png").toString();
		Platform plat2 = new Platform(chonk);
		plat2.setX(295);
		plat2.setY(240);
		
		String slimeLeft = getClass().getClassLoader().getResource("images/slimeLeft.png").toString();
		SlantedPlatform plat3 = new SlantedPlatform(slimeLeft, true);
		plat3.setX(275);
		plat3.setY(241);
		
		String slimeInvis = getClass().getClassLoader().getResource("images/slimeSmallFlatInvis.png").toString();
		String invis = getClass().getClassLoader().getResource("images/slimeBlank.png").toString();
		FlippingPlatform plat4 = new FlippingPlatform(slimeInvis, invis, 3, 2);
		plat4.setX(360);
		plat4.setY(190);
		
		String smallFlat = getClass().getClassLoader().getResource("images/slimeSmallFlat.png").toString();
		Platform plat5 = new Platform(smallFlat);
		plat5.setX(240);
		plat5.setY(130);
		
		FlippingPlatform plat6 = new FlippingPlatform(smallFlat, invis, 1.5, 2);
		plat6.setX(120);
		plat6.setY(140);
		
		Platform plat7 = new Platform(chonk);
		plat7.setX(-5);
		plat7.setY(110);
		
		String slimeRight = getClass().getClassLoader().getResource("images/slimeRight.png").toString();
		SlantedPlatform plat8 = new SlantedPlatform(slimeRight, false);
		plat8.setX(10);
		plat8.setY(111);
		
		Platform plat9 = new Platform(slimeInvis);
		plat9.setX(100);
		plat9.setY(55);
		
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
	
	public void setPrevWorld(Level5 lev5) {
		prevWorld = lev5;
	}
	
	public void setNextWorld(Level7 lev7) {
		nextWorld = lev7;
	}
}
