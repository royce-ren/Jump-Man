import javafx.scene.image.Image;

public class Platform extends Actor {
	
	protected boolean isInvisible = false;

	public Platform(String path) {
		Image img = new Image(path);
		setImage(img);
	}
	
	@Override
	public void act(long now) {
		
	}
}
