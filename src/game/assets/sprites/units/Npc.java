package game.assets.sprites.units;

import org.newdawn.slick.Graphics;

import game.assets.sprites.Position;

public abstract class Npc extends Unit{
  public Npc(String image_src, String sound_src, Position position) {
    super(image_src, sound_src, position);
  }

  public void render(Graphics g, float xOffset, float yOffset) {
    super.render(g, xOffset, yOffset);
  }
}