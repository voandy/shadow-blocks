import java.util.ArrayList;

public class Stone extends Actor {
	public Stone(Position position) {
		super("res/stone.png", "res/stone.wav", position);
	}
	
	// this second constructor allows for subclasses to be instantiated with a different image
	public Stone(String image_src, String sound_src, Position position) {
		super(image_src, sound_src, position);
	}

	public boolean move(LevelProperties properties, Assets assets) {
		Position nextPos = getPos().nextPos();
		
		// we use this method to update how many targets have been toggled onlu when a stone is moved
		// previously I did it with the update() method but that was less efficient as the game had to update and count
		// the number of targets toggled for every single frame
		if (assets.getMap()[getPos().getXPos()][getPos().getYPos()] instanceof Target) {
			properties.untoggleTarget();
		}
		if (assets.getMap()[nextPos.getXPos()][nextPos.getYPos()] instanceof Target) {
			properties.toggleTarget();
		}

		// note that move validation is done in the push() method in the unit class so is unnecessary here
		assets.getStones()[nextPos.getXPos()][nextPos.getYPos()] = this;
		assets.getStones()[getPos().getXPos()][getPos().getYPos()] = null;
		getPos().setPos(nextPos);
		makeSound();
		
		return true;
	}
	
	public boolean isValidMove(Position destination, Assets assets) {
		return super.isValidMove(destination, assets);
	}
}