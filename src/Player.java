import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Player extends Actor {
	private double dx;
	private double dy;
	private double prevX;
	private double prevY;
	private boolean isGrounded;
	private boolean hasCollided;
	
	private final String idle = getClass().getClassLoader().getResource("images/idle.png").toString();
	private final String walkLeft = getClass().getClassLoader().getResource("images/idle.png").toString();
	private final String walkRight = getClass().getClassLoader().getResource("images/idle.png").toString();
	private final String falling = getClass().getClassLoader().getResource("images/idle.png").toString();
	private final String rising = getClass().getClassLoader().getResource("images/idle.png").toString();
	private final String collide = getClass().getClassLoader().getResource("images/idle.png").toString();
	private final String prepJump = getClass().getClassLoader().getResource("images/idle.png").toString();
	
	public Player() {
		setImage(new Image(idle));
		isGrounded = true;
		hasCollided = false;
		prevX = 0;
		prevY = 0;
	}
	
	//actions when falling
	public void fall() {
		dy += 0.5;
		dy = Math.min(dy, 5); //cap falling speed
		move(dx, dy);
	}

	//method to handle all airborne and jumping actions
	public void move() {
		if(!isGrounded) {
			fall();
		} else {
			//movement along platforms if no jump
			if(getWorld().isKeyDown(KeyCode.LEFT) && !getWorld().isHeld() && !outOfBounds()) {
				move(-2, 0);
			} else if(getWorld().isKeyDown(KeyCode.RIGHT) && !getWorld().isHeld() && !outOfBounds()) {
				move(2, 0);
			}
			
			//actions when jumping
			if(getWorld().isReleased()) {
				long time = getWorld().getTime();
				
				dx = 0; //if direction not held at release jump straight up
				if(getWorld().isKeyDown(KeyCode.LEFT)) dx = -2 - time * 0.001; //set respective x speeds if held arrow keys
				else if(getWorld().isKeyDown(KeyCode.RIGHT)) dx = 2 + time * 0.001;
					
				dy = -1 * time * 0.02; //scale y velocity proportionally to time held
				
				dy = Math.max(-12, dy); //cap dy at a constant, holding too long caps speed
				
				move(dx, dy); //when space is released, JUMP!, at set speeds
							  //note this gets player off platform, thus not grounded, and starts the midair process
				
				getWorld().setReleased(false); //immediately when space is released to jump set released to false
			}
			//consider edge case falling down stairs for example
		}
	}
	
	//platform and obstacle interactions, changes movement variables!
	public void interact() {
		Platform platform = getOneIntersectingObject(Platform.class);
		
		//if we're touching a platform and we're not grounded, then execute task
		//platform interactions
		if(platform != null && !isGrounded) {
			double xCenter = getX() + getWidth() / 2;
			double yCenter = getY() + getHeight() / 2;
			
			if(xCenter > platform.getX() && xCenter < platform.getX() + platform.getWidth()) {
				if(getY() <= platform.getY() + platform.getHeight()) {
					dy *= -0.8; //if hit bottom of plat, reverse y
					hasCollided = true;
				}
			} else if(getY() + getHeight() > platform.getY() && getY() <= platform.getY() + platform.getHeight()) {
				dx *= -0.9; //if hit side of platform, reverse x direction
				System.out.println("lalalalalalal");
				hasCollided = true;
			} 
		}
		
		double xMax = getWorld().getWidth();
		if(getX() <= 0 || getX() >= xMax - getWidth()) {
			dx *= -1; //bounce off walls
			hasCollided = true;
		}
		
		
	}
	
	//method to change statuses, does not change movement variables!
	public void updateStatus() {
		Platform platform = getOneIntersectingObject(Platform.class);
		
		//if we are touching a platform and we're on top of it, we're grounded
		if(getY() >= getWorld().getHeight() - getHeight()) isGrounded = true;
		else if(platform != null) {
			if(getX() + getWidth() > platform.getX() && getX() < platform.getX() + platform.getWidth()) {
				System.out.println(getY() + getHeight() + "     " + platform.getY());
				if(getY() + getHeight() >= platform.getY() && getY() + getHeight() < platform.getY() + platform.getHeight() / 3) {
					isGrounded = true;
					setY(platform.getY() - getHeight());
				}
			} 
		} else isGrounded = false;
		
		//sprite updates
		if(isGrounded) {
			hasCollided = false; //if we're on the ground now reset collision
			if(getX() > prevX) setImage(new Image(walkRight));
			else if(getX() < prevX) setImage(new Image(walkLeft));
			else if(getWorld().isHeld()) setImage(new Image(prepJump));
			else setImage(new Image(idle));
		} else {
			if(hasCollided) setImage(new Image(collide)); //if collided collision animation default, else rise/fall
			else if(prevY < getY()) setImage(new Image(falling));
			else if(prevY > getY()) setImage(new Image(rising));
		}
	}
	
	//a method for isGrounded movement bounding
	public boolean outOfBounds() {
		if(!getWorld().isOpenBottom()) {
			if(getY() + getHeight() > getWorld().getHeight()) {
				setY(getWorld().getHeight() - getHeight());
				dy = 0;
				return true;
			}
		}
		
		if(getX() < 0 || getX() + getWidth() > getWorld().getWidth()) {
			if(getX() < 0) setX(0);
			if(getX() + getWidth() > getWorld().getWidth()) setX(getWorld().getWidth() - getWidth());
			dx = 0;
			return true;
		}
		
//		Platform plat = getOneIntersectingObject(Platform.class);
//		if(plat != null) {
//			double centerX = plat.getX() + plat.getWidth() / 2;
//			double centerY = plat.getY() + plat.getHeight() / 2;
//			
//			if(getX() + getWidth() > plat.getX() && getX() < centerX) {
//				setX(plat.getX() - getWidth()); 
//				dx = 0;
//				return true;
//			}
//			
//			if(getX() < plat.getX() + plat.getWidth() && getX() > centerX) {
//				setX(plat.getX() + plat.getWidth());
//				dx = 0;
//				return true;
//			}
//			
//			if(getY() < plat.getY() + plat.getHeight() && getY() > centerY) {
//				setY(plat.getY() + getHeight()); 
//				return true;
//			}
//			
//			if(getY() + getHeight() > plat.getY() && getY() < centerY) {
//				setY(plat.getY() - getHeight()); 
//				return true;
//			}
//		}
		
		return false;
	}
	
	@Override
	public void act(long now) {
		System.out.println(isGrounded);
		interact(); //change dy and dx through any interactions with objects, if no interactions then continue
		updateStatus(); //after interactions update statuses and sprites accordingly
		move(); //move with any modified values of dy and dx
		
		//at the end, update our previous positions to where they are now
		prevX = getX();
		prevY = getY();
	}
	
	public boolean isGrounded() {
		return isGrounded;
	}
}
