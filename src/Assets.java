import java.util.ArrayList;

public class Assets {
  
  // map items and stone are represented by a 2d array as they will never occupy the same grid coordinates
  private MapItem[][] map;
  private Stone[][] stones;
  
  // units are represented by and ArrayList as they may occupy the same grid coordinates
  private ArrayList<Unit> units;
  private ArrayList<Unit> toRemove;
  
  // stores a list of targets to check which ones have been activated by a stone
  private ArrayList<Target> targets;
  
  private GameEffects gameEffects;
  
  private Position playerPos;
  private Door door;

  
  public Assets(String filename, ArrayList<Sprite> sprites, Properties properties) {
    map = Loader.loadMap(sprites, properties.getLevelWidth(), properties.getLevelHeight());
    stones = Loader.loadStones(sprites, properties.getLevelWidth(), properties.getLevelHeight());
    units = Loader.getSubset(sprites, Unit.class);
    targets = Loader.getSubset(sprites, Target.class);
    gameEffects = new GameEffects();
    toRemove = new ArrayList<>();
    
    // finds the player and loads its position if it is present
    Player player = Loader.findPlayer(sprites);
    if (player != null) {
      playerPos = player.getPos();
    }
    
    door = Loader.findDoor(sprites);
  }
  
  public void update(){
    units.removeAll(toRemove);
  }

  // removes a unit from the game, this method avoid a concurrent modification exception
  public void killUnit(Unit unit) {
    toRemove.add(unit);
  }

  public MapItem[][] getMap() {
    return map;
  }
  public Stone[][] getStones() {
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
  public Position getPlayerPos() {
    return playerPos;
  }
  public void setPlayerPos(Position playerPos) {
    this.playerPos = playerPos;
  } 
  public Door getDoor() {
    return door;
  }
}
