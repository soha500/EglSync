package Chapter3;

public class Example5SmallTest {

	public static void main(String[] args) throws java.io.IOException {
		// TODO Auto-generated method stub
		
		char choice;
     System.out.println("help on");
     System.out.println(" 1. if");
     System.out.println(" 2. switch");
     System.out.println("choose one");
     
     choice = (char) System.in.read();
     System.out.println("\n");
     
     switch(choice) {
     case '1':
    	 System.out.println("The if:\n");
    	 System.out.println("if(condition) statment");
    	 System.out.println("else statment");
    	 break;
     case '2':
	 System.out.println("The switch:\n");
	 System.out.println("switch(expresion) {");
	 System.out.println("	case constant:");
	 System.out.println("		statment sequense");
	 System.out.println("		break");
	 System.out.println("		// ...");
	 System.out.println("}");
	 break;
	 default:
    	 System.out.print("selection not found");
     }	 
	}

}
