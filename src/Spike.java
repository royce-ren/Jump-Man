import javafx.scene.image.Image;

public class Spike extends Actor {
	
	public Spike(String path) {
		Image img = new Image(path);
		setImage(img);
	}
	
	@Override
	public void act(long now) {
		
	}
}
