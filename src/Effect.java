import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Effect extends Sprite{
	private Animation animation;
	SpriteSheet sheet;
	
	public Effect(String animation_src, String sound_src, Position position, int width, int height, int duration) {
		super(null, sound_src, position);
		
		try {
			sheet = new SpriteSheet(animation_src, width, height);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		animation = new Animation(sheet, duration);
	}
	
	public void showz() {
		animation.draw(500, 350);
	}
}
