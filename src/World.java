import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import java.util.ArrayList;

public class World {	
	private ArrayList<Sprite> sprites;
	private ArrayList<Sprite> map;
	WorldDimensions dimensions;
	
	private Sound sonicBoom = null;
	
	public World() throws SlickException {
		dimensions = Loader.loadDimensions("res/levels/0.lvl");
		sprites = Loader.loadSprites("res/levels/0.lvl");
		map = Loader.loadMap(sprites);
		
		sonicBoom = new Sound("res/guile/sonic_boom.ogg");
		sonicBoom.play();
		
	}
	
	public void update(Input input, int delta) {
	}
	
	public void render(Graphics g) {
		for (Sprite sprite : map) {
			if (sprite != null) {
				sprite.render(g, dimensions.getXOffset(), dimensions.getYOffset());
			}
		}
	}
}