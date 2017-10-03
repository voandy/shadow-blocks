package sprites.effects;

import org.newdawn.slick.Input;

import game.Assets;
import game.Properties;
import sprites.Position;
import sprites.units.Unit;

public class Kick extends Effect{
  private static final int WIDTH = 96;
  private static final int HEIGHT = 96;
  private static final int NO_FRAMES = 8;
  public static final int TIME = 450;
  
  // the duration, as a ratio of timeToShow that Giles' kick is attacking above him
  private static final float HIGH_KICK_TIME = (float) 0.5;
  // number of grid squares affects by each portion of the kick
  private static final int KICK_RANGE = 4;
  // the positions affected by the upper and lower parts of the kick
  private Position[] highKickRange;
  private Position[] lowKickRange;
  
  public Kick(Position position) {
    super("res/giles/kick_swoosh.png", "res/giles/kick_swoosh.wav", position, WIDTH, HEIGHT, TIME / NO_FRAMES, TIME);
    
    int xPos = getPos().getXPos();
    int yPos = getPos().getYPos();
    
    highKickRange = getHighKickRange(xPos, yPos);
    lowKickRange = getLowKickRange(xPos, yPos);
  }
  
  public void update(Input input, int delta, Properties properties, Assets assets) {
    super.update(input, delta, properties, assets);
    if (getTimeSinceShown() < getTimeToShow() * HIGH_KICK_TIME) {
      attack(highKickRange, assets);
    } else {
      attack(lowKickRange, assets);
    }
  }
  
  public void attack(Position[] kickRange, Assets assets) {
    for (int i = 0; i < KICK_RANGE; i++) {
      for (Unit unit : assets.getUnits()) {
        if (kickRange[i].equals(unit.getPos())) {
          assets.killUnit(unit);
        }
      }
    }
  }
  
  // the upper portion of the Kick attacks the grid position directly above Giles and the 3 grid positions to the right
  private Position[] getHighKickRange(int xPos, int yPos) {
    highKickRange = new Position[KICK_RANGE];
    
    highKickRange[0] = new Position(xPos, yPos -1);
    highKickRange[1] = new Position(xPos + 1, yPos - 1);
    highKickRange[2] = new Position(xPos + 1, yPos);
    highKickRange[3] = new Position(xPos + 1, yPos + 1);
    
    return highKickRange;
  }
  // the lower portion of the Kick attacks the grid position directly below Giles and the 3 grid positions to the left
  private Position[] getLowKickRange(int xPos, int yPos) {
    lowKickRange = new Position[KICK_RANGE];
    
    lowKickRange[0] = new Position(xPos, yPos + 1);
    lowKickRange[1] = new Position(xPos - 1, yPos - 1);
    lowKickRange[2] = new Position(xPos - 1, yPos);
    lowKickRange[3] = new Position(xPos - 1, yPos + 1);
    
    return lowKickRange;
  }
}