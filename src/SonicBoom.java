import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class SonicBoom extends Effect {
  private final static String ANIMATION_SRC = "res/giles/sonic_boom.png";
  private final static String SOUND_SRC = "res/giles/sonic_boom.wav";
  
  private final static int WIDTH = 32;
  private final static int HEIGHT = 32;
  private final static int DURATION = 40;
  
  private int timeSinceMove;
  private final int MOVE_DELAY = 150;
  private Position nextPos;
  
  public SonicBoom(Position position) {
    super(ANIMATION_SRC, SOUND_SRC, WIDTH, HEIGHT, DURATION, 0);
    
    getAnimation().setLooping(true);
    
    setPos(position);
    nextPos = getPos().nextPos();
  }

  public void update(Input input, int delta, Properties properties, Assets assets) {
    timeSinceMove += delta;
    if (timeSinceMove > MOVE_DELAY) {
      if (move(properties, assets)) {  
        timeSinceMove = 0;
      } else {
        setFinished(true);
      }
    }
  }
  
  public boolean move(Properties properties, Assets assets) {
    if (isValidMove(nextPos, assets)) {
      getPos().setPos(getPos().nextPos());
      nextPos = getPos().nextPos();
      return true;
    }
    return false;
  }
  
  public void render(Graphics g, float xOffset, float yOffset) {
    getAnimation().draw(getPos().getXPos() * App.TILE_SIZE + xOffset, getPos().getYPos() * App.TILE_SIZE + yOffset);
  }
  
  // returns false if the destination contains a wall or block and true otherwise
  public boolean isValidMove(Position destination, Assets assets) {
    // checks if destination contains a stone.
    if (assets.getStones()[destination.getXPos()][destination.getYPos()] != null ||
        assets.getMap()[destination.getXPos()][destination.getYPos()].isBlocked()) {
      return false;
    }
    
    return true;
  }
}
