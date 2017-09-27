public class Position{
	// stores the x and y coordinates of a Sprite and the direction it's facing
	private int xPos;
	private int yPos;
	private Direction direction;
	
	public Position(int xPos, int yPos, Direction direction) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.direction = direction;
	}
	
	// if a Position is created without specifying a Direction it defaults to DIR_NONE
	public Position(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.direction = Direction.DIR_NONE;
	}
	
	// returns a Position adjacent to the current position (in the the direction this Position is facing)
	public Position nextPos() {
		int nextX = this.xPos;
		int nextY = this.yPos;
		
		switch (this.direction) {
		case DIR_LEFT:
			nextX--;
			break;
		case DIR_RIGHT:
			nextX++;
			break;
		case DIR_UP:
			nextY--;
			break;
		case DIR_DOWN:
			nextY++;
			break;
		case DIR_NONE:
			break;
		}
		
		return new Position(nextX, nextY, this.direction);
	}
	
	// copies another Position into this one
	public void setPos(Position another) {
		this.xPos = another.xPos;
		this.yPos = another.yPos;
		this.direction = another.direction;
	}
	
	public void setXPos(int xPos) {
		this.xPos = xPos;
	}
	public int getXPos() {
		return xPos;
	}
	
	public void setYPos(int yPos) {
		this.yPos = yPos;
	}
	public int getYPos() {
		return yPos;
	}
	
	public void setDir(Direction direction) {
		this.direction = direction;
	}
	public Direction getDir() {
		return direction;
	}
}