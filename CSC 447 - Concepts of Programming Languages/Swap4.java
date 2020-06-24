public class Main4 {
	public static void main(String[] args) {

		int a = 4;
		int b = 4;
		boolean comparisonResult = a == b; 
		System.out.println(comparisonResult);

	// The following line does not compile.
	// comparisonResult = a.equals(b);
	// System.out.println(comparisonResult); 

	StringObject x = new StringObject("hello"); 
	StringObject y = new StringObject("hello"); 

	comparisonResult = x == y; 
	System.out.println(comparisonResult); 

	comparisonResult = x.equals(y); 
	System.out.println(comparisonResult);

class StringObject {
	public String str;
	public StringObject(String str) {
	this.str = str;
}

public String toString() {
	return str;
	} 
}
}}