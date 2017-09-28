import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Guile extends Player{
  private Image guile_left;
  private Image guile_right;
  private Image guile_up;
  private Image guile_down;
  private Sound theme_song;
  
  public Guile(Position position) {
    super("res/guile/guile_down.png", "res/step.wav" , position);
    try {
      guile_left = new Image("res/guile/guile_left.png");
      guile_right = new Image("res/guile/guile_right.png");
      guile_up = new Image("res/guile/guile_up.png");
      guile_down = new Image("res/guile/guile_down.png");
    } catch (SlickException e) {
      e.printStackTrace();
    }
    
  }

  public void update(Input input, int delta, LevelProperties properties, Assets assets) {
    super.update(input, delta, properties, assets);
    if (input.isKeyPressed(Input.KEY_LEFT)) {
      setImage(guile_left);
    } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
      setImage(guile_right);
    } else if (input.isKeyPressed(Input.KEY_UP)) {
      setImage(guile_up);
    } else if (input.isKeyPressed(Input.KEY_DOWN)) {
      setImage(guile_down);
    }
  }
}
