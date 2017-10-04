package game.assets.sprites.effects;

import game.assets.sprites.Position;

public class Poof extends Effect{
  private static final int WIDTH = 64;
  private static final int HEIGHT = 64;
  private static final int NO_FRAMES = 10;
  public static final int TIME = 300;
  
  public Poof(Position position) {
    super("res/smoke.png", "res/smoke.wav", position, WIDTH, HEIGHT, TIME / NO_FRAMES, TIME);
  }
}
