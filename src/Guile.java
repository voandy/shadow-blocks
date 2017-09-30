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
      theme_song = new Sound("res/guile/theme.wav");
    } catch (SlickException e) {
      e.printStackTrace();
    }
    
    theme_song.loop();
  }

  public void update(Input input, int delta, Properties properties, Assets assets) {
    if (!isFrozen()) {
      if (input.isKeyPressed(Input.KEY_LEFT)) {
        setImage(guile_left);
        getPos().setDir(Direction.DIR_LEFT);
        playerMove(properties, assets);
      } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
        setImage(guile_right);
        getPos().setDir(Direction.DIR_RIGHT);
        playerMove(properties, assets);
      } else if (input.isKeyPressed(Input.KEY_UP)) {
        setImage(guile_up);
        getPos().setDir(Direction.DIR_UP);
        playerMove(properties, assets);
      } else if (input.isKeyPressed(Input.KEY_DOWN)) {
        setImage(guile_down);
        getPos().setDir(Direction.DIR_DOWN);
        playerMove(properties, assets);
        // point guile without moving hum using WASD
      } else if (input.isKeyPressed(Input.KEY_A)) {
        setImage(guile_left);
        getPos().setDir(Direction.DIR_LEFT);
      } else if (input.isKeyPressed(Input.KEY_D)) {
        setImage(guile_right);
        getPos().setDir(Direction.DIR_RIGHT);
      } else if (input.isKeyPressed(Input.KEY_W)) {
        setImage(guile_up);
        getPos().setDir(Direction.DIR_UP);
      } else if (input.isKeyPressed(Input.KEY_S)) {
        setImage(guile_down);
        getPos().setDir(Direction.DIR_DOWN);
      }
    }
    

  }
}
