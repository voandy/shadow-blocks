package game.assets.sprites.units;

import game.assets.sprites.Position;

public abstract class Npc extends Unit{
	public Npc(String image_src, String sound_src, Position position) {
		super(image_src, sound_src, position);
	}
}