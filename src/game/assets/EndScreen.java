package game.assets;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import game.App;
import game.assets.sprites.units.Player;
import game.assets.sprites.units.Unit;
import game.methods.Loader;

public class EndScreen {
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
  
  public EndScreen() {
    message = null;
    messageTimer = 0;
    
    readyToGo = false;
    won = false;
    finished = false;
    
    try {
      gameOverSound = new Sound("res/wasted.wav");
      victorySound = new Sound("res/win.wav");
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }
  
  public void update(Input input, int delta) {

    // if the player is dead there is a delay before the level is reset to show the message
    if (finished) {
      messageTimer += delta;
    }
    if (messageTimer > MESSAGE_TIME) {
      readyToGo = true;
    }
  }

  public void render(Graphics g) {
    // show message if present: game over or level complete
    if (message != null) {
      message.drawCentered(App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2);
    }
  }

  // removes player and shows game over message
  public void gameOver(Assets assets) {
    Player player = Loader.findPlayer(assets.getUnits());
    assets.killUnit(player);
    // setting playerPos to null ensures that this method is only called once
    assets.setPlayerPos(null);
    assets.getMusic().stopMusic();
    
    // freezes all units
    for (Unit unit : assets.getUnits()) {
      unit.freeze();
    }
    
    try {
      message = new Image(WASTED_SRC);
    } catch (SlickException e) {
      e.printStackTrace();
    }
    gameOverSound.play();
    
    won = false;
    finished = true;
  }
  
  public void levelWon(Assets assets) {
    // freezes all units
    for (Unit unit : assets.getUnits()) {
      unit.freeze();
    }
    
    assets.getMusic().stopMusic();
    
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
  
  public boolean isFinished() {
    return finished;
  }
}
