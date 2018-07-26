package Chapter4;


	public class Example6AddingParamaterizedMethodToVehicle {

		public static void main(String[] args) {


			// remember, minivan and sportcar refer to separate o
			Vehicle minivan = new Vehicle();
			Vehicle sportcar = new Vehicle();
			double gallons;
			int dist = 252;

			// assign values to fields in minivan
			minivan.passengers = 7;
			minivan.fuelcap = 16;
			minivan.mpg = 21;

			// assign values to fields in minivan
			sportcar.passengers = 2;
			sportcar.fuelcap = 14;
			sportcar.mpg = 12;
			
			gallons = minivan.fuelneeded(dist);
			
			System.out.println("To go " + dist + " miles minivan needs " + gallons + " gallons of fuel.");

			gallons = sportcar.fuelneeded(dist);
			
			System.out.println("To go " + dist + " miles sportcar needs " + gallons + " gallons of fuel.");
		

		}

	}
	class Vehicle{

		int passengers;  // number of passenger
		int fuelcap;  // fuel capacity in gallon
		int mpg;    //fuel consumption in miles per gallon
		
		
		// return the rang 
		int range() {
			
			// the form for return value is return vale;
		return mpg *fuelcap; // return the range for given vehicle
		
		}
		// compute fuel needed for given distance
		double fuelneeded(int miles) {
			
			return (double) miles / mpg;
		}
		
	}

