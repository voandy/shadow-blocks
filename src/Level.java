import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Level {
  // properties stores level dimensions and the number of moves
	private Properties properties;
  // assets stores everything in the level including the map, units, stones and effects
	private Assets assets;
	
	// message, image and sound to be displayed upon completion of level/death
	private Image message;
  private int messageTimer;
  private final int MESSAGE_TIME = 5000;
  private final String WASTED_SRC = "res/wasted.png";
  private final String WIN_SRC = "res/win.png";
	private Sound gameOverSound;
	private Sound victorySound;
	
	// indicates to World that the win/lose message has finished playing and we are ready to either load the next level
	// or restart the current level
	private boolean readyToGo;
	 // true if all the targets have been covered
  private boolean won;
  // true if the level is finished win or lose, we should now display a message
  private boolean finished;

	public Level(String filename) {
	  ArrayList<Sprite> sprites = Loader.loadSprites(filename);
	  
		properties = Loader.loadProperties(filename, sprites);
		assets = new Assets(filename, sprites, properties);		
		
    message = null;
    messageTimer = 0;
    try {
      gameOverSound = new Sound("res/wasted.wav");
      victorySound = new Sound("res/win.wav");
    } catch (SlickException e) {
      e.printStackTrace();
    }
    
    readyToGo = false;
    won = false;
    finished = false;
    
    assets.music.playMusic();
	}
	
	public void update(Input input, int delta) {
		updateUnits(input, delta);
		updateArrays(input, delta);

		// updates effects with delta
		assets.getGameEffects().update(input, delta, properties, assets);;
		
		// !isFinished() ensures that the image and sound are only shown/played once
		if (isCompleted() && !finished) {
		  levelWon();
		}
		
		// if the player is dead there is a delay before the level is reset to show the message
		if (finished) {
		  messageTimer += delta;
		}
		if (messageTimer > MESSAGE_TIME) {
		  readyToGo = true;
		}
		
		assets.update();
	}
	
  // updates units
	public void updateUnits(Input input, int delta) {
    for (Unit unit : assets.getUnits()) {
      unit.update(input, delta, properties, assets);
      // if the player is in the same position as an Npc the player dies
      if (unit instanceof Npc && unit.getPos().equals(assets.getPlayerPos())) {
        gameOver();
      }
    }
	}
	
  // updates MapItems and Stones
	public void updateArrays(Input input, int delta) {
    for (int i = 0; i < properties.getLevelWidth(); i++) {
      for (int j = 0; j < properties.getLevelHeight(); j++) {
        if (assets.getStones()[i][j] != null) {
          assets.getStones()[i][j].update(input, delta, properties, assets);
        }
        if (assets.getMap()[i][j] != null) {
          assets.getMap()[i][j].update(input, delta, properties, assets);
        }
      }
    }
	}

  public void render(Graphics g) {
		// renders map items
		renderSpriteArray(g, assets.getMap());
		// renders actors
		renderSpriteArray(g, assets.getStones());
		// renders units
		renderArrayList(g, assets.getUnits());
		// renders effects
		renderArrayList(g, assets.getGameEffects().getEffects());
		// shows number of moves made
		g.drawString("Moves: " + properties.getNoMoves(), 0, 0);
		// show message if present, game over or level complete
    if (message != null) {
      message.drawCentered(App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2);
    }
	}
  
  private void renderSpriteArray(Graphics g, Sprite[][] spriteArray) {
    for (int i = 0; i < properties.getLevelWidth(); i++) {
      for (int j = 0; j < properties.getLevelHeight(); j++) {
        if (spriteArray[i][j] != null) {
          spriteArray[i][j].render(g, properties.getXOffset(), properties.getYOffset());
        }
      }
    }
  }
  
  // used to render both units and effects
  public void renderArrayList(Graphics g, ArrayList<? extends Sprite> sprites) {
    if (sprites != null) {
      for (Sprite sprite : sprites) {
        sprite.render(g, properties.getXOffset(), properties.getYOffset());
      }
    }
  }
	
  // checks if level is won
  public boolean isCompleted() {
    // if there are no targets return false
    if (assets.getTargets().isEmpty()) {
      return false;
    }
    // if any target does not have a stone on it return false
    for (Target target : assets.getTargets()) {
      if (assets.getStones()[target.getPos().getXPos()][target.getPos().getYPos()] == null) {
        return false;
      }
    }
    // all targets have stones on them
    return true;
  }
	
	// removes player and shows game over message
  public void gameOver() {
    Player player = Loader.findPlayer(assets.getUnits());
    assets.killUnit(player);
    // setting playerPos to null ensures that this method is only called once
    assets.setPlayerPos(null);
    assets.music.stopMusic();
    
    try {
      message = new Image(WASTED_SRC);
    } catch (SlickException e) {
      e.printStackTrace();
    }
    gameOverSound.play();
    
    won = false;
    finished = true;
  }
  
  public void levelWon() {
    Player player = Loader.findPlayer(assets.getUnits());
    player.freeze();
    assets.music.stopMusic();
    
    try {
      message = new Image(WIN_SRC);
    } catch (SlickException e) {
      e.printStackTrace();
    }
    victorySound.play();
    
    won = true;
    finished = true;
  }
  
  public boolean isReadyToGo() {
    return readyToGo;
  }
  
  public boolean isWon() {
    return won;
  }
  
  public void stopMusic() {
    assets.music.stopMusic();
  }
}
