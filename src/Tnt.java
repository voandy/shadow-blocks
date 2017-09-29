import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Tnt extends Stone {
	Image floor;
	Explosion explosion;
	
	public Tnt(Position position) {
		super("res/tnt.png", "res/tnt.wav", position);

		try {
      floor = new Image("res/floor.png");
    } catch (SlickException e) {
      e.printStackTrace();
    }
		
		explosion = new Explosion();
	}
	
	public void update(Input input, int delta, LevelProperties properties, Assets assets) {
	  // removes CrackedWall and Tnt after the explosion is finished playing
		if (explosion.isFinished()) {
		  assets.getMap()[getPos().getXPos()][getPos().getYPos()] = new Floor(getPos());
		  assets.getStones()[getPos().getXPos()][getPos().getYPos()] = null;
		}
	}
	
	public void detonate(Assets assets) {
	  // shows explosion effect
	  assets.getGameEffects().showEffect(explosion, getPos());
		
		// hides Tnt and CrackedWall while animation is playing
		assets.getMap()[getPos().getXPos()][getPos().getYPos()].setImage(floor);
		assets.getStones()[getPos().getXPos()][getPos().getYPos()].setImage(null);
	}
}