package game.assets.sprites.effects;

import game.assets.sprites.Position;

public class Pop extends Effect{
  private static final int WIDTH = 32;
  private static final int HEIGHT = 32;
  private static final int NO_FRAMES = 4;
  public static final int TIME = 150;
  
  public Pop(Position position) {
    super("res/giles/pop.png", "res/giles/pop.wav", position, WIDTH, HEIGHT, TIME / NO_FRAMES, TIME);
  }
}