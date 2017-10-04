package game.assets.sprites.units;

import org.newdawn.slick.Input;

import game.Properties;
import game.assets.Assets;
import game.assets.sprites.Direction;
import game.assets.sprites.Position;
import game.assets.sprites.stones.Stone;

public class Skeleton extends Npc {
  private static final int MOVE_DELAY = 1000;
  private int timeSinceMove;
  private Position nextPos;
  
	public Skeleton(Position position) {
		super("res/skull.png", "res/skull.wav" , position);
		timeSinceMove = 0;
		getPos().setDir(Direction.DIR_UP);
		nextPos = getPos().nextPos();
	}
	
  public void update(Input input, int delta, Properties properties, Assets assets) {
    timeSinceMove += delta;
    if (timeSinceMove > MOVE_DELAY) {
      
      if (isValidMove(nextPos, assets)) {
        getPos().setPos(nextPos);
        nextPos = getPos().nextPos();
        makeSound();
        timeSinceMove = 0;
        
      } else {
    	// if is unable to move it switches directions
        if (getPos().getDir() == Direction.DIR_UP) {
          getPos().setDir(Direction.DIR_DOWN);
          nextPos = getPos().nextPos();
        } else {
          getPos().setDir(Direction.DIR_UP);
          nextPos = getPos().nextPos();
        }
        // this takes up a move
        timeSinceMove = 0;
      }
    }
  }
  
  // Override isValidMove as the Skeleton cannot push blocks
  public boolean isValidMove(Position destination, Assets assets) {
    if (assets.getMap()[destination.getXPos()][destination.getYPos()].isBlocked() ||
        assets.getStones()[destination.getXPos()][destination.getYPos()] instanceof Stone) {
      return false;
    }
    return true;
  }
	
}