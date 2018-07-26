//package Chapter3;
//// Demonstrate ho can we use the switch statement 
//public class Example4SwitchStatment {
//
//	public static void main(String[] args) {
//		int i;
//		for ( i = 0; i < 10; i++) 
//			switch(i) {
//			case 0:
//				System.out.println("i is zero");
//				break;
//			case 1:
//				System.out.println("i is one");
//				break;
//			case 2:
//				System.out.println("i is two");
//				break;
//			case 3:
//				System.out.println("i is three");
//				break;
//			case 4:
//				System.out.println("i is four");
//				break;
//			default:
//				System.out.println("i is five or more");
//		
//		}
//
//	}
//
//}

package Chapter3;
// Demonstrate switch without using break 
public class Example4SwitchStatment {

	public static void main(String[] args) {
		int i;
		for ( i = 0; i <= 4; i++) {
			switch(i) {
			case 0:
				System.out.println("i is less than zero");
			case 1:
				System.out.println("i is less than one");
			case 2:
				System.out.println("i is less than two");
			case 3:
				System.out.println("i is less than three");
			case 4:
				System.out.println("i is less than four");
		
		}
			System.out.println();
		}
	
	}

}