package game.assets;

import java.util.ArrayList;

import game.Properties;
import game.assets.sprites.Position;
import game.assets.sprites.Sprite;
import game.assets.sprites.map.Door;
import game.assets.sprites.map.MapItem;
import game.assets.sprites.map.Target;
import game.assets.sprites.stones.Stone;
import game.assets.sprites.units.Giles;
import game.assets.sprites.units.Player;
import game.assets.sprites.units.Unit;
import game.methods.Loader;

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
  private Music music;
  
  private History history;

  public Assets(String filename, ArrayList<Sprite> sprites, Properties properties) {
    map = Loader.loadMap(sprites, properties.getLevelWidth(), properties.getLevelHeight());
    stones = Loader.loadStones(sprites, properties.getLevelWidth(), properties.getLevelHeight());
    units = Loader.getSubset(sprites, Unit.class);
    targets = Loader.getSubset(sprites, Target.class);
    gameEffects = new GameEffects();
    toRemove = new ArrayList<>();
    door = Loader.findDoor(sprites);
    music = new Music();
    history = new History();
    
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
  public void setInStone(Stone[][] stones) {
    this.stones = stones;
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
  public Music getMusic() {
    return music;
  }
  public History getHistory() {
    return history;
  }
}
