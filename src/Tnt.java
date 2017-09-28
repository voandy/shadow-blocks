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
	
	public void update(Input input, int delta, LevelProperties properties, Sprite[][] map, Sprite[][] stones, 
			ArrayList<Unit> units, ArrayList<Effect> effects) {
		if (exploading) {
			timeSinceExplosion += delta;
		}
		if (timeSinceExplosion > EXPLOSION_DURATION) {
			effects.remove(explosion);
			map[getPos().getXPos()][getPos().getYPos()] = new Floor(getPos());
			stones[getPos().getXPos()][getPos().getYPos()] = null;
		}
	}
	
	public void detonate(Sprite[][] map, Sprite[][] stones, ArrayList<Effect> effects) {
		effects.add(explosion);
		exploading = true;
		explosion.makeSound();
		timeSinceExplosion = 0;
	}
}