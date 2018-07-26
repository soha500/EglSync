package Chapter2;
// how to use long type
// compute the number of cubic inches in 1 cubic mile
public class Examole1 {

	public static void main(String[] args) {
		long cubicInches ;
		long inchesMile = 5280 * 12;
		
		cubicInches = inchesMile * inchesMile * inchesMile;
		System.out.println("there are " + cubicInches + " cubic inches in cubic mile");

	}

}
