package game.methods;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;

import game.Properties;
import game.assets.sprites.Sprite;

public class Render {
  /** used to render either Block[][] array or MapItem[][] array */
  public static void renderSpriteArray(Graphics g, Properties properties, Sprite[][] spriteArray) {
    for (int i = 0; i < properties.getLevelWidth(); i++) {
      for (int j = 0; j < properties.getLevelHeight(); j++) {
        if (spriteArray[i][j] != null) {
          spriteArray[i][j].render(g, properties.getXOffset(), properties.getYOffset());
        }
      }
    }
  }
  
  /** used to render Sprites stored in ArrayLists i.e. units and effects */
  public static void renderArrayList(Graphics g, Properties properties, ArrayList<? extends Sprite> sprites) {
    if (sprites != null) {
      for (Sprite sprite : sprites) {
        sprite.render(g, properties.getXOffset(), properties.getYOffset());
      }
    }
  }

}
