import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class Level {
	private ArrayList<Sprite> sprites;
	LevelDimensions dimensions;
	private Sprite[][] map;
	private Sprite[][] actors;
	
	public Level(String levelName) {
		sprites = Loader.loadSprites(levelName);
		dimensions = Loader.loadDimensions(levelName);
		map = populateLevel(sprites, dimensions.getLevelHeight(), dimensions.getLevelWidth(), MapItem.class);
		actors = populateLevel(sprites, dimensions.getLevelHeight(), dimensions.getLevelWidth(), Actor.class);
	}
	
	// returns a 2d array of sprites which are an instance of "type"
	// note that the returned array only references the sprites and does not copy them
	private Sprite[][] populateLevel(ArrayList<Sprite> sprites, int levelWidth, int levelHeight, Class<?> type) {
		Sprite[][] entities = new Sprite[levelWidth][levelHeight];
		
		for (Sprite sprite : sprites) {
			if (type.isInstance(sprite)) {
				entities[sprite.getPos().getXPos()][sprite.getPos().getYPos()] = sprite;
			}
		}
		
		return entities;
	}
	
	private void renderSpriteArray(Graphics g, Sprite[][] spriteArray, int levelWidth, int levelHeight, 
			float xOffset, float yOffset) {
		for (int i = 0; i < levelHeight; i++) {
			for (int j = 0; j < levelWidth; j++) {
				if (spriteArray[i][j] != null) {
					spriteArray[i][j].render(g, xOffset, yOffset);
				}
			}
		}
	}
	
	public void render(Graphics g) {
		// renders map items
		renderSpriteArray(g, map, dimensions.getLevelHeight(), dimensions.getLevelWidth(), dimensions.getXOffset(),
				dimensions.getYOffset());
		// renders actors
		renderSpriteArray(g, actors, dimensions.getLevelHeight(), dimensions.getLevelWidth(), dimensions.getXOffset(),
				dimensions.getYOffset());
	}
}
