package game.methods;

import java.io.FileReader;
import java.util.Scanner;

import game.Properties;
import game.assets.sprites.Position;
import game.assets.sprites.Sprite;
import game.assets.sprites.blocks.Block;
import game.assets.sprites.blocks.Ice;
import game.assets.sprites.blocks.Tnt;
import game.assets.sprites.map.CrackedWall;
import game.assets.sprites.map.Door;
import game.assets.sprites.map.Floor;
import game.assets.sprites.map.MapItem;
import game.assets.sprites.map.Switch;
import game.assets.sprites.map.Target;
import game.assets.sprites.map.Wall;
import game.assets.sprites.units.Giles;
import game.assets.sprites.units.Mage;
import game.assets.sprites.units.Player;
import game.assets.sprites.units.Rogue;
import game.assets.sprites.units.Shadow;
import game.assets.sprites.units.Skeleton;

import java.util.ArrayList;

public class Loader {	
	private static final int NO_DIMENSIONS = 2;
	private static final int MAX_LEVELS = 20;

	/** loads level dimensions from file 
	 * @param name of level file and List of sprites in level
	 * @return Properties object with level dimensions
	 */
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
		
		properties = new Properties(sprites, levelWidth, levelHeight);
		
		return properties;
	}
	
	/** loads the list of levels, this is represents as an array of String indicating the filename of the levels 
	 * @param filename of file containing list of levels (one per line)
	 * @return array of paths to each level file
	 */
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
	
	/** creates a new sprite given the type and position 
	 * @param name of sprite type and Position object
	 * @return a sprite
	 */
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
				return new Block(position);
			case "ice":
				return new Ice(position);
			case "tnt":
				return new Tnt(position);
			case "player":
				return new Player(position);
      case "giles":
        return new Giles(position);
			case "skeleton":
				return new Skeleton(position);
			case "rogue":
				return new Rogue(position);
			case "mage":
				return new Mage(position);
			case "shadow":
			  return new Shadow(position);
		}
		return null;
	}
	
	/** loads all the level data into an array list of sprites
	 * @param filename of level file
	 * @return List of all sprites in a level
	 */
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
	
	/** returns a 2d array of MapItems filtered from sprites */
  public static MapItem[][] loadMap(ArrayList<Sprite> sprites, int levelWidth, int levelHeight) {
    MapItem[][] map = new MapItem[levelWidth][levelHeight];
    
    for (Sprite sprite : sprites) {
      if (sprite instanceof MapItem) {
        map[sprite.getPos().getXPos()][sprite.getPos().getYPos()] = (MapItem) sprite;
      }
    }
    return map;
  }
  
  /** returns a 2d array of Blocks filtered from sprites */
  public static Block[][] loadBlocks(ArrayList<Sprite> sprites, int levelWidth, int levelHeight) {
    Block[][] stones = new Block[levelWidth][levelHeight];
    
    for (Sprite sprite : sprites) {
      if (sprite instanceof Block) {
        stones[sprite.getPos().getXPos()][sprite.getPos().getYPos()] = (Block) sprite;
      }
    }
    return stones;
  }
	
	/** given an ArrayList of sprites returns an ArrayList of objects that are instances of type
	 * credit: Tamas Rev https://stackoverflow.com/questions/46480748/ 
	 */
	@SuppressWarnings("unchecked")
  public static <T> ArrayList<T> getSubset(ArrayList<? super T> sprites, Class<T> type) {
	  ArrayList<T> subSet = new ArrayList<>();
	  for (Object object : sprites) {
	    if (type.isInstance(object)) {
	      subSet.add((T) object);
	    }
	  }
	  return subSet;
	}
	
	public static Player findPlayer(ArrayList<? extends Sprite> sprites) {
		Player player = null;
		for (Sprite sprite: sprites) {
			if (sprite instanceof Player) {
				player = (Player) sprite;
				break;
			}
		}
		return player;
	}
	
	public static Door findDoor(ArrayList<Sprite> sprites) {
	  Door door = null;
	   for (Sprite sprite: sprites) {
	      if (sprite instanceof Door) {
	        door = (Door) sprite;
	        break;
	      }
	   }
	   return door;
	}
}