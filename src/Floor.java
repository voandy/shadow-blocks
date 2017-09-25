
public class Floor extends MapItem {
	public Floor(Position position) {
		super("res/floor.png", position);
	}
	
	// this second constructor allows for subclasses to be instantiated with a different image
	public Floor(String image_src, Position position) {
		super(image_src, position);
	}
}