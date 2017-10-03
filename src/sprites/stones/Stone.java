package sprites.stones;

import game.Assets;
import game.Properties;
import sprites.Movable;
import sprites.Position;
import sprites.Sprite;

public class Stone extends Sprite implements Movable{
	public Stone(Position position) {
		super("res/stone.png", "res/stone.wav", position);
	}
	
	// this second constructor allows for subclasses to be instantiated with a different image
	public Stone(String image_src, String sound_src, Position position) {
		super(image_src, sound_src, position);
	}
	
//	public Stone copy() {
//	  
//	}

	public boolean move(Properties properties, Assets assets) {
		Position nextPos = getPos().nextPos();

		// note that move validation is done in the push() method in the unit class so is unnecessary here
		assets.getStones()[nextPos.getXPos()][nextPos.getYPos()] = this;
		assets.getStones()[getPos().getXPos()][getPos().getYPos()] = null;
		getPos().setPos(nextPos);
		if (getSound() != null) {
	    makeSound();
		}
		
		return true;
	}
	
  // returns false if the destination contains a wall or block and true otherwise
	public boolean isValidMove(Position destination, Assets assets) {
    if (assets.getMap()[destination.getXPos()][destination.getYPos()].isBlocked()) {
      return false;
    }
    // checks if destination contains a stone.
    if (assets.getStones()[destination.getXPos()][destination.getYPos()] != null) {
      return false;
    }
    return true;
	}
}