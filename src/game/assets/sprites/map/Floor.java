package game.assets.sprites.map;

import game.assets.sprites.Position;

public class Floor extends MapItem {
	public Floor(Position position) {
		super("res/floor.png", null, position);
		setBlocked(false);
	}
	
	// this second constructor allows for subclasses to be instantiated with a different image
	public Floor(String image_src, String sound_src, Position position) {
		super(image_src, sound_src, position);
    setBlocked(false);
	}
}