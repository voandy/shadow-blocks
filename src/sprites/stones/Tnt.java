package sprites.stones;

import game.Assets;
import game.Properties;
import sprites.Position;
import sprites.map.CrackedWall;
import sprites.map.Floor;

public class Tnt extends Stone {
	
	public Tnt(Position position) {
		super("res/tnt.png", "res/tnt.wav", position);
	}
	
	public Tnt(Tnt another) {
	  super(another);
	}
	
	public void detonate(Assets assets) {
	  // shows explosion effect
	  assets.getGameEffects().showExplosion(getPos());
	  
	  // removes Tnt and replaces CrackedWall with Floor
    assets.getMap()[getPos().getXPos()][getPos().getYPos()] = new Floor(getPos());
    assets.getStones()[getPos().getXPos()][getPos().getYPos()] = null;
    
    // clears history and detonation cannot be undone
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
	
	// as well as being able to do anying a stone can do Tnt can move into CrackedWalls
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