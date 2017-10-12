package game.assets.sprites.effects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import game.App;
import game.Properties;
import game.assets.Assets;
import game.assets.sprites.Movable;
import game.assets.sprites.Position;
import game.assets.sprites.units.Npc;
import game.assets.sprites.units.Unit;

public class SonicBoom extends Effect implements Movable{
  private final static String ANIMATION_SRC = "res/giles/sonic_boom.png";
  private final static String SOUND_SRC = "res/giles/sonic_boom.wav";

  private final static int WIDTH = 32;
  private final static int HEIGHT = 32;
  private final static int DURATION = 40;

  private int timeSinceMove;
  private int squaresMoved;
  private final static int MOVE_DELAY = 150;

  /** Offset that changes with delta to allow animation to move smoothly */
  private float xRenderOffset;
  private float yRenderOffset;
  private final static float SPEED = (float) App.TILE_SIZE / MOVE_DELAY;

  /** the actual position of the SonicBoom (invisible) */
  private Position nextPos;
  /** the initial position of the SonicBoom when it is thrown */
  private Position throwPos;

  public SonicBoom(Position position) {
    super(ANIMATION_SRC, SOUND_SRC, position, WIDTH, HEIGHT, DURATION, 0);

    getAnimation().setLooping(true);

    nextPos = getPos().nextPos();

    squaresMoved = 0;

    // initialise renderOffsets based on the direction Giles is facing
    switch(getPos().getDir()) {
    case DIR_LEFT:
      xRenderOffset = App.TILE_SIZE / 2;
      break;
    case DIR_RIGHT:
      xRenderOffset = -(App.TILE_SIZE / 2);
      break;
    case DIR_UP:
      yRenderOffset = App.TILE_SIZE / 2;
      break;
    case DIR_DOWN:
      yRenderOffset = -(App.TILE_SIZE / 2);
      break;
    case DIR_NONE:
      break;
    }

    throwPos = new Position(getPos());
  }

  public void update(Input input, int delta, Properties properties, Assets assets) {
    // kills any Npcs the SonicBoom encounters
    for (Unit unit : assets.getUnits()) {
      if (unit.getPos().equals(getPos()) && unit instanceof Npc) {
        assets.killUnit(unit);
        setFinished(true);
      }
    }

    timeSinceMove += delta;

    // updates the render position based on the direction the SonicBoom is moving
    switch(getPos().getDir()) {
    case DIR_LEFT:
      xRenderOffset = -squaresMoved * App.TILE_SIZE + timeSinceMove * -SPEED;
      break;
    case DIR_RIGHT:
      xRenderOffset = squaresMoved * App.TILE_SIZE + timeSinceMove * SPEED;
      break;
    case DIR_UP:
      yRenderOffset = -squaresMoved * App.TILE_SIZE + timeSinceMove * -SPEED;
      break;
    case DIR_DOWN:
      yRenderOffset = squaresMoved * App.TILE_SIZE + timeSinceMove * SPEED;
      break;
    case DIR_NONE:
      break;
    }

    // invisibly moves the SonicBoom until it encounters an obstacle
    if (timeSinceMove > MOVE_DELAY) {
      if (move(properties, assets)) {
        timeSinceMove = 0;
        squaresMoved++;
      } else {
        setFinished(true);
        assets.getGameEffects().showPop(getPos().nextPos());
      }
    }
  }

  public boolean move(Properties properties, Assets assets) {
    if (isValidMove(nextPos, assets)) {
      getPos().setPos(getPos().nextPos());
      nextPos = getPos().nextPos();
      return true;
    }
    return false;
  }

  public void render(Graphics g, float xOffset, float yOffset) {
    getAnimation().draw(throwPos.getXPos() * App.TILE_SIZE + xOffset + xRenderOffset,
        throwPos.getYPos() * App.TILE_SIZE + yOffset + yRenderOffset);
  }

  /** returns false if the destination contains a wall or block and true otherwise */
  public boolean isValidMove(Position destination, Assets assets) {
    if (assets.getBlocks()[destination.getXPos()][destination.getYPos()] != null ||
        assets.getMap()[destination.getXPos()][destination.getYPos()].isBlocked()) {
      return false;
    }
    return true;
  }
}
