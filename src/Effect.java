import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Effect extends Sprite{
	private SpriteSheet sheet;
	private Animation animation;
	private float xDrawPos;
	private float yDrawPos;
	
	public Effect(String animation_src, String sound_src, Position position, int width, int height, int duration) {
		super(animation_src, sound_src, position);
		
		try {
			sheet = new SpriteSheet(animation_src, width, height);
		} catch (SlickException e) { 
			e.printStackTrace();
		}
		
		animation = new Animation(sheet, duration);
	}
	
	public void update(Input input, int delta, LevelProperties properties, Assets assets) {
		animation.update(delta);
	}
	
	public void render(Graphics g, float xOffset, float yOffset) {
		xDrawPos = (getPos().getXPos() * App.TILE_SIZE) + xOffset - (animation.getWidth() / 4);;
		yDrawPos = (getPos().getYPos() * App.TILE_SIZE) + yOffset - (animation.getHeight() / 4);
		animation.draw(xDrawPos, yDrawPos);
	}	
}
