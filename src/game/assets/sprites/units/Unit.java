package game.assets.sprites.units;

import game.Properties;
import game.assets.Assets;
import game.assets.sprites.Movable;
import game.assets.sprites.Position;
import game.assets.sprites.Sprite;
import game.assets.sprites.stones.Ice;
import game.assets.sprites.stones.Stone;

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