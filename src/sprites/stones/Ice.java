package sprites.stones;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import game.App;
import game.Assets;
import game.Properties;
import sprites.Position;

public class Ice extends Stone {
  private Sound slide;
  
	private final static int MOVE_DELAY = 250;
	private boolean sliding;
	private int timeSinceMove;
	private Position nextPos;
	
  // Offset that changes with delta to allow animation to move smoothly
  private float renderOffsetX;
  private float renderOffsetY;
  private final static float SPEED = (float) App.TILE_SIZE / MOVE_DELAY;
	
	public Ice(Position position) {
		super("res/ice.png", null, position);
		
		try {
      slide = new Sound("res/ice.wav");
    } catch (SlickException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
		
		sliding = false;
		timeSinceMove = 0;
		nextPos = null;
		
    renderOffsetX = 0;
    renderOffsetY = 0;
	}
	
	// if the ice block is in a sliding state it will continue to move unit it encounters a wall or block
	public void update(Input input, int delta, Properties properties, Assets assets) {
		if (sliding) {
			timeSinceMove += delta;
			
	    switch(getPos().getDir()) {
	    case DIR_LEFT:
	      renderOffsetX = timeSinceMove * -SPEED + App.TILE_SIZE;
	      break;
	    case DIR_RIGHT:
	      renderOffsetX = timeSinceMove * SPEED - App.TILE_SIZE;
	      break;
	    case DIR_UP:
	      renderOffsetY = timeSinceMove * -SPEED + App.TILE_SIZE;
	      break;
	    case DIR_DOWN:
	      renderOffsetY = timeSinceMove * SPEED - App.TILE_SIZE;
	      break;
	    case DIR_NONE:
	      break;
	    }
	    
			if (timeSinceMove > MOVE_DELAY) {
				
				if (isValidMove(nextPos, assets)) {
					move(properties, assets);
					nextPos = getPos().nextPos();
					timeSinceMove = 0;
	        renderOffsetX = 0;
	        renderOffsetY = 0;
					
				} else {
					sliding = false;
			    slide.stop();
			    renderOffsetX = 0;
			    renderOffsetY = 0;
				}
			}
		}
	}
	
	public void makeSlide() {
	  // prevents sound from continuously looping if pushed multiple times before it stops
	  slide.stop();
	  
		sliding = true;
    slide.loop();
    timeSinceMove = 0;
		nextPos = getPos().nextPos();
	}
  
  public void render(Graphics g, float xOffset, float yOffset) {
    getImage().draw(getPos().getXPos() * App.TILE_SIZE + xOffset + renderOffsetX, 
                    getPos().getYPos() * App.TILE_SIZE + yOffset + renderOffsetY);
  }
	
	// returns false if the destination contains a wall or block and true otherwise
	public boolean isValidMove(Position destination, Assets assets) {
		if (!super.isValidMove(destination, assets)) {
			return false;
		}
		
		// checks if destination contains a stone.
		if (assets.getStones()[destination.getXPos()][destination.getYPos()] != null) {
			return false;
		}
		
		return true;
	}
}
