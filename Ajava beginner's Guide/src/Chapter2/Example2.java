package Chapter2;
           // Use the pythagorean theorem to find the length of the hyportenuse 
public class Example2 {

	public static void main(String[] args) {
		// sqrt() method use to compute the length of hyportenuse 
		
		double x , y , z;
		x = 3;
		y = 4;
		
		z = Math.sqrt(x*x + y*y);
		
		System.out.println("Hyportenuse is " + z);

	}

}
