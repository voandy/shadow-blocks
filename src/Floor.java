import java.util.ArrayList;

import org.newdawn.slick.Input;

public class Floor extends MapItem {
	public Floor(Position position) {
		super("res/floor.png", null, position);
	}
	
	// this second constructor allows for subclasses to be instantiated with a different image
	public Floor(String image_src, String sound_src, Position position) {
		super(image_src, sound_src, position);
	}
	
	public void update(Input input, int delta, Properties properties, Assets assets) {
	}
}