import java.io.FileReader;
import java.util.Scanner;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;

public class Loader {	
	private static final int NO_DIMENSIONS = 2;

	public static WorldDimensions loadDimensions(String filename) {
		int levelWidth = 0;
		int levelHeight = 0;
		WorldDimensions dimensions;
		
		try (Scanner scanner = new Scanner(new FileReader(filename))) {
			String[] line = new String[NO_DIMENSIONS];
			line = scanner.nextLine().split(",");
			
			levelWidth = Integer.parseInt(line[0]);
			levelHeight = Integer.parseInt(line[1]);
		} 
		catch (Exception e) {
            e.printStackTrace();
        }
		
		dimensions = new WorldDimensions(levelWidth, levelHeight);
		
		return dimensions;
	}
	
	public static Sprite createSprite(String type, Position position) {
		switch (type) {
			case "wall":
				return new Wall(position);
			case "floor":
				return new Floor(position);
			case "stone":
				return new Stone(position);
			case "target":
				return new Target(position);
			case "player":
				return new Player(position);
		}
		return null;
	}
	
	public static ArrayList<Sprite> loadSprites(String filename) {
		ArrayList<Sprite> sprites = new ArrayList<>();
		
		try (Scanner scanner = new Scanner(new FileReader(filename))) {
        	// declares input String and skips the first line which only contains dimensions
        	String inputLine = scanner.nextLine();
        	String[] parts = inputLine.split(",");
			
        	while (scanner.hasNextLine()) {
        		String type;
        		int x, y;
        		Position position;
        		
        		inputLine = scanner.nextLine();
        		parts = inputLine.split(",");
        		type = parts[0];
				x = Integer.parseInt(parts[1]);
				y = Integer.parseInt(parts[2]);
				position = new Position(x, y);
				
				sprites.add(createSprite(type, position));
        	}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
		
		return sprites;
	}
	
	// returns map items (Wall/Floor and their subclasses) from sprites in a separate ArrayList
	public static ArrayList<Sprite> loadMap(ArrayList<Sprite> sprites) {
		ArrayList<Sprite> map = new ArrayList<>();
		for (Sprite sprite : sprites) {
			if (sprite instanceof Wall || sprite instanceof Floor) {
				map.add(sprite);
			}
		}
		return map;
	}
}