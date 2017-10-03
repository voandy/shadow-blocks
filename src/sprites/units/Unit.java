package sprites.units;

import game.Assets;
import game.Properties;
import sprites.Movable;
import sprites.Position;
import sprites.Sprite;
import sprites.stones.Ice;
import sprites.stones.Stone;

public abstract class Unit extends Sprite implements Movable{

	public Unit(String image_src, String sound_src, Position position) {
		super(image_src, sound_src, position);
	}
	
	// moves the Unit one grid length and pushes a stone if one is present
	public boolean move(Properties properties, Assets assets) {
		Position nextPos = getPos().nextPos();
		if (isValidMove(nextPos, assets)) {
		  
		  Stone stone = assets.getStones()[nextPos.getXPos()][nextPos.getYPos()];
			if (stone != null) {
		    // if there is a stone in nextPos we try to push it
			  stone.getPos().setDir(getPos().getDir());
			  if (stone.move(properties, assets)) {
		       // the player doesn't move when pushing Ice, this makes for a smoother animation        
	        if (!(stone instanceof Ice)) {
	          shift();
	          return true;
	        }
			  } else {
			    // if the stone can't move the player doesn't move
			    return false;
			  }
			} else {
			  shift();
				return true;
			}
		}
		return false;
	}
	
	// shifts the unit one square and makes its noise
	private void shift() {
    if (getSound() != null) {
      makeSound();
    }
    getPos().setPos(getPos().nextPos());
	}
	
	// returns true if the unit can move to the destination
	public boolean isValidMove(Position destination, Assets assets) {
	  // if the destination is a wall return false
    if (assets.getMap()[destination.getXPos()][destination.getYPos()].isBlocked()) {
      return false;
    }
    return true;
	}
}