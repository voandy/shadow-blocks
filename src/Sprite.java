import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Sprite {	
	private Image image = null;
	private Position position;
	
	public Sprite(String image_src, Position position) {
		try {
			image = new Image(image_src);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.position = position;
	}
	
	public Position getPos() {
		return position;
	}
	
	public void update(Input input, int delta, LevelProperties properties, Sprite[][] map, Sprite[][] stones, 
			ArrayList<Unit> units) {
	}
	
	public void render(Graphics g, float xOffset, float yOffset) {
		image.draw(position.getXPos() * App.TILE_SIZE + xOffset, position.getYPos() * App.TILE_SIZE + yOffset);
	}
}