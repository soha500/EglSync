package Chapter1;

public class GallonTolLitersTable {

	public static void main(String[] args) {
		// this programe conversion table of gallons to liters
		
		double gallons , liters;
		int counter = 0;
		for (gallons = 1; gallons <= 100; gallons++) {
			
			liters = gallons * 3.7854;
			System.out.println(gallons + " gallons is" + liters + " liters is.");
			
		counter++;
		// every 10th line. print a blank line
		if (counter == 10) { // if counter 10 output blank line 
			
			System.out.println();
			counter = 0;
			
		}
			
		}
		

	}

}
