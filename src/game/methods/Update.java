package game.methods;

import org.newdawn.slick.Input;

import game.Properties;
import game.assets.Assets;
import game.assets.EndScreen;
import game.assets.sprites.units.Npc;
import game.assets.sprites.units.Unit;

public class Update {
  /** updates all the units in the ArrayList<Unit> in assets */
  public static void updateUnits(Input input, int delta, Assets assets, Properties properties, EndScreen endScreen) {
    for (Unit unit : assets.getUnits()) {
      unit.update(input, delta, properties, assets);
      // if the player is in the same position as an Npc the player dies
      if (unit instanceof Npc && unit.getPos().equals(assets.getPlayerPos())) {
        endScreen.gameOver(assets);
      }
    }
  }
  
  /** updates both the MapItem[][] and Block[][] array in assets */
  public static void updateArrays(Input input, int delta, Assets assets, Properties properties) {
    for (int i = 0; i < properties.getLevelWidth(); i++) {
      for (int j = 0; j < properties.getLevelHeight(); j++) {
        if (assets.getBlocks()[i][j] != null) {
          assets.getBlocks()[i][j].update(input, delta, properties, assets);
        }
        if (assets.getMap()[i][j] != null) {
          assets.getMap()[i][j].update(input, delta, properties, assets);
        }
      }
    }
  }

}
