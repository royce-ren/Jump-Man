import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Player extends Actor {
	private double dx;
	private double dy;
	private double prevX;
	private double prevY;
	private double x;
	private double y;
	private boolean isGrounded;
	private boolean onSlant;
	private boolean hasCollided;
	private long update;
	private boolean change;
	private boolean freeze;
	private boolean holdingFreeze;
	private boolean facingLeft;
	private boolean facingRight;

	private final String idleRight = getClass().getClassLoader().getResource("images/idleRight.png").toString();
	private final String idleLeft = getClass().getClassLoader().getResource("images/idleLeft.png").toString();
	private final String walkLeft = getClass().getClassLoader().getResource("images/walkLeft.png").toString();
	private final String walkRight = getClass().getClassLoader().getResource("images/walkRight.png").toString();
	private final String walkLeft2 = getClass().getClassLoader().getResource("images/walkLeft2.png").toString();
	private final String walkRight2 = getClass().getClassLoader().getResource("images/walkRight2.png").toString();
	private final String fallingLeft = getClass().getClassLoader().getResource("images/fallingLeft.png").toString();
	private final String fallingRight = getClass().getClassLoader().getResource("images/fallingRight.png").toString();
	private final String risingLeft = getClass().getClassLoader().getResource("images/risingLeft.png").toString();
	private final String risingRight = getClass().getClassLoader().getResource("images/risingRight.png").toString();
	private final String collideLeft = getClass().getClassLoader().getResource("images/collideLeft.png").toString();
	private final String collideRight = getClass().getClassLoader().getResource("images/collideRight.png").toString();
	private final String prepJump = getClass().getClassLoader().getResource("images/held.png").toString();

	public Player() {
		setImage(new Image(idleRight));
		isGrounded = true;
		hasCollided = false;
		prevX = 0;
		prevY = 0;
		update = 0;
		freeze = false;
		holdingFreeze = false;
		facingLeft = false;
		onSlant = false;
	}

	// actions when falling
	public void fall() {
		if (pixelPerfectColl(SlantedPlatform.class)) {
			SlantedPlatform plat = getOneIntersectingObject(SlantedPlatform.class);
			hasCollided = true;
			dy = 0;
			if (plat.isLeft()) {
				dx -= 0.6;
				dx = Math.max(dx, -7);
			} else {
				dx += 0.6;
				dx = Math.min(dx, 7);
			}

			dy = Math.abs(dx);

			move(dx, dy);
		} else {
			dy += 0.4;
			dy = Math.min(dy, 7); // cap falling speed
			move(dx, dy);
		}

	}

	// method to handle all airborne and jumping actions
	public void move() {
		if (!isGrounded) {
			fall();
		} else {
			// movement along platforms if no jump
			if (getWorld().isKeyDown(KeyCode.LEFT) && !getWorld().isHeld() && !outOfBounds()) {
				move(-2, 0);
			} else if (getWorld().isKeyDown(KeyCode.RIGHT) && !getWorld().isHeld() && !outOfBounds()) {
				move(2, 0);
			}

			// actions when jumping
			if (getWorld().isReleased()) {
				holdingFreeze = false;
				updateStatus();
				long time = getWorld().getTime();

				dx = 0; // if direction not held at release jump straight up
				if (getWorld().isKeyDown(KeyCode.LEFT))
					dx = -3 - time * 0.001; // set respective x speeds if held arrow keys
				else if (getWorld().isKeyDown(KeyCode.RIGHT))
					dx = 3 + time * 0.001;
				
				dx = Math.max(Math.min(dx, 9), -9);

				dy = -2 + -1 * time * 0.015; // scale y velocity proportionally to time held, 2

				dy = Math.max(-9, dy); // cap dy at a constant, holding too long caps speed, 12

				move(dx, dy); // when space is released, JUMP!, at set speeds
								// note this gets player off platform, thus not grounded, and starts the midair
								// process

				getWorld().setReleased(false); // immediately when space is released to jump set released to false
			}
			// consider edge case falling down stairs for example
		}
	}

	// platform and obstacle interactions, changes movement variables!
	public void interact() {
		Platform platform = getOneIntersectingObject(Platform.class);

		// if we're touching a platform and we're not grounded, then execute task
		// platform interactions
		if (platform != null && !platform.isInvisible && !isGrounded) {
			double xCenter = getX() + getWidth() / 2;
			double yCenter = getY() + getHeight() / 2;

			// slight bug where xcenter phases through sides of platform, but changing to
			// proper edges bugs out
			if (xCenter > platform.getX() && xCenter < platform.getX() + platform.getWidth()) {
				if (getY() <= platform.getY() + platform.getHeight()
						&& getY() >= platform.getY() + platform.getHeight() / 2) {
					dy = Math.abs(dy * 0.8); // if hit bottom of plat, reverse y
					hasCollided = true;
				}
			} else if (getY() + getHeight() >= platform.getY() && getY() <= platform.getY() + platform.getHeight()) {
				if (getX() < platform.getX())
					dx = -Math.abs(dx * 0.9); // if hit side of platform, reverse x direction
				else
					dx = Math.abs(0.9 * dx);
				hasCollided = true;
			} else {
				dy = Math.abs(dy * 0.8);
				dx *= -0.9;
			}
		}
		
		//fixthis
		/*SlantedPlatform slant = getOneIntersectingObject(SlantedPlatform.class);
		if(slant != null && !isGrounded) {
			if (getX() + getWidth() >= slant.getX() && getX() <= slant.getX() + slant.getWidth()) {
				if (getY() <= slant.getY() + slant.getHeight()
						&& getY() >= slant.getY() + slant.getHeight() / 2) {
					onSlant = false;
					dy = Math.abs(dy * 0.8); // if hit bottom of plat, reverse y
					hasCollided = true;
				}
			} else if(getY() + getHeight() >= slant.getY() && getY() <= slant.getY() + slant.getHeight()) {
				System.out.println("bruhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
				if(slant.isLeft() && getX() > slant.getX() + slant.getWidth() / 2) {
					onSlant = false;
					dx = Math.abs(0.9 * dx);
					hasCollided = true;
				} else if(!slant.isLeft() && getX() < slant.getX() + slant.getWidth() / 2) {
					onSlant = false;
					dx = -Math.abs(dx * 0.9);
					hasCollided = true;
				}
			} else onSlant = true;
		}*/

		double xMax = getWorld().getWidth();
		if (getX() <= 0 || getX() >= xMax - getWidth()) {
			if (getX() <= 0)
				dx = Math.abs(0.9 * dx);
			else
				dx = -Math.abs(0.9 * dx); // bounce off walls
			hasCollided = true;
		}

	}

	// method to change statuses
	public void updateStatus() {
		Platform platform = getOneIntersectingObject(Platform.class);
		// if we are touching a platform and we're on top of it, we're grounded
		if (getY() >= getWorld().getHeight() - getHeight() && !getWorld().isOpenBottom())
			isGrounded = true;
		else if (platform != null  && !platform.isInvisible) {
			if (getX() + getWidth() > platform.getX() && getX() < platform.getX() + platform.getWidth()) {
				if (getY() + getHeight() >= platform.getY()
						&& getY() + getHeight() < platform.getY() + platform.getHeight() / 2) { // caution! bug was found here
					isGrounded = true;
					setY(platform.getY() - getHeight());
				}
			}
		} else isGrounded = false;

		if (update % 7 == 0) {
			change = !change;
		}

		if (prevX > getX()) {
			facingLeft = true;
			facingRight = false;
		} else if (getX() > prevX) {
			facingLeft = false;
			facingRight = true;
		}

		// sprite updates
		if (isGrounded && !holdingFreeze) {
			onSlant = false;
			hasCollided = false; // if we're on the ground now reset collision
			freeze = false;
			if (getWorld().isHeld() && !holdingFreeze) {
				if (facingRight)
					setImage(new Image(idleRight));
				else
					setImage(new Image(idleLeft));
				holdingFreeze = !holdingFreeze;
			} else if (getX() < prevX && change)
				setImage(new Image(walkLeft));
			else if (getX() > prevX && change)
				setImage(new Image(walkRight));
			else if (getX() < prevX)
				setImage(new Image(walkLeft2));
			else if (getX() > prevX)
				setImage(new Image(walkRight2));
			else if (facingLeft)
				setImage(new Image(idleLeft));
			else if (facingRight)
				setImage(new Image(idleRight));
		} else if (!freeze && !holdingFreeze) {
			if (hasCollided && prevX > getX()) {
				setImage(new Image(collideRight)); // if collided collision animation default, else rise/fall
				freeze = true;
			} else if (hasCollided && prevX < getX()) {
				setImage(new Image(collideLeft));
				freeze = true;
			} else if (prevY < getY() && prevX < getX())
				setImage(new Image(fallingRight));
			else if (prevY < getY() && prevX > getX())
				setImage(new Image(fallingLeft));
			else if (prevY > getY() && prevX < getX())
				setImage(new Image(risingRight));
			else if (prevY > getY() && prevX > getX())
				setImage(new Image(risingLeft));
		}

		update++;

	}

	// a method for isGrounded movement bounding
	public boolean outOfBounds() {
		if (!getWorld().isOpenBottom()) {
			if (getY() + getHeight() > getWorld().getHeight()) {
				setY(getWorld().getHeight() - getHeight());
				return true;
			}
		}

		List<Platform> list = getIntersectingObjects(Platform.class);
		for (Platform plat : list) {

			double left = plat.getX() - getWidth();
			double right = plat.getX() + plat.getWidth();

			if ((getX() + getWidth() > plat.getX() && getX() + getWidth() < plat.getX() + plat.getWidth() / 3)
					|| (getX() < plat.getX() + plat.getWidth() && getX() > plat.getX() + plat.getWidth() / 3)) {
				if (getY() + getHeight() > plat.getY() && getY() < plat.getY() + plat.getHeight()) {
//					System.out
//							.println("x: " + getX() + " " + "y: " + getY() + "   prevX: " + prevX + " prevY: " + prevY);
//					if (prevX >= plat.getX() || prevX <= plat.getX() + plat.getWidth())
//						System.out.println(true);
					if (Math.abs(left - getX()) > Math.abs(right - getX())) {
						setX(right);
						prevX = getX();
					} else {
						setX(left);
						prevX = getX();
					}

					return true;
				}
			}
		}

		SlantedPlatform slant = getOneIntersectingObject(SlantedPlatform.class);
		if (slant != null) {
			double left = slant.getX() - getWidth();
			double right = slant.getX() + slant.getWidth();
			if (slant.isLeft()) {
				if (getX() + getWidth() < slant.getX() + slant.getWidth() / 2) {
					setX(left);
					prevX = getX();
				} else {
					setX(right);
				}
			} else {
				if (getX() >= slant.getX() + slant.getWidth() / 2) {
					setX(right);
					prevX = getX();
				} else {
					setX(left);
				}
			}
		}

		// Platform plat = getOneIntersectingObject(Platform.class);
		// if(plat != null) {
		// double centerX = getX() + getWidth() / 2;
		// double centerY = getY() + getHeight() / 2;
		//
		// if(getX() + getWidth() > plat.getX() && getX() < centerX) {
		// setX(plat.getX() - getWidth());
		// dx = 0;
		// return true;
		// }
		//
		// if(getX() < plat.getX() + plat.getWidth() && getX() > centerX) {
		// setX(plat.getX() + plat.getWidth());
		// dx = 0;
		// return true;
		// }
		//
		// if(getY() < plat.getY() + plat.getHeight() && getY() + getHeight() >
		// plat.getY() && centerX < plat.getX() + plat.getWidth() && centerX >
		// plat.getX()) {
		// setY(prevY);
		// setX(prevX);
		// return true;
		// }

		// if(getY() + getHeight() > plat.getY() && getY() < centerY) {
		// setY(plat.getY() - getHeight());
		// return true;
		// }
		// }

		return false;
	}

	@Override
	public void act(long now) {
		interact(); // change dy and dx through any interactions with objects, if no interactions
					// then continue
		updateStatus(); // after interactions update statuses and sprites accordingly

		// Update our previous positions to where they are now, before moving
		prevX = Math.min(Math.max(getX(), 0), getWorld().getWidth());
		prevY = Math.min(getY(), getWorld().getHeight() - getHeight());

		move(); // move with any modified values of dy and dx
		// System.out.println("x: " + getX() + " " + "y: " + getY() + " prevX: " + prevX
		// + " prevY: " + prevY);
		// System.out.println(getX() == prevX);
//		System.out.println(isGrounded);

		// good bound checking
		setX(Math.min(Math.max(getX(), 0), getWorld().getWidth() - 22)); // idk why, but the world is not the correct x
																			// size
		if (!getWorld().isOpenBottom())
			setY(Math.min(getY(), getWorld().getHeight() - getHeight()));
	}

	public boolean isGrounded() {
		return isGrounded;
	}

	public double getDX() {
		return dx;
	}

	public double getDY() {
		return dy;
	}
}
