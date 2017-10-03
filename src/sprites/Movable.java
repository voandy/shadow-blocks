package sprites;

import game.Assets;
import game.Properties;

public interface Movable {
  public boolean move(Properties properties, Assets assets);
  public boolean isValidMove(Position destination, Assets assets);
}
