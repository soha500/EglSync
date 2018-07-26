package Chapter3;

public class Example17UsingBreakAndDone {

	public static void main(String[] args) {
		
		
	done:
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					System.out.println(k + " ");
					if(k == 5) break done;// jump to done
					
				}
				System.out.println("after k loop");  // won't Executed 
			}
			System.out.println("after j loop");  // won't Executed 
		}
	    System.out.println("after i loop");  
	}

}
