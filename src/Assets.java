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
  
  Music music;

  
  public Assets(String filename, ArrayList<Sprite> sprites, Properties properties) {
    map = Loader.loadMap(sprites, properties.getLevelWidth(), properties.getLevelHeight());
    stones = Loader.loadStones(sprites, properties.getLevelWidth(), properties.getLevelHeight());
    units = Loader.getSubset(sprites, Unit.class);
    targets = Loader.getSubset(sprites, Target.class);
    gameEffects = new GameEffects();
    toRemove = new ArrayList<>();
    music = new Music();
    
    // finds the player and loads its position if it is present
    Player player = Loader.findPlayer(sprites);
    if (player != null) {
      playerPos = player.getPos();
      
      // play music depending on who the player character is
      if (player instanceof Giles) {
        music.switchGiles();
      } 
    } else {
      // if the level has no player then it is a win screen
      music.youWin();
    }
    
    door = Loader.findDoor(sprites);
    

  }

  // removes a unit from the game and shows a Poof, this method avoid a concurrent modification exception
  public void killUnit(Unit unit) {
    gameEffects.showPoof(unit.getPos());
    toRemove.add(unit);
  }
  public void update(){
    units.removeAll(toRemove);
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
