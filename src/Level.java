import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Level {
	private ArrayList<Sprite> sprites;
	private LevelProperties properties;
	
	// map items and stone are represented by a 2d array as they will never occupy the same grid coordinates
	private Sprite[][] map;
	private Sprite[][] stones;
	// units are represented by and ArrayList as they may occupy the same grid coordinates
	private ArrayList<Unit> units;
	
	public Level(String levelName) {
		sprites = Loader.loadSprites(levelName);
		properties = Loader.loadProperties(levelName, sprites);
		
		map = Loader.populateLevel(sprites, properties.getLevelHeight(), properties.getLevelWidth(), MapItem.class);
		stones = Loader.populateLevel(sprites, properties.getLevelHeight(), properties.getLevelWidth(), Stone.class);
		units = Loader.getUnits(sprites);
	}
	
	public boolean isCompleted() {
		return properties.isCompleted();
	}
	
	private void renderSpriteArray(Graphics g, Sprite[][] spriteArray) {
		for (int i = 0; i < properties.getLevelHeight(); i++) {
			for (int j = 0; j < properties.getLevelWidth(); j++) {
				if (spriteArray[i][j] != null) {
					spriteArray[i][j].render(g, properties.getXOffset(), properties.getYOffset());
				}
			}
		}
	}
	
	public void renderUnits(Graphics g, ArrayList<Unit> units) {
		for (Sprite unit : units) {
			unit.render(g, properties.getXOffset(), properties.getYOffset());
		}
	}
	
	public void update(Input input, int delta) {
		for (Sprite unit : units) {
			unit.update(input, delta, properties, map, stones, units);
		}
	}
	
	public void render(Graphics g) {
		// renders map items
		renderSpriteArray(g, map);
		// renders actors
		renderSpriteArray(g, stones);
		// renders units
		renderUnits(g, units);
	}
}
