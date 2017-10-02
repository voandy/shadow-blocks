import org.newdawn.slick.Graphics;

public class Pop extends Effect{
  private static final int WIDTH = 32;
  private static final int HEIGHT = 32;
  
  // the total time in we want the explosion to play
  // not to be confused with the "duration" argument in Animation which is the duration of one frame
  private static final int NO_FRAMES = 4;
  public static final int TIME = 150;
  
  public Pop() {
    super("res/giles/pop.png", "res/giles/pop.wav", WIDTH, HEIGHT, TIME / NO_FRAMES, TIME);
  }
}