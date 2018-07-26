package Chapter1;
//         this example to illustrate the difference between int and double types
public class Example3 {

	public static void main(String[] args) {
		int var = 10;
		double x = 10.0;
		
		System.out.println("original vale of var:" + var);
		System.out.println("original value of x:" + x );
		System.out.println();
		
		var = var / 4;
		x = x / 4;
		
		System.out.println("var after division:" + var); //here the fractional component lost 
		System.out.println("x after division:" + x); // whereas in the double type the fractional component preserved 
		

	}

}
