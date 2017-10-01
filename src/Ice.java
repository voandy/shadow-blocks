import org.newdawn.slick.Input;

public class Ice extends Stone {
	private static final int MOVE_DELAY = 250;
	private boolean sliding;
	private int timeSinceMove;
	private Position nextPos;
	
	public Ice(Position position) {
		super("res/ice.png", "res/ice.wav", position);
		sliding = false;
		nextPos = null;
	}
	
	// if the ice block is in a sliding state it will continue to move unit it encounters a wall or block
	public void update(Input input, int delta, Properties properties, Assets assets) {
		if (sliding) {
			
			timeSinceMove += delta;
			if (timeSinceMove > MOVE_DELAY) {
				
				if (isValidMove(nextPos, assets)) {
					move(properties, assets);
					nextPos = getPos().nextPos();
					timeSinceMove = 0;
					
				} else {
					sliding = false;
				}
			}
		}
	}
	
	public void makeSlide() {
		sliding = true;
		nextPos = getPos().nextPos();
	}
	
	// returns false if the destination contains a wall or block and true otherwise
	public boolean isValidMove(Position destination, Assets assets) {
		if (!super.isValidMove(destination, assets)) {
			return false;
		}
		
		// checks if destination contains a stone.
		if (assets.getStones()[destination.getXPos()][destination.getYPos()] != null) {
			return false;
		}
		
		return true;
	}
}
