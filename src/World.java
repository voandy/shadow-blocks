import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World {	
	private Level level;
	// stores a list of level filenames
	private String[] levelList;
	private int currLevel;
	
	public World() throws SlickException {
		levelList = Loader.loadLevelList("res/levels/level_list.txt");
		currLevel = 0;
		level = new Level(levelList[currLevel]);
	}
	
	public void update(Input input, int delta) {
		level.update(input, delta);
		// when the level is complete or skipped by pressing space the next level is loaded
		if (input.isKeyPressed(Input.KEY_P) && levelList[currLevel + 1] != null) {
		  level.stopMusic();
			currLevel++;
			level = new Level(levelList[currLevel]);
		}
		// restarts the current level if the r key is pressed or if the player has died
		if (input.isKeyPressed(Input.KEY_R)) {
		  level.stopMusic();
		  level = new Level(levelList[currLevel]);
		}
		
		if (level.isReadyToGo()) {
		  if (level.isWon() && levelList[currLevel + 1] != null) {
	      currLevel++;
	      level = new Level(levelList[currLevel]);
		  } else {
		    level = new Level(levelList[currLevel]);
		  }
		}
	}
	
	public void render(Graphics g) {
		level.render(g);
	}
	
}