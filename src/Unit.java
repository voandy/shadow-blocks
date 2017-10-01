public abstract class Unit extends Actor{

	public Unit(String image_src, String sound_src, Position position) {
		super(image_src, sound_src, position);
	}
	
	// moves the Unit one grid length and pushes a stone if one is present
	public boolean move(Properties properties, Assets assets) {
		Position nextPos = getPos().nextPos();
		if (isValidMove(nextPos, assets)) {
			// if there is a stone in nextPos we push it
			if (assets.getStones()[nextPos.getXPos()][nextPos.getYPos()] != null) {
				getPos().setPos(nextPos);
				push(properties, (Stone) assets.getStones()[nextPos.getXPos()][nextPos.getYPos()], getPos().getDir(), assets);
				return true;
			} else {
				getPos().setPos(nextPos);
				return true;
			}
		}
		return false;
	}
	
	// returns true if the unit can move to the destination
	public boolean isValidMove(Position destination, Assets assets) {
		if (!super.isValidMove(destination, assets)) {
			return false;
		}
		
		// checks if the destination contains a Stone
		if (assets.getStones()[destination.getXPos()][destination.getYPos()] != null) {
			// gets the grid position behind the stone
			Position nextDest = destination.nextPos();
			if (assets.getMap()[nextDest.getXPos()][nextDest.getYPos()].isBlocked()) {
				// if the Wall is a CrackedWall and the Stone is Tnt then the move is valid
				if (assets.getStones()[destination.getXPos()][destination.getYPos()] instanceof Tnt && 
				    assets.getMap()[nextDest.getXPos()][nextDest.getYPos()] instanceof CrackedWall) {
					return true;
				}
				// otherwise it is invalid
				return false;
			} 
			// if there is another stone behind the stone then the move is invalid
			else if (assets.getStones()[nextDest.getXPos()][nextDest.getYPos()] != null) {
				return false;
			}
		}
		return true;
	}
	
	public void push(Properties properties, Stone stone, Direction direction, Assets assets) {
		stone.getPos().setDir(direction);
		stone.move(properties, assets);
		// if the Stone is Tnt and it is being pushed into a CrackedWall it detonates
		if (stone instanceof Tnt && 
		    assets.getMap()[stone.getPos().getXPos()][stone.getPos().getYPos()] instanceof CrackedWall) {
			((Tnt) stone).detonate(assets);
		}
		
		// if the stone is an ice block set sliding to true
		if (stone instanceof Ice) {
			((Ice) stone).makeSlide();
		}
	}
}