public abstract class Actor extends Sprite{
	public Actor(String image_src, Position position) {
		super(image_src, position);
	}
	
	public boolean move(Direction direction) {
		return false;
	}
	
}