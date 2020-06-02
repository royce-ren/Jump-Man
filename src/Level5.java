import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

public class Level5 extends World {
	
	private Level4 prevWorld;
	private Level6 nextWorld;
	
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
		} else if(isKeyDown(KeyCode.DIGIT6)) {
			stop();
			rootNode.setCenter(nextWorld);
			nextWorld.getChildren().add(player);
			this.getChildren().remove(player);
			nextWorld.clearSet();
			nextWorld.requestFocus();
			nextWorld.start();
			player.setX(250); //130
			player.setY(105); //250
		}
	}
	
	public Level5(BorderPane rootNode) {
		this.setPrefSize(400, 300);
		setOpenBottom(true);
		
		String smallFlat = getClass().getClassLoader().getResource("images/slimeSmallFlat.png").toString();
		Platform plat = new Platform(smallFlat);
		plat.setX(-20);
		plat.setY(275);
		
		System.out.println(plat.getHeight());
		
		String invis = getClass().getClassLoader().getResource("images/slimeBlank.png").toString();
		FlippingPlatform plat2 = new FlippingPlatform(smallFlat, invis, 2, 2);
		plat2.setX(0);
		plat2.setY(180);
		
		Platform plat3 = new Platform(smallFlat);
		plat3.setX(280);
		plat3.setY(220);
		
		String slimeDot = getClass().getClassLoader().getResource("images/slimeDot.png").toString();
		Platform plat4 = new Platform(slimeDot);
		plat4.setX(375);
		plat4.setY(220);
		
		FlippingPlatform plat5 = new FlippingPlatform(smallFlat, invis, 2, 1.5);
		plat5.setX(310);
		plat5.setY(170);
		
		Platform plat6 = new Platform(smallFlat);
		plat6.setX(250);
		plat6.setY(110);
		
		FlippingPlatform plat7 = new FlippingPlatform(slimeDot, invis, 3, 1);
		plat7.setX(350);
		plat7.setY(70);
		
		Platform plat8 = new Platform(smallFlat);
		plat8.setX(235);
		plat8.setY(30);
		
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
	
	public void setPrevWorld(Level4 lev4) {
		prevWorld = lev4;
	}
	
	public void setNextWorld(Level6 lev6) {
		nextWorld = lev6;
	}
}
