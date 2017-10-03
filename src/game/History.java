package game;

import java.util.LinkedList;

import sprites.Position;
import sprites.stones.Ice;
import sprites.stones.Stone;
import sprites.stones.Tnt;
import sprites.units.Player;

public class History {
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

      assets.setInStone(stoneHistory.getLast());
      stoneHistory.removeLast();
      
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
