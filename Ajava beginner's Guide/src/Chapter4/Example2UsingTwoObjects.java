//package Chapter4;
//
//public class Example2UsingTwoObjects {
//
//	public static void main(String args[]) {
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
//
//		// compute the range assuming a full tank of gas
//		range1 = minivan.fuelcap * minivan.mpg;
//		range2 = sportcar.fuelcap * sportcar.mpg;
//
//		System.out.println("minivan can carry " + minivan.passengers + " with a range of " + range1);
//		System.out.println("minivan can carry " + sportcar.passengers + " with a range of " + range2);
//
//
//	}
//
//}
//class Vehicle {
//
//	int passengers;  // number of passenger
//	int fuelcap;  // fuel capacity in gallon
//	int mpg;    //fuel consumption in miles per gallon
//}
