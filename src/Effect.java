import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Effect extends Sprite{
	private SpriteSheet sheet;
	private Animation animation;
	
	private float xDrawPos;
	private float yDrawPos;
	
  private boolean finished;
  private int timeSinceShown;
  private int timeToShow;
	
  // not that effects are created without a position. one must be set before they are rendered in GameEffects
	public Effect(String animation_src, String sound_src, int width, int height, int duration, int timeToShow) {
		super(animation_src, sound_src, null);
		
		try {
			sheet = new SpriteSheet(animation_src, width, height);
		} catch (SlickException e) { 
			e.printStackTrace();
		}
		
		animation = new Animation(sheet, duration);
		animation.setLooping(false);
		
		finished = false;
		timeSinceShown = 0;
		this.timeToShow = timeToShow;
	}
	
	// offsets Effect render position as animations don't have a drawCentered like images method for some reason
	public void render(Graphics g, float xOffset, float yOffset) {
		xDrawPos = (getPos().getXPos() * App.TILE_SIZE) + xOffset - (animation.getWidth() / 4);;
		yDrawPos = (getPos().getYPos() * App.TILE_SIZE) + yOffset - (animation.getHeight() / 4);
		animation.draw(xDrawPos, yDrawPos);
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
  public void setTimeSinceShown(int timeSinceShown) {
	  this.timeSinceShown = timeSinceShown;
  }
  public int getTimeToShow() {
    return timeToShow;
  }
}
