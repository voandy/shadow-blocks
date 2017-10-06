package game.assets;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Music {
  private final float MUSIC_VOL = (float) 0.7;
  
  private Sound adventure;
  private Sound giles_theme;
  private Sound music;
  
  private Sound youWin;
  
  public Music() {
    try {
      adventure = new Sound("res/music.wav");
      giles_theme = new Sound("res/giles/theme.wav");
      youWin = new Sound("res/giles/you_win.wav");
    } catch (SlickException e) {
      e.printStackTrace();
    }
    
    music = adventure;
  }
  
  public void playMusic() {
    if (music != null) {
      music.loop(1, MUSIC_VOL);
    }
  }
  public void stopMusic() {
    if (music != null) {
      music.stop();
    }
  }
  
  public void switchGiles() {
    music = giles_theme;
  }
  public void youWin() {
    music = null;
    youWin.play();
  }
}
