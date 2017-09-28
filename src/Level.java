import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Level {
	private ArrayList<Sprite> sprites;
	private LevelProperties properties;
	private Assets assets;
	
	public Level(String filename) {
		sprites = Loader.loadSprites(filename);
		properties = Loader.loadProperties(filename, sprites);
		assets = new Assets(filename, sprites, properties);		
	}
	
	public boolean isCompleted() {
		return properties.isCompleted();
	}
	
	public boolean getRestartStatus() {
		return properties.getRestartStatus();
	}
	
	private void renderSpriteArray(Graphics g, Sprite[][] spriteArray) {
		for (int i = 0; i < properties.getLevelWidth(); i++) {
			for (int j = 0; j < properties.getLevelHeight(); j++) {
			  if (spriteArray[i][j] != null) {
					spriteArray[i][j].render(g, properties.getXOffset(), properties.getYOffset());
				}
			}
		}
	}
	
	// used to render both units and effects
	public void renderArrayList(Graphics g, ArrayList<? extends Sprite> sprites) {
		if (sprites != null) {
			for (Sprite sprite : sprites) {
				sprite.render(g, properties.getXOffset(), properties.getYOffset());
			}
		}
	}
	
	public void update(Input input, int delta) {
		for (Sprite unit : assets.getUnits()) {
			unit.update(input, delta, properties, assets);
		}
		for (int i = 0; i < properties.getLevelWidth(); i++) {
			for (int j = 0; j < properties.getLevelHeight(); j++) {
				if (assets.getStones()[i][j] != null) {
				  assets.getStones()[i][j].update(input, delta, properties, assets);
				}
			}
		}
	}
	
	public void render(Graphics g) {
		// renders map items
		renderSpriteArray(g, assets.getMap());
		// renders actors
		renderSpriteArray(g, assets.getStones());
		// renders units
		renderArrayList(g, assets.getUnits());
		// renders effects
		renderArrayList(g, assets.getEffects());
		// shows number of moves made
		g.drawString("Moves: " + properties.getNoMoves(), 0, 0);
	}
}
