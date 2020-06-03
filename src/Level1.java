import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class Level1 extends World {
	
	private Level2 nextWorld;
	
	@Override
	public void act(long now) {
		if(player.getY() < 0 && player.getDY() < 0) {
			double x = player.getX();
			
			stop();
			rootNode.setCenter(nextWorld);
			nextWorld.getChildren().add(player);
			this.getChildren().remove(player);
			nextWorld.clearSet();
			nextWorld.requestFocus();
			nextWorld.start();
			player.setX(x);
			player.setY(312); //nextWorld.getHeight() returns zero for some reason
		} else if(isKeyDown(KeyCode.DIGIT2)) {
			stop();
			rootNode.setCenter(nextWorld);
			nextWorld.getChildren().add(player);
			this.getChildren().remove(player);
			nextWorld.clearSet();
			nextWorld.requestFocus();
			nextWorld.start();
			player.setX(60);
			player.setY(265);
			player.clearDYDX();
		}
	}
	
	public Level1(BorderPane rootNode)  {
		this.setPrefSize(400, 300);
		this.rootNode = rootNode;
		rootNode.setCenter(this);
		
		player = new Player();
		player.setX(0);
		player.setY(0);
		
		String brickWall = getClass().getClassLoader().getResource("images/brickWall.png").toString();
		Platform plat = new Platform(brickWall);
		plat.setX(50);
		plat.setY(-10);
		
//		String slab = getClass().getClassLoader().getResource("images/slab.png").toString();
//		Platform plat2 = new Platform(slab);
//		plat2.setX(175);
//		plat2.setY(290);
		
		String smallFlat = getClass().getClassLoader().getResource("images/BrickSmallFlat.png").toString();
		Platform plat3 = new Platform(smallFlat);
		plat3.setX(260);
		plat3.setY(280);
		
		Platform plat4 = new Platform(smallFlat);
		plat4.setX(340);
		plat4.setY(190);
		
		Platform plat5 = new Platform(smallFlat);
		plat5.setX(310);
		plat5.setY(95);
		
		Platform plat6 = new Platform(smallFlat);
		plat6.setX(160);
		plat6.setY(65);
		
		this.getChildren().addAll(player, plat,  plat3, plat4, plat5, plat6);
		
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
		
		requestFocus();
	}
	
	public Level1(BorderPane rootNode, Level2 nextWorld) {
		this.nextWorld = nextWorld;
		this.setPrefSize(400, 300);
		
		rootNode.setCenter(this);
		
		player = new Player();
		player.setX(0);
		player.setY(290);
		
		String smallFlat = getClass().getClassLoader().getResource("images/BrickSmallFlat.png").toString();
		Platform plat2 = new Platform(smallFlat);
		plat2.setX(250);
		plat2.setY(250);
		
		Platform plat3 = new Platform(smallFlat);
		plat3.setX(340);
		plat3.setY(150);
		
		
		
		this.getChildren().addAll(player, plat2, plat3);
		
		//stuff here
		
		
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
		
		requestFocus();
	}
	
	public void setNextWorld(Level2 lev2) {
		nextWorld = lev2;
	}
	
}
