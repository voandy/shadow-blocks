import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tnt extends Stone {
	Image floor;
	
	public Tnt(Position position) {
		super("res/tnt.png", "res/tnt.wav", position);

		try {
      floor = new Image("res/floor.png");
    } catch (SlickException e) {
      e.printStackTrace();
    }
	}
	
	public void detonate(Assets assets) {
	  // shows explosion effect
	  assets.getGameEffects().showExplosion(getPos());
	  
	  // removes Tnt and replaces CrackedWall with Floor
    assets.getMap()[getPos().getXPos()][getPos().getYPos()] = new Floor(getPos());
    assets.getStones()[getPos().getXPos()][getPos().getYPos()] = null;
	}
}