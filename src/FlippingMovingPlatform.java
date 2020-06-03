
public class FlippingMovingPlatform extends FlippingPlatform {

	private int startX;
	private int finishX;
	private int startY;
	private int finishY;
	private double dy;
	private double dx;
	private double time;

	public FlippingMovingPlatform(String path, String disPath, double lenAppear, double lenDisappear, int startX,
			int finishX, int startY, int finishY, double time) {
		super(path, disPath, lenAppear, lenDisappear);

		this.startX = startX;
		this.startY = startY;
		this.finishX = finishX;
		this.finishY = finishY;
		this.time = time;

		int xDiff = finishX - startX;
		int yDiff = finishY - startY;

		dx = xDiff / (60 * time);
		dy = yDiff / (60 * time);

		setX(startX);
		setY(startY);
	}
	
	@Override
	public void act(long now) {
		
		super.act(now);
		
		if(startX < finishX) {
			if(getX() < startX || getX() > finishX) dx *= -1;
		} else {
			if(getX() < finishX || getX() > startX) dx *= -1;
		}
		
		if(startY < finishY) {
			if(getY() < startY || getY() > finishY) dy *= -1;
		} else {
			if(getY() < finishY || getY() > startY) dy *= -1;
		}
		
		Player player = getOneIntersectingObject(Player.class);
		if(player != null ) {
			player.move(0, dy);
		}
		move(dx, dy);
	}

}
