import java.util.ArrayList;

public class Stone extends Actor {
	public Stone(Position position) {
		super("res/stone.png", "res/stone.wav", position);
	}
	
	// this second constructor allows for subclasses to be instantiated with a different image
	public Stone(String image_src, String sound_src, Position position) {
		super(image_src, sound_src, position);
	}

	public boolean move(Properties properties, Assets assets) {
		Position nextPos = getPos().nextPos();

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