 /**
 * 
 */
package pizza;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 */
public class PizzaShop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<MenuItem> items = new ArrayList<>();
		MenuItem i1 = new MenuItem("PizzaMargherita", 8.99);
		MenuItem i2 = new MenuItem("PizzaHam", 10.99);
		MenuItem i3 = new MenuItem("Soda", 2.0);

		items.add(i1);
		items.add(i2);
		items.add(i3);

		//printReceipt(items);
		
		ArrayList<MenuItem> allOrders = readOrderIn();
		
		System.out.println("---------------------------");
		System.out.println("------ ORDER DETAILS ------");
		System.out.println("---------------------------");
		printReceipt(allOrders);
		
		findVeggies(allOrders);
	}

	public static ArrayList<MenuItem> readOrderIn() {
		ArrayList<MenuItem> menuItems = new ArrayList<>();

		String line;
		String[] lines;

		File file = new File("C:\\Users\\jytua\\Downloads\\FullOrder3.csv");

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			// we ignore the headers
			br.readLine();

			// we read in the first line with relevant info
			line = br.readLine();

			while (line != null) {

				lines = line.split(",");

				if (lines[0].equalsIgnoreCase("pizza")) {
					//USING DEFAULT CONSTRUCTOR - PROBABLY A BETTER IDEA TO USE CONSTRUCTOR WITH PARAMETERS TO AVOID WRONG INPUT
					Pizza pizza = new Pizza();

					pizza.setName(lines[0].trim());
					pizza.setSize(Integer.parseInt(lines[1]));
					pizza.setPrice(Double.parseDouble(lines[1]));

					// IF PIZZA HAS TOPPINGS
					if (lines.length > 2) {
						// CREATE THE HASHSHET THAT WILL INCLUDE TOPPINGS
						Set<Topping> toppings = new HashSet<>();
						// ADD EACH ADDITIONAL COLUMN AS A TOPPING-ENUM
						for (int i = 2; i < lines.length; i++) {
							toppings.add(Topping.valueOf(lines[i]));
						}
						pizza.setToppings(toppings);
						
					}
					//ADD PIZZA - EVEN IF IT DOESN´T HAVE TOPPINGS
					menuItems.add(pizza);

				} else { // ELSE - IT'S NOT A PIZZA
					MenuItem item = new MenuItem();

					item.setName(lines[0].trim());
					item.setPrice(Double.parseDouble(lines[1]));

					menuItems.add(item);
				}
				line = br.readLine();
			}br.close();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuItems;
	

	}

	public static void printReceipt(ArrayList<MenuItem> items) {
		double total = 0;
		for (MenuItem i : items) {
			i.printDetails();
			total += i.getPrice();
		}
		System.out.println();
		System.out.println("************************");
		System.out.println("TOTAL PRICE : $" + total);
		System.out.println("************************");
	}

	public static void findVeggies(ArrayList<MenuItem> items) {
		int veggieCounter = 0;
		System.out.println("VEGGIE PIZZAS: ");
		System.out.println();
		for (MenuItem item : items) {

			if (item instanceof Pizza && ((Pizza) item).isVegeratian()) { // CHECK SAVED SCREENSHOTS WITH EXPLANATION
				veggieCounter++;
				item.printDetails();
			}
			
		}System.out.println("Number of veggie pizzas : " + veggieCounter);

	}
}
