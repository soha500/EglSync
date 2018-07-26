package Chapter4;

public class MyCounter {

	public static void main(String args[]) {
		// TODO Auto-generated method stub
		
		Counter counterObjectx = new Counter(2 , 3);
		Counter counterObjecty = new Counter(5 , 8);
		
        System.out.println("the first element is: " + counterObjectx.a + " the second element is: " + counterObjectx.b );
        System.out.println("the first element is: " + counterObjecty.b + " the second element is: " + counterObjecty.b );
		

	}

}
class Counter {
  int a , b;
  double c;
  
     Counter(int first, int second) {
    	 a = first;
    	 b = second;
    	 c = 1.0;
    	 
    	 if(first == 0) return;
        
         }
     double myMath() {
     return c ;
     }
}