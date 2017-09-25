
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
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public Direction getDirection() {
		return direction;
	}
}