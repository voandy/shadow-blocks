import org.newdawn.slick.Graphics;

public class Pop extends Effect{
  private static final int POP_WIDTH = 32;
  private static final int POP_HEIGHT = 32;
  
  // the total time in we want the explosion to play
  // not to be confused with the "duration" argument in Animation which is the duration of one frame
  private static final int NO_FRAMES = 4;
  public static final int P0P_TIME = 150;
  
  public Pop() {
    super("res/giles/pop.png", "res/giles/pop.wav", POP_WIDTH, POP_HEIGHT, 
        P0P_TIME / NO_FRAMES, P0P_TIME);
  }
  
  public void render(Graphics g, float xOffset, float yOffset) {
    getAnimation().draw(getPos().getXPos() * App.TILE_SIZE + xOffset, getPos().getYPos() * App.TILE_SIZE + yOffset);
  }
}