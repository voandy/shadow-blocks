package game.assets.sprites.stones;

import game.Properties;
import game.assets.Assets;
import game.assets.sprites.Movable;
import game.assets.sprites.Position;
import game.assets.sprites.Sprite;

public class Stone extends Sprite implements Movable{
	public Stone(Position position) {
		super("res/stone.png", "res/stone.wav", position);
	}
	
	// this second constructor allows for subclasses to be instantiated with a different image
	public Stone(String image_src, String sound_src, Position position) {
		super(image_src, sound_src, position);
	}

	public Stone(Stone another) {
	  super(another);
  }

  public boolean move(Properties properties, Assets assets) {	  
		Position nextPos = getPos().nextPos();

		if (isValidMove(nextPos, assets)) {
		  shift(getPos(), assets);
	    return true;
		}
		return false;
	}
	
	private void shift(Position position, Assets assets) {
    assets.getStones()[position.nextPos().getXPos()][position.nextPos().getYPos()] = this;
    assets.getStones()[position.getXPos()][position.getYPos()] = null;
    position.setPos(position.nextPos());
    if (getSound() != null) {
      makeSound();
    }
	}
	
  // returns false if the destination contains a wall or block and true otherwise
	public boolean isValidMove(Position destination, Assets assets) {
    if (assets.getMap()[destination.getXPos()][destination.getYPos()].isBlocked()) {
      return false;
    }
    if (assets.getStones()[destination.getXPos()][destination.getYPos()] != null) {
      return false;
    }
    return true;
	}
}