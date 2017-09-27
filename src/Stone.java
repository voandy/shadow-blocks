import java.util.ArrayList;

public class Stone extends Actor {
	public Stone(Position position) {
		super("res/stone.png", position);
	}
	
	public boolean move(Sprite[][] map, Sprite[][] stones, ArrayList<Unit> units) {
		Position nextPos = getPos().nextPos();

		// note that move validation is done in the push() method in the unit class so is unnecessary here
		stones[nextPos.getXPos()][nextPos.getYPos()] = this;
		stones[getPos().getXPos()][getPos().getYPos()] = null;
		getPos().setPos(nextPos);
		return true;
	}
}