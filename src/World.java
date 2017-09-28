import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World {	
	private Level level;
	private String[] levelList;
	private int currLevel;
	
	public World() throws SlickException {
		levelList = Loader.loadLevelList("res/levels/level_list.txt");
		currLevel = 0;
		level = new Level(levelList[currLevel]);
	}
	
	public void update(Input input, int delta) {
		level.update(input, delta);
		// loads the next level when level is complete of skip by pressing space
		if (level.isCompleted() || input.isKeyPressed(Input.KEY_SPACE)) {
			currLevel++;
			level = new Level(levelList[currLevel]);
		}
		
		// restarts the current level if the r key is pressed of if the level's restart status is true
		if (input.isKeyPressed(Input.KEY_R) || level.getRestartStatus()) {
			level = new Level(levelList[currLevel]);
		}
		
	}
	
	public void render(Graphics g) {
		level.render(g);
	}
	
	
}