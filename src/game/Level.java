package game;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import game.assets.Assets;
import game.assets.EndScreen;
import game.assets.sprites.Sprite;
import game.assets.sprites.map.Target;
import game.assets.sprites.units.Player;
import game.methods.Loader;
import game.methods.Render;
import game.methods.Update;

public class Level {
  /** properties stores level dimensions and the number of moves */
	private Properties properties;
	/** stores all sprites in the level including the map, units, stones and effects */
	private Assets assets;
	/** shows a message at the end of each level */
	private EndScreen endScreen;
	
	public Level(String filename) {
	  ArrayList<Sprite> sprites = Loader.loadSprites(filename);
	  
		properties = Loader.loadProperties(filename, sprites);
		assets = new Assets(filename, sprites, properties);
		endScreen = new EndScreen();
    
    assets.getMusic().playMusic();
	}
	
	public void update(Input input, int delta) {
	  // updates all objects in the units Arraylist in assets
		Update.updateUnits(input, delta, assets, properties, endScreen);
		// updates all objects in the map[][] and block[][] arrays in assets
		Update.updateArrays(input, delta, assets, properties);
		// updates effects
		assets.getGameEffects().update(input, delta, properties, assets);
		
		assets.update();
		endScreen.update(input, delta);
		
		// undo a move when Z is pressed, can't undo death
		if (input.isKeyPressed(Input.KEY_Z) && !endScreen.isFinished()) {
		  undo();
		}
		
    // !isFinished() ensures that the image and sound are only shown/played once
    if (isCompleted() && !endScreen.isFinished()) {
      endScreen.levelWon(assets);
    }
	}

  public void render(Graphics g) {
		Render.renderSpriteArray(g, properties, assets.getMap());
		Render.renderSpriteArray(g, properties, assets.getBlocks());
		Render.renderArrayList(g, properties, assets.getUnits());
		Render.renderArrayList(g, properties, assets.getGameEffects().getEffects());
		
    // show EndScreen message if present
    endScreen.render(g);
		// shows number of moves made
		g.drawString("Moves: " + properties.getNoMoves(), 0, 0);
	}
	
  /** checks if level is won */
  public boolean isCompleted() {
    // if there are no targets return false
    if (assets.getTargets().isEmpty()) {
      return false;
    }
    // if any target does not have a stone on it return false
    for (Target target : assets.getTargets()) {
      if (assets.getBlocks()[target.getPos().getXPos()][target.getPos().getYPos()] == null) {
        return false;
      }
    }
    // all targets have stones on them
    return true;
  }
  /** undoes one move, see History class */
  private void undo() {
    Player player = Loader.findPlayer(assets.getUnits());
    if (assets.getHistory().undo(player, properties, assets)) {
      assets.getGameEffects().showSplash(player.getPos());
    }
  }
  
  public boolean isReadyToGo() {
    return endScreen.isReadyToGo();
  }
  public boolean isWon() {
    return endScreen.isWon();
  }
  
  public void stopMusic() {
    assets.getMusic().stopMusic();
  }
}
