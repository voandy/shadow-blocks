public class Poof extends Effect{
  private static final int POOF_WIDTH = 64;
  private static final int POOF_HEIGHT = 64;
  private static final int NO_FRAMES = 10;
  public static final int POOF_TIME = 300;
  
  public Poof() {
    super("res/smoke.png", "res/smoke.wav", POOF_WIDTH, POOF_HEIGHT, 
        POOF_TIME / NO_FRAMES, POOF_TIME);
  }
}
