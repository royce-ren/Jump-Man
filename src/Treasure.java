import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Treasure extends Actor {

	@Override
	public void act(long now) {
		Player player = getOneIntersectingObject(Player.class);
		if(player != null) {
			getWorld().stop();
			BorderPane root = new BorderPane();
			getScene().setRoot(root);
			
			String victory = getClass().getClassLoader().getResource("images/youwin.jpg").toString();
			BackgroundImage end = new BackgroundImage(new Image(victory),
			        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
			          BackgroundSize.DEFAULT); 
			root.setBackground(new Background(end));
			
			HBox hbox = new HBox();
			root.setBottom(hbox);
			
			Label label = new Label("Absolute Respect. Congratz.");
			label.setTextFill(Color.web("#FFFFFF"));
			hbox.getChildren().add(label);
			hbox.setAlignment(Pos.BOTTOM_CENTER);
		}
	}

	public Treasure(String path) {
		setImage(new Image(path));
	}
}
