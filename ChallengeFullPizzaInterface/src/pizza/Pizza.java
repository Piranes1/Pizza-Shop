/**
 * 
 */
package pizza;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 */
public class Pizza extends MenuItem {
	
	//instance vars
	private int size;
	private Set<Topping> toppings = new HashSet<Topping>();

	
	
	
	/**
	 * DEFAULT CONSTRUCTOR WITH NO PARAMS
	 */
	public Pizza() {
		
	}
	
	
	/**
	 * MAIN CONSTRUCTOR WITH PARAMS SPECIFIC TO A PLAIN PIZZA (NO TOPPINGS)
	 */
		public Pizza(String name, double price, int size) {
			super(name, price); //BASE PRICE IS EQUAL TO SIZE
			this.setSize(size);
		}
	/**
	 * CONSTRUCTOR WITH PARAMS FOR PIZZAS WITH TOPPINGS THAT CALLS THE MAIN CONSTRUCTOR
	 * @param name
	 * @param price
	 */
	public Pizza(String name, double price, int size, Set<Topping> toppings) {
		this(name, price, size); //CALLS THE MAIN CONSTRUCTOR
		this.setToppings(toppings);
	}

	
	
	// GETTERS AND SETTERS
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		if(size>=8 && size<=16) {
			this.size = size;
		} else if(size<8) {
			this.size = 8;
		} else {
			this.size = 16;
		}
		
	}

	/**
	 * TOPPINGS GETTER
	 * @return the toppings
	 */
	public Collection<Topping> getToppings() {
		return toppings;
	}

	/**
	 * TOPPINGS SETTER
	 * ONLY ONE TOPPING OF EACH CAN BE SELEECTED - USING HASHSET TO REMOVE DUPLICATES
	 * @param toppings the toppings to set
	 */
	public void setToppings(Set<Topping> toppings) {
		this.toppings = new HashSet<Topping>(toppings);
		//could also be placed in the constructor
		this.prizeWithToppings();
	}
	
	@Override
	public void printDetails() {
		if (this.getToppings().isEmpty()) {
	        // Format for plain pizza
			System.out.printf("%-25s %6s %6.2f%n", "Plain Pizza (" + this.getSize() + ")", "$", this.getPrice());
	    } else {
	        // Format for custom pizza
	    	System.out.printf("%-25s %6s %6.2f%n", "Custom Pizza (" + this.getSize() + ")", "$", this.getPrice());
	        System.out.println("  with toppings:");
	        // Enhanced for loop to print toppings
	        for (Topping topping : this.getToppings()) {
	            System.out.printf("    * %-20s%n", topping);
	        }
	    }
		
	}
	
	public void prizeWithToppings() {
		double additionalCost = toppings.size()*0.5;
		
		this.setPrice(additionalCost+this.getPrice());
	}
	
	
	public boolean isVegeratian() {
		
		//ENHANCE FOR LOOP OR FOR EACH LOOP ALLOWS YOU TO ITERATE OVER A COLLECTION WITHOUT THE NEED FOR CONVERSION
		for(Topping topping : toppings) {
			if(topping == Topping.BEEF || topping == Topping.CHICKEN || topping == Topping.HAM || topping == Topping.PEPPERONI) {
				return false;
			} 
		}  return true;
	}
	

}
