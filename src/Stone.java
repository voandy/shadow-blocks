import java.util.ArrayList;

public class Stone extends Actor {
	public Stone(Position position) {
		super("res/stone.png", "res/stone.wav", position);
	}
	
	public boolean move(LevelProperties properties, Sprite[][] map, Sprite[][] stones, ArrayList<Unit> units) {
		Position nextPos = getPos().nextPos();
		
		// we use this method to update how many targets have been toggled onlu when a stone is moved
		// previously I did it with the update() method but that was less efficient as the game had to update and count
		// the number of targets toggled for every single frame
		if (map[getPos().getXPos()][getPos().getYPos()] instanceof Target) {
			properties.untoggleTarget();
		}
		if (map[nextPos.getXPos()][nextPos.getYPos()] instanceof Target) {
			properties.toggleTarget();
		}

		// note that move validation is done in the push() method in the unit class so is unnecessary here
		stones[nextPos.getXPos()][nextPos.getYPos()] = this;
		stones[getPos().getXPos()][getPos().getYPos()] = null;
		getPos().setPos(nextPos);
		makeSound();
		
		return true;
	}
}