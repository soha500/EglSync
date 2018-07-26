package Chapter3;

public class Example16UsingBreakWithNeastedLoops {

	public static void main(String[] args) {
		// when used inside a set of nested loops, break statement will break out of only the innermost loops EX
		
		for (int i = 0; i < 3; i++) {
			System.out.println("outer loop count: " + i);
			System.out.print("     inner loop count: ");
			
			
			int t = 0;
			while(t < 100) {
				if(t == 10) break;  // terminate loop if t is 10
				System.out.print(t + " ");
				t++;
			}
			System.out.println();
		}
			System.out.println("complete loop");
	}

}
