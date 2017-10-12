package game.assets.sprites.effects;

import game.assets.sprites.Position;

public class Splash extends Effect{
  private static final int WIDTH = 37;
  private static final int HEIGHT = 48;
  private static final int NO_FRAMES = 6;
  public static final int TIME = 500;

  public Splash(Position position) {
    super("res/splash.png", "res/undo.wav", position, WIDTH, HEIGHT, TIME / NO_FRAMES, TIME);
  }
}
