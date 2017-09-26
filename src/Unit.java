import java.util.ArrayList;
import org.newdawn.slick.Input;

public abstract class Unit extends Actor{

	public Unit(String image_src, Position position) {
		super(image_src, position);
	}
	public void update(Input input, int delta, Sprite[][] map, Sprite[][] stones, ArrayList<Unit> units) {
	}
	
//	public boolean isValidMove(int xPos, int yPos, Direction direction,
//			Sprite[][] map, Sprite[][] stones, ArrayList<Unit> units) {
//		if (!super.isValidMove(xPos, yPos, direction, map, stones, units)) {
//			return false;
//		}
//		return true;
//	}
}