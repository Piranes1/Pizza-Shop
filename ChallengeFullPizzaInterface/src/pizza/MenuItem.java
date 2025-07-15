/**
 * 
 */
package pizza;

/**
 * 
 */
public class MenuItem implements Detail{

	//private instance vars following encapsulation principles
	private String name;
	private double price;

	
	
	/**
	 * DEFAULT CONSTRUCTOR WITH NO PARAMS
	 */
	public MenuItem() {
		
	}
	
	
	
	
	//CONSTRUCTOR WITH PARAMS MODIFIED TO ALLOW VALIDATION
	public MenuItem(String name, double price) {
		this.setName(name);
		this.setPrice(price);
	}
	
	
	
	//GETTERS AND SETTERS
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) throws IllegalArgumentException {
		if(!name.isEmpty() && !name.startsWith(" ") && containsOnlyLetters(name)) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("INVALID NAME");
		}
		
		
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) throws IllegalArgumentException {
		if(price >= 0) {
			this.price = price;
		} else  {
			this.price = 0;
		}
		
	}
	
	/**
	 * METHOD TO CHECK IF STRING CONTAINS ONLY LETTERS
	 * 
	 */
	
	public boolean containsOnlyLetters(String name) {
		for(int i = 0; i < name.length(); i++) {
			if (!Character.isLetter(name.charAt(i)) && name.charAt(i) != ' ') {
				return false;
			}
		} return true;
	}
	
	
	/**
	 * METHOD THAT PRINTS DETAILS TO SCREEN
	 */
	
	@Override
	public void printDetails() {
		System.out.printf("%-25s %6s %6.2f%n", this.getName(), "$", this.getPrice());
		
	}
	
	
}
