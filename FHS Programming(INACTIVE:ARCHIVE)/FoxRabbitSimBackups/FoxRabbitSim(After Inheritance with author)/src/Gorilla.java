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

    /**
     * Create a new gorilla. A gorilla may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the gorilla will have a random age.
     */
    public Gorilla(boolean randomAge){
        super(BREEDAGE,MAXAGE,BREEDINGPROB,MAXLITTER);
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
     * Tell the gorilla that it's dead now :(
     */
    public void setEaten()
    {
        alive = false;
    }
}
