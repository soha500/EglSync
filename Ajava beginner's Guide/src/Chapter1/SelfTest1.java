package Chapter1;

public class SelfTest1 {


	public static void main(String[] args) {
		// this programe conversion table of gallons to liters

		double inches , meters;
		int counter = 0;
		for (inches = 0; inches <= 100; inches++) {

			meters = inches * 39.37;
			System.out.println(inches + " inches is " + meters + " meters is.");

			counter++;
			// every 12th line. print a blank line
			if (counter == 12) { // if counter 10 output blank line 

				System.out.println();
				counter = 0;

			}

		}

	}

}
