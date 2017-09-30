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
	
	public void setImage(Image image) {
	  this.image = image;
	}
	public Image getImage() {
	  return image;
	}
	
	public Sound getSound() {
    return sound;
  }
  public void setSound(Sound sound) {
    this.sound = sound;
  }

  public Position getPos() {
    return position;
  }
  public void setPos(Position position) {
    this.position = position;
  }

  public void update(Input input, int delta, Properties properties, Assets assets) {
	}
	
	public void render(Graphics g, float xOffset, float yOffset) {
	  if (image != null) {
	    image.draw(position.getXPos() * App.TILE_SIZE + xOffset, position.getYPos() * App.TILE_SIZE + yOffset);
	  }
	}

}