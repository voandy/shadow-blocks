package sprites.effects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import game.App;
import game.Assets;
import game.Properties;
import sprites.Position;
import sprites.Sprite;

public class Effect extends Sprite{
private SpriteSheet sheet;
	private Animation animation;
	
	private float xDrawPos;
  private float yDrawPos;
  private float xRenderOffset;
  private float yRenderOffset;
	
  private int timeSinceShown;
  private int timeToShow;
  
  private boolean finished;
	
  // not that effects are created without a position. one must be set before they are rendered in GameEffects
	public Effect(String animation_src, String sound_src, Position position, 
	    int width, int height, int duration, int timeToShow) {
		super(animation_src, sound_src, position);
		
		try {
			sheet = new SpriteSheet(animation_src, width, height);
		} catch (SlickException e) { 
			e.printStackTrace();
		}
		
		animation = new Animation(sheet, duration);
		animation.setLooping(false);
		
	  // offsets Effect render position as animations don't have a drawCentered like images method for some reason
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
		xDrawPos = (getPos().getXPos() * App.TILE_SIZE) + xOffset - xRenderOffset;
		yDrawPos = (getPos().getYPos() * App.TILE_SIZE) + yOffset - yRenderOffset;
		animation.draw(xDrawPos, yDrawPos);
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
