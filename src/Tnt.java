import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Tnt extends Stone {
	private static final int EXPLOSION_WIDTH = 96;
	private static final int EXPLOSION_HEIGHT = 96;
	private static final int EXPLOSION_DURATION = 400;
	private static final int NO_FRAMES = 12;
	Effect explosion;
	private int timeSinceExplosion;
	private boolean exploading;
	
	public Tnt(Position position) {
		super("res/tnt.png", "res/tnt.wav", position);
		explosion = new Effect("res/explosion.png", "res/explosion.wav", getPos(), 
				EXPLOSION_WIDTH, EXPLOSION_HEIGHT, EXPLOSION_DURATION / NO_FRAMES);
		exploading = false;

	}
	
	public void update(Input input, int delta, LevelProperties properties, Assets assets) {
		if (exploading) {
			timeSinceExplosion += delta;
		}
		if (timeSinceExplosion > EXPLOSION_DURATION) {
		  assets.getEffects().remove(explosion);
		  assets.getMap()[getPos().getXPos()][getPos().getYPos()] = new Floor(getPos());
		  assets.getStones()[getPos().getXPos()][getPos().getYPos()] = null;
		}
	}
	
	public void detonate(Assets assets) {
	  assets.getEffects().add(explosion);
		exploading = true;
		explosion.makeSound();
		timeSinceExplosion = 0;
	}
}