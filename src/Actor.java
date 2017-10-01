public abstract class Actor extends Sprite{
	// actors are dynamic sprites in that they can move
	public Actor(String image_src, String sound_src, Position position) {
		super(image_src, sound_src, position);
	}
	
	// moves the Actor one grid length in the direction in which it is currently facing
	public boolean move(Properties properties, Assets assets) {
		return false;
	}
	
	public boolean isValidMove(Position destination, Assets assets) {
		if (assets.getMap()[destination.getXPos()][destination.getYPos()].isBlocked()) {
			return false;
		}
		return true;
	}
}