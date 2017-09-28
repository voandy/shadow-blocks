import java.util.ArrayList;
import org.newdawn.slick.Input;

public class Player extends Unit{
	public Player(Position position) {
		super("res/player_left.png", "res/step.wav" , position);
	}
	
	//@Override
	public void update(Input input, int delta, LevelProperties properties, Sprite[][] map, Sprite[][] stones, 
			ArrayList<Unit> units, ArrayList<Effect> effects) {
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			getPos().setDir(Direction.DIR_LEFT);
			playerMove(properties, map, stones, units, effects);
		} else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			getPos().setDir(Direction.DIR_RIGHT);
			playerMove(properties, map, stones, units, effects);

		} else if (input.isKeyPressed(Input.KEY_UP)) {
			getPos().setDir(Direction.DIR_UP);
			playerMove(properties, map, stones, units, effects);

		} else if (input.isKeyPressed(Input.KEY_DOWN)) {
			getPos().setDir(Direction.DIR_DOWN);
			playerMove(properties, map, stones, units, effects);
		}
	}
	
	// moves the player, makes a sound and updates history
	private void playerMove(LevelProperties properties, Sprite[][] map, Sprite[][] stones, 
			ArrayList<Unit> units, ArrayList<Effect> effects) {
		move(properties, map, stones, units, effects);
		makeSound();
		properties.incrementMoves();
	}
}