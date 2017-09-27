import java.util.ArrayList;
import org.newdawn.slick.Input;

public abstract class Unit extends Actor{

	public Unit(String image_src, Position position) {
		super(image_src, position);
	}
	
	public void update(Input input, int delta, LevelProperties properties, Sprite[][] map, Sprite[][] stones, 
			ArrayList<Unit> units) {
	}
	
	// moves the Unit one grid length and pushes a stone if one is present
	public boolean move(LevelProperties properties, Sprite[][] map, Sprite[][] stones, ArrayList<Unit> units) {
		Position nextPos = getPos().nextPos();
		if (isValidMove(nextPos, map, stones, units)) {
			// if there is a stone in nextPos we push it
			if (stones[nextPos.getXPos()][nextPos.getYPos()] != null) {
				getPos().setPos(nextPos);
				push(properties, (Stone) stones[nextPos.getXPos()][nextPos.getYPos()], getPos().getDir(), 
						map, stones, units);
				return true;
			} else {
				getPos().setPos(nextPos);
				return true;
			}
		}
		return false;
	}
	
	public boolean isValidMove(Position destination, Sprite[][] map, Sprite[][] stones, ArrayList<Unit> units) {
		if (!super.isValidMove(destination, map, stones, units)) {
			return false;
		}
		
		// checks if the destination contains a stone
		if (stones[destination.getXPos()][destination.getYPos()] != null) {
			// gets the grid position behind the stone and checks if it's a wall or another stone
			Position nextDest = destination.nextPos();
			if (map[nextDest.getXPos()][nextDest.getYPos()] instanceof Wall) {
				return false;
			} else if (stones[nextDest.getXPos()][nextDest.getYPos()] != null) {
				return false;
			}
		}
		return true;
	}
	
	public void push(LevelProperties properties, Stone stone, Direction direction, Sprite[][] map, Sprite[][] stones, 
			ArrayList<Unit> units) {
		stone.getPos().setDir(direction);
		stone.move(properties, map, stones, units);
	}
}