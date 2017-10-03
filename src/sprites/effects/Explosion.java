package sprites.effects;

import sprites.Position;

public class Explosion extends Effect {
  private static final int WIDTH = 96;
  private static final int HEIGHT = 96;
  
  // the total time in we want the explosion to play
  // not to be confused with the "duration" argument in Animation which is the duration of one frame
  private static final int NO_FRAMES = 12;
  public static final int TIME = 400;
  
  public Explosion(Position position) {
    super("res/explosion.png", "res/explosion.wav", position, WIDTH, HEIGHT, TIME / NO_FRAMES, TIME);
  }
}
