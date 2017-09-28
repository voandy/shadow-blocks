import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;


public abstract class Sprite {	
	private Image image = null;
	private Sound sound = null;
	private Position position;
	
	public Sprite(String image_src, String sound_src, Position position) {
		try {
			if (image_src != null) {
				image = new Image(image_src);
			}
			if (sound_src != null) {
				sound = new Sound(sound_src);
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.position = position;
	}
	
	public void makeSound() {
		if (sound != null) {
			sound.play();
		}
	}
	
	public void update(Input input, int delta, LevelProperties properties, Sprite[][] map, Sprite[][] stones, 
			ArrayList<Unit> units, ArrayList<Effect> effects) {
	}
	
	public void render(Graphics g, float xOffset, float yOffset) {
		image.draw(position.getXPos() * App.TILE_SIZE + xOffset, position.getYPos() * App.TILE_SIZE + yOffset);
	}

	public Position getPos() {
		return position;
	}

}