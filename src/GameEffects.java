import java.util.ArrayList;
import java.util.Iterator;

// stores and renders game effects and animations such as Explosion
public class GameEffects {
  private ArrayList<Effect> effects;

  public GameEffects() {
    effects = new ArrayList<>();
  }
  
  // adds the given effect to effects to be rendered
  public void showEffect(Effect effect, Position position) {
    effect.setPos(position);
    effects.add(effect);
    effect.makeSound();
  }
  
  public void showExplosion(Position position) {
    Explosion explosion = new Explosion();
    showEffect(explosion, position);
  }
  
  public void showPoof(Position position) {
    Poof poof = new Poof();
    showEffect(poof, position);
  }
  
  // removes effects that have finished playing from effects
  public void update(int delta) {
    if (effects != null) {
      Iterator<Effect> iter = effects.iterator();
      
      while (iter.hasNext()) {
        Effect effect = iter.next();
        effect.setTimeSinceShown(effect.getTimeSinceShown() + delta);
        
        if (effect.getTimeSinceShown() > effect.getTimeToShow()) {
          effect.setFinished(true);
          iter.remove();
        }
      }
    }
  }
  
  public ArrayList<Effect> getEffects() {
    return effects;
  }

}

