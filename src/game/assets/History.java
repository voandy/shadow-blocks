package game.assets;

import java.util.LinkedList;

import game.Properties;
import game.assets.sprites.Position;
import game.assets.sprites.stones.Ice;
import game.assets.sprites.stones.Stone;
import game.assets.sprites.stones.Tnt;
import game.assets.sprites.units.Player;

public class History {
  private static final int MAX_HISTORY_SIZE = 10;
  
  private LinkedList<Position> playerHistory;
  private LinkedList<Stone[][]> stoneHistory;

  public History() {
    playerHistory = new LinkedList<Position>();
    stoneHistory = new LinkedList<Stone[][]>();
  }
  
  public void addStep(Position playerPos, Properties properties, Assets assets) {
    Position playerStep = new Position(playerPos);
    playerHistory.add(playerStep);
    stoneHistory.add(copyStones(properties, assets));
  }
  
  private Stone[][] copyStones(Properties properties, Assets assets) {
    int width = properties.getLevelWidth();
    int height = properties.getLevelHeight();
    
    Stone[][] stoneStep = new Stone[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (assets.getStones()[i][j] != null) {
          if (assets.getStones()[i][j] instanceof Tnt) {
            stoneStep[i][j] = new Tnt((Tnt) assets.getStones()[i][j]);
          } else if (assets.getStones()[i][j] instanceof Ice) {
            stoneStep[i][j] = new Ice((Ice) assets.getStones()[i][j]);
          } else {
            stoneStep[i][j] = new Stone(assets.getStones()[i][j]);
          }

        }
      }
    }
    return stoneStep;
  }
  
  public boolean undo(Player player, Properties properties, Assets assets) {
    if (!playerHistory.isEmpty()) {
      player.setPos(playerHistory.getLast());
      assets.setPlayerPos(player.getPos());
      playerHistory.removeLast();
      
      // we don't have to check if stoneHistory is empty as it contains the same number of elements as playerHistory
      assets.setInStone(stoneHistory.getLast());
      stoneHistory.removeLast();
      
      // limits the history length to max history size.
      if (playerHistory.size() > MAX_HISTORY_SIZE) {
        playerHistory.removeFirst();
        stoneHistory.removeFirst();
      }
      
      properties.decrementsMoves();
      return true;
    }
    return false;
  }
  
  public void clearHistory() {
    playerHistory.clear();
    stoneHistory.clear();
  }
}
