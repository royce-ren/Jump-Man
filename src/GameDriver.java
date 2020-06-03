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
	private Level5 lev5;
	private Level6 lev6;
	private Level7 lev7;
	private Level8 lev8;
	private Level9 lev9;
	private Level10 lev10;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("JUMP!");
		primaryStage.setResizable(false);
		
		BorderPane rootNode = new BorderPane();
		Scene scene = new Scene(rootNode);
		
		String path = getClass().getClassLoader().getResource("images/dungeon.png").toString();
		BackgroundImage myBI= new BackgroundImage(new Image(path),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT); //322.0 412.0
		
		rootNode.setBackground(new Background(myBI));
		
		lev1 = new Level1(rootNode);
		lev2 = new Level2(rootNode);
		lev3 = new Level3(rootNode);
		lev4 = new Level4(rootNode);
		lev5 = new Level5(rootNode);
		lev6 = new Level6(rootNode);
		lev7 = new Level7(rootNode);
		lev8 = new Level8(rootNode);
		lev9 = new Level9();
		lev10 = new Level10();
		
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
		lev4.setNextWorld(lev5);
		lev4.setRootNode(rootNode);
		lev4.setPlayer(lev1.getPlayer());
		
		lev5.setPrevWorld(lev4);
		lev5.setNextWorld(lev6);
		lev5.setRootNode(rootNode);
		lev5.setPlayer(lev1.getPlayer());
		
		lev6.setPrevWorld(lev5);
		lev6.setNextWorld(lev7);
		lev6.setRootNode(rootNode);
		lev6.setPlayer(lev1.getPlayer());
		
		lev7.setPrevWorld(lev6);
		lev7.setNextWorld(lev8);
		lev7.setRootNode(rootNode);
		lev7.setPlayer(lev1.getPlayer());
		
		lev8.setPrevWorld(lev7);
		lev8.setNextWorld(lev9);
		lev8.setRootNode(rootNode);
		lev8.setPlayer(lev1.getPlayer());
		
		lev9.setPrevWorld(lev8);
		lev9.setNextWorld(lev10);
		lev9.setRootNode(rootNode);
		lev9.setPlayer(lev1.getPlayer());
		
		lev10.setPrevWorld(lev9);
		lev10.setRootNode(rootNode);
		lev10.setPlayer(lev1.getPlayer());
		
		lev1.start();
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}
