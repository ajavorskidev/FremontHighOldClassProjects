import java.util.Random;
/**
 * Parent class for all animals
 * @author Adrian Javorski 2018
 *@version 2018.04.26
 *
 */
public class Animal {
	// Characteristics shared by all animals (static fields).
	// The age the animal can start breeding, is mature.
	protected int BREEDING_AGE;
	
	// The age to which the animal can live.
	protected int MAX_AGE;
	
	// The likelihood of the animal breeding.
	protected double BREEDING_PROBABILITY;
	
	// The maximum number of births.
	protected int MAX_LITTER_SIZE;
	
	 // A shared random number generator to control breeding.
    protected Random rand;
	// Characteristics shared by all animals (instance fields).
	// The animal's age
	protected int age;
	// Wether the animal is alive
	protected boolean alive;
	//the animal's position
	protected Location location;
	
	//Constructor
	public Animal(int breed, int maxAge, double breedingP, int maxLitter) {
		//static fields
		BREEDING_AGE = breed;
		MAX_AGE = maxAge;
		BREEDING_PROBABILITY = breedingP;
		MAX_LITTER_SIZE = maxLitter;
		
		rand = new Random();
		
		//instance fields
		age = 0;
		alive = true;
	}
	
	 /**
     * Increase the age.
     * This could result in the animal's death.
     */
    protected void incrementAge(){
        age++;
        if(age > MAX_AGE) {
            alive = false;
        }
    }
    

    /**
     * An animal can breed if it has reached the breeding age.
     * @return true if the animal can breed, false otherwise.
     */
    protected boolean canBreed(){
        return age >= BREEDING_AGE;
    }
    
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    protected int breed(){
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }
    
    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    public boolean isAlive()
    {
        return alive;
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
     * Set the rabbit's location.
     * @param location The rabbit's location.
     */
    public void setLocation(Location location)
    {
        this.location = location;
    }

}
