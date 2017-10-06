package game.assets.sprites.units;

import org.newdawn.slick.Input;

import game.Properties;
import game.assets.Assets;
import game.assets.sprites.Direction;
import game.assets.sprites.Position;

public class Player extends Unit{
	public Player(Position position) {
		super("res/player.png", "res/step.wav" , position);
	}
	
	 public Player(String image_src, String sound_src, Position position) {
	    super(image_src, sound_src, position);
	  }
	
	public void update(Input input, int delta, Properties properties, Assets assets) {
	  if (!isFrozen()) {
	  
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
	
	// Rogues and Mages move with the player
	public void moveNpcs(Properties properties, Assets assets) {
	  for(Unit unit : assets.getUnits()) {
	    // we use the getClass method to prevent the Shadow from moving with the player
	    if (unit instanceof Rogue || unit.getClass().equals(Mage.class)) {
	      // this conditional prevents the player from sneakily switching positions with the Npc by walking into it
	      if (!getPos().equals(unit.getPos())) {
	        unit.move(properties, assets);
	      }
	    }
	  }
	}
}