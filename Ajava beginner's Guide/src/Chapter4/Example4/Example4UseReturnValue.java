package Chapter4.Example4;

public class Example4UseReturnValue {

	public static void main(String[] args) {


		// remember, minivan and sportcar refer to separate o
		Vehicle minivan = new Vehicle();
		Vehicle sportcar = new Vehicle();

		int range1 , range2;

		// assign values to fields in minivan
		minivan.passengers = 7;
		minivan.fuelcap = 16;
		minivan.mpg = 21;

		// assign values to fields in minivan
		sportcar.passengers = 2;
		sportcar.fuelcap = 14;
		sportcar.mpg = 12;
		
		// get the rang 
//		range1 = minivan.range(); // assign the value returned to a variable
		// i can used direct in println() as i did 
		range2 = sportcar.range();
		
		System.out.println("minivan can carry " + minivan.passengers + " with rang of " + minivan.range() + " miles");

		
		
		System.out.println("sportcar can carry " + sportcar.passengers + " with range of " + range2 + " miles");
	

	}

}
class Vehicle{

	int passengers;  // number of passenger
	int fuelcap;  // fuel capacity in gallon
	int mpg;    //fuel consumption in miles per gallon
	
	
	// return rang 
	int range() {
		
		// the form for return value is return vale;
	return mpg *fuelcap; // return the range for given vehicle
	
	}
	
}

