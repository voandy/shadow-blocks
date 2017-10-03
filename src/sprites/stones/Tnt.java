package sprites.stones;

import game.Assets;
import sprites.Position;
import sprites.map.Floor;

public class Tnt extends Stone {
	
	public Tnt(Position position) {
		super("res/tnt.png", "res/tnt.wav", position);
	}
	
	public void detonate(Assets assets) {
	  // shows explosion effect
	  assets.getGameEffects().showExplosion(getPos());
	  
	  // removes Tnt and replaces CrackedWall with Floor
    assets.getMap()[getPos().getXPos()][getPos().getYPos()] = new Floor(getPos());
    assets.getStones()[getPos().getXPos()][getPos().getYPos()] = null;
	}
}