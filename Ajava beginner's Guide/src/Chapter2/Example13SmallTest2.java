//package Chapter2;
//// Demonstrate the truth table of logical operators
//public class Example13SmallTest2 {
//
//	public static void main(String[] args) {
//		boolean p , q;
//		System.out.println("p\tq\tAND\tOR\tXOR\tNOT");
//		
//		p = true; q = true;
//		System.out.print(p + "\t" + q + "\t");
//		System.out.print((p&q)+ "\t" + (p|q) + "\t");
//		System.out.println((p^q) + "\t" + (!p) + "\t");
//
//		p = true; q = false;
//		System.out.print(p + "\t" + q + "\t");
//		System.out.print((p&q)+ "\t" + (p|q) + "\t");
//		System.out.println((p^q) + "\t" + (!p) + "\t");
//		
//		p = false; q = true;
//		System.out.print(p + "\t" + q + "\t");
//		System.out.print((p&q)+ "\t" + (p|q) + "\t");
//		System.out.println((p^q) + "\t" + (!p) + "\t");
//		
//		p = false; q = false;
//		System.out.print(p + "\t" + q + "\t");
//		System.out.print((p&q)+ "\t" + (p|q) + "\t");
//		System.out.println((p^q) + "\t" + (!p) + "\t");
//		
//	
//	}
//
//}

package Chapter2;
// Demonstrate the truth table of logical operators
public class Example13SmallTest2 {

	public static void main(String[] args) {
		int i , p;
		System.out.println("i\tp\tAND\tOR\tXOR\tnot");
		
		i = 1 ; p = 0;
		System.out.print(i + "\t" + p + "\t");
		System.out.print((i&p)+ "\t" + (i|p) + "\t");
		System.out.println((i^p) + "\t" + (i) + "\t");

		i = 1 ; p = 0;
		System.out.print(i + "\t" + p + "\t");
		System.out.print((i&p)+ "\t" + (i|p) + "\t");
		System.out.println((i^p) + "\t" + (i) + "\t");
		
		i = 1 ; p = 0;
		System.out.print(i + "\t" + p + "\t");
		System.out.print((i&p)+ "\t" + (i|p) + "\t");
		System.out.println((i^p) + "\t" + (i) + "\t");
		
		i = 1; p = 0 ;
		System.out.print(i + "\t" + p + "\t");
		System.out.print((i&p)+ "\t" + (i|p) + "\t");
		System.out.println((i^p) + "\t" + (i) + "\t");
		
	
	}

}
