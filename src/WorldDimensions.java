public class WorldDimensions {
	private int levelWidth;
	private int levelHeight;
	private float xOffset;
	private float yOffset;

	public WorldDimensions(int levelWidth, int levelHeight) {
		this.levelWidth = levelWidth;
		this.levelHeight = levelHeight;
		xOffset = (App.SCREEN_WIDTH - (levelWidth * App.TILE_SIZE)) / 2;
		yOffset = (App.SCREEN_HEIGHT - (levelHeight * App.TILE_SIZE)) / 2;
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
}