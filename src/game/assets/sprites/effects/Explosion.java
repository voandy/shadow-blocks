package game.assets.sprites.effects;

import game.assets.sprites.Position;

public class Explosion extends Effect {
  private static final int WIDTH = 96;
  private static final int HEIGHT = 96;
  private static final int NO_FRAMES = 12;
  public static final int TIME = 400;

  public Explosion(Position position) {
    super("res/explosion.png", "res/explosion.wav", position, WIDTH, HEIGHT, TIME / NO_FRAMES, TIME);
  }
}
