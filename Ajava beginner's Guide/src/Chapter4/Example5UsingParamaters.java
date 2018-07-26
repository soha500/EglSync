package Chapter4;

public class Example5UsingParamaters {

	public static void main(String[] args) {
	
		Factor x = new Factor();
		
		if(x.Example5UsingParamaters(2, 20)) 
			System.out.println(" 2 is factor ");
		if(x.Example5UsingParamaters(3, 20))
			System.out.println("this is won't be displayed");

	}

}
class Factor{
	boolean Example5UsingParamaters(int a, int b) {
		
		if((b % a) == 0) return true;
		else return false;
	}
	
}