package sprites.map;

import org.newdawn.slick.Input;

import game.Assets;
import game.Properties;
import sprites.Position;

public class Switch extends Floor{
	public Switch(Position position) {
		super("res/switch.png", "res/ice.wav", position);
	}
	
  public void update(Input input, int delta, Properties properties, Assets assets) {
    if (assets.getDoor() != null) {
      if (assets.getStones()[getPos().getXPos()][getPos().getYPos()] != null) {
        
        assets.getDoor().open();
      } else {
        assets.getDoor().close();
      }
    }
  }
}
