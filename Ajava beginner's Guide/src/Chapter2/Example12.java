package Chapter2;
 //Demonstrate automatic conversion from long to double 
public class Example12 {

	public static void main(String[] args) {
	   long L = 10000012323384L;
	   double D;
	   
	   D = L;
	   System.out.println("L and D : " + L + " " + D);

	}

}
/*
  //Demonstrate automatic conversion from double to long
public class Example12 {

	public static void main(String[] args) {
	   long L ;
	   double D = 1000025373.0;
	   
	   L = D;
	   System.out.println("L and D : " + L + " " + D);

	}

}
 */
