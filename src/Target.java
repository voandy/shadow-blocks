import java.util.ArrayList;

import org.newdawn.slick.Input;

public class Target extends Floor{
	private boolean toggled;

	public Target(Position position) {
		super("res/target.png", position);
		this.toggled = false;
	}
	
	public boolean isToggled() {
		return toggled;
	}
	
	public void update(Input input, int delta, Sprite[][] map, Sprite[][] stones, ArrayList<Unit> units) {
		if (stones[getPos().getXPos()][getPos().getYPos()] != null){
			this.toggled = true;
		} else {
			this.toggled = false;
		}
	}
}
