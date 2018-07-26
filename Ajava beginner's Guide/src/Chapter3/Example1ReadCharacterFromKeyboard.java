package Chapter3;

public class Example1ReadCharacterFromKeyboard {

	public static void main(String[] args) throws java.io.IOException {
		
       char ch;
       System.out.println("Press a key followed by inter: ");
       
       ch = (char) System.in.read(); //read the character from the keyboard
       
       System.out.println("Your key is: " + ch);
	}

}
