package game.assets.sprites.units;

import game.Properties;
import game.assets.Assets;
import game.assets.sprites.Direction;
import game.assets.sprites.Position;

public class Rogue extends Npc{
	public Rogue(Position position) {
		super("res/rogue.png", null , position);
		getPos().setDir(Direction.DIR_LEFT);
	}
	
	public boolean move(Properties properties, Assets assets) {
	  if (super.move(properties, assets)) {
	    return true;
	  } else {
	     // if the Rogue can't move anymore turn her around
      if (getPos().getDir() == Direction.DIR_LEFT) {
        getPos().setDir(Direction.DIR_RIGHT);
      } else {
        getPos().setDir(Direction.DIR_LEFT);
      }
      // the rogue doesn't move after changing directions
      return false;
	  }
	}
}
