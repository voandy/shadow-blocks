package game.assets.sprites.units;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import game.Properties;
import game.assets.Assets;
import game.assets.sprites.Direction;
import game.assets.sprites.Position;
import game.assets.sprites.effects.Kick;
import game.assets.sprites.map.Wall;

/** Giles is a unique creation and does not infringe on any international copyrights */
public class Giles extends Player{
  private Image giles_left;
  private Image giles_right;
  private Image giles_up;
  private Image giles_down;
  private Image giles_kick;
  
  private boolean kicking;
  private int kickTime;
  
  public Giles(Position position) {
    super("res/giles/giles_down.png", "res/step.wav" , position);
    try {
      giles_left = new Image("res/giles/giles_left.png");
      giles_right = new Image("res/giles/giles_right.png");
      giles_up = new Image("res/giles/giles_up.png");
      giles_down = new Image("res/giles/giles_down.png");
      giles_kick = new Image("res/giles/giles_kick.png");
    } catch (SlickException e) {
      e.printStackTrace();
    }
    
    kicking = false;
    kickTime = 0;
    
    // set Giles' Direction to prevent him from throwing a SonicBoom with DIR_NONE at the start of the level
    getPos().setDir(Direction.DIR_DOWN);
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
        // prevents SonicBoom from being thrown inside a wall or block
        if (!(assets.getMap()[getPos().nextPos().getXPos()][getPos().nextPos().getYPos()] instanceof Wall) &&
            !(assets.getBlocks()[getPos().nextPos().getXPos()][getPos().nextPos().getYPos()] != null)) {
          assets.getGameEffects().throwSonicBoom(getPos().nextPos());
        } else {
          assets.getGameEffects().showPop(getPos().nextPos());
        }
        
        //throws Kick
      } else if (input.isKeyPressed(Input.KEY_SPACE)){
        assets.getGameEffects().throwKick(getPos());
        setImage(giles_kick);
        freeze();
        kicking = true;
      } 
    }
    
    if (kicking) {
      kickTime += delta;
    }
    if (kickTime > Kick.TIME) {
      kicking = false;
      kickTime = 0;
      setImageDir();
      unFreeze();
    }
  }
  
  /** resets image to direction after kick */
  public void setImageDir() {
    switch(getPos().getDir()) {
    case DIR_LEFT:
      setImage(giles_left);
      break;
    case DIR_RIGHT:
      setImage(giles_right);
      break;
    case DIR_UP:
      setImage(giles_up);
      break;
    case DIR_DOWN:
      setImage(giles_down);
      break;
    default:
      break;
    }
  }
}
