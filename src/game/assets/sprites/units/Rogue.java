package game.assets.sprites.units;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import game.Properties;
import game.assets.Assets;
import game.assets.sprites.Direction;
import game.assets.sprites.Position;

public class Rogue extends Npc{
  Image rogue_left;
  Image rogue_right;

  public Rogue(Position position) {
    super("res/rogue_left.png", null , position);
    getPos().setDir(Direction.DIR_LEFT);

    try {
      rogue_left = new Image("res/rogue_left.png");
      rogue_right = new Image("res/rogue_right.png");
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }

  public boolean move(Properties properties, Assets assets) {
    if (super.move(properties, assets)) {
      return true;
    } else {
      // if the Rogue can't move anymore turn her around
      if (getPos().getDir() == Direction.DIR_LEFT) {
        getPos().setDir(Direction.DIR_RIGHT);
        setImage(rogue_right);
      } else {
        getPos().setDir(Direction.DIR_LEFT);
        setImage(rogue_left);
      }
      // the rogue doesn't move after changing directions
      return false;
    }
  }
}
