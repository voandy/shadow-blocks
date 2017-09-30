import java.util.ArrayList;

public class Assets {
  
  // map items and stone are represented by a 2d array as they will never occupy the same grid coordinates
  private Sprite[][] map;
  private Sprite[][] stones;
  // units are represented by and ArrayList as they may occupy the same grid coordinates
  private ArrayList<Unit> units;
  // stores a list of targets to check which ones have been activated by a stone
  private ArrayList<Target> targets;
  private GameEffects gameEffects;
  private ArrayList<Unit> toRemove;
  
  public Assets(String filename, ArrayList<Sprite> sprites, Properties properties) {
    map = Loader.populateLevel(sprites, properties.getLevelWidth(), properties.getLevelHeight(), MapItem.class);
    stones = Loader.populateLevel(sprites, properties.getLevelWidth(), properties.getLevelHeight(), Stone.class);
    units = Loader.getSubset(sprites, Unit.class);
    targets = Loader.getSubset(sprites, Target.class);
    gameEffects = new GameEffects();
    toRemove = new ArrayList<>();
  }
  
  public void update(){
    units.removeAll(toRemove);
  }

  // removes a unit from the game, this method avoid a concurrent modification exception
  public void killUnit(Unit unit) {
    toRemove.add(unit);
  }

  public Sprite[][] getMap() {
    return map;
  }
  public Sprite[][] getStones() {
    return stones;
  }
  public ArrayList<Unit> getUnits() {
    return units;
  }
  public ArrayList<Target> getTargets() {
    return targets;
  }
  public GameEffects getGameEffects() {
    return gameEffects;
  }
}
