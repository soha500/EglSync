package Chapter2;
// Demonstrate the module % operator
public class Example9 {

	public static void main(String[] args) {
		int iresult = 10 / 3 ; // can declare like this 
		int irem = 10 % 3;
		
		double dresult = 10.0 / 3.0 , drem = 10.0 % 3.0; // variable can declare in one line using the same data type
		
		System.out.println(" the result and remainder of 10 / 3 is " + iresult + " " + irem);
		System.out.println(" the result and remainder of using module 10 % 3 is " + dresult + " " + drem);

	}

}
