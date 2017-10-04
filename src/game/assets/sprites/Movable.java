package game.assets.sprites;

import game.Properties;
import game.assets.Assets;

public interface Movable {
  public boolean move(Properties properties, Assets assets);
  public boolean isValidMove(Position destination, Assets assets);
}
