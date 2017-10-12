package game.assets.sprites.map;

import game.assets.sprites.Position;
import game.assets.sprites.Sprite;

public abstract class MapItem extends Sprite{
  // true in the case of Wall or closed Door
  private boolean blocked;

  public MapItem(String image_src, String sound_src, Position position) {
    super(image_src, sound_src, position);
    blocked = false;
  }

  public boolean isBlocked() {
    return blocked;
  }

  public void setBlocked(boolean blocked) {
    this.blocked = blocked;
  }
}