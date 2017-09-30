import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;

public class Loader {	
	private static final int NO_DIMENSIONS = 2;
	private static final int MAX_LEVELS = 10;

	// loads level dimensions from file
	public static Properties loadProperties(String filename, ArrayList<Sprite> sprites) {
		int levelWidth = 0;
		int levelHeight = 0;
		Properties properties;
		
		try (Scanner scanner = new Scanner(new FileReader(filename))) {
			String[] line = new String[NO_DIMENSIONS];
			line = scanner.nextLine().split(",");
			
			levelWidth = Integer.parseInt(line[0]);
			levelHeight = Integer.parseInt(line[1]);
		} 
		catch (Exception e) {
            e.printStackTrace();
        }
		
		properties = new Properties(filename, sprites, levelWidth, levelHeight);
		
		return properties;
	}
	
	// loads an array of levels in the game
	public static String[] loadLevelList(String filename) {
		String[] levelList = new String[MAX_LEVELS];
		
		try (Scanner scanner = new Scanner(new FileReader(filename))) {
        	int i = 0;
            while (scanner.hasNextLine()) {
            	levelList[i] = "res/levels/" + scanner.nextLine();
            	i++;
            }
		} 
		catch (Exception e) {
            e.printStackTrace();
        }
		
		return levelList;
	}
	
	// creates a new sprite given the type and position
	public static Sprite createSprite(String type, Position position) {
		switch (type) {
			case "wall":
				return new Wall(position);
			case "cracked":
				return new CrackedWall(position);
			case "door":
				return new Door(position);
			case "floor":
				return new Floor(position);
			case "target":
				return new Target(position);
			case "switch":
				return new Switch(position);
			case "stone":
				return new Stone(position);
			case "ice":
				return new Ice(position);
			case "tnt":
				return new Tnt(position);
			case "player":
				return new Player(position);
      case "guile":
        return new Guile(position);
			case "skeleton":
				return new Skeleton(position);
			case "rogue":
				return new Rogue(position);
			case "mage":
				return new Mage(position);
		}
		return null;
	}
	
	// loads all the level data into an array list
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
	
	// returns a 2d array of sprites which are an instance of "type"
	// primarily intended to create a 2d maps of map items and stones
	// note that the returned array only references the sprites and does not copy them
	public static Sprite[][] populateLevel(ArrayList<Sprite> sprites, int levelWidth, int levelHeight, Class<?> type) {
		Sprite[][] entities = new Sprite[levelWidth][levelHeight];
		
		for (Sprite sprite : sprites) {
			if (type.isInstance(sprite)) {
				entities[sprite.getPos().getXPos()][sprite.getPos().getYPos()] = sprite;
			}
		}
		
		return entities;
	}
	
	// given an ArrayList of sprites returns an ArrayList of all sprites that are instances of subClass
	// credit: Tamas Rev https://stackoverflow.com/questions/46480748/
	@SuppressWarnings("unchecked")
  public static <T> ArrayList<T> getSubset(ArrayList<? super T> sprites, Class<T> subClass) {
	  ArrayList<T> subSet = new ArrayList<>();
	  for (Object object : sprites) {
	    if (subClass.isInstance(object)) {
	      subSet.add((T) object);
	    }
	  }
	  return subSet;
	}
	
	// finds the player in the units ArrayList
	public static Player findPlayer(ArrayList<Sprite> sprites) {
		Player player = null;
		for (Sprite sprite: sprites) {
			if (sprite instanceof Player) {
				player = (Player) sprite;
				break;
			}
		}
		return player;
	}
}