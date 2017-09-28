import java.util.ArrayList;

public class LevelProperties {
	private String filename;
	
	private int levelWidth;
	private int levelHeight;
	private float xOffset;
	private float yOffset;
	
	private int numTargets;
	private int targetsToggled;
	private int noMoves;
	
	private boolean restartStatus;
	
	public LevelProperties(String filename, ArrayList<Sprite> sprites, int levelWidth, int levelHeight) {
		this.filename = filename;
		
		this.levelWidth = levelWidth;
		this.levelHeight = levelHeight;
		xOffset = (App.SCREEN_WIDTH - (levelWidth * App.TILE_SIZE)) / 2;
		yOffset = (App.SCREEN_HEIGHT - (levelHeight * App.TILE_SIZE)) / 2;
		
		numTargets = countTargets(sprites);
		targetsToggled = 0;
		noMoves = 0;
		
		restartStatus = false;
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
	
	public boolean isCompleted() {
		// special case for 5.lvl which has no targets
		if (numTargets == 0) {
			return false;
		}
		
		return targetsToggled == numTargets;
	}
	public void toggleTarget() {
		targetsToggled++;
	}
	public void untoggleTarget() {
		targetsToggled--;
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
	
	public boolean getRestartStatus() {
		return restartStatus;
	}
}

