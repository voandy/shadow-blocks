public class Mage extends Npc {
  private int distX;
  private int distY;
  private int signX;
  
  private final int POSITIVE = 1;
  private final int NEGATIVE = -1;
  
	public Mage(Position position) {
		super("res/mage.png", null , position);
		distX = 0;
		distY = 0;
		signX = POSITIVE;
	}
	
	public boolean move(Properties properties, Assets assets) {
	  distX = Math.abs(properties.getPlayerPos().getXPos() - getPos().getXPos());
	  distY = Math.abs(properties.getPlayerPos().getYPos() - getPos().getYPos());
	  
	  // moves toward the player on the X and Y axis whichever is greater
	  if (distX > distY) {
	    if (getPos().getXPos() > properties.getPlayerPos().getXPos()) {
	      getPos().setDir(Direction.DIR_LEFT);
	    } else {
	      getPos().setDir(Direction.DIR_RIGHT);
	    }
	  } else {
	    if (getPos().getYPos() > properties.getPlayerPos().getYPos()) {
	      getPos().setDir(Direction.DIR_UP);
	    } else {
	      getPos().setDir(Direction.DIR_DOWN);
	    }
	  }
	  
	  if (isValidMove(getPos().nextPos(), assets)) {
	    getPos().setPos(getPos().nextPos());
	    return true;
	  }
	  
	  return false;
	}
	
  // Override isValidMove as the Mage cannot push blocks
  public boolean isValidMove(Position destination, Assets assets) {
    if (assets.getMap()[destination.getXPos()][destination.getYPos()] instanceof Wall ||
        assets.getStones()[destination.getXPos()][destination.getYPos()] instanceof Stone) {
      return false;
    }
    return true;
  }
}
