package game.assets.sprites.units;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import game.Properties;
import game.assets.Assets;
import game.assets.sprites.Position;

public class Shadow extends Mage{
  //moves like the mage but on a timer like the skeleton
  private static final int MOVE_DELAY = 1000;
  private int timeSinceMove;
  private static final int RESPAWN_TIME = 4000;
  private int timeSinceKilled;
  
  private boolean dead;
  
  private Position initialPos;
  
  public Shadow(Position position) {
    super("res/shadow.png", "res/shadow.wav", position);
    timeSinceMove = 0;
    dead = false;
    initialPos = new Position(getPos());
  }
  
  public void update(Input input, int delta, Properties properties, Assets assets) {
    if (!dead) {
      timeSinceMove += delta;
      if (timeSinceMove > MOVE_DELAY) {
        if (move(properties, assets)) {
          makeSound();
        }
        timeSinceMove = 0;
      }
    }
    
    if (dead) {
      timeSinceKilled += delta;
      if (timeSinceKilled > RESPAWN_TIME) {
        dead = false;
        getPos().setPos(new Position(initialPos));
        assets.getGameEffects().showSplash(initialPos);
        timeSinceKilled = 0;
      }
    }
  }
  
  public void kill() {
    dead = true;
    getPos().setPos(new Position());
    timeSinceMove = 0;
  }
  
  // shadow is only rendered if it is alive
  public void render(Graphics g, float xOffset, float yOffset) {
    if (!dead) {
      super.render(g, xOffset, yOffset);
    }
  }
}
