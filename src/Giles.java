import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

// Giles is a unique creation and does not infringe on any international copyrights
public class Giles extends Player{
  private Image giles_left;
  private Image giles_right;
  private Image giles_up;
  private Image giles_down;
  
  public Giles(Position position) {
    super("res/giles/giles_down.png", "res/step.wav" , position);
    try {
      giles_left = new Image("res/giles/giles_left.png");
      giles_right = new Image("res/giles/giles_right.png");
      giles_up = new Image("res/giles/giles_up.png");
      giles_down = new Image("res/giles/giles_down.png");
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }

  public void update(Input input, int delta, Properties properties, Assets assets) {
    if (!isFrozen()) {
      if (input.isKeyPressed(Input.KEY_LEFT)) {
        setImage(giles_left);
        getPos().setDir(Direction.DIR_LEFT);
        playerMove(properties, assets);
      } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
        setImage(giles_right);
        getPos().setDir(Direction.DIR_RIGHT);
        playerMove(properties, assets);
      } else if (input.isKeyPressed(Input.KEY_UP)) {
        setImage(giles_up);
        getPos().setDir(Direction.DIR_UP);
        playerMove(properties, assets);
      } else if (input.isKeyPressed(Input.KEY_DOWN)) {
        setImage(giles_down);
        getPos().setDir(Direction.DIR_DOWN);
        playerMove(properties, assets);
        
        // point giles without moving hum using WASD
      } else if (input.isKeyPressed(Input.KEY_A)) {
        setImage(giles_left);
        getPos().setDir(Direction.DIR_LEFT);
      } else if (input.isKeyPressed(Input.KEY_D)) {
        setImage(giles_right);
        getPos().setDir(Direction.DIR_RIGHT);
      } else if (input.isKeyPressed(Input.KEY_W)) {
        setImage(giles_up);
        getPos().setDir(Direction.DIR_UP);
      } else if (input.isKeyPressed(Input.KEY_S)) {
        setImage(giles_down);
        getPos().setDir(Direction.DIR_DOWN);
        
        //throws SonicBoom
      } else if (input.isKeyPressed(Input.KEY_F)) {
        // prevents SonicBoom from being thrown inside a wall
        if (!(assets.getMap()[getPos().nextPos().getXPos()][getPos().nextPos().getYPos()] instanceof Wall)) {
          assets.getGameEffects().throwSonicBoom(getPos().nextPos());
        } else {
          assets.getGameEffects().showPop(getPos().nextPos());
        }
      }
    }
    

  }
}
