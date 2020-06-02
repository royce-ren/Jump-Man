import javafx.scene.image.Image;

public class FlippingPlatform extends Platform {
	
	private long counter = 0;
	private String path;
	private String disPath;
	private double lenAppear;
	private double lenDisappear;

	public FlippingPlatform(String path, String disPath, double lenAppear, double lenDisappear) {
		super(path);
		this.path = path;
		this.disPath = disPath;
		this.lenAppear = lenAppear;
		this.lenDisappear = lenDisappear;
	}
	
	@Override
	public void act(long now) {
		counter++;
		if(!isInvisible) {
			setImage(new Image(path));
			if(counter / (60 * lenAppear) >= 1) {
				System.out.println("hey, listen!");
				counter = 0;
				isInvisible = true;
			}
		} else {
			setImage(new Image(disPath));
			if(counter / (60 * lenDisappear) >= 1) {
				counter = 0;
				isInvisible = false;
			}
		}
	}

}
