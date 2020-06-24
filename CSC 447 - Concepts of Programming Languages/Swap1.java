public class Main { 

	private Main() {}
	public static void main (String[] args) { 

		int x = 42;
		int y = 27;

		Main.swap(x,y); 

		// Method parameters are call-by-value in Java, so it returns the original values unswapped.
		// This means the value of the variable is copied into the method parameters on the stack. 
		// The variables in the calling scope are untouched.
		
		System.out.println(x); 
		System.out.println(y);
}

private static void swap (int a, int b) {
	int t = a; 
	a = b;
	b = t;

	}
}