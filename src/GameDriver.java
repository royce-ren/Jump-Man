import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
public class GameDriver extends Application {
	
	private Level1 lev1;
	private Level2 lev2;
	private Level3 lev3;
	private Level4 lev4;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("JUMP!");
		primaryStage.setResizable(false);
		
		BorderPane rootNode = new BorderPane();
		Scene scene = new Scene(rootNode);
		
//		lev1 = new Level1(rootNode, null);
		
		String path = getClass().getClassLoader().getResource("images/dungeon.png").toString();
		BackgroundImage myBI= new BackgroundImage(new Image(path),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT); //322.0 412.0
		
		rootNode.setBackground(new Background(myBI));
		
		lev1 = new Level1(rootNode);
		lev2 = new Level2(rootNode);
		lev3 = new Level3(rootNode);
		lev4 = new Level4(rootNode);
		
		lev1.setNextWorld(lev2);
		lev1.setRootNode(rootNode);
		
		lev2.setPrevWorld(lev1);
		lev2.setNextWorld(lev3);
		lev2.setRootNode(rootNode);
		lev2.setPlayer(lev1.getPlayer());
		
		lev3.setPrevWorld(lev2);
		lev3.setNextWorld(lev4);
		lev3.setRootNode(rootNode);
		lev3.setPlayer(lev1.getPlayer());
		
		lev4.setPrevWorld(lev3);
		lev4.setRootNode(rootNode);
		lev4.setPlayer(lev1.getPlayer());
		
		
		lev1.start();
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}
