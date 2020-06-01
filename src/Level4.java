import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

public class Level4 extends World {
	
	private Level3 prevWorld;
	//private Level5 nextWorld;
	
	@Override
	public void act(long now) {
		if(player.getY() + player.getHeight() > getHeight() && player.getDY() > 0) {
			double x = player.getX();
			
			stop();
			rootNode.setBackground(new Background(dungeon));
			rootNode.setCenter(prevWorld);
			prevWorld.getChildren().add(player);
			this.getChildren().remove(player);
			prevWorld.clearSet();
			prevWorld.requestFocus();
			player.setX(x);
			player.setY(0);
			prevWorld.start();
		}
	}
	
	public Level4(BorderPane rootNode) {
		this.setPrefSize(400, 300);
		setOpenBottom(true);
		
		String smallFlat = getClass().getClassLoader().getResource("images/slimeSmallFlat.png").toString();
		Platform plat = new Platform(smallFlat);
		plat.setX(275);
		plat.setY(275);
		
		Platform plat2 = new Platform(smallFlat);
		plat2.setX(120);
		plat2.setY(285);
		
		String slimeDot = getClass().getClassLoader().getResource("images/slimeDot.png").toString();
		Platform plat3 = new Platform(slimeDot);
		plat3.setX(370);
		plat3.setY(255);
		
		Platform plat4 = new Platform(smallFlat);
		plat4.setX(310);
		plat4.setY(170);
		
		String slimeInvis = getClass().getClassLoader().getResource("images/slimeSmallFlatInvis.png").toString();
		Platform plat5 = new Platform(slimeInvis);
		plat5.setX(160);
		plat5.setY(170);
		
		Platform plat6 = new Platform(smallFlat);
		plat6.setX(0);
		plat6.setY(150);
		
		Platform plat7 = new Platform(slimeInvis);
		plat7.setX(90);
		plat7.setY(55);
		
		this.getChildren().addAll(plat, plat2, plat3, plat4, plat5, plat6, plat7);
		
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
	
	public void setPrevWorld(Level3 lev3) {
		prevWorld = lev3;
	}
	
//	public void setNextWorld(Level5 lev5) {
//		nextWorld = lev5;
//	}
}
