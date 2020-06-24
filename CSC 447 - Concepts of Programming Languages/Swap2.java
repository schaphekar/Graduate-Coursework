public class Main2 {
	public static void main(String[] args){

		String x = "hello"; 
		String y = "world"; 
		swap(x, y); 

		// Method parameters are call-by-value in Java, so it returns the original values unswapped
		// This means the value of the variable is copied into the method parameters on the stack. 
		// The variables in the calling scope are untouched.
		
		System.out.println(x); 
		System.out.println(y);
}
public static void swap(String a, String b){
	String t = a; 
	a = b;
	b = t;
}
}