import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * A simple model of a Gorilla.
 * Gorillas age, move, block predators, breed, and die.
 * 
 * @author David J. Barnes and Michael Kolling.  Modified by David Dobervich 2007-2013
 * @version 2006.03.30
 */
public class Gorilla implements Serializable {
	private static final int BREEDING_AGE = 9;
	
    // The age to which a Gorilla can live.
    private static final int MAX_AGE = 100;
    
    // The likelihood of a Gorilla breeding.
    private static final double BREEDING_PROBABILITY = 0.02;
    
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 1;
    
    // A shared random number generator to control breeding.
    private static final Random rand = new Random();
    
    // Individual characteristics (instance fields).
    
    // The gorilla's age.
    private int age;
    
    // Whether the gorilla is alive or not.
    private boolean alive;
    
    // The gorilla's position
    private Location location;

    /**
     * Create a new gorilla. A gorilla may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the gorilla will have a random age.
     */
    public Gorilla(boolean randomAge)
    {
        age = 0;
        alive = true;
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }
    
    /**
     * This is what the gorilla does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param updatedField The field to transfer to.
     * @param newGorillas A list to add newly born gorillas to.
     */
    public void run(Field updatedField, List<Gorilla> newGorillas)
    {
        incrementAge();
        if(alive) {
            int births = breed();
            for(int b = 0; b < births; b++) {
                Gorilla newGorilla = new Gorilla(false);
                newGorillas.add(newGorilla);
                Location loc = updatedField.randomAdjacentLocation(location);
                newGorilla.setLocation(loc);
                updatedField.put(newGorilla, loc);
            }
            Location newLocation = updatedField.freeAdjacentLocation(location);
            // Only transfer to the updated field if there was a free location
            if(newLocation != null) {
                setLocation(newLocation);
                updatedField.put(this, newLocation);
            }
            else {
                // can neither move nor stay - overcrowding - all locations taken
                alive = false;
            }
        }
    }
    
    /**
     * Increase the age.
     * This could result in the gorilla's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            alive = false;
        }
    }
    
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * A gorilla can breed if it has reached the breeding age.
     * @return true if the gorilla can breed, false otherwise.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
    
    /**
     * Check whether the gorilla is alive or not.
     * @return true if the gorilla is still alive.
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     * Tell the gorilla that it's dead now :(
     */
    public void setEaten()
    {
        alive = false;
    }
    
    /**
     * Set the animal's location.
     * @param row The vertical coordinate of the location.
     * @param col The horizontal coordinate of the location.
     */
    public void setLocation(int row, int col)
    {
        this.location = new Location(row, col);
    }

    /**
     * Set the gorilla's location.
     * @param location The gorilla's location.
     */
    public void setLocation(Location location)
    {
        this.location = location;
    }
}
