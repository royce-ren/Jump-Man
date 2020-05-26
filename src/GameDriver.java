import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
public class GameDriver extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("JUMP!");
		primaryStage.setResizable(false);
		
		BorderPane rootNode = new BorderPane();
		
		Level1 lev1 = new Level1();
		lev1.setPrefSize(400, 300);
		
		rootNode.setCenter(lev1);
		
		Scene scene = new Scene(rootNode);
		
		Player player = new Player();
		player.setX(0);
		player.setY(290);
		
		String smallFlat = getClass().getClassLoader().getResource("images/BrickSmallFlat.png").toString();
		Platform plat2 = new Platform(smallFlat);
		plat2.setX(250);
		plat2.setY(250);
		
		Platform plat3 = new Platform(smallFlat);
		plat3.setX(340);
		plat3.setY(150);
		
		
		
		lev1.getChildren().addAll(player, plat2, plat3);
		
		//stuff here
		
		
		lev1.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.SPACE && player.isGrounded()) lev1.addKey(event.getCode());
				else if(event.getCode() != KeyCode.SPACE) lev1.addKey(event.getCode());
			}
		});
		
		lev1.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.SPACE && player.isGrounded()) lev1.removeKey(event.getCode());
				else if(event.getCode() != KeyCode.SPACE) lev1.removeKey(event.getCode());
			}
		});
		
		lev1.start();
		
		primaryStage.setScene(scene);
		primaryStage.show();
		lev1.requestFocus();
	}
	
}
