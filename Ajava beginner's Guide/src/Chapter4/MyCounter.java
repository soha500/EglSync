package Chapter4;

public class MyCounter {

	public static void main(String args[]) {
		// TODO Auto-generated method stub

		Counter counterObjectx = new Counter();
		Counter counterObjecty = new Counter();

		System.out.println(counterObjectx.myMath(5 , 3));
//		System.out.println("the first element is: " + counterObjecty.b + " the second element is: " + counterObjecty.b);

	}

}

class Counter {
	
	

	double myMath(int a, int b) {
		
       double  c =  a + b;
		return c;
	}
}