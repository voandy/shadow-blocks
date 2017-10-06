package game.assets.sprites.blocks;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import game.App;
import game.Properties;
import game.assets.Assets;
import game.assets.sprites.Position;

public class Ice extends Block {
  private Sound slide;
  
	private final static int MOVE_DELAY = 250;
	private boolean sliding;
	private int timeSinceMove;
	// squares moved since pushed
	private int squaresMoved;
	
	private Position nextPos;
	// the initial position of the ice block when it is pushed, used for rendering smoothly
	private Position pushPos;
	
	
  // Offset that changes with delta to allow animation to move smoothly
  private float renderOffsetX;
  private float renderOffsetY;
  private final static float SPEED = (float) App.TILE_SIZE / MOVE_DELAY;
	
	public Ice(Position position) {
		super("res/ice.png", null, position);
		
		try {
      slide = new Sound("res/ice.wav");
    } catch (SlickException e) {
      e.printStackTrace();
    }
		
		sliding = false;
		timeSinceMove = 0;
		squaresMoved = 0;
		
		nextPos = null;
		pushPos = getPos();
		
    renderOffsetX = 0;
    renderOffsetY = 0;
	}
	
	// copies an Ice block's properties
	public Ice(Ice another) {
	  super(another);
	    this.slide = another.slide;
	    slide.stop();
	  
	    this.sliding = another.sliding;
	    this.timeSinceMove = another.timeSinceMove;
	    
	    this.pushPos = new Position(another.pushPos);
	    if (another.nextPos != null) {
	      this.nextPos = new Position(another.nextPos);
	    }

	    this.renderOffsetX = another.renderOffsetX;
	    this.renderOffsetY = another.renderOffsetY;
	}
	
	public boolean move(Properties properties, Assets assets) {
	  // when an Ice block is moved we make it slide if it isn't already sliding
	  if (super.move(properties, assets)) {
	    if (!sliding) {
	      makeSlide();
	    }
	  }
	  return false;
	}
	
	// if the ice block is in a sliding state it will continue to move unit it encounters a wall, block or unit
	public void update(Input input, int delta, Properties properties, Assets assets) {
		if (sliding) {
			timeSinceMove += delta;
			
			// moves the Image's render position smoothly based on direction
	    switch(getPos().getDir()) {
	    case DIR_LEFT:
	      renderOffsetX = -squaresMoved * App.TILE_SIZE + timeSinceMove * -SPEED + App.TILE_SIZE;
	      break;
	    case DIR_RIGHT:
	      renderOffsetX = squaresMoved * App.TILE_SIZE + timeSinceMove * SPEED - App.TILE_SIZE;
	      break;
	    case DIR_UP:
	      renderOffsetY = -squaresMoved * App.TILE_SIZE + timeSinceMove * -SPEED + App.TILE_SIZE;
	      break;
	    case DIR_DOWN:
	      renderOffsetY = squaresMoved * App.TILE_SIZE + timeSinceMove * SPEED - App.TILE_SIZE;
	      break;
	    case DIR_NONE:
	      break;
	    }
	    
	    
	    // moves the actual block invisibly
	    // though the animations is smooth the actual block is only ever in a single grid at any one time
			if (timeSinceMove > MOVE_DELAY) {
				if (isValidMove(nextPos, assets)) {
					move(properties, assets);
					nextPos = getPos().nextPos();
					timeSinceMove = 0;
					squaresMoved++;
					
				} else {
					sliding = false;
			    slide.stop();
			    renderOffsetX = 0;
			    renderOffsetY = 0;
			    squaresMoved = 0;
			    pushPos = getPos();
				}
			}
		}
	}
	
	
	public void makeSlide() {
	  // prevents sound from continuously looping if pushed multiple times before it stops
	  slide.stop();
	  
	  pushPos = new Position(getPos());
		sliding = true;
    slide.loop();
    timeSinceMove = 0;
		nextPos = getPos().nextPos();
	}
  
  public void render(Graphics g, float xOffset, float yOffset) {
    getImage().draw(pushPos.getXPos() * App.TILE_SIZE + xOffset + renderOffsetX, 
                    pushPos.getYPos() * App.TILE_SIZE + yOffset + renderOffsetY);
  }
}
