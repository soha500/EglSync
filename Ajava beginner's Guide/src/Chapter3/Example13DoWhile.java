package Chapter3;

public class Example13DoWhile {

	public static void main(String[] args) throws java.io.IOException {
		char ch;
		do {
			System.out.print("Press the key followed by inter: ");
			
			ch = (char) System.in.read();
			
			
		} while (ch != 'q');
	}

}
