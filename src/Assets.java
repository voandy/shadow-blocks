import java.util.ArrayList;

public class Assets {
  
  // map items and stone are represented by a 2d array as they will never occupy the same grid coordinates
  private Sprite[][] map;
  private Sprite[][] stones;
  // units and effects are represented by and ArrayList as they may occupy the same grid coordinates
  private ArrayList<Unit> units;
  private ArrayList<Effect> effects;
  
  public Assets(String filename, ArrayList<Sprite> sprites) {
    
  }
  
}
