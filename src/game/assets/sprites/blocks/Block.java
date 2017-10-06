package game.assets.sprites.blocks;

import game.Properties;
import game.assets.Assets;
import game.assets.sprites.Movable;
import game.assets.sprites.Position;
import game.assets.sprites.Sprite;
import game.assets.sprites.units.Unit;

public class Block extends Sprite implements Movable{
	public Block(Position position) {
		super("res/block.png", "res/block.wav", position);
	}
	
	// this second constructor allows for subclasses to be instantiated with a different image
	public Block(String image_src, String sound_src, Position position) {
		super(image_src, sound_src, position);
	}

	public Block(Block another) {
	  super(another);
  }

	// moves the block one grid position if possible
  public boolean move(Properties properties, Assets assets) {	  
		Position nextPos = getPos().nextPos();

		if (isValidMove(nextPos, assets)) {
		  shift(getPos(), assets);
	    return true;
		}
		return false;
	}
	
  // updates the Blocks positions as well as the Block[][] array in Assets to reflect this
	private void shift(Position position, Assets assets) {
    assets.getBlocks()[position.nextPos().getXPos()][position.nextPos().getYPos()] = this;
    assets.getBlocks()[position.getXPos()][position.getYPos()] = null;
    position.setPos(position.nextPos());
    if (getSound() != null) {
      makeSound();
    }
	}
	
  // returns false if the destination contains a wall, block or unit and true otherwise
	public boolean isValidMove(Position destination, Assets assets) {
    if (assets.getMap()[destination.getXPos()][destination.getYPos()].isBlocked()) {
      return false;
    }
    if (assets.getBlocks()[destination.getXPos()][destination.getYPos()] != null) {
      return false;
    }
    for (Unit unit : assets.getUnits()) {
      if(destination.equals(unit.getPos())) {
        return false;
      }
    }
    return true;
	}
}