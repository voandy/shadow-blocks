package game.assets.sprites.map;

import org.newdawn.slick.Input;

import game.Properties;
import game.assets.Assets;
import game.assets.sprites.Position;

public class Switch extends Floor{
  public Switch(Position position) {
    super("res/switch.png", "res/ice.wav", position);
  }

  public void update(Input input, int delta, Properties properties, Assets assets) {
    if (assets.getDoor() != null) {
      // the player can activate the switch too
      if (assets.getBlocks()[getPos().getXPos()][getPos().getYPos()] != null ||
          getPos().equals(assets.getPlayerPos())) {

        assets.getDoor().open();
      } else {
        assets.getDoor().close();
      }
    }
  }
}
