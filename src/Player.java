import java.util.ArrayList;
import org.newdawn.slick.Input;

public class Player extends Unit{
	public Player(Position position) {
		super("res/player_left.png", "res/step.wav" , position);
	}
	
	//@Override
	public void update(Input input, int delta, LevelProperties properties, Assets assets) {
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			getPos().setDir(Direction.DIR_LEFT);
			playerMove(properties, assets);
		} else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			getPos().setDir(Direction.DIR_RIGHT);
      playerMove(properties, assets);

		} else if (input.isKeyPressed(Input.KEY_UP)) {
			getPos().setDir(Direction.DIR_UP);
      playerMove(properties, assets);

		} else if (input.isKeyPressed(Input.KEY_DOWN)) {
			getPos().setDir(Direction.DIR_DOWN);
      playerMove(properties, assets);
		}
	}
	
	// moves the player, makes a sound and updates history
	private void playerMove(LevelProperties properties, Assets assets) {
		move(properties, assets);
		makeSound();
		properties.incrementMoves();
	}
}