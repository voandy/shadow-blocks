import java.util.ArrayList;

public class LevelProperties {
	private int levelWidth;
	private int levelHeight;
	private float xOffset;
	private float yOffset;
	
	private int numTargets;
	private int targetsToggled;
	
	public LevelProperties(ArrayList<Sprite> sprites, int levelWidth, int levelHeight) {
		this.levelWidth = levelWidth;
		this.levelHeight = levelHeight;
		xOffset = (App.SCREEN_WIDTH - (levelWidth * App.TILE_SIZE)) / 2;
		yOffset = (App.SCREEN_HEIGHT - (levelHeight * App.TILE_SIZE)) / 2;
		
		numTargets = countTargets(sprites);
		targetsToggled = 0;
	}
	
	private int countTargets(ArrayList<Sprite> sprites) {
		int numTargets = 0;
		for (Sprite sprite : sprites) {
			if (sprite instanceof Target) {
				numTargets++;
			}
		}
		return numTargets;
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
	
	public boolean isCompleted() {
		return targetsToggled == numTargets;
	}
	public void toggleTarget() {
		targetsToggled++;
	}
	public void untoggleTarget() {
		targetsToggled--;
	}
}

