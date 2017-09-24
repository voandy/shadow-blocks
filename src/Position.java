
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
}