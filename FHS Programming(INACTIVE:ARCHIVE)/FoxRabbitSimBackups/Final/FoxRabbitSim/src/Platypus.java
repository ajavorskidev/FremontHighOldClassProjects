import java.io.Serializable;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a platypus. platypus age, move, eat rabbits and foxes(fights the fox), and die.
 * 
 * @author David J. Barnes and Michael Kolling.  Modified by David Dobervich 2007-2013.
 * @author Modified by Adrian Javorski 2018.
 * @version 2006.03.30
 */
public class Platypus extends Animal implements Serializable {
	// Characteristics shared by all platypus (static fields).
	static int BREEDAGE = 3;
	static int MAXAGE = 50;
	static double BREEDINGPROB = 0.15;
	static int MAXLITTER = 6;
	// The food value of a single rabbit or fox. In effect, this is the
	// number of steps a platypus can go before it has to eat again.
	private int RABBIT_FOOD_VALUE;
	private int FOX_FOOD_VALUE;

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
		super(BREEDAGE,MAXAGE,BREEDINGPROB,MAXLITTER);
		RABBIT_FOOD_VALUE = 8;
		FOX_FOOD_VALUE = 10;
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
	public void act(Field currentField, Field updatedField, List<Animal> animals) {
		incrementAge();
		incrementHunger();
		if (alive) {
			// New platypus are born into adjacent locations.
			int births = breed();
			for (int b = 0; b < births; b++) {
				Platypus newPlatypus = new Platypus(false);
				newPlatypus.setFoodLevel(this.foodLevel);
				animals.add(newPlatypus);
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
					int b = (int)(Math.random()*15); //probability of fight
					// The platypus is the weaker predator due to its size, so it only has a 
					// 1/2 chance of eating the fox
					if(b == 1) {
						if(a == 1 ) {
							fox.setEaten();
							if(animal instanceof Fox) {
								foodLevel = FOX_FOOD_VALUE;
								return where;
							}
						}
					}
					if(a == 0) {
						alive = false;
					}
				}
			}
			if (animal instanceof Platypus) { //The platypus will fight other platypus 
				Platypus platypus = (Platypus) animal;
				if(platypus.isAlive()) {
					//The chance of a platypus fighting and chance of winning
					int a = (int)(Math.random()*10); // choosing to fight
					int b = (int)(Math.random()*2); // winning a fight
					int c = (int)(Math.random()*2); // platypus surviving fight
					if (a == 3) {
						if (b == 1) { // Platypus won fight
							if (c == 1) {
								platypus.alive = false; // other platypus died from fight injuries
							}
						}
						if (b != 1) { //Platypus lost fight
							if (c == 1) {
								alive = false; // Platypus did not survive the fight
							}
						}
					}
				}
			}
		}

		return null;
	}

	public void setFoodLevel(int fl) {
		this.foodLevel = fl;
	}
}
