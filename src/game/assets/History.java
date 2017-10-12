package game.assets;

import java.util.LinkedList;

import game.Properties;
import game.assets.sprites.Position;
import game.assets.sprites.blocks.Block;
import game.assets.sprites.blocks.Ice;
import game.assets.sprites.blocks.Tnt;
import game.assets.sprites.units.Giles;
import game.assets.sprites.units.Player;

public class History {
  private static final int MAX_HISTORY_SIZE = 10;
  
  private LinkedList<Position> playerHistory;
  private LinkedList<Block[][]> blockHistory;

  public History() {
    playerHistory = new LinkedList<Position>();
    blockHistory = new LinkedList<Block[][]>();
  }
  
  /** adds a single step to both playerHistory and blockHistory */
  public void addStep(Position playerPos, Properties properties, Assets assets) {
    Position playerStep = new Position(playerPos);
    playerHistory.add(playerStep);
    blockHistory.add(copyBlocks(properties, assets));
    
    // limits the history length to max history size.
    if (playerHistory.size() > MAX_HISTORY_SIZE) {
      playerHistory.removeFirst();
      blockHistory.removeFirst();
    }
  }
  
  /** performs a shallow copy of Block[][], see individual copy constructors for details
   * this is important as Blocks are mutable and will likely change before an undo is performed 
   */
  private Block[][] copyBlocks(Properties properties, Assets assets) {
    int width = properties.getLevelWidth();
    int height = properties.getLevelHeight();
    
    Block[][] stoneStep = new Block[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        
        if (assets.getBlocks()[i][j] != null) {
          // casts Blocks back to their original subclass, there's probably a better way to do this but it'll do for now
          if (assets.getBlocks()[i][j] instanceof Tnt) {
            stoneStep[i][j] = new Tnt((Tnt) assets.getBlocks()[i][j]);
          } else if (assets.getBlocks()[i][j] instanceof Ice) {
            stoneStep[i][j] = new Ice((Ice) assets.getBlocks()[i][j]);
          } else {
            stoneStep[i][j] = new Block(assets.getBlocks()[i][j]);
          }

        }
      }
    }
    return stoneStep;
  }
  
  /** undoes a single move */
  public boolean undo(Player player, Properties properties, Assets assets) {
    if (!playerHistory.isEmpty()) {
      // undoes the player's Position
      player.setPos(playerHistory.getLast());
      assets.setPlayerPos(player.getPos());
      
      // reset Giles' image according to his direction
      if (player instanceof Giles) {
        ((Giles) player).setImageDir();
      }
      playerHistory.removeLast();
      
      // we don't have to check if blockHistory is empty as it contains the same number of elements as playerHistory
      assets.setInStone(blockHistory.getLast());
      blockHistory.removeLast();
      
      properties.decrementsMoves();
      return true;
    }
    return false;
  }
  
  /** clears history, called when Tnt destroys a crackedWall as this cannot be undone */
  public void clearHistory() {
    playerHistory.clear();
    blockHistory.clear();
  }
}
