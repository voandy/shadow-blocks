public class Tnt extends Stone {
	private static final int EXPLOSION_WIDTH = 32;
	private static final int EXPLOSION_HEIGHT = 32;
	private static final int EXPLOSION_DURATION = 100;
	
	public Tnt(Position position) {
		super("res/tnt.png", "res/tnt.wav", position);
	}
	
	public void detonate(Sprite[][] map, Sprite[][] stones) {
		map[getPos().getXPos()][getPos().getYPos()] = new Floor(getPos());
		stones[getPos().getXPos()][getPos().getYPos()] = null;
		
		Effect explosion = new Effect("res/explosion.png", "res/explosion.wav", 
				getPos(), EXPLOSION_WIDTH, EXPLOSION_HEIGHT, EXPLOSION_DURATION);
		explosion.showz();
	}
}