public class Main3 {
	public static void main(String[] args) {
		
		StringObject x = new StringObject("hello"); 
		StringObject y = new StringObject("world"); 
		swap(x, y);
		
		System.out.println(x); 
		System.out.println(y);
}

// You can’t modify the pointer of the object that’s passed in 
// However, you CAN modify attributes ON THE OBJECT it’s pointing to if it’s mutable.
public static void swap(StringObject a, StringObject b) {

	String t = a.str; 
	a.str = b.str; 
	b.str = t;
	
	} 
}

class StringObject{

	public String str;

	public StringObject(String str){ 
		this.str = str; 
	}

	public String toString() {
		return str; 
	}

}