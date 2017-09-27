import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class World {	
	private Level level;
	
	public World() throws SlickException {
		level = new Level("res/levels/test.lvl");
	}
	
	public void update(Input input, int delta) {
		level.update(input, delta);
		if (level.isCompleted()) {
			level = new Level("res/levels/test.lvl");
		}
	}
	
	public void render(Graphics g) {
		level.render(g);
	}
	
	
}