package Chapter2;
// Demonstrate the short circuit operators
public class Example11 {

	public static void main(String[] args) {
		int n , d , q;
		n = 10;
		d = 2;
		if(d != 0 && (n % d) == 0)
			System.out.println( d + " is a factor of " + n);
		
		d = 0; // now set d to zero
		
		// since d is zero, the second operand is not evaluated
		System.out.println(d + " is a factor of " + n);
		
		/* Now, will try without using short-circuit operators 
		   
		  This is cause a divide-by-zero error
		 */
		
		if(d != 0 & (n % d) == 0)
			System.out.println( d + " is a factor of " + n);
		
	}

}
