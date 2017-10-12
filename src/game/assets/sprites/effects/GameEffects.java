package game.assets.sprites.effects;

import java.util.ArrayList;
import org.newdawn.slick.Input;

import game.Properties;
import game.assets.Assets;
import game.assets.sprites.Position;

/** stores and renders game effects and animations such as Explosion */
public class GameEffects {
  /** effects currently playing */
  private ArrayList<Effect> effects;
  /** effects queued to be added */
  private ArrayList<Effect> effectsToAdd;
  /** effects queued to be removed */
  private ArrayList<Effect> effectsToRemove;

  public GameEffects() {
    effects = new ArrayList<>();
    effectsToAdd = new ArrayList<>();
    effectsToRemove = new ArrayList<>();
  }

  /** adds the given effect to effects to be rendered */
  public void showEffect(Effect effect, Position position) {
    effectsToAdd.add(effect);
    effect.makeSound();
  }
  public void showPop(Position position) {
    Pop pop = new Pop(position);
    showEffect(pop, position);
  }

  public void showExplosion(Position position) {
    Explosion explosion = new Explosion(position);
    showEffect(explosion, position);
  }

  public void showPoof(Position position) {
    Poof poof = new Poof(position);
    showEffect(poof, position);
  }

  public void showSplash(Position position) {
    Splash splash = new Splash(position);
    showEffect(splash, position);
  }

  public void throwSonicBoom(Position position) {
    SonicBoom sonicBoom = new SonicBoom(position);
    effects.add(sonicBoom);
    sonicBoom.makeSound();
  }

  public void throwKick(Position position) {
    Kick kick = new Kick(position);
    showEffect(kick, position);
  }

  /** adds new effects from queue, updates current effects and removes effects
   * that have finished playing from effects
   */
  public void update(Input input, int delta, Properties properties, Assets assets) {
    if (!effectsToAdd.isEmpty()) {
      effects.addAll(effectsToAdd);
      effectsToAdd.clear();;
    }

    if (!effects.isEmpty()) {
      for (Effect effect : effects) {
        effect.update(input, delta, properties, assets);
        if (effect.isFinished()) {
          effectsToRemove.add(effect);
        }
      }
    }

    if (!effectsToRemove.isEmpty()) {
      effects.removeAll(effectsToRemove);
      effectsToRemove.clear();;
    }
  }

  public ArrayList<Effect> getEffects() {
    return effects;
  }
}

