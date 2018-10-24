import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * A simple model of a Gorilla.
 * Gorillas age, move, block predators, breed, and die.
 * 
 * @author David J. Barnes and Michael Kolling.  Modified by David Dobervich 2007-2013
 * @author Modified by Adrian Javorski 2018
 * @version 2018.04.26
 */
public class Gorilla extends Animal implements Serializable{
	static int BREEDAGE = 9;
    static int MAXAGE = 100;
    static double BREEDINGPROB = 0.02;
    static int MAXLITTER = 1;
    
    // Instance Variables for the disease
    private int resistance;
    private boolean infected;

    /**
     * Create a new gorilla. A gorilla may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the gorilla will have a random age.
     */
    public Gorilla(boolean randomAge, int resist){
        super(BREEDAGE,MAXAGE,BREEDINGPROB,MAXLITTER);
        resistance = resist;
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
            resistance = rand.nextInt(100);
            if (1 == Math.random()*1) {
            		infected = true;
            }
        }
    }
    
    /**
     * This is what the gorilla does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param updatedField The field to transfer to.
     * @param newGorillas A list to add newly born gorillas to.
     */
    public void act(Field currentField, Field updatedField, List<Animal> animals)
    {
        incrementAge();
        if(alive) {
            int births = breed();
            for(int b = 0; b < births; b++) {
                Gorilla newGorilla = new Gorilla(false, resistance);
                animals.add(newGorilla);
                Location loc = updatedField.randomAdjacentLocation(location);
                newGorilla.setLocation(loc);
                updatedField.put(newGorilla, loc);
            }
            Location newLocation = disease(currentField, location);
			if (newLocation == null) { // no food found - move randomly
				newLocation = updatedField.freeAdjacentLocation(location);
			}
            Location newLocation1 = updatedField.freeAdjacentLocation(location);
            // Only transfer to the updated field if there was a free location
            if(newLocation1 != null) {
                setLocation(newLocation1);
                updatedField.put(this, newLocation1);
            }
            else {
                // can neither move nor stay - overcrowding - all locations taken
                alive = false;
            }
        }
    }

    /**
     * Tell the gorilla that it's dead now :(
     */
    public void setEaten()
    {
        alive = false;
    }
    public Location disease(Field field, Location location) 
    {
    	 	boolean spread = false;
    		if (infected)
    		{
    			if( resistance < 30)
    			{
    				this.alive = false;
    			}
    			if (resistance > 30) {
    				
    			}
    		}
    		//Gorillas can contract this disease by having contact with other gorillas
    		List<Location> adjacentLocations = field.adjacentLocations(location);

    		for (Location where : adjacentLocations) {
    			Object animal = field.getObjectAt(where);
    			if (animal instanceof Gorilla) {
    				Gorilla gorilla = (Gorilla) animal;
    				if (gorilla.isAlive() && spread && infected) {
    					gorilla.infected = true;
    					return where;
    				}
    			}
    		}

    		return null;
    }
}
