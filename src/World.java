import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import java.util.ArrayList;

public class World {	
	private ArrayList<Sprite> sprites;
	WorldDimensions dimensions;
	
	public World() {
		dimensions = Loader.loadDimensions("res/levels/0.lvl");
		sprites = Loader.loadSprites("res/levels/0.lvl");
	}
	
	public void update(Input input, int delta) {
	}
	
	public void render(Graphics g) {
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				sprite.render(g, dimensions.getXOffset(), dimensions.getYOffset());
			}
		}
	}
}