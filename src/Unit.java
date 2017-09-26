import org.newdawn.slick.Input;

public abstract class Unit extends Actor{

	public Unit(String image_src, Position position) {
		super(image_src, position);
	}
	public void update(Input input, int delta) {
	}
}
