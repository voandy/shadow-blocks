package game.assets.sprites.map;

import game.assets.sprites.Position;

public class Wall extends MapItem {
  public Wall(Position position) {
    super("res/wall.png", null, position);
    setBlocked(true);
  }

  /** this second constructor allows for subclasses to be instantiated with a different image */
  public Wall(String image_src, String sound_src, Position position) {
    super(image_src, sound_src, position);
    setBlocked(true);
  }
}