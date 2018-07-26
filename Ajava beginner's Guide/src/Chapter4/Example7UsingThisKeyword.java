package Chapter4;
// computes the result of number raised to some integer power
public class Example7UsingThisKeyword {

	public static void main(String[] args) {
		pwr x = new pwr(4.0, 2);
		pwr y = new pwr(2.5, 1);
		pwr z = new pwr(5.7, 0);
		
		System.out.println(x.b + "raised to the " + x.e + " power is " + x.get_pwr());
		
		System.out.println(y.b + "raised to the " + y.e + " power is " + y.get_pwr());
		
		System.out.println(z.b + "raised to the " + z.e + " power is " + z.get_pwr());
	}

}
class pwr{
	double b;
	int e;
	double val;
	
	pwr(double base , int exp){
		// like this.b
		b = base;
		// like this.e
		e = exp;
		// like.this.val
		val = 1;
		if(exp == 0) return;
		                      // like this.val * this.val
		for ( ;exp > 0; exp-- ) val = val * base;
			
	}
	double get_pwr() {
		// like this.val
		return val;
	}
	
}