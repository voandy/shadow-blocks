import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

public class Effect extends Sprite{
	private SpriteSheet sheet;
	private Animation animation;
	private Sound sound;
	private Position position;
	
	
	public Effect(String animation_src, String sound_src, Position position, int width, int height, int duration) {
		super(animation_src, sound_src, position);
		
		try {
			sheet = new SpriteSheet(animation_src, width, height);
			if (sound_src != null) {
				sound = new Sound(sound_src);
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		animation = new Animation(sheet, duration);
		
	}
	public void render(Graphics g, float xOffset, float yOffset) {
		animation.draw(500, 350);
	}
	
	public void makeSound() {
		if (sound != null) {
			sound.play();
		}
	}
	
	public void drawX() {
		animation.draw(500, 350);
	}
	
}
