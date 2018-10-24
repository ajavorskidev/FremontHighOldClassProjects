import java.io.Serializable;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a fox. Foxes age, move, eat rabbits, and die.
 * 
 * @author David J. Barnes and Michael Kolling.  Modified by David Dobervich 2007-2013.
 * @author Modified by Adrian Javorski 2018.
 * @version 2018.04.26
 */
public class Fox extends Animal implements Serializable {
	/** VALUES ARE SET IN THE CONSTRUCTOR**/
	//Static Field modifiers
	static int BREEDAGE = 3;
	static int MAXAGE = 50;
	static double BREEDINGPROB = 0.16;
	static int MAXLITTER = 6;
	// The food value of a single rabbit. In effect, this is the
	// number of steps a fox can go before it has to eat again.
	private int RABBIT_FOOD_VALUE;

	// The fox's food level, which is increased by eating rabbits.
	private int foodLevel;

	/**
	 * Create a fox. A fox can be created as a new born (age zero and not
	 * hungry) or with random age.
	 * 
	 * @param randomAge
	 *            If true, the fox will have random age and hunger level.
	 */
	public Fox(boolean randomAge) {
		super(BREEDAGE,MAXAGE,BREEDINGPROB,MAXLITTER);
		RABBIT_FOOD_VALUE = 8;
		if (randomAge) {
			age = rand.nextInt(MAX_AGE);
			foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
		} else {
			// leave age at 0
			foodLevel = RABBIT_FOOD_VALUE;
		}
	}

	/**
	 * This is what the fox does most of the time: it hunts for rabbits. In the
	 * process, it might breed, die of hunger, or die of old age.
	 * 
	 * @param currentField
	 *            The field currently occupied.
	 * @param updatedField
	 *            The field to transfer to.
	 * @param newFoxes
	 *            A list to add newly born foxes to.
	 */
	public void act(Field currentField, Field updatedField, List<Animal> animals) {
		incrementAge();
		incrementHunger();
		if (alive) {
			// New foxes are born into adjacent locations.
			int births = breed();
			for (int b = 0; b < births; b++) {
				Fox newFox = new Fox(false);
				newFox.setFoodLevel(this.foodLevel);
				animals.add(newFox);
				Location loc = updatedField.randomAdjacentLocation(location);
				newFox.setLocation(loc);
				updatedField.put(newFox, loc);
			}
			// Move towards the source of food if found.
			Location newLocation = findFood(currentField, location);
			if (newLocation == null) { // no food found - move randomly
				newLocation = updatedField.freeAdjacentLocation(location);
			}
			if (newLocation != null) {
				setLocation(newLocation);
				updatedField.put(this, newLocation);
			} else {
				// can neither move nor stay - overcrowding - all locations
				// taken
				alive = false;
			}
		}
	}

	/**
	 * Make this fox more hungry. This could result in the fox's death.
	 */
	private void incrementHunger() {
		foodLevel--;
		if (foodLevel <= 0) {
			alive = false;
		}
	}

	/**
	 * Tell the fox to look for rabbits adjacent to its current location. Only
	 * the first live rabbit is eaten.
	 * 
	 * @param field
	 *            The field in which it must look.
	 * @param location
	 *            Where in the field it is located.
	 * @return Where food was found, or null if it wasn't.
	 */
	private Location findFood(Field field, Location location) {
		List<Location> adjacentLocations = field.adjacentLocations(location);

		for (Location where : adjacentLocations) {
			Object animal = field.getObjectAt(where);
			if (animal instanceof Rabbit) {
				Rabbit rabbit = (Rabbit) animal;
				if (rabbit.isAlive()) {
					rabbit.setEaten();
					foodLevel = RABBIT_FOOD_VALUE;
					return where;
				}
			}
			if (animal instanceof Fox) {
				//Fox might fight other Fox
				int a = (int)Math.random()*10; // probability of fight 
				int b = (int)Math.random()*2; // probability of winning fight
				int c = (int)Math.random()*10; // probability of dying in the fight
				Fox fox = (Fox) animal;
				if (fox.isAlive()) {
					if (a == 1) { // fight occurs
						if (b == 1) { //this fox wins
							if (c == 0) { //other fox dies
								fox.alive = false;
							}
						}
						if (b != 1) { //this fox loses
							if (c == 0) { //this fox dies
								alive = false;
							}
						}
					}
				}
			}
		}

		return null;
	}
	
	/**
	 * Check wether a platypus ate the fox or not
	 * @set alive = false if, eaten = true
	 */
	 public void setEaten()
	    {
	        alive = false;
	    }

	public void setFoodLevel(int fl) {
		this.foodLevel = fl;
	}
}
