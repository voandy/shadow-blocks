package sprites.effects;

import sprites.Position;

public class Lightning extends Effect{
  private static final int WIDTH = 60;
  private static final int HEIGHT = 53;
  private static final int NO_FRAMES = 20;
  public static final int TIME = 300;
  
  public Lightning(Position position) {
    super("res/undo.png", "res/undo.wav", position, WIDTH, HEIGHT, TIME / NO_FRAMES, TIME);
  }
}
