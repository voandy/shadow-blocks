public class Kick extends Effect{
  private static final int WIDTH = 96;
  private static final int HEIGHT = 96;
  private static final int NO_FRAMES = 8;
  public static final int TIME = 450;
  
  public Kick() {
    super("res/giles/kick_swoosh.png", "res/giles/kick_swoosh.wav", WIDTH, HEIGHT, TIME / NO_FRAMES, TIME);
  }
}