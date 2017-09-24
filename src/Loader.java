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
	
	public static ArrayList<Sprite> loadSprites(String filename) {
		ArrayList<Sprite> sprites = new ArrayList<>();
		
		try (Scanner scanner = new Scanner(new FileReader(filename))) {
        	// skips the first line which only contains dimensions
        	scanner.nextLine();
			
        	while (scanner.hasNextLine()) {
        		
        	}
		}
        catch (Exception e) {
            e.printStackTrace();
        }
		
		return sprites;
	}
}