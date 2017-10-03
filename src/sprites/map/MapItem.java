package sprites.map;

import sprites.Position;
import sprites.Sprite;

public abstract class MapItem extends Sprite{
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