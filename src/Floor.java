public class Floor extends Sprite {
	
	public Floor(String image_src, Position position) {
		super(image_src, position);
	}
	
	public Floor(Position position) {
		super("res/floor.png", position);
	}
}