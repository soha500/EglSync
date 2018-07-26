//package Chapter4;
//
//public class Example3AddingMethod {
//
//	public static void main(String args[]) {
//
//		// remember, minivan and sportcar refer to separate o
//		Vehicle minivan = new Vehicle();
//		Vehicle sportcar = new Vehicle();
//
//		int range1 , range2;
//
//		// assign values to fields in minivan
//		minivan.passengers = 7;
//		minivan.fuelcap = 16;
//		minivan.mpg = 21;
//
//		// assign values to fields in minivan
//		sportcar.passengers = 2;
//		sportcar.fuelcap = 14;
//		sportcar.mpg = 12;
//		
//		System.out.print("minivan can carry " + minivan.passengers + ". ");
//		
//		minivan.range(); // display range of minivan
//		
//		
//		System.out.print("sportcar can carry " + sportcar.passengers + ". ");
//		
//		sportcar.range(); // display range of sportcar
//
//	}
//
//}
//class Vehicle{
//
//	int passengers;  // number of passenger
//	int fuelcap;  // fuel capacity in gallon
//	int mpg;    //fuel consumption in miles per gallon
//	
//	void range() {
//		
//	System.out.println("the range is " + fuelcap * mpg);
//	
//	}
//	
//}
