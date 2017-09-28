import java.util.ArrayList;

public abstract class Unit extends Actor{

	public Unit(String image_src, String sound_src, Position position) {
		super(image_src, sound_src, position);
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
		
		// checks if the destination contains a Stone
		if (stones[destination.getXPos()][destination.getYPos()] != null) {
			// gets the grid position behind the stone
			Position nextDest = destination.nextPos();
			
			// checks if this position contains a Wall
			if (map[nextDest.getXPos()][nextDest.getYPos()] instanceof Wall) {
				
				// if the Wall is a CrackedWall and the Stone is Tne then the move is valid
				if (stones[destination.getXPos()][destination.getYPos()] instanceof Tnt && 
						map[nextDest.getXPos()][nextDest.getYPos()] instanceof CrackedWall) {
					return true;
				}
				
				// otherwise it is invalid
				return false;
			} 
			// if there is another stone behind the stone then the move is invalid
			else if (stones[nextDest.getXPos()][nextDest.getYPos()] != null) {
				return false;
			}
		}
		return true;
	}
	
	public void push(LevelProperties properties, Stone stone, Direction direction, Sprite[][] map, Sprite[][] stones, 
			ArrayList<Unit> units) {
		stone.getPos().setDir(direction);
		stone.move(properties, map, stones, units);
		
		// if the stone is an ice block set sliding to true
		if (stone instanceof Ice) {
			((Ice) stone).makeSlide();
		}
	}
}