package Chapter3;

// small game to check if the enter letter is correct or not 

public class Example2GuessLeterGameUseDoWhile {

	public static void main(String[] args) throws java.io.IOException {
		
		char ch , ignore , answer = 'K';    // when enter the letter it must be the same whether is lowCase or upperCace
				
		do {
			System.out.println("I am thinking of the letter between A and Z. ");
			System.out.println("Can you guess it: ");
			
			ch = (char) System.in.read();       // read a character 
			
		// discard any other character in the input buffer
		do {
			ignore = (char) System.in.read();
			
		} while(ignore != '\n');
		
		if(ch == answer)   System.out.println("** Right** ");
		else { 
			System.out.print("... Sorry, you're "); 
			if(ch < answer) System.out.println("too low");
			else System.out.println("too high");
			System.out.println("Try again!\n");
		}
		}while(answer != ch);
	}

}