package sprites.units;

import game.Assets;
import game.Properties;
import sprites.Direction;
import sprites.Movable;
import sprites.Position;
import sprites.Sprite;
import sprites.map.CrackedWall;
import sprites.stones.Ice;
import sprites.stones.Stone;
import sprites.stones.Tnt;

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
		     // if there is a stone in nextPos we push it
				push(properties, (Stone) assets.getStones()[nextPos.getXPos()][nextPos.getYPos()], getPos().getDir(), assets);
				
				// the player doesn't move when pushing Ice, this makes for a smoother animation				
				if (!(stone instanceof Ice)) {
	        getPos().setPos(nextPos);
				}
				return true;
				
			} else {
				getPos().setPos(nextPos);
				return true;
			}
		}
		return false;
	}
	
	// returns true if the unit can move to the destination
	public boolean isValidMove(Position destination, Assets assets) {
	  // if the destination is a wall return false
    if (assets.getMap()[destination.getXPos()][destination.getYPos()].isBlocked()) {
      return false;
    }
		
		// checks if the destination contains a Stone
		if (assets.getStones()[destination.getXPos()][destination.getYPos()] != null) {
			// gets the grid position behind the stone
			Position nextDest = destination.nextPos();
			// checks if there is a wall behind the stone
			if (assets.getMap()[nextDest.getXPos()][nextDest.getYPos()].isBlocked()) {
				// if the Wall is a CrackedWall and the Stone is Tnt then the move is valid
				if (assets.getStones()[destination.getXPos()][destination.getYPos()] instanceof Tnt && 
				    assets.getMap()[nextDest.getXPos()][nextDest.getYPos()] instanceof CrackedWall) {
					return true;
				}
				// otherwise it is invalid
				return false;
			} 
			// if there is another stone behind the stone then the move is invalid
			else if (assets.getStones()[nextDest.getXPos()][nextDest.getYPos()] != null) {
				return false;
			}
		}
		return true;
	}
	
	public void push(Properties properties, Stone stone, Direction direction, Assets assets) {
		stone.getPos().setDir(direction);
		stone.move(properties, assets);
		// if the Stone is Tnt and it is being pushed into a CrackedWall it detonates
		if (stone instanceof Tnt && 
		    assets.getMap()[stone.getPos().getXPos()][stone.getPos().getYPos()] instanceof CrackedWall) {
			((Tnt) stone).detonate(assets);
		}
		
		// if the stone is an ice block set sliding to true
		if (stone instanceof Ice) {
			((Ice) stone).makeSlide();
		}
	}
}