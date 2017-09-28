import java.util.ArrayList;
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
	public void update(Input input, int delta, LevelProperties properties, Sprite[][] map, Sprite[][] stones, 
			ArrayList<Unit> units, ArrayList<Effect> effects) {
		if (sliding) {
			
			timeSinceMove += delta;
			if (timeSinceMove > MOVE_DELAY) {
				
				if (isValidMove(nextPos, map, stones, units)) {
					move(properties, map, stones, units);
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
	public boolean isValidMove(Position destination, Sprite[][] map, Sprite[][] stones, ArrayList<Unit> units) {
		if (!super.isValidMove(destination, map, stones, units)) {
			return false;
		}
		
		// checks if destination contains a stone.
		if (stones[destination.getXPos()][destination.getYPos()] != null) {
			return false;
		}
		
		return true;
	}
}