import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
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
	
	public void update(Input input, int delta) {
	}
	
	public void render(Graphics g, float xOffset, float yOffset) {
		image.draw(position.getXPos() * App.TILE_SIZE + xOffset, position.getYPos() * App.TILE_SIZE + yOffset);
	}
}