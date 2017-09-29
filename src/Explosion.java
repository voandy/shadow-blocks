public class Explosion extends Effect {
  private static final int EXPLOSION_WIDTH = 96;
  private static final int EXPLOSION_HEIGHT = 96;
  
  // the total time in we want the explosion to play
  // not to be confused with the "duration" argument in Animation which is the duration of one frame
  private static final int EXPLOSION_TIME = 400;
  private static final int NO_FRAMES = 12;
  
  public Explosion() {
    super("res/explosion.png", "res/explosion.wav", EXPLOSION_WIDTH, EXPLOSION_HEIGHT, 
        EXPLOSION_TIME / NO_FRAMES, EXPLOSION_TIME);
  }
}
