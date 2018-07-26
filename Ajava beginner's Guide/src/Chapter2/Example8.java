package Chapter2;
// Demonstrate lifetime of a variable
public class Example8 {

	public static void main(String[] args) {
		int x;
		for ( x = 0; x < 3; x++) {
			
		int y = -1; // y is initialised each time block is entered 
		System.out.println("y now is " + y); // this always print -1
		y = 100;
		System.out.println("y now is " + y);
			
		}

	}

}
