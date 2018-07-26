package Chapter3;

public class Example9MoveInitOutOfForLoop  {

	public static void main(String[] args) {
		int i ;
		
		i = 0;// move the initialisation out of the for
		for (; i < 10; ) {   // the iteration expression is empty
			
			System.out.println("pass % " + i);
			i++;
		}

	}

}


