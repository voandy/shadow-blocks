import java.util.ArrayList;
import org.newdawn.slick.Input;

public abstract class Actor extends Sprite{
	// actors are dynamic sprites in that they can move
	public Actor(String image_src, Position position) {
		super(image_src, position);
	}
	
	// moves the director one grid length in the direction which it is currently facing
	public boolean move(Sprite[][] map, Sprite[][] stones, ArrayList<Unit> units) {
		Position position = getPos();
		switch (position.getDir()) {
		case DIR_UP:
			if (isValidMove(position.getXPos(), position.getYPos(), Direction.DIR_UP, map, stones, units)) {
				position.setYPos(position.getYPos() - 1);
				return true;
			} else {
				return false;
			}
		case DIR_DOWN:
			if (isValidMove(position.getXPos(), position.getYPos(), Direction.DIR_DOWN, map, stones, units)) {
				position.setYPos(position.getYPos() + 1);
				return true;
			} else {
				return false;
			}
		case DIR_LEFT:
			if (isValidMove(position.getXPos(), position.getYPos(), Direction.DIR_LEFT, map, stones, units)) {
				position.setXPos(position.getXPos() - 1);
				return true;
			} else {
				return false;
			}
		case DIR_RIGHT:
			if (isValidMove(position.getXPos(), position.getYPos(), Direction.DIR_RIGHT, map, stones, units)) {
				position.setXPos(position.getXPos() + 1);
				return true;
			} else {
				return false;
			}
		default:
			return false;
		}
	}
	
	public boolean isValidMove(int xPos, int yPos, Direction direction,
			Sprite[][] map, Sprite[][] stones, ArrayList<Unit> units) {
		switch (direction) {
		case DIR_UP:
			if (map[xPos][yPos - 1] instanceof Wall) {
				return false;
			}
		case DIR_DOWN:
			if (map[xPos][yPos + 1] instanceof Wall) {
				return false;
			}
		case DIR_LEFT:
			if (map[xPos - 1][yPos] instanceof Wall) {
				return false;
			}
		case DIR_RIGHT:
			if (map[xPos + 1][yPos] instanceof Wall) {
				return false;
			}
		default:
			return true;
		}
	}
	
	public void update(Input input, int delta, Sprite[][] map, Sprite[][] stones, ArrayList<Unit> units) {
	}
	
}