package Chapter3;

// small game to check if the enter letter is correct or not 

public class Example2GuessLeterGame {

	public static void main(String[] args) throws java.io.IOException {
		
		char ch;
		char answer = 'K'; // when enter the letter it must be the same whether is lowCase or upperCace
		System.out.println("I am thinking of the letter between A and Z: ");
		System.out.println("Can you guess it: ");
		
		ch = (char) System.in.read(); // to enter the character 
		
		if (ch == answer) 
			System.out.println("** Right** ");
		else 
			System.out.println("oops! the enter character is wrong"); // until here we just use the if and else
		// Now start with nested if's 
		
		if(ch < answer) // this is the nested if
			System.out.println("too low");
		else 
			System.out.println("too high");
	}

}
