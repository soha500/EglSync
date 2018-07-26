package Chapter3;

public class Example11DeclareVaribleInsideTheLoop {

	public static void main(String[] args) {
		int sum =0 ;
		int fact = 1;
		
		// compute the factorial of the number through5
		for (int i = 0; i <= 5; i++) { // the i declared inside the for loop 
			sum += i;  // i is known throughout the loop
			fact *= i;
			
		}
		
		//		but, i is not here
		System.out.println("sum is " + sum);
		System.out.println("fact is " + fact);

	}

}
