package game.assets.sprites.blocks;

import game.Properties;
import game.assets.Assets;
import game.assets.sprites.Position;
import game.assets.sprites.map.CrackedWall;
import game.assets.sprites.map.Floor;

public class Tnt extends Block {
	
	public Tnt(Position position) {
		super("res/tnt.png", "res/block.wav", position);
	}
	
	public Tnt(Tnt another) {
	  super(another);
	}
	
	public void detonate(Assets assets) {
	  // shows explosion effect
	  assets.getGameEffects().showExplosion(getPos());
	  
	  // removes Tnt and replaces CrackedWall with Floor
    assets.getMap()[getPos().getXPos()][getPos().getYPos()] = new Floor(getPos());
    assets.getBlocks()[getPos().getXPos()][getPos().getYPos()] = null;
    
    // clears history as detonation cannot be undone
    assets.getHistory().clearHistory();
	}
	
	public boolean move(Properties properties, Assets assets) {
	  boolean moved = super.move(properties, assets);
	  // BOOM!
	  if (assets.getMap()[getPos().getXPos()][getPos().getYPos()] instanceof CrackedWall) {
	    detonate(assets);
	  }
	  return moved;
	}
	
	// as well as being able to do anything a block can do Tnt can move into CrackedWalls
	public boolean isValidMove(Position destination, Assets assets) {
	   if (super.isValidMove(destination, assets)) {
	     return true;
	   }
	   if (assets.getMap()[destination.getXPos()][destination.getYPos()] instanceof CrackedWall) {
	     return true;
	   }
	   return false;
	}
}