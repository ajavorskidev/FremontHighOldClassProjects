import java.io.Serializable;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a platypus. platypus age, move, eat rabbits and foxes(fights the fox), and die.
 * 
 * @author David J. Barnes and Michael Kolling.  Modified by David Dobervich 2007-2013.
 * @version 2006.03.30
 */
public class Platypus implements Serializable {
	// Characteristics shared by all platypus (static fields).
	private static final int BREEDING_AGE = 3;
	// The age to which a platypus can live.
	private static final int MAX_AGE = 50;
	// The likelihood of a platypus breeding.
	private static final double BREEDING_PROBABILITY = 0.15;
	// The maximum number of births.
	private static final int MAX_LITTER_SIZE = 6;
	// The food value of a single rabbit or fox. In effect, this is the
	// number of steps a platypus can go before it has to eat again.
	private static final int RABBIT_FOOD_VALUE = 7;
	private static final int FOX_FOOD_VALUE = 10;
	// A shared random number generator to control breeding.
	private static final Random rand = new Random();

	// Individual characteristics (instance fields).

	// The platypus's age.
	private int age;
	// Whether the platypus is alive or not.
	private boolean alive;
	// The platypus's position
	private Location location;
	// The platypus's food level, which is increased by eating rabbits and/or foxes.
	private int foodLevel;

	/**
	 * Create a platypus. A platypus can be created as a new born (age zero and not
	 * hungry) or with random age.
	 * 
	 * @param randomAge
	 *            If true, the platypus will have random age and hunger level.
	 */
	public Platypus(boolean randomAge) {
		age = 0;
		alive = true;
		if (randomAge) {
			age = rand.nextInt(MAX_AGE);
			foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
		} else {
			// leave age at 0
			foodLevel = RABBIT_FOOD_VALUE + FOX_FOOD_VALUE;
		}
	}

	/**
	 * This is what the Platypus does most of the time: it hunts for rabbits and foxes. In the
	 * process, it might breed, die of hunger, or die of old age.
	 * 
	 * @param currentField
	 *            The field currently occupied.
	 * @param updatedField
	 *            The field to transfer to.
	 * @param newPlatypus
	 *            A list to add newly born platypods to.
	 */
	public void hunt(Field currentField, Field updatedField, List<Platypus> newPlatypod) {
		incrementAge();
		incrementHunger();
		if (alive) {
			// New platypus are born into adjacent locations.
			int births = breed();
			for (int b = 0; b < births; b++) {
				Platypus newPlatypus = new Platypus(false);
				newPlatypus.setFoodLevel(this.foodLevel);
				newPlatypod.add(newPlatypus);
				Location loc = updatedField.randomAdjacentLocation(location);
				newPlatypus.setLocation(loc);
				updatedField.put(newPlatypus, loc);
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
	 * Increase the age. This could result in the platypus's death.
	 */
	private void incrementAge() {
		age++;
		if (age > MAX_AGE) {
			alive = false;
		}
	}

	/**
	 * Make this platypus more hungry. This could result in the platypus's death.
	 */
	private void incrementHunger() {
		foodLevel--;
		if (foodLevel <= 0) {
			alive = false;
		}
	}

	/**
	 * Tell the platypus to look for rabbits and foxes adjacent to its current location. Only
	 * the first live rabbit or fox is eaten.
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
			if (animal instanceof Rabbit) { // The platypus finds a rabbit
				Rabbit rabbit = (Rabbit) animal;
				if ( rabbit.isAlive() ) {
					rabbit.setEaten();
					if(animal instanceof Rabbit) {
						foodLevel = RABBIT_FOOD_VALUE;
						return where;
					}
				}
				
			}
			if (animal instanceof Fox) { //the playtpus finds a fox
				Fox fox = (Fox) animal;
				if ( fox.isAlive() ) {
					// The platypus will fight the fox to try to get food
					int a = (int)(Math.random()*2);
					
					// The platypus is the weaker predator due to its size, so it only has a 
					// 1/2 chance of eating the fox
					if(a == 1 ) {
						fox.setEaten();
						if(animal instanceof Fox) {
							foodLevel = FOX_FOOD_VALUE;
							return where;
						}
					}
					if(a == 0) {
						alive = false;
					}
				}
			}
		}

		return null;
	}

	/**
	 * Generate a number representing the number of births, if it can breed.
	 * 
	 * @return The number of births (may be zero).
	 */
	private int breed() {
		int births = 0;
		if (canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
			births = rand.nextInt(MAX_LITTER_SIZE) + 1;
		}
		return births;
	}

	/**
	 * A platypus can breed if it has reached the breeding age.
	 */
	private boolean canBreed() {
		return age >= BREEDING_AGE;
	}

	/**
	 * Check whether the platypus is alive or not.
	 * 
	 * @return True if the platypus is still alive.
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * Set the animal's location.
	 * 
	 * @param row
	 *            The vertical coordinate of the location.
	 * @param col
	 *            The horizontal coordinate of the location.
	 */
	public void setLocation(int row, int col) {
		this.location = new Location(row, col);
	}

	/**
	 * Set the platypus's location.
	 * 
	 * @param location
	 *            The platypus's location.
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	public void setFoodLevel(int fl) {
		this.foodLevel = fl;
	}
}