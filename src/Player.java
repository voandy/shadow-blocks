import java.util.ArrayList;
import org.newdawn.slick.Input;

public class Player extends Unit{
	public Player(Position position) {
		super("res/player_left.png", position);
	}
	
	@Override
	public void update(Input input, int delta, LevelProperties properties, Sprite[][] map, Sprite[][] stones, 
			ArrayList<Unit> units) {
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			getPos().setDir(Direction.DIR_LEFT);
			move(properties, map, stones, units);
		}
		else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			getPos().setDir(Direction.DIR_RIGHT);
			move(properties, map, stones, units);
		}
		else if (input.isKeyPressed(Input.KEY_UP)) {
			getPos().setDir(Direction.DIR_UP);
			move(properties, map, stones, units);
		}
		else if (input.isKeyPressed(Input.KEY_DOWN)) {
			getPos().setDir(Direction.DIR_DOWN);
			move(properties, map, stones, units);
		}
	}
}