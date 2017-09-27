import java.util.ArrayList;
import org.newdawn.slick.Input;

public abstract class Actor extends Sprite{
	// actors are dynamic sprites in that they can move
	public Actor(String image_src, Position position) {
		super(image_src, position);
	}
	
	// moves the Actor one grid length in the direction in which it is currently facing
	public boolean move(Sprite[][] map, Sprite[][] stones, ArrayList<Unit> units) {
		Position nextPos = getPos().nextPos();
		if (isValidMove(nextPos, map, stones, units)) {
			getPos().setPos(nextPos);
			return true;
		}
		return false;
	}
	
	public boolean isValidMove(Position destination, Sprite[][] map, Sprite[][] stones, ArrayList<Unit> units) {
		if (map[destination.getXPos()][destination.getYPos()] instanceof Wall) {
			return false;
		}
		return true;
	}
	
	public void update(Input input, int delta, Sprite[][] map, Sprite[][] stones, ArrayList<Unit> units) {
	}
}