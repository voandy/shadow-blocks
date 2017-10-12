package game.assets.sprites.units;

import game.Properties;
import game.assets.Assets;
import game.assets.sprites.Direction;
import game.assets.sprites.Position;
import game.assets.sprites.blocks.Block;

public class Mage extends Npc {
  private int distX;
  private int distY;

  public Mage(Position position) {
    super("res/mage.png", null , position);
    distX = 0;
    distY = 0;
  }

  public Mage(String image_src, String sound_src, Position position) {
    super(image_src, sound_src, position);
  }

  /** moves toward the player on the X and Y axis whichever is greater */
  public boolean move(Properties properties, Assets assets) {
    if (!assets.isPlayerDead()) {
      distX = Math.abs(assets.getPlayerPos().getXPos() - getPos().getXPos());
      distY = Math.abs(assets.getPlayerPos().getYPos() - getPos().getYPos());

      // sets the mages directions based on distance to the player
      if (distX > distY) {
        if (getPos().getXPos() > assets.getPlayerPos().getXPos()) {
          getPos().setDir(Direction.DIR_LEFT);
        } else {
          getPos().setDir(Direction.DIR_RIGHT);
        }
      } else {
        if (getPos().getYPos() > assets.getPlayerPos().getYPos()) {
          getPos().setDir(Direction.DIR_UP);
        } else {
          getPos().setDir(Direction.DIR_DOWN);
        }
      }

      // moves the mage
      if (!isFrozen() && isValidMove(getPos().nextPos(), assets)) {
        getPos().setPos(getPos().nextPos());
        return true;
      }
    }
    return false;
  }

  /** Override isValidMove as the Mage cannot push blocks and cannot move into other units
   * this prevents Mages and Shadows from merging into one unit
   */
  public boolean isValidMove(Position destination, Assets assets) {
    if (assets.getMap()[destination.getXPos()][destination.getYPos()].isBlocked() ||
        assets.getBlocks()[destination.getXPos()][destination.getYPos()] instanceof Block) {
      return false;
    }
    for (Unit unit : assets.getUnits()) {
      if (!(unit instanceof Player) && destination.equals(unit.getPos())) {
        return false;
      }
    }
    return true;
  }
}
