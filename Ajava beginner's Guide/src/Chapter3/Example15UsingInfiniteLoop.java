package Chapter3;

public class Example15UsingInfiniteLoop {

	public static void main(String[] args) throws java.io.IOException {
		char ch;
		for( ; ;) { // use infinite loop
			
			ch = (char) System.in.read();
			if(ch == 'q') break ; // use break as well 
		}
		
			System.out.println("you preassed q!");
	}

}
