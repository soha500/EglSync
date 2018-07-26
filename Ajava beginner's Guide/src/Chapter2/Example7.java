package Chapter2;
//               Demonstrate block scope
public class Example7 {

	public static void main(String[] args) {
		int x = 10; // x here is known to all code within main
		if(x == 10) { // start new scope
			
			int y = 20; // an important point // y here known only to this block
			            // x and y bath known here 
			System.out.println("x and y : " + x + " " + y);
			x = y * 2;
		} // end the scope
		
      //  y = 100; // Error y not known here 
		
		System.out.println("x is " + x); // x still known here
	}

}
