package game.assets.sprites.effects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import game.App;
import game.Properties;
import game.assets.Assets;
import game.assets.sprites.Position;
import game.assets.sprites.Sprite;

public class Effect extends Sprite{
private SpriteSheet sheet;
	private Animation animation;
	
  private float xRenderOffset;
  private float yRenderOffset;
	
  private int timeSinceShown;
  private int timeToShow;
  
  /** true when the animation has completed, GameEffects now knows to remove it */
  private boolean finished;
	
	public Effect(String animation_src, String sound_src, Position position, int width, int height, int duration, int timeToShow) {
		super(animation_src, sound_src, position);
		
		try {
			sheet = new SpriteSheet(animation_src, width, height);
		} catch (SlickException e) { 
			e.printStackTrace();
		}
		
		// animations are created from SpriteSheets
		animation = new Animation(sheet, duration);
		animation.setLooping(false);
		
	  // offsets Effect render position as animations don't have a drawCentered method like Images method for some reason
		xRenderOffset = (animation.getWidth() - App.TILE_SIZE) / (float) 2;
		yRenderOffset = (animation.getHeight() - App.TILE_SIZE) / (float) 2;
		
		timeSinceShown = 0;
		this.timeToShow = timeToShow;
		
		finished = false;
	}

  public void update(Input input, int delta, Properties properties, Assets assets) {
	  timeSinceShown += delta;
	  if (timeSinceShown > timeToShow) {
	    finished = true;
	  }
	  
	}

	public void render(Graphics g, float xOffset, float yOffset) {
		animation.draw((getPos().getXPos() * App.TILE_SIZE) + xOffset - xRenderOffset, 
		               (getPos().getYPos() * App.TILE_SIZE) + yOffset - yRenderOffset);
	}
	
  public Animation getAnimation() {
    return animation;
  }
  public boolean isFinished() {
    return finished;
  }
  public void setFinished(boolean finished) {
    this.finished = finished;
  }
  public int getTimeSinceShown() {
    return timeSinceShown;
  }
  public int getTimeToShow() {
    return timeToShow;
  }
}
