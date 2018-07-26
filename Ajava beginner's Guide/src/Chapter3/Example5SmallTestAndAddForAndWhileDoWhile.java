package Chapter3;

public class Example5SmallTestAndAddForAndWhileDoWhile {

	public static void main(String[] args) throws java.io.IOException {
		// TODO Auto-generated method stub
		char choice ,ignore ;

		do {
			System.out.println("help on");
			System.out.println(" 1. if");
			System.out.println(" 2. switch");
			System.out.println(" 3. for");
			System.out.println(" 4. while");
			System.out.println(" 1. do-while\n");
			System.out.print("choose one: ");

			choice = (char) System.in.read();
			do {
				ignore = (char) System.in.read();
			} while( ignore != '\n');
			
		} while(choice < '1' | choice > '5');
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
		case '3':
			System.out.println("The for:\n");
			System.out.println("for(init ,condition, iteration)");
			System.out.println("statment");
			break;
		case '4':
			System.out.println("The while:\n");
			System.out.println("while(condition) statment;");
			break;
		case '5':
			System.out.println("The do-while:\n");
			System.out.println("do {");
			System.out.println("  statment");
			System.out.println("} while(condition);");
			break;
		}
	}
}