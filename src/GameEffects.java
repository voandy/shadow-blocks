import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Input;

// stores and renders game effects and animations such as Explosion
public class GameEffects {
  private ArrayList<Effect> effects;
  private ArrayList<Effect> effectToRemove;
  
  private ArrayList<SonicBoom> sonicBooms;
  private ArrayList<SonicBoom> sonicToRemove;

  public GameEffects() {
    effects = new ArrayList<>();
    effectToRemove = new ArrayList<>();
    
    sonicBooms = new ArrayList<>();
    sonicToRemove = new ArrayList<>();
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
  
  public void throwSonicBoom(Position position) {
    SonicBoom sonicBoom = new SonicBoom(position);
    sonicBooms.add(sonicBoom);
    sonicBoom.makeSound();
  }
  
  // removes effects that have finished playing from effects
  public void update(Input input, int delta, Properties properties, Assets assets) {
    if (effects != null) {
      for (Effect effect : effects) {
        effect.update(input, delta, properties, assets);
        if (effect.isFinished()) {
          effectToRemove.add(effect);
        }
      }
    }
    effects.removeAll(effectToRemove);
    
    if (sonicBooms != null) {
      for (SonicBoom sonicBoom : sonicBooms) {
        sonicBoom.update(input, delta, properties, assets);
        if (sonicBoom.isFinished()) {
          sonicToRemove.add(sonicBoom);
        }
      }
    }
    sonicBooms.removeAll(sonicToRemove);
  }
  
  public ArrayList<Effect> getEffects() {
    return effects;
  }
  public ArrayList<SonicBoom> getSonicBooms() {
    return sonicBooms;
  }

}

