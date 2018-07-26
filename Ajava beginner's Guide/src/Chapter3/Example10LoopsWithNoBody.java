package Chapter3;

public class Example10LoopsWithNoBody {

	public static void main(String[] args) {
		int i;
		int sum = 0;
		
		for ( i = 0; i <= 5 ; sum += i++); // No body in this loop 
		
			System.out.println("the sum is " + sum);
		
	}

}
