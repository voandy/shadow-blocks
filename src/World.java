import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import java.util.ArrayList;

public class World {	
	private ArrayList<Sprite> sprites;
	private Sprite[][] map;
	WorldDimensions dimensions;
	
	public World() throws SlickException {
		dimensions = Loader.loadDimensions("res/levels/0.lvl");
		sprites = Loader.loadSprites("res/levels/0.lvl");
		map = Loader.loadMap(sprites, dimensions.getLevelHeight(), dimensions.getLevelWidth());
		
	}
	
	public void update(Input input, int delta) {
	}
	
	public void render(Graphics g) {
		
		//renders the map
		for (int i = 0; i < dimensions.getLevelHeight(); i++) {
			for (int j = 0; j < dimensions.getLevelWidth(); j++) {
				if (map[i][j] != null) {
					map[i][j].render(g, dimensions.getXOffset(), dimensions.getYOffset());
				}
			}
		}
	}
	
}