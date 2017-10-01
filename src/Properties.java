import java.util.ArrayList;

public class Properties {
	private String filename;

  private int levelWidth;
	private int levelHeight;
	private float xOffset;
	private float yOffset;
	
	private int noMoves;
	
	public Properties(String filename, ArrayList<Sprite> sprites, int levelWidth, int levelHeight) {
		this.filename = filename;
		
		this.levelWidth = levelWidth;
		this.levelHeight = levelHeight;
		xOffset = (App.SCREEN_WIDTH - (levelWidth * App.TILE_SIZE)) / 2;
		yOffset = (App.SCREEN_HEIGHT - (levelHeight * App.TILE_SIZE)) / 2;
		
		noMoves = 0;
	}

	public String getFilename() {
		return filename;
	}
	public int getLevelWidth() {
		return levelWidth;
	}
	public int getLevelHeight() {
		return levelHeight;
	}
	public float getXOffset() {
		return xOffset;
	}
	public float getYOffset() {
		return yOffset;
	}
	public int getNoMoves() {
		return noMoves;
	}
	public void incrementMoves() {
		noMoves++;
	}
	public void decrementsMoves() {
		noMoves--;
	}
}

