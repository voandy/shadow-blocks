public class Rogue extends Npc{
	public Rogue(Position position) {
		super("res/rogue.png", null , position);
		getPos().setDir(Direction.DIR_LEFT);
	}
	
	public boolean move(Properties properties, Assets assets) {
	  // if the Rogue can't move anymore turn her around
	  if (!isValidMove(getPos().nextPos(), assets)) {
	    if (getPos().getDir() == Direction.DIR_LEFT) {
	      getPos().setDir(Direction.DIR_RIGHT);
	    } else {
	      getPos().setDir(Direction.DIR_LEFT);
	    }
	    // the rogue doesn't move after changing directions
	    return false;
	  }
	  return super.move(properties, assets);
	}
}
