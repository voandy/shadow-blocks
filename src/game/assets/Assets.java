package game.assets;

import java.util.ArrayList;

import game.Properties;
import game.assets.sprites.Position;
import game.assets.sprites.Sprite;
import game.assets.sprites.blocks.Block;
import game.assets.sprites.effects.GameEffects;
import game.assets.sprites.map.Door;
import game.assets.sprites.map.MapItem;
import game.assets.sprites.map.Target;
import game.assets.sprites.units.Giles;
import game.assets.sprites.units.Player;
import game.assets.sprites.units.Shadow;
import game.assets.sprites.units.Unit;
import game.methods.Loader;

public class Assets {
  
  /** map items and blocks are represented by a 2d array as they will never occupy the same grid coordinates
   * this makes them more efficient to find as we do not have to traverse a linked list 
   */
  private MapItem[][] map;
  private Block[][] blocks;
  
  /** units are represented by and ArrayList as they may occupy the same grid coordinates */
  private ArrayList<Unit> units;
  private ArrayList<Unit> toRemove;
  
  /** stores a list of targets to check which ones have been activated by a stone */
  private ArrayList<Target> targets;
  
  private GameEffects gameEffects;
  
  private Position playerPos;
  
  /** true if player is dead, used so that Mage doesn't freak out when there is no player present and the endScreen
   * is being displayed 
   */
  private boolean playerDead;
  
  private Door door;
  private Music music;
  
  private History history;

  public Assets(String filename, ArrayList<Sprite> sprites, Properties properties) {
    map = Loader.loadMap(sprites, properties.getLevelWidth(), properties.getLevelHeight());
    blocks = Loader.loadBlocks(sprites, properties.getLevelWidth(), properties.getLevelHeight());
    
    units = Loader.getSubset(sprites, Unit.class);
    toRemove = new ArrayList<>();
    
    targets = Loader.getSubset(sprites, Target.class);
    
    gameEffects = new GameEffects();
    
    door = Loader.findDoor(sprites);
    music = new Music();
    
    history = new History();
    
    /** finds the player and loads its position if it is present */
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

  /** removes a unit from the game and shows a Poof, this method avoids a concurrent modification exception */
  public void killUnit(Unit unit) {
    if (unit instanceof Player) {
      playerDead = true;
    }
    
    // new Position so that the effect remains in place after the creating entity is altered
    gameEffects.showPoof(new Position(unit.getPos()));
    
    // the Shadow cannot be permanently killed so we call its own kill method instead
    if (unit instanceof Shadow) {
      ((Shadow) unit).kill();
    } else {
      toRemove.add(unit);
    }
  }
  
  public void update(){
    units.removeAll(toRemove);
  }
  
  public MapItem[][] getMap() {
    return map;
  }
  public Block[][] getBlocks() {
    return blocks;
  }
  public void setInStone(Block[][] stones) {
    this.blocks = stones;
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
  public boolean isPlayerDead() {
    return playerDead;
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
