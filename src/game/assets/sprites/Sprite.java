package game.assets.sprites;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

import game.App;
import game.Properties;
import game.assets.Assets;


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
	
	public Sprite(Sprite another) {
	  // note that this copy constructor only references the Image and Sound as they are not supposed to change for stones
	  // remember to look here if this creates a bug in the future
	  this.image = another.image;
	  this.sound = another.sound;
	  this.position = new Position(another.position);
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