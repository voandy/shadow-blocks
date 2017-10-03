package sprites.units;

import org.newdawn.slick.Input;

import game.Assets;
import game.Properties;
import sprites.Direction;
import sprites.Position;

public class Player extends Unit{
  // used to freeze player while message is being displayed
  private boolean frozen;
  
	public Player(Position position) {
		super("res/player_left.png", "res/step.wav" , position);
		frozen = false;
	}
	
	 public Player(String image_src, String sound_src, Position position) {
	    super(image_src, sound_src, position);
	  }
	
	//@Override
	public void update(Input input, int delta, Properties properties, Assets assets) {
	  if (!frozen) {
	    if (input.isKeyPressed(Input.KEY_LEFT)) {
	      getPos().setDir(Direction.DIR_LEFT);
	      playerMove(properties, assets);
	    } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
	      getPos().setDir(Direction.DIR_RIGHT);
	      playerMove(properties, assets);
	    } else if (input.isKeyPressed(Input.KEY_UP)) {
	      getPos().setDir(Direction.DIR_UP);
	      playerMove(properties, assets);
	    } else if (input.isKeyPressed(Input.KEY_DOWN)) {
	      getPos().setDir(Direction.DIR_DOWN);
	      playerMove(properties, assets);
	    }
	  }
	}
	
	// moves the player, makes a sound and updates history
	public void playerMove(Properties properties, Assets assets) {
	  assets.getHistory().addStep(getPos(), properties, assets);
	  
    move(properties, assets);
    moveNpcs(properties, assets);
		properties.incrementMoves();
	}
	
	// rogues and mages move with the player
	public void moveNpcs(Properties properties, Assets assets) {
	  for(Unit unit : assets.getUnits()) {
	    if (unit instanceof Rogue || unit instanceof Mage) {
	      // this conditional prevents the player from switching positions with the Npc by walking into it
	      if (!getPos().equals(unit.getPos())) {
	        unit.move(properties, assets);
	      }
	    }
	  }
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
}