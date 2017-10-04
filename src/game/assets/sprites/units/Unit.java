package game.assets.sprites.units;

import org.newdawn.slick.Graphics;

import game.App;
import game.Properties;
import game.assets.Assets;
import game.assets.sprites.Movable;
import game.assets.sprites.Position;
import game.assets.sprites.Sprite;
import game.assets.sprites.blocks.Block;
import game.assets.sprites.blocks.Ice;

public abstract class Unit extends Sprite implements Movable{
  // used to freeze unit while message is being displayed
  private boolean frozen;

	public Unit(String image_src, String sound_src, Position position) {
		super(image_src, sound_src, position);
    frozen = false;
	}
	
	// moves the Unit one grid length and pushes a stone if one is present
	public boolean move(Properties properties, Assets assets) {
		Position nextPos = getPos().nextPos();
		if (!frozen && isValidMove(nextPos, assets)) {
		  
		  Block block = assets.getBlocks()[nextPos.getXPos()][nextPos.getYPos()];
			if (block != null) {
		    // if there is a stone in nextPos we try to push it
			  block.getPos().setDir(getPos().getDir());
			  if (block.move(properties, assets)) {
		       // the player doesn't move when pushing Ice, this makes for a smoother animation        
	        if (!(block instanceof Ice)) {
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
	
  public void freeze() {
    frozen = true;
  }
  public void unFreeze() {
    frozen = false;
  }
  public boolean isFrozen() {
    return frozen;
  }
  
  public void render(Graphics g, float xOffset, float yOffset) {
    super.render(g, xOffset, yOffset);
  }
}