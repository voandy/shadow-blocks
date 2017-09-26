import org.newdawn.slick.Input;

public abstract class Actor extends Sprite{
	// actors are dynamic sprites in that they can move
	public Actor(String image_src, Position position) {
		super(image_src, position);
	}
	
	public boolean move() {
		Position position = getPos();
		switch(position.getDir()) {
		case DIR_UP:
			position.setYPos(position.getYPos() + 1);
			return true;
		case DIR_DOWN:
			position.setYPos(position.getYPos() - 1);
			return true;
		case DIR_LEFT:
			position.setXPos(position.getXPos() - 1);
			return true;
		case DIR_RIGHT:
			position.setXPos(position.getXPos() + 1);
			return true;
		case DIR_NONE:
			return false;

		default:
			return false;
		}
	}
	
	public void update(Input input, int delta) {
	}
	
}