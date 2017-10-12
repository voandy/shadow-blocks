package game;

import java.util.ArrayList;

import game.assets.sprites.Sprite;

public class Properties {
  private int levelWidth;
  private int levelHeight;

  /** offsets used for rendering the level in the center of the screen */
  private float xOffset;
  private float yOffset;

  private int noMoves;

  public Properties(ArrayList<Sprite> sprites, int levelWidth, int levelHeight) {

    this.levelWidth = levelWidth;
    this.levelHeight = levelHeight;

    xOffset = (App.SCREEN_WIDTH - (levelWidth * App.TILE_SIZE)) / 2;
    yOffset = (App.SCREEN_HEIGHT - (levelHeight * App.TILE_SIZE)) / 2;

    noMoves = 0;
  }

  public int getLevelWidth() {
    return levelWidth;
  }

  public int getLevelHeight() {
    return levelHeight;
  }

  public float getXOffset() {
    return xOffset;
  }

  public float getYOffset() {
    return yOffset;
  }

  public int getNoMoves() {
    return noMoves;
  }

  public void incrementMoves() {
    noMoves++;
  }

  public void decrementsMoves() {
    noMoves--;
  }
}
