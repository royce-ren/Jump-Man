import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

public class SlantedPlatform extends Actor {
	
	private boolean isLeft;
	
	public SlantedPlatform(String path, boolean isLeft) {
		Image img = new Image(path);
		setImage(img);
//		super(path);
		this.setLeft(isLeft);
	}
	
	@Override
	public void act(long now) {
		
	}

	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}
}
