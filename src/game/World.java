package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import game.methods.Loader;

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
		// loads next level when the P key is pressed
		if (input.isKeyPressed(Input.KEY_P) && levelList[currLevel + 1] != null) {
		  level.stopMusic();
			currLevel++;
			level = new Level(levelList[currLevel]);
		}
		// restarts the current level when the R key is pressed
		if (input.isKeyPressed(Input.KEY_R)) {
		  level.stopMusic();
		  level = new Level(levelList[currLevel]);
		}
		
		// if the level is finished and the player has won then load next level after message is finished displaying
		// if the player had died then reset the current level instead
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