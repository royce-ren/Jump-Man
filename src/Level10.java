import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

public class Level10 extends World {
	
	private Level9 prevWorld;
	private boolean freeze = false;
	
	@Override
	public void act(long now) {
		if(player.getY() + player.getHeight() > getHeight() && player.getDY() > 0) {
			double x = player.getX();
			
			stop();
			rootNode.setBackground(new Background(dirt));
			rootNode.setCenter(prevWorld);
			prevWorld.getChildren().add(player);
			this.getChildren().remove(player);
			prevWorld.clearSet();
			prevWorld.requestFocus();
			player.setX(x);
			player.setY(0);
			prevWorld.start();
		} else if(isKeyDown(KeyCode.DIGIT0)) {
			player.setX(320);
			player.setY(265);
		}
		
		if(player.getX() <= 130 && player.getX() > 50) {
			clearSet();
			freeze = true;
			player.setDX(0);
			player.move(-0.75, 0);
		}
	}
	
	public Level10() {
		this.setPrefSize(400, 300);
		setOpenBottom(true);
		
		String smallFlat = getClass().getClassLoader().getResource("images/grassSmallFlat.png").toString();
		Platform plat = new Platform(smallFlat);
		plat.setX(310);
		plat.setY(290);
		
		String grassFinal = getClass().getClassLoader().getResource("images/grassFinal.png").toString();
		Platform plat2 = new Platform(grassFinal);
		plat2.setX(0);
		plat2.setY(245);
		
		String money = getClass().getClassLoader().getResource("images/treasure.png").toString();
		Treasure treasure = new Treasure(money);
		treasure.setX(27);
		treasure.setY(245 - treasure.getHeight());
		
		
		this.getChildren().addAll(plat, plat2, treasure);
		
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(freeze) return;
				if(event.getCode() == KeyCode.SPACE && player.isGrounded()) addKey(event.getCode());
				else if(event.getCode() != KeyCode.SPACE) addKey(event.getCode());
			}
		});
		
		this.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(freeze) return;
				if(event.getCode() == KeyCode.SPACE && player.isGrounded()) removeKey(event.getCode());
				else if(event.getCode() != KeyCode.SPACE) removeKey(event.getCode());
			}
		});
	}
	
	public void setPrevWorld(Level9 lev9) {
		prevWorld = lev9;
	}
}
