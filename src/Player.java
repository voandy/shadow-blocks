import org.newdawn.slick.Input;

public class Player extends Unit{
	public Player(Position position) {
		super("res/player_left.png", position);
	}
	
	@Override
	public void update(Input input, int delta) {
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			getPos().setDir(Direction.DIR_LEFT);
			move();
		}
		else if (input.isKeyPressed(Input.KEY_RIGHT)) {
		}
		else if (input.isKeyPressed(Input.KEY_UP)) {
		}
		else if (input.isKeyPressed(Input.KEY_DOWN)) {
		}
	}
}
