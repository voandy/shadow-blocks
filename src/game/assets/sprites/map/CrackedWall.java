package game.assets.sprites.map;

import game.assets.sprites.Position;

public class CrackedWall extends Wall {
  // unnecessary to change cracked boolean as the crackedWall is completely removed from the map when it is destroyed
	public CrackedWall(Position position) {
		super("res/cracked_wall.png", null, position);
	}
}