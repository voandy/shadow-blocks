import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Door extends Wall{
  private Image door;
  private Image floor;
  private boolean open;
  
	public Door(Position position) {
		super("res/door.png", "res/door.wav", position);
		open = false;
		
		try {
      door = new Image("res/door.png");
      floor = new Image("res/floor.png");
    } catch (SlickException e) {
      e.printStackTrace();
    }
		
	}
	
	// opens door if not already open
	public void open() {
	  if (!open) {
	    setBlocked(false);
	    setImage(floor);
	    makeSound();
	    open = true;
	  }
	}
	
	// closes door if not already closed
	public void close() {
	  if (open) {
	    setBlocked(true);
	    setImage(door);
	    makeSound();
	    open = false;
	  }
	}
}
