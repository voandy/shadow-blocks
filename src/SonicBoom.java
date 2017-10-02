import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class SonicBoom extends Effect {
  private final static String ANIMATION_SRC = "res/giles/sonic_boom.png";
  private final static String SOUND_SRC = "res/giles/sonic_boom.wav";
  
  private final static int WIDTH = 32;
  private final static int HEIGHT = 32;
  private final static int DURATION = 40;
  
  private int timeSinceMove;
  private final static int MOVE_DELAY = 150;
  // Offset that changes with delta to allow animation to move smoothly
  private float xRenderOffset;
  private float yRenderOffset;
  private final static float SPEED = (float) App.TILE_SIZE / MOVE_DELAY;
  
  private Position nextPos;
  
  public SonicBoom(Position position) {
    super(ANIMATION_SRC, SOUND_SRC, WIDTH, HEIGHT, DURATION, 0);
     
    getAnimation().setLooping(true);
    
    setPos(position);
    nextPos = getPos().nextPos();
    
    xRenderOffset = 0;
    yRenderOffset = 0;
    resetRenderOffset();
  }

  public void update(Input input, int delta, Properties properties, Assets assets) {
    // kills any Npcs the SonicBoom encounters
    for (Unit unit : assets.getUnits()) {
      if (unit.getPos().equals(getPos()) && unit instanceof Npc) {
        assets.killUnit(unit);
        assets.getGameEffects().showPoof(getPos());
        setFinished(true);
      }
    }
    
    timeSinceMove += delta;
    
    switch(getPos().getDir()) {
    case DIR_LEFT:
      xRenderOffset = timeSinceMove * -SPEED;
      break;
    case DIR_RIGHT:
      xRenderOffset = timeSinceMove * SPEED;
      break;
    case DIR_UP:
      yRenderOffset = timeSinceMove * -SPEED;
      break;
    case DIR_DOWN:
      yRenderOffset = timeSinceMove * SPEED;
      break;
    case DIR_NONE:
      break;
    }
    
    if (timeSinceMove > MOVE_DELAY) {
      if (move(properties, assets)) {  
        timeSinceMove = 0;
        resetRenderOffset();
      } else {
        setFinished(true);
        assets.getGameEffects().showPop(getPos().nextPos());
      }
    }
  }
  
  // resets render offsets after each move
  private void resetRenderOffset() {
    switch(getPos().getDir()) {
    case DIR_LEFT:
      xRenderOffset = App.TILE_SIZE / 2;
      break;
    case DIR_RIGHT:
      xRenderOffset = -(App.TILE_SIZE / 2);
      break;
    case DIR_UP:
      yRenderOffset = App.TILE_SIZE / 2;
      break;
    case DIR_DOWN:
      yRenderOffset = -(App.TILE_SIZE / 2);
      break;
    case DIR_NONE:
      break;
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
    getAnimation().draw(getPos().getXPos() * App.TILE_SIZE + xOffset + xRenderOffset, 
                        getPos().getYPos() * App.TILE_SIZE + yOffset + yRenderOffset);
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
