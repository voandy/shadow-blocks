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
	
	// returns a 2d array of map items (StaticTile and its subclasses) from sprites in an ArrayList
	// note that the returned array only references the sprites and does not copy them
	public static Sprite[][] loadMap(ArrayList<Sprite> sprites, int levelWidth, int levelHeight) {
		Sprite[][] map = new Sprite[levelWidth][levelHeight];
		
		for (Sprite sprite : sprites) {
			if (sprite instanceof StaticTile) {
				map[sprite.getPos().getXPos()][sprite.getPos().getYPos()] = sprite;
			}
		}
		
		return map;
	}
	
	//public static ArrayList<Sprite> load
}